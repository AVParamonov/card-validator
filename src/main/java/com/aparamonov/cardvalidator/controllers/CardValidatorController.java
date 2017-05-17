package com.aparamonov.cardvalidator.controllers;

import com.aparamonov.cardvalidator.exceptions.CardNumberLengthNotValid;
import com.aparamonov.cardvalidator.services.CardValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by AVParamonov on 17.05.17.
 */
@RestController
@RequestMapping(value = Api.ROOT_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class CardValidatorController {

    @Autowired
    private CardValidatorService validator;

    @RequestMapping(value = Api.V1.VALIDATE)
    public ResponseEntity<String> validateCardNumber(@PathVariable String cardNumber) {
        String result;
        try {
            result = validator.validate(cardNumber);
        } catch (CardNumberLengthNotValid e) {
            e.printStackTrace();
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
        return ResponseEntity.ok(result);
    }
}
