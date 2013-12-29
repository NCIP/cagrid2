package org.cagrid.trust.service.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public class XMLUtils {

	public final static String DEFAULT_ENCODING = "ISO-8859-1";

	/*
	 * Strings (and Nodes) don't care about encoding but the marshaller will use
	 * it to determine if characters should be hex-escaped -- UTF-8 results in
	 * no escaping.
	 */
	public final static String ALL_ENCODING = "UTF-8";

	private static Logger logger = LoggerFactory.getLogger(XMLUtils.class);

	public static <T> T copy(T obj) {
		return fromXMLObject(null, obj);
	}

	public static <T> T fromXMLObject(JAXBContext jaxbContext, T xml) {
		@SuppressWarnings("unchecked")
		Class<T> klass = (Class<T>) xml.getClass();

		if (jaxbContext == null) {
			try {
				jaxbContext = JAXBContext.newInstance(klass);
			} catch (JAXBException jaxbe) {
				logger.error("Could not create JAXB context for " + klass.getName());
				return null;
			}
		}

		Source source = null;
		try {
			source = new JAXBSource(jaxbContext, xml);
		} catch (JAXBException jaxbe) {
			logger.error("Could not create JAXBSource for " + klass.getName());
			return null;
		}

		return fromXML(jaxbContext, klass, source);
	}

	public static <T> T fromXMLString(Class<T> c, String xml) {
		return fromXMLString(null, c, xml);
	}

	public static <T> T fromXMLString(JAXBContext jaxbContext, Class<T> klass, String xml) {
		return fromXML(jaxbContext, klass, new StreamSource(new StringReader(xml)));
	}

	public static <T> T fromXMLFile(Class<T> c, File f) {
		return fromXMLFile(null, c, f);
	}

	public static <T> T fromXMLFile(JAXBContext jaxbContext, Class<T> klass, File file) {
		return fromXML(jaxbContext, klass, new StreamSource(file));
	}

	public static <T> T fromXMLDOM(Class<T> c, Node n) {
		return fromXMLDOM(null, c, n);
	}

	public static <T> T fromXMLDOM(JAXBContext jaxbContext, Class<T> klass, Node node) {
		return fromXML(jaxbContext, klass, new DOMSource(node));
	}

	/*
	 * All unmarshalling goes through here.
	 */
	public static <T> T fromXML(JAXBContext jaxbContext, Class<T> klass, Source source) {
		if (jaxbContext == null) {
			try {
				jaxbContext = JAXBContext.newInstance(klass);
			} catch (JAXBException jaxbe) {
				logger.error("Could not create JAXB context for " + klass.getName());
				return null;
			}
		}

		T t = null;
		try {
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			JAXBElement<T> element = unmarshaller.unmarshal(source, klass);
			t = element.getValue();
		} catch (JAXBException jaxbe) {
			logger.error("Could not unmarshal " + klass.getName(), jaxbe);
		}
		return t;
	}

	public static String toXMLString(Object object) {
		return toXMLString(null, object, false, ALL_ENCODING);
	}

	/**
	 * This method is used to return an XML document with or without the XML
	 * declaration.
	 * 
	 * @param object
	 *            - Object to be marshalled into XML
	 * @param excludeXMLDeclaration
	 *            - Boolean indicating whether to exclude the xml declaration
	 * @param encoding
	 *            the JAXB_ENCODING value to use
	 * 
	 * @return
	 */
	public static String toXMLString(Object object, boolean excludeXMLDeclaration, String encoding) {
		return toXMLString(null, object, excludeXMLDeclaration, encoding);
	}

	/**
	 * This method is used to return an XML document with or without the XML
	 * declaration.
	 * 
	 * @param object
	 *            - Object to be marshalled into XML
	 * @param excludeXMLDeclaration
	 *            - Boolean indicating whether to exclude the xml declaration
	 * @return
	 */
	public static String toXMLString(Object object, boolean excludeXMLDeclaration) {
		return toXMLString(null, object, excludeXMLDeclaration, ALL_ENCODING);
	}

	public static String toXMLString(JAXBContext jaxbContext, Object object, boolean excludeXMLDeclaration) {
		return toXMLString(jaxbContext, object, excludeXMLDeclaration, ALL_ENCODING);
	}

	public static String toXMLString(JAXBContext jaxbContext, Object object, boolean excludeXMLDeclaration, String encoding) {
		StringWriter sw = new StringWriter();
		toXML(jaxbContext, object, excludeXMLDeclaration, encoding, new StreamResult(sw));
		return sw.toString();
	}

	public static boolean toXMLFile(Object object, File file) throws Exception {
		return toXMLFile(null, object, DEFAULT_ENCODING, file);
	}

	/*
	 * All toFile should go through here.
	 */
	public static boolean toXMLFile(JAXBContext jaxbContext, Object object, String encoding, File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		try {
			Result result = new StreamResult(fos);
			toXML(jaxbContext, object, false, encoding, result);
		} finally {
			try {
				fos.close();
			} catch (IOException ignored) {
			}
		}
		return true;
	}

	public static Node toXMLDOM(Object object) {
		return toXMLDOM(null, object);
	}

	public static Node toXMLDOM(JAXBContext jaxbContext, Object object) {
		DOMResult result = new DOMResult();
		toXML(jaxbContext, object, false, ALL_ENCODING, result);
		return result.getNode();
	}

	/*
	 * All marshalling goes through here.
	 */
	public static void toXML(JAXBContext jaxbContext, Object object, boolean excludeXMLDeclaration, String encoding, Result result) {
		if (object == null)
			return;
		Class<?> klass = object.getClass();
		if (jaxbContext == null) {
			try {
				jaxbContext = JAXBContext.newInstance(klass);
			} catch (JAXBException jaxbe) {
				logger.error("Could not create JAXB context for " + klass.getName());
				return;
			}
		}

		try {
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, excludeXMLDeclaration);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
			marshaller.marshal(object, result);
		} catch (JAXBException jaxbe) {
			logger.error("Could not marshal " + klass.getName(), jaxbe);
		}
	}

	public static Document createDocumentFromFile(File file) throws Exception {
		DocumentBuilderFactory factory = getDocumentBuilderFactory();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new Exception(e);
		}
		return builder.parse(file);
	}

	public static Document createDocumentFromStream(InputStream is) throws Exception {
		DocumentBuilderFactory factory = getDocumentBuilderFactory();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new Exception(e);
		}
		return builder.parse(is);
	}

	private static DocumentBuilderFactory getDocumentBuilderFactory() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		factory.setXIncludeAware(true);
		return factory;
	}

	public static void writeDocumentToFile(Document d, File f) throws TransformerException, FileNotFoundException {
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();

		DOMSource source = new DOMSource(d);
		StreamResult result = new StreamResult(new FileOutputStream(f));
		transformer.transform(source, result);
	}

	public static String formatXML(String xml) {
		try {
			Transformer serializer = SAXTransformerFactory.newInstance().newTransformer();
			serializer.setOutputProperty(OutputKeys.INDENT, "yes");
			serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			Source xmlSource = new SAXSource(new InputSource(new ByteArrayInputStream(xml.getBytes())));
			StreamResult res = new StreamResult(new ByteArrayOutputStream());
			serializer.transform(xmlSource, res);
			return new String(((ByteArrayOutputStream) res.getOutputStream()).toByteArray());
		} catch (Exception e) {

			return xml;
		}
	}

}
