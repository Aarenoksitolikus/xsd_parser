package com.example.xsd_parser;

public enum XsdType {
    OBJECT ("object"),
    STRING ("string"),
    NUMBER ("number"),
    ARRAY ("array"),
    BOOLEAN ("boolean");

    private final String name;

    private XsdType(String name) {
        this.name = name;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
