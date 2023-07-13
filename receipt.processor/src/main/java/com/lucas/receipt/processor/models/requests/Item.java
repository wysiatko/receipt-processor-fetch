package com.lucas.receipt.processor.models.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableItem.class)
public interface Item {

    String getShortDescription();

    double getPrice();
}
