package com.avparamonov.cardvalidator.services;

import com.avparamonov.cardvalidator.dto.CardType;
import com.avparamonov.cardvalidator.dto.ValidationResult;
import com.avparamonov.cardvalidator.exceptions.CardNumberLengthNotValid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * Service provides validation of card number using Luhn algorithm.
 *
 * Created by AVParamonov on 17.05.17.
 */
@Service
public class CardValidatorService {

    @Value("${card.type.number.not.valid.message}")
    private String cardTypeIfNumberNotValidMessage;

    public ValidationResult validate(String cardNumber) throws CardNumberLengthNotValid {
        if (cardNumber == null) {
            throw new CardNumberLengthNotValid("Please enter a number");
        } else if (cardNumber.length() < 12) {
            throw new CardNumberLengthNotValid("Card number is too short! (length=" + cardNumber.length() + ")");
        } else if (cardNumber.length() > 19) {
            throw new CardNumberLengthNotValid("Card number is too long! (length=" + cardNumber.length() + ")");
        }

        int[] digits = IntStream.range(0, cardNumber.length()).map(i -> Character.digit(cardNumber.charAt(i), 10)).toArray();

        boolean isValid = luhnCheck(digits);

        String cardType = isValid ? determineCardType(cardNumber).getValue() : cardTypeIfNumberNotValidMessage;

        ValidationResult result = new ValidationResult();
        result.setCardNumber(cardNumber);
        result.setCardType(cardType);
        result.setValid(isValid);

        return result;
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

    private CardType determineCardType(String digits) {
        Map<CardType, Pattern> patterns = new HashMap<>();
        patterns.put(CardType.VISA, Pattern.compile("^4[0-9]{12}(?:[0-9]{3})?$"));
        patterns.put(CardType.AMERICAN_EXPRESS, Pattern.compile("^3[47][0-9]{13}$"));
        patterns.put(CardType.MASTER_CARD,
                Pattern.compile("^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$"));

        Optional<CardType> suggestedType = patterns.entrySet().stream()
                .filter(e -> e.getValue().matcher(digits).matches())
                .map(Map.Entry::getKey)
                .findFirst();

        return suggestedType.orElse(CardType.NOT_KNOWN);
    }
}
