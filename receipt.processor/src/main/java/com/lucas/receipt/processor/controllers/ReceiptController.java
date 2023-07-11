package com.lucas.receipt.processor.controllers;

import com.lucas.receipt.processor.models.requests.ReceiptRequest;
import com.lucas.receipt.processor.models.responses.ReceiptPointsResponse;
import com.lucas.receipt.processor.models.responses.ReceiptResponse;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReceiptController {

    @PostMapping("/receipts/process")
    public ReceiptResponse processReceipts(@RequestBody ReceiptRequest request) {
        return null;
    }

    @GetMapping("/receipts/{id}/points")
    public ReceiptPointsResponse getReceiptPoints(@PathVariable("id") long id) {
        return id;
    }
}
