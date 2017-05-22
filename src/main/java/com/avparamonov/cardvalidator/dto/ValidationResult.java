package com.avparamonov.cardvalidator.dto;

import lombok.Data;

/**
 * Result of bank card number validation process.
 *
 * Created by andrey.paramonov@sigma.software on 22.05.17.
 */
@Data
public class ValidationResult {

    String cardNumber;
    String cardType;
    boolean isValid;
}
