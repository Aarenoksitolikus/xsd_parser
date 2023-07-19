package com.example.xsd_parser;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class XsdParameter implements XsdField {
    @JsonProperty("type")
    private XsdType type;
    @JsonProperty("xml_type")
    private String xmlType;
    @JsonProperty("xpath")
    private String xPath;
    @JsonProperty("title")
    private String title;
    @JsonProperty("decode")
    private String decode;
}
