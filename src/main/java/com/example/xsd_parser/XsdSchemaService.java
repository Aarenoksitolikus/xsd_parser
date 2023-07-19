package com.example.xsd_parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class XsdSchemaService {

    private final XsdXpathGenerator xpathGenerator;

    @Autowired
    public XsdSchemaService(XsdXpathGenerator xpathGenerator) {
        this.xpathGenerator = xpathGenerator;
    }

    public XsdObject getResult(String fileName) {
        Map<String, String> xpaths = xpathGenerator.getAllXpaths(new File(fileName));
        XsdObject result = new XsdObject();
        return null;
    }

    public Map<String, String> get(String fileName) {
        return xpathGenerator.getAllXpaths(new File(fileName));
    }
}
