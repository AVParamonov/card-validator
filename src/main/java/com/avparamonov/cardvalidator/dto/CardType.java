package com.avparamonov.cardvalidator.dto;

/**
 * Created by andrey.paramonov@sigma.software on 22.05.17.
 */
public enum CardType {

    VISA("Visa"),
    MASTER_CARD("Master Card"),
    AMERICAN_EXPRESS("American Express"),
    NOT_KNOWN("Card type not known");

    private String value;

    CardType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    public static CardType getEnum(String value) {
        for(CardType v : values())
            if(v.getValue().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
