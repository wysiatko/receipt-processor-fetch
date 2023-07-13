package com.lucas.receipt.processor.services;

import com.lucas.receipt.processor.models.requests.ImmutableItem;
import com.lucas.receipt.processor.models.requests.ImmutableReceiptRequest;
import com.lucas.receipt.processor.models.requests.Item;
import com.lucas.receipt.processor.models.requests.ReceiptRequest;
import com.lucas.receipt.processor.repositories.ReceiptRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptServiceTest {

    @Test
    void testCalculatePointsSampleReceipt1() {

        List<Item> items = new ArrayList<>();
        items.add(ImmutableItem.builder().shortDescription("Mountain Dew 12PK").price(6.49).build());
        items.add(ImmutableItem.builder().shortDescription("Emils Cheese Pizza").price(12.25).build());
        items.add(ImmutableItem.builder().shortDescription("Knorr Creamy Chicken").price(1.26).build());
        items.add(ImmutableItem.builder().shortDescription("Doritos Nacho Cheese").price(3.35).build());
        items.add(ImmutableItem.builder().shortDescription("   Klarbrunn 12-PK 12 FL OZ  ").price(12.00).build());

        ReceiptRequest receipt = ImmutableReceiptRequest.builder()
                .retailer("Target")
                .purchaseDate("2022-01-01")
                .purchaseTime("13:01")
                .addAllItems(items)
                .total(35.35)
                .build();

        assertEquals(28, ReceiptService.calculatePoints(receipt));
    }

    @Test
    void testCalculatePointsSampleReceipt2() {

        List<Item> items = new ArrayList<>();
        items.add(ImmutableItem.builder().shortDescription("Gatorade").price(2.25).build());
        items.add(ImmutableItem.builder().shortDescription("Gatorade").price(2.25).build());
        items.add(ImmutableItem.builder().shortDescription("Gatorade").price(2.25).build());
        items.add(ImmutableItem.builder().shortDescription("Gatorade").price(2.25).build());

        ReceiptRequest receipt = ImmutableReceiptRequest.builder()
                .retailer("M&M Corner Market")
                .purchaseDate("2022-03-20")
                .purchaseTime("14:33")
                .addAllItems(items)
                .total(9.00)
                .build();

        assertEquals(109, ReceiptService.calculatePoints(receipt));
    }
}