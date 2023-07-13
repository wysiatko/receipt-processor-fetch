package com.lucas.receipt.processor.controllers;

import com.lucas.receipt.processor.models.requests.ReceiptRequest;
import com.lucas.receipt.processor.models.responses.ReceiptPointsResponse;
import com.lucas.receipt.processor.models.responses.ReceiptResponse;
import com.lucas.receipt.processor.services.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReceiptController {

    private ReceiptService receiptService;

    @Autowired
    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping("/receipts/process")
    public ReceiptResponse processReceipt(@RequestBody ReceiptRequest request) {
        return receiptService.processReceipt(request);
    }

    @GetMapping("/receipts/{id}/points")
    public ReceiptPointsResponse getReceiptPoints(@PathVariable("id") long id) {
        return receiptService.getReceiptPoints(id);
    }
}
