package org.oldfashionpound;

import org.oldfashionpound.parser.OldPoundParser;

/**
 * Old Pound Calculator class
 *
 * All input currencies strings must be it the the format: 'Xp Ys Zd'
 * Example add method: add("5p 17s 8d", "3p 4s 10d");
 */
public class OldPoundCalculator {

    private OldPoundParser oldPoundParser;

    public OldPoundCalculator() {
        oldPoundParser = new OldPoundParser();
    }

    /**
     * @return the formatted string result of addition
     */
    public String add(String initialCurrency, String toAdd) {
        var currency = oldPoundParser.parseCurrencyString(initialCurrency);
        var currencyToAdd = oldPoundParser.parseCurrencyString(toAdd);
        currency.add(currencyToAdd);

        return currency.getFormattedCurrency();
    }

    /**
     * @return the formatted string result of subtraction
     */
    public String subtract(String initialCurrency, String toSubtract) {
        var currency = oldPoundParser.parseCurrencyString(initialCurrency);
        var currencyToAdd = oldPoundParser.parseCurrencyString(toSubtract);
        currency.subtract(currencyToAdd);

        return currency.getFormattedCurrency();
    }

    /**
     * @return the formatted string result of multiplication
     */
    public String multiply(String initialCurrency, int multiplicationValue) {
        var currency = oldPoundParser.parseCurrencyString(initialCurrency);
        currency.multiply(multiplicationValue);

        return currency.getFormattedCurrency();
    }

    /**
     * @return the formatted string result of division plus reminder
     */
    public String divide(String initialCurrency, int divisionValue) {
        var currency = oldPoundParser.parseCurrencyString(initialCurrency);
        currency.divide(divisionValue);

        String result = currency.getFormattedCurrency();
        String reminder = currency.getFormattedReminder();

        return reminder == null ? result : result + " " + reminder;
    }
}
