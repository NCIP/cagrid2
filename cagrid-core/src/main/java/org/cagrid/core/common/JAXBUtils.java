package org.cagrid.core.common;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchema;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.cagrid.core.cache.WeakValueConcurrentCache;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class JAXBUtils {

	public final static String DEFAULT_NAMESPACE_VALUE = "##default";

	private final static WeakValueConcurrentCache<String, JAXBContext> jaxbCache = new WeakValueConcurrentCache<String, JAXBContext>();

	private final static DocumentBuilderFactory dbf = DocumentBuilderFactory
			.newInstance();

	static {
		dbf.setNamespaceAware(true);
	}

	public static JAXBContext getJAXBContext(Class<?> klass)
			throws JAXBException {
		String pkg = klass.getPackage().getName();
		JAXBContext jaxb = jaxbCache.get(pkg);
		if (jaxb == null) {
			jaxb = JAXBContext.newInstance(pkg);
			JAXBContext oldJAXB = jaxbCache.putIfAbsent(pkg, jaxb);
			if (oldJAXB != null) {
				jaxb = oldJAXB;
			}
		}
		return jaxb;
	}

	public static String getNamespace(Class<?> klass) {
		String namespace = null;
		XmlType xmlType = klass.getAnnotation(XmlType.class);
		if (xmlType != null) {
			String ns = xmlType.namespace();
			if (!DEFAULT_NAMESPACE_VALUE.equals(ns)) {
				namespace = ns;
			}
		}
		if (namespace == null) {
			namespace = DEFAULT_NAMESPACE_VALUE;
			Package pkg = klass.getPackage();
			XmlSchema xmlSchema = pkg.getAnnotation(XmlSchema.class);
			if (xmlSchema != null) {
				namespace = xmlSchema.namespace();
			}
		}
		return namespace;
	}

	public static QName getQName(Class<?> klass) {
		XmlType xmlType = klass.getAnnotation(XmlType.class);
		if (xmlType == null)
			return null;
		String namespace = getNamespace(klass);
		String name = xmlType.name();
		return new QName(namespace, name);
	}

	public static <T> String marshal(T t) throws JAXBException {
		return marshal(t, null);
	}

	public static <T> String marshal(T t, QName qName) throws JAXBException {
		@SuppressWarnings("unchecked")
		Class<T> klass = (Class<T>) t.getClass();
		Marshaller marshaller = getMarshaller(klass);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		StringWriter sw = new StringWriter();
		marshaller.marshal(wrap(t, qName), sw);
		return sw.toString();
	}

	public static <T> String marshalRaw(T t, QName qName) throws JAXBException {
		@SuppressWarnings("unchecked")
		Class<T> klass = (Class<T>) t.getClass();
		Marshaller marshaller = getMarshaller(klass);
		StringWriter sw = new StringWriter();
		marshaller.marshal(wrap(t, qName), sw);
		return sw.toString();
	}

	public static <T> Element marshalToElement(T t) throws JAXBException,
			ParserConfigurationException {
		return marshalToElement(t, null);
	}

	public static <T> Element marshalToElement(T t, QName qName)
			throws JAXBException, ParserConfigurationException {
		@SuppressWarnings("unchecked")
		Class<T> klass = (Class<T>) t.getClass();
		Marshaller marshaller = getMarshaller(klass);
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();
		marshaller.marshal(wrap(t, qName), doc);
		return doc.getDocumentElement();
	}

	public static <T> T unmarshal(Class<T> klass, URL url) throws JAXBException {
		Unmarshaller unmarshaller = getUnmarshaller(klass);
		Object obj = unmarshaller.unmarshal(url);
		return castT(klass, obj);
	}

	public static <T> T unmarshal(Class<T> klass, String s)
			throws JAXBException {
		return unmarshal(klass, new StringReader(s));
	}

	public static <T> T unmarshal(Class<T> klass, Reader r)
			throws JAXBException {
		Unmarshaller unmarshaller = getUnmarshaller(klass);
		Object obj = unmarshaller.unmarshal(r);
		return castT(klass, obj);
	}

	public static <T> T unmarshal(Class<T> klass, Node node)
			throws JAXBException {
		Unmarshaller unmarshaller = getUnmarshaller(klass);
		Object obj = unmarshaller.unmarshal(node);
		return castT(klass, obj);
	}

	public static <T> Object wrap(T t) {
		return wrap(t, null);
	}

	public static <T> Object wrap(T t, QName qName) {
		Object o = t;
		@SuppressWarnings("unchecked")
		Class<T> klass = (Class<T>) t.getClass();
		if (!klass.isAnnotationPresent(XmlRootElement.class)) {
			if (qName == null)
				qName = getQName(klass);
			JAXBElement<T> je = new JAXBElement<T>(qName, klass, t);
			o = je;
		}
		return o;
	}

	public static <T> Marshaller getMarshaller(Class<T> klass)
			throws JAXBException {
		JAXBContext jaxb = getJAXBContext(klass);
		Marshaller marshaller = jaxb.createMarshaller();
		if (!klass.isAnnotationPresent(XmlRootElement.class)) {
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
		}
		return marshaller;
	}

	public static Unmarshaller getUnmarshaller(Class<?> klass)
			throws JAXBException {
		JAXBContext jaxb = getJAXBContext(klass);
		Unmarshaller unmarshaller = jaxb.createUnmarshaller();
		return unmarshaller;
	}

	@SuppressWarnings("unchecked")
	private static <T> T castT(Class<T> klass, Object obj) {
		T t = null;
		if (obj instanceof JAXBElement<?>) {
			JAXBElement<T> tElement = (JAXBElement<T>) obj;
			t = tElement.getValue();
		} else {
			t = (T) obj;
		}
		return t;
	}
}
