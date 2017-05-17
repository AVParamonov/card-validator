package com.aparamonov.cardvalidator.services;

import com.aparamonov.cardvalidator.exceptions.CardNumberLengthNotValid;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

/**
 * Service provides validation of card number with Luhn check algorithm.
 *
 * Created by AVParamonov on 17.05.17.
 */
@Service
public class CardValidatorService {

    public String validate(String cardNumber) throws CardNumberLengthNotValid {
        if (cardNumber.length() < 13) {
            throw new CardNumberLengthNotValid("Card number is too short! (length=" + cardNumber.length() + ")");
        } else if (cardNumber.length() > 19) {
            throw new CardNumberLengthNotValid("Card number is too long! (length=" + cardNumber.length() + ")");
        }

        int[] digits = IntStream.range(0, cardNumber.length()).map(i -> Character.digit(cardNumber.charAt(i), 10)).toArray();

        boolean isValid = luhnCheck(digits);
        String answer = isValid ? "VALID" : "NOT VALID";
        return "Card is " + answer + "!";
    }

    private boolean luhnCheck(int[] digits) {
        return IntStream.range(0, digits.length)
                .map(i -> {
                    if (i % 2 == digits.length % 2) {
                        return (digits[i] * 2) / 10 + (digits[i] * 2) % 10;
                    } else {
                        return digits[i];
                    }
                })
                .sum() % 10 == 0;
    }
}
