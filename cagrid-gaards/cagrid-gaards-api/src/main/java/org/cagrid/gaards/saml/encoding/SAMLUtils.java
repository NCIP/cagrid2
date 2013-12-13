package org.cagrid.gaards.saml.encoding;

import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.cagrid.opensaml.SAMLAssertion;
import gov.nih.nci.cagrid.opensaml.SAMLAttribute;
import gov.nih.nci.cagrid.opensaml.SAMLAttributeStatement;
import gov.nih.nci.cagrid.opensaml.SAMLException;
import gov.nih.nci.cagrid.opensaml.XML;

import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.xml.namespace.QName;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * @author <A href="mailto:langella@bmi.osu.edu">Stephen Langella </A>
 * @author <A href="mailto:oster@bmi.osu.edu">Scott Oster </A>
 * @author <A href="mailto:hastings@bmi.osu.edu">Shannon Hastings </A>
 * @version $Id: ArgumentManagerTable.java,v 1.2 2004/10/15 16:35:16 langella
 *          Exp $
 */
public class SAMLUtils {

	public final static String XMLSEC_IGNORE_LINE_BREAK = "org.apache.xml.security.ignoreLineBreaks";

	public final static String XMLNS_PREFIX = "xmlns";
	public final static String XMLSIG_NS_PREFIX = "ds";
	public final static QName ASSERTION_QNAME = new QName(XML.SAML_NS,
			"Assertion");
	public final static QName SIGNATURE_QNAME = new QName(XML.XMLSIG_NS,
			"Signature");
	public final static QName SIGNED_INFO_QNAME = new QName(XML.XMLSIG_NS,
			"SignedInfo");
	public final static QName TRANSFORM_QNAME = new QName(XML.XMLSIG_NS,
			"Transform");
	public final static QName INCLUSIVE_NAMESPACES_QNAME = new QName(
			"http://www.w3.org/2001/10/xml-exc-c14n#", "InclusiveNamespaces");
	public final static QName PREFIX_LIST_QNAME = new QName(null, "PrefixList");

	private final static Set<QName> _WHITESPACE_EXCLUSIONS = new HashSet<QName>();
	public final static Set<QName> WHITESPACE_EXCLUSIONS = Collections
			.unmodifiableSet(_WHITESPACE_EXCLUSIONS);

	static {
		_WHITESPACE_EXCLUSIONS.add(TRANSFORM_QNAME);
	}

	public static SAMLAssertion domToSAMLAssertion(Element dom)
			throws Exception {
		SAMLAssertion saml = new SAMLAssertion(dom);
		return saml;
	}

	public static SAMLAssertion stringToSAMLAssertion(String str)
			throws Exception {
		SAMLAssertion saml = new SAMLAssertion(new ByteArrayInputStream(
				str.getBytes()));
		return saml;
	}

	public static String samlAssertionToString(SAMLAssertion saml)
			throws Exception {
		String xml = saml.toString();
		return xml;
	}

	public static Element samlAssertionToDOM(SAMLAssertion saml)
			throws Exception {
		return (Element) saml.toDOM();
	}

	public static String getAttributeValue(SAMLAssertion saml,
			String namespace, String name) {
		Iterator itr = saml.getStatements();
		while (itr.hasNext()) {
			Object o = itr.next();
			if (o instanceof SAMLAttributeStatement) {
				SAMLAttributeStatement att = (SAMLAttributeStatement) o;
				Iterator attItr = att.getAttributes();
				while (attItr.hasNext()) {
					SAMLAttribute a = (SAMLAttribute) attItr.next();
					if ((a.getNamespace().equals(namespace))
							&& (a.getName().equals(name))) {
						Iterator vals = a.getValues();
						while (vals.hasNext()) {
							String val = Utils.clean((String) vals.next());
							if (val != null) {
								return val;
							}
						}
					}
				}
			}
		}
		return null;
	}

	public static SAMLAssertion canonicalizeSAMLAssertion(SAMLAssertion saml0)
			throws SAMLException {
		Element element = (Element) saml0.toDOM();
		canonicalizeAssertion(element);
		SAMLAssertion saml = new SAMLAssertion(element);
		return saml;
	}

	public static void canonicalizeAssertion(Element element) {
		Element assertionElement = findElement(element, ASSERTION_QNAME);
		if (assertionElement == null)
			return;

		// Remove any 'ds' namespace declaration from assertion.
		removeNamespaceDeclartion(assertionElement.getAttributes(),
				XML.XMLSIG_NS);

		// Remove all whitespace-only text nodes.
		removeWhitespace(assertionElement);

		// Nothing more to do if no signature element.
		Element signatureElement = findElement(assertionElement,
				SIGNATURE_QNAME);
		if (signatureElement == null)
			return;

		// Ensure signature element has namespace declaration.
		ensureNamespaceDeclartion(signatureElement.getOwnerDocument(),
				signatureElement.getAttributes(), XML.XMLSIG_NS,
				XMLSIG_NS_PREFIX);

		// Add whitespace in the signature element.
		Element signedInfoElement = findElement(signatureElement,
				SIGNATURE_QNAME);
		if (signedInfoElement == null)
			return;

		// If org.apache.xml.security.ignoreLineBreaks is true don't add
		// whitespace
		if (!Boolean.valueOf(System.getProperty(XMLSEC_IGNORE_LINE_BREAK)))
			addWhitespace(signedInfoElement, _WHITESPACE_EXCLUSIONS);
	
		// Force the digital signature namespace prefix to 'ds'.
		String currentPrefix = signatureElement.getPrefix();
		boolean replacePrefix = !XMLSIG_NS_PREFIX.equals(currentPrefix);
		if (replacePrefix)
			setPrefix(signatureElement, SIGNATURE_QNAME.getNamespaceURI(),
					XMLSIG_NS_PREFIX);

		// In all inclusive-namespaces elements:
		// 1) Remove any 'ds' namespace declaration
		// 2) Replace the prefix in the PrefixList attribute
		NodeList inclusiveNamespaceElements = signedInfoElement
				.getElementsByTagNameNS(
						INCLUSIVE_NAMESPACES_QNAME.getNamespaceURI(),
						INCLUSIVE_NAMESPACES_QNAME.getLocalPart());
		for (int i = 0, n = inclusiveNamespaceElements.getLength(); i < n; i++) {
			Node inclusiveNamespaceNode = inclusiveNamespaceElements.item(i);
			NamedNodeMap attrs = inclusiveNamespaceNode.getAttributes();
			removeNamespaceDeclartion(attrs, XML.XMLSIG_NS);
			if (replacePrefix) {
				Attr prefixAttr = (Attr) attrs.getNamedItemNS(
						PREFIX_LIST_QNAME.getNamespaceURI(),
						PREFIX_LIST_QNAME.getLocalPart());
				if (prefixAttr == null)
					continue;
				String attrValue = prefixAttr.getValue();
				attrValue = replacePrefixInList(attrValue, currentPrefix,
						XMLSIG_NS_PREFIX);
				prefixAttr.setValue(attrValue);
			}
		}
	}

	public static Element findElement(Element element, QName qName) {
		if (qName.getNamespaceURI().equals(element.getNamespaceURI())
				&& qName.getLocalPart().equals(element.getLocalName()))
			return element;
		else {
			NodeList children = element.getChildNodes();
			for (int i = 0, n = children.getLength(); i < n; i++) {
				Node child = children.item(i);
				if (child instanceof Element) {
					element = findElement((Element) child, qName);
					if (element != null)
						return element;
				}
			}
		}
		return null;
	}

	public static void removeWhitespace(Element element) {
		NodeList children = element.getChildNodes();
		for (int i = children.getLength() - 1; i >= 0; i--) {
			Node child = children.item(i);
			if (child instanceof Text) {
				String data = ((Text) child).getData().trim();
				if (data.isEmpty())
					element.removeChild(child);
			} else if (child instanceof Element)
				removeWhitespace((Element) child);
		}
	}

	public static void addWhitespace(Element element, Set<QName> exemptions) {
		QName qName = new QName(element.getNamespaceURI(),
				element.getLocalName());
		if (exemptions.contains(qName))
			return;
		NodeList children = element.getChildNodes();
		int n = children.getLength();
		if (n == 0)
			return;
		Node firstChild = element.getFirstChild();
		if ((n == 1) && (firstChild.getNodeType() == Node.TEXT_NODE))
			return;
		Document doc = element.getOwnerDocument();
		Text whitespace = doc.createTextNode("\n");
		element.insertBefore(whitespace, null);
		for (int i = n - 1; i >= 0; i--) {
			Node child = children.item(i);
			whitespace = doc.createTextNode("\n");
			element.insertBefore(whitespace, child);
			if (child instanceof Element)
				addWhitespace((Element) child, exemptions);
		}
	}

	public static void removeNamespaceDeclartion(NamedNodeMap attrs,
			String namespaceURI) {
		for (int i = 0, n = attrs.getLength(); i < n; i++) {
			Attr attr = (Attr) attrs.item(i);
			if (XML.XMLNS_NS.equals(attr.getNamespaceURI())
					&& namespaceURI.equals(attr.getValue())) {
				attrs.removeNamedItemNS(XML.XMLNS_NS, attr.getLocalName());
				break;
			}
		}
	}

	public static void ensureNamespaceDeclartion(Document doc,
			NamedNodeMap attrs, String namespaceURI, String localName) {
		Attr attr = doc.createAttributeNS(XML.XMLNS_NS, XMLNS_PREFIX + ":"
				+ localName);
		attr.setValue(namespaceURI);
		attrs.setNamedItemNS(attr);
	}

	public static void setPrefix(Element element, String namespaceURI,
			String prefix) {
		if (namespaceURI.equals(element.getNamespaceURI()))
			element.setPrefix(prefix);
		NodeList children = element.getChildNodes();
		for (int i = 0, n = children.getLength(); i < n; i++) {
			Node child = children.item(i);
			if (child instanceof Element)
				setPrefix((Element) child, namespaceURI, prefix);
		}
	}

	public static String replacePrefixInList(String list, String oldPrefix,
			String newPrefix) {
		int listLength = list.length();
		int oldPrefixLength = oldPrefix.length();
		int ix = 0;
		int iy = oldPrefixLength;
		while (true) {
			ix = list.indexOf(oldPrefix, iy);
			if (ix < 0)
				break;
			if ((ix > 0) && !Character.isWhitespace(list.charAt(ix - 1)))
				continue;
			iy = ix + oldPrefixLength;
			if ((iy < listLength) && !Character.isWhitespace(list.charAt(iy)))
				continue;
			list = list.substring(0, ix) + newPrefix + list.substring(iy);
			break;
		}
		return list;
	}
}
