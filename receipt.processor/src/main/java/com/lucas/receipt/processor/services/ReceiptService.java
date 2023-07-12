package com.lucas.receipt.processor.services;

import com.lucas.receipt.processor.models.requests.Item;
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
        receipt.setPoints(calculatePoints(request));

        receipt = receiptRepository.saveAndFlush(receipt);
        return ImmutableReceiptResponse.builder().id(receipt.getId()).build();
    }

    public ReceiptPointsResponse getReceiptPoints(long id) {

        ReceiptDataObject receipt = receiptRepository.getById(id);
        return ImmutableReceiptPointsResponse.builder().points(receipt.getPoints()).build();
    }

    private static long calculatePoints(ReceiptRequest receipt) {

        long totalPoints = 0;
        totalPoints += alphaNumericCharactersInString(receipt.getRetailer());

        if (isRoundDollarAmount(receipt.getTotal())) {
            totalPoints += 50;
        }

        if (isMultipleOf25Cents(receipt.getTotal())) {
            totalPoints += 25;
        }

        totalPoints += ((receipt.getItems().size() / 2) * 5L);

        for (Item item : receipt.getItems()) {

            // check if trimmed length of the item description is a multiple of 3
            if (item.getShortDescription().strip().length() % 3 == 0) {
                totalPoints += (Math.ceil(item.getPrice() * 0.2));
            }
        }

        if (isDateOdd(receipt.getPurchaseDate())) {
            totalPoints += 6;
        }

        if (isTimeBetween2pmAnd4pm(receipt.getPurchaseTime())) {
            totalPoints += 10;
        }

        return totalPoints;
    }

    // TODO
    private static long alphaNumericCharactersInString(String string) {
        return 0;
    }

    // TODO
    private static boolean isRoundDollarAmount(double cost) {
        return false;
    }

    // TODO
    private static boolean isMultipleOf25Cents(double cost) {
        return false;
    }

    // TODO
    private static boolean isDateOdd(String date) {
        return false;
    }

    // TODO
    private static boolean isTimeBetween2pmAnd4pm(String time) {
        return false;
    }
}
