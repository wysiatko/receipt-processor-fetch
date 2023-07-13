package com.lucas.receipt.processor.models.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonDeserialize(as = ImmutableReceiptRequest.class)
public interface ReceiptRequest {

    String getRetailer();

    String getPurchaseDate();

    String getPurchaseTime();

    List<Item> getItems();

    double getTotal();
}
