package com.lucas.receipt.processor.models.responses;

import org.immutables.value.Value;

@Value.Immutable
public interface ReceiptPointsResponse {

    long getPoints();
}
