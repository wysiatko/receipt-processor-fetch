package com.lucas.receipt.processor.services;

import com.lucas.receipt.processor.models.requests.ReceiptRequest;
import com.lucas.receipt.processor.models.responses.ImmutableReceiptPointsResponse;
import com.lucas.receipt.processor.models.responses.ImmutableReceiptResponse;
import com.lucas.receipt.processor.models.responses.ReceiptPointsResponse;
import com.lucas.receipt.processor.models.responses.ReceiptResponse;
import com.lucas.receipt.processor.repositories.ReceiptRepository;
import com.lucas.receipt.processor.repositories.entities.ReceiptDataObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiptService {

    private ReceiptRepository receiptRepository;

    @Autowired
    public ReceiptService(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    public ReceiptResponse processReceipt(ReceiptRequest request) {

        ReceiptDataObject receipt = new ReceiptDataObject();
        receipt.setPoints(2);

        receipt = receiptRepository.saveAndFlush(receipt);
        return ImmutableReceiptResponse.builder().id(receipt.getId()).build();
    }

    public ReceiptPointsResponse getReceiptPoints(long id) {

        ReceiptDataObject receipt = receiptRepository.getById(id);
        return ImmutableReceiptPointsResponse.builder().points(receipt.getPoints()).build();
    }
}
