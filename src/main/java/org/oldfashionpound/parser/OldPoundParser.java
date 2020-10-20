package org.oldfashionpound.parser;

import org.oldfashionpound.validator.OldPoundValidator;
import org.oldfashionpound.currency.OldPoundCurrency;

import java.util.Map;
import java.util.stream.*;

public class OldPoundParser {

    private static final String POUNDS = "p";
    private static final String SHILLINGS = "s";
    private static final String PENCE= "d";

    private OldPoundValidator oldPoundValidator;
    private String currency;

    public OldPoundParser() {
        this.oldPoundValidator = new OldPoundValidator();
    }

    public OldPoundParser(OldPoundValidator oldPoundValidator, String currency) {
        this.oldPoundValidator = oldPoundValidator;
        this.currency = currency;
    }

    public OldPoundCurrency parseCurrencyString(String currency) {
        this.currency = currency;
        return parseCurrencyString();
    }

    public OldPoundCurrency parseCurrencyString() {
        oldPoundValidator.validateCurrencyString(currency);

        Map<String, Integer> currencyMap = Stream.of(currency.split(" "))
                .collect(Collectors.toMap(
                        part -> part.substring(part.length() - 1),
                        part -> Integer.valueOf(part.substring(0, part.length() - 1)))
                );

        return new OldPoundCurrency(
                currencyMap.get(POUNDS), currencyMap.get(SHILLINGS), currencyMap.get(PENCE));
    }
}
