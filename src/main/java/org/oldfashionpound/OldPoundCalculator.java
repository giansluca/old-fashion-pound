package org.oldfashionpound;

import org.oldfashionpound.parser.OldPoundParser;

public class OldPoundCalculator {

    private OldPoundParser oldPoundParser;

    public OldPoundCalculator() {
        oldPoundParser = new OldPoundParser();
    }

    public String add(String actual, String toAdd) {
        var currency = oldPoundParser.parseCurrencyString(actual);
        var currencyToAdd = oldPoundParser.parseCurrencyString(toAdd);
        currency.add(currencyToAdd);

        return currency.getAmount();
    }
}
