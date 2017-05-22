package com.avparamonov.cardvalidator.services;

import com.avparamonov.cardvalidator.Application;
import com.avparamonov.cardvalidator.dto.ValidationResult;
import com.avparamonov.cardvalidator.exceptions.CardNumberLengthNotValid;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by AVParamonov on 17.05.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class CardValidatorServiceTest {

    @Autowired
    CardValidatorService service;

    @Value("${card.type.number.not.valid.message}")
    private String cardTypeIfNumberNotValidMessage;

    @Test(expected = CardNumberLengthNotValid.class)
    public void testCardNumberLengthTooLong() throws Exception {
        String cardNumberLength20 = "12345678901234567890";
        service.validate(cardNumberLength20);
    }

    @Test(expected = CardNumberLengthNotValid.class)
    public void testCardNumberLengthTooShort() throws Exception {
        String cardNumberLength11 = "12345678901";
        service.validate(cardNumberLength11);
    }

    @Test(expected = CardNumberLengthNotValid.class)
    public void testCardNumberNull() throws Exception {
        service.validate(null);
    }

    @Test
    public void testCardNumberVisa() throws Exception {
        String validVisa = "4012888888881881";
        ValidationResult result = service.validate(validVisa);
        Assert.assertEquals(validVisa, result.getCardNumber());
        Assert.assertEquals("Visa", result.getCardType());
        Assert.assertEquals(true, result.isValid());
    }

    @Test
    public void testCardNumberMasterCard() throws Exception {
        String validMasterCard = "5105105105105100";
        ValidationResult result = service.validate(validMasterCard);
        Assert.assertEquals(validMasterCard, result.getCardNumber());
        Assert.assertEquals("Master Card", result.getCardType());
        Assert.assertEquals(true, result.isValid());

    }

    @Test
    public void testCardNumberAmericanExpress() throws Exception {
        String validAmericanExpress = "378282246310005";
        ValidationResult result = service.validate(validAmericanExpress);
        Assert.assertEquals(validAmericanExpress, result.getCardNumber());
        Assert.assertEquals("American Express", result.getCardType());
        Assert.assertEquals(true, result.isValid());
    }

    @Test
    public void testCardNumberValidTypeNotKnown() throws Exception {
        String validButTypeNotKnown = "3530111333300000";
        ValidationResult result = service.validate(validButTypeNotKnown);
        Assert.assertEquals(validButTypeNotKnown, result.getCardNumber());
        Assert.assertEquals("Card type not known", result.getCardType());
        Assert.assertEquals(true, result.isValid());
    }

    @Test
    public void testCardNumberNotValid() throws Exception {
        String cardNumberNotValid = "1234567890123456";
        ValidationResult result = service.validate(cardNumberNotValid);
        Assert.assertEquals(cardNumberNotValid, result.getCardNumber());
        Assert.assertEquals(cardTypeIfNumberNotValidMessage, result.getCardType());
        Assert.assertEquals(false, result.isValid());
    }
}
