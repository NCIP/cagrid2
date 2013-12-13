package org.oasis.names.tc.saml.assertion;

import gov.nih.nci.cagrid.opensaml.SAMLAssertion;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.cagrid.core.common.JAXBUtils;
import org.cagrid.gaards.saml.encoding.SAMLUtils;
import org.w3c.dom.Element;

public class AssertionTypeAdapter extends XmlAdapter<Object, AssertionType> {

    public AssertionTypeAdapter() {
        super();
        System.setProperty(SAMLUtils.XMLSEC_IGNORE_LINE_BREAK, Boolean.FALSE.toString());
    }

    @Override
    public Element marshal(AssertionType assertion) throws Exception {
        SAMLAssertion saml = assertion.getSamlAssertion();
        Element samlAssertionToDOM = (Element) saml.toDOM();
        // System.out.println("FROM DOM:" + XMLUtils.toString(samlAssertionToDOM));
        // System.out.println("FROM String:" + saml.toString());
        return samlAssertionToDOM;
    }

    @Override
    public AssertionType unmarshal(Object assertionElement) throws Exception {
        SAMLUtils.canonicalizeAssertion((Element) assertionElement);

        // Element assertionElement = JAXBUtils.marshalToElement(assertion, SAMLUtils.ASSERTION_QNAME);
        // SAMLUtils.canonicalizeAssertion(assertionElement);
        SAMLAssertion samlAssertion = new SAMLAssertion((Element) assertionElement);
        // Must regenerate internal DOM!
        samlAssertion.toString();

        // SAMLAssertion domToSAMLAssertion = SAMLUtils.domToSAMLAssertion((Element) assertionElement);
        AssertionType assertion = JAXBUtils.unmarshal(AssertionType.class, samlAssertion.toString());
        assertion.setSamlAssertion(samlAssertion);
        return assertion;

    }

}
