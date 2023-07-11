package com.lucas.receipt.processor.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiptController {

    @GetMapping("/receipts/{id}/points")
    public Long getReceiptPoints(@PathVariable("id") long id) {
        return id;
    }
}
