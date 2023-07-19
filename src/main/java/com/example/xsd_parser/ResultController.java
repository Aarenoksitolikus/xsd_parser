package com.example.xsd_parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/xsd")
public class ResultController {

    private final XsdSchemaService schemaService;

    @Autowired
    public ResultController(XsdSchemaService schemaService) {
        this.schemaService = schemaService;
    }

    @PostMapping
    public ResponseEntity<List<String>> getResult(@RequestPart String filePath) {
        return ResponseEntity.ok(schemaService.get(filePath).keySet().stream().sorted().toList());
    }
}
