package com.example.xsd_parser;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class XsdXpathGenerator {
    public Map<String, String> getAllXpaths(File file) {
        Map<String, String> result = new HashMap<>();
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document document = docBuilder.parse(file);

            NodeList elementList = document.getElementsByTagName("xs:element");
//            NodeList attributeList = document.getElementsByTagName("xs:attribute");

//            for (int i = 0; i < attributeList.getLength(); i++) {
//                Element element = (Element) attributeList.item(i);
//                if (element.hasAttributes() && !element.getAttribute("type").equals("")) {
//                    result.put(element.getAttribute("name"), extractType(element.getAttribute("type")));
//                }
//            }

            for (int i = 0; i < elementList.getLength(); i++) {
                Element element = (Element) elementList.item(i);
                if (element.hasAttributes() && !element.getAttribute("name").equals("")) {
                    result.put(xpathString(element, new StringBuilder(element.getAttribute("name"))).toString(), extractType(element.getAttribute("type")));
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private StringBuilder xpathString(Element element, StringBuilder xpath) {
        Element parent = getParentElement(element);
        if (parent != null) {
            if (parent.hasAttributes() && !parent.getAttribute("name").equals("")) {
                xpath = new StringBuilder(parent.getAttribute("name")).append("/").append(xpath);
                return xpathString(parent, xpath);
            } else {
                return xpath;
            }
        } else {
            return xpath;
        }
    }

    private Element getParentElement(Element element) {
        Node parentNode = element.getParentNode();
        if (parentNode instanceof Element) {
            String nodeName = parentNode.getNodeName();
            if (nodeName.equalsIgnoreCase("xs:element")) {
                return (Element) element.getParentNode();
            } else {
                return getParentElement((Element) parentNode);
            }
        } else {
            return null;
        }
    }

    private String extractType(String type) {
        if (type.startsWith("xs:")) {
            return type.replace("xs:", "");
        } else {
            return type;
        }
    }
}
