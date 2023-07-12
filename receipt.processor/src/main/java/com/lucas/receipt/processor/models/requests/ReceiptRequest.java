package com.lucas.receipt.processor.models.requests;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public interface ReceiptRequest {

    String getRetailer();

    String getPurchaseDate();

    String getPurchaseTime();

    List<Item> getItems();

    double getTotal();
}
