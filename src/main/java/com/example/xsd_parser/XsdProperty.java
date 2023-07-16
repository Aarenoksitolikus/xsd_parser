package com.example.xsd_parser;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class XsdProperty {
    private String name;
    private XsdField content;

    @Override
    public String toString() {
        return "\"" + name + "\": {\n" + content.toString() + "\n}";
    }
}
