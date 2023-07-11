package com.lucas.receipt.processor.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class ReceiptController {

    @PostMapping("/receipts/process")
    public Long processReceipts(@RequestBody ) {
        return 5L;
    }

    @GetMapping("/receipts/{id}/points")
    public Long getReceiptPoints(@PathVariable("id") long id) {
        return id;
    }
}
