package com.avparamonov.cardvalidator.controllers;

import com.avparamonov.cardvalidator.dto.ValidationResult;
import com.avparamonov.cardvalidator.exceptions.CardNumberLengthNotValid;
import com.avparamonov.cardvalidator.services.CardValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by AVParamonov on 17.05.17.
 */
@Controller
@RequestMapping(value = Api.ROOT_PATH)
public class CardValidatorController {

    private final CardValidatorService validator;

    @Autowired
    public CardValidatorController(CardValidatorService validator) {
        this.validator = validator;
    }

    @RequestMapping
    public String validationForm() {
        return "index";
    }

    @RequestMapping(value = Api.V1.VALIDATE, method = RequestMethod.POST)
    public String validateCardNumber(@RequestParam(value = "cardNumber") String cardNumber, Model model) {
        ValidationResult result;
        try {
            result = validator.validate(cardNumber);
        } catch (CardNumberLengthNotValid e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return "error";
        }
        model.addAttribute("result", result);
        return "validation";
    }
}
