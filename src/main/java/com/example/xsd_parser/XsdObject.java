package com.example.xsd_parser;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class XsdObject {
    @JsonProperty("type")
    private final static XsdType type = XsdType.OBJECT;
    @JsonProperty("properties")
    private List<XsdField> properties;
}
