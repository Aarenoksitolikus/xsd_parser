package com.example.xsd_parser;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class XsdArray extends XsdField {
    @JsonProperty("type")
    private final static XsdType type = XsdType.ARRAY;

    @JsonProperty("items")
    private List<XsdObject> items;
}
