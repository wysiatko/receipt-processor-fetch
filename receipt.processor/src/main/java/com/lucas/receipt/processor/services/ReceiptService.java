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

import java.util.regex.Pattern;

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

    public static long calculatePoints(ReceiptRequest receipt) {

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

    private static long alphaNumericCharactersInString(String string) {

        char[] characters = string.toCharArray();

        int count = 0;

        for (char character : characters) {

            if (Character.isLetter(character) || Character.isDigit(character)) {
                count++;
            }
        }

        return count;
    }

    private static boolean isRoundDollarAmount(double cost) {
        return getDecimalValue(cost) == 0;
    }

    private static boolean isMultipleOf25Cents(double cost) {

        int cents = getDecimalValue(cost);

        return cents % 25 == 0;
    }

    private static boolean isDateOdd(String date) {

        String[] yearMonthDay = date.split("-");

        return Integer.parseInt(yearMonthDay[2]) % 2 != 0;
    }

    private static boolean isTimeBetween2pmAnd4pm(String time) {

        time = time.replace(":", "");
        int hourMin = Integer.parseInt(time);

        return hourMin > 1400 && hourMin < 1600;
    }

    private static int getDecimalValue(double cost) {

        String costAsString = String.valueOf(cost);

        String[] intDecimal = costAsString.split("\\.");

        return Integer.parseInt(intDecimal[1]);
    }
}
