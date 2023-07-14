package com.example.xsd_parser;

import org.springframework.stereotype.Component;
import org.xmlet.xsdparser.core.XsdParser;
import org.xmlet.xsdparser.xsdelements.XsdAbstractElement;
import org.xmlet.xsdparser.xsdelements.XsdComplexType;
import org.xmlet.xsdparser.xsdelements.XsdSchema;
import org.xmlet.xsdparser.xsdelements.elementswrapper.ReferenceBase;

import java.util.ArrayList;
import java.util.List;

@Component
public class XsdXpathGeneratorVersion2 {

    public static void main(String[] args) {
        getAllXpaths("src/main/resources/xml/XSD CDA/CDA.xsd");
    }

    private static void getAllXpaths(String fileName) {
        XsdObject document = new XsdObject();
        XsdParser parser = new XsdParser(fileName);
        List<XsdSchema> schemas = parser.getResultXsdSchemas().toList();

//        for (XsdSchema schema : schemas) {
//            for (XsdAbstractElement schemaChild : getSchemaChildren(schema)) {
//                List<String> childrenNames = getChildrenNames(schemaChild, new StringBuilder("/"), new ArrayList<>());
//                for (String childrenName : childrenNames) {
//                    System.out.println(childrenName);
//                }
//            }
//        }

        for (XsdAbstractElement child : getSchemaChildren(schemas.get(schemas.size() - 1))) {
            if (!child.getClass().equals(XsdComplexType.class)) {
                continue;
            }
            List<String> xpaths = getChildrenNames(child, new StringBuilder("/"), new ArrayList<>());
            for (String xpath : xpaths) {
                System.out.println(xpath);
            }
        }
    }

    private static List<XsdAbstractElement> getSchemaChildren(XsdSchema parentSchema) {
//        return parentSchema.getXsdElements().toList();
//        return parentSchema.getChildrenElements().map(XsdAbstractElement.class::cast).toList();
        return parentSchema.getElements().stream().map(ReferenceBase::getElement).toList();
    }

    private static List<String> getChildrenNames(XsdAbstractElement parent, StringBuilder xpath, List<String> result) {
//        List<XsdAbstractElement> children = parent.getXsdElements().toList();
        List<ReferenceBase> children = parent.getElements();

        String name = parent.getAttributesMap().get("name");
        String parentClass = parent.getClass().getSimpleName();
        switch (parentClass) {
            case "XsdComplexType" -> xpath.append("/xs:complexType");
            case "XsdAttribute" -> xpath.append("/xs:attribute");
            case "XsdElement" -> xpath.append("/xs:element");
            case "XsdSequence" -> xpath.append("/xs:sequence");
            case "XsdComplexContent" -> xpath.append("/xs:complexContent");
            case "XsdRestriction" -> xpath.append("/xs:restriction");
            case "XsdChoice" -> xpath.append("/xs:choice");
            case "XsdUnion" -> xpath.append("/xs:union");
            case "XsdAnnotation" -> xpath.append("/xs:annotation");
            case "XsdDocumentation" -> xpath.append("/xs:documentation");
            default -> xpath.append("/fuck");
        }
        if (name != null && !name.equals("")) xpath.append(String.format("[@name=\"%s\"]", name));
        if (children != null && !children.isEmpty()) {
            for (ReferenceBase child : children) {
                getChildrenNames(child.getElement(), new StringBuilder(xpath), result);
            }
        } else {
            result.add(xpath.toString());
        }
        return result;
    }
}
