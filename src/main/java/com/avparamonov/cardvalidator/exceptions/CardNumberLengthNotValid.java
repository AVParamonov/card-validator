package com.avparamonov.cardvalidator.exceptions;

/**
 * Should be thrown when bank card number length is longer or shorter than expected.
 *
 * Created by andrey.paramonov@sigma.software on 17.05.17.
 */
public class CardNumberLengthNotValid extends Exception {

    public CardNumberLengthNotValid(String message) {
        super(message);
    }
}
