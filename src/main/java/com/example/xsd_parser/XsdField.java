package com.example.xsd_parser;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class XsdField {
    @JsonProperty("xpath")
    String xPath;
}
