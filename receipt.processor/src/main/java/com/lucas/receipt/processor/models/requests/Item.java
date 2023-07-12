package com.lucas.receipt.processor.models.requests;

import org.immutables.value.Value;

@Value.Immutable
public interface Item {

    String getShortDescription();

    double getPrice();
}
