package com.example.xsd_parser;

import org.springframework.stereotype.Component;
import org.xmlet.xsdparser.core.XsdParser;
import org.xmlet.xsdparser.xsdelements.XsdAbstractElement;
import org.xmlet.xsdparser.xsdelements.XsdElement;
import org.xmlet.xsdparser.xsdelements.XsdSchema;

import java.util.stream.Stream;

@Component
public class XsdXpathGeneratorVersion2 {

    public static void main(String[] args) {
        getAllXpath("src/main/resources/xml/XSD CDA/CDA.xsd");
    }

    public static void parse(String fileName) {
        XsdParser parser = new XsdParser(fileName);

        Stream<XsdElement> resultXsdElements = parser.getResultXsdElements();
        Stream<XsdSchema> resultXsdSchemas = parser.getResultXsdSchemas();

        resultXsdElements.forEach(e -> {
            System.out.println(e.getName());
        });
    }

    private static void getAllXpath(String fileName) {
        XsdParser parser = new XsdParser(fileName);
//        Stream<XsdSchema> schemas = parser.getResultXsdSchemas();
//        schemas.forEach(e -> {
//            System.out.println("\n" + e.getFilePath() + "\n");
//            e.getAttributesMap().forEach((k, v) -> System.out.println("[" + k + ": " + v + "]"));
//        });

        Stream<XsdElement> elements = parser.getResultXsdElements();
        elements.forEach(e -> {
            System.out.println("\n" + e.getName());
            e.getAttributesMap().forEach((k, v) -> System.out.println("[" + k + ": " + v + "]"));
            System.out.println("xpath: " + getXpathString(e, new StringBuilder(e.getName())));
        });
    }

    private static StringBuilder getXpathString(XsdElement element, StringBuilder xpath) {
        XsdAbstractElement parent = getParentElement(element);
        if (parent != null && parent.getAttributesMap().get("name") != null) {
            xpath = new StringBuilder(parent.getAttributesMap().get("name")).append("/").append(xpath);
            return getXpathString((XsdElement) parent, xpath);
        } else return xpath;
    }

    private static XsdAbstractElement getParentElement(XsdElement element) {
        if (element.parentAvailable) return element.getParent();
        else return null;
    }

//    private static StringBuilder xpathString(Element element, StringBuilder xpath) {
//        Element parent = getParentElement(element);
//        if (parent != null) {
//            if (parent.hasAttributes() && !parent.getAttribute("name").equals("")) {
//                xpath = new StringBuilder(parent.getAttribute("name")).append("/").append(xpath);
//                return xpathString(parent, xpath);
//            } else {
//                return xpath;
//            }
//        } else {
//            return xpath;
//        }
//    }

//    private static Element getParentElement(Element element) {
//        Node parentNode = element.getParentNode();
//        if (parentNode instanceof Element) {
//            String nodeName = parentNode.getNodeName();
//            if (nodeName.equalsIgnoreCase("xs:element")) {
//                return (Element) element.getParentNode();
//            } else {
//                return getParentElement((Element) parentNode);
//            }
//        } else {
//            return null;
//        }
//    }
}
