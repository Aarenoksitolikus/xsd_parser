package com.example.xsd_parser;

import org.springframework.stereotype.Component;
import org.xmlet.xsdparser.core.XsdParser;
import org.xmlet.xsdparser.xsdelements.XsdAbstractElement;
import org.xmlet.xsdparser.xsdelements.XsdElement;

import java.util.ArrayList;
import java.util.List;

@Component
public final class XsdXpathGeneratorVersion2 {

    // добавить проход для детей по включенным схемам
    public static List<String> getAllXpaths(String fileName) {
        XsdParser parser = new XsdParser(fileName);
        parser.getResultXsdSchemas().forEach(s -> parser.addFileToParse(s.getFilePath()));
        List<XsdElement> elements = parser.getResultXsdElements().toList();
        List<String> result = new ArrayList<>();
        for (XsdElement element : elements) {
            getChildrenNames(element, new StringBuilder(""), result);
        }
        return result;
    }

    private static List<String> getChildrenNames(XsdAbstractElement parent, StringBuilder xpath, List<String> result) {
        List<XsdAbstractElement> children = parent.getXsdElements().toList();

        String name = parent.getAttributesMap().get("name");
        if (name != null && !name.equals("")) xpath.append("/").append(name);
        if (children != null && !children.isEmpty()) {
            for (XsdAbstractElement child : children) {
                getChildrenNames(child, new StringBuilder(xpath), result);
            }
        } else {
            result.add(xpath.toString());
        }
        return result;
    }
}
