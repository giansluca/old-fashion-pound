package org.oldfashionpound;

import java.util.*;
import java.util.stream.Stream;

public class OldPoundValidator {

    private static final String ALLOWED_CHARACTERS = "psd 0123456789";
    private static final Character[] MANDATORY_LETTERS = {'p', 's', 'd'};
    private static final Character SPACE = ' ';
    private static final int LETTER_NUMBER = 1;
    private static final int SPACE_NUMBER = 2;

    private final String currency;

    public OldPoundValidator(String currency) {
        this.currency = currency;
    }

    public void validateCurrencyString() {
        checkEmptyOrNull();
        validateAllowedCharacters();
        validateSymbolsQuantity();
        validateCurrencyValue();
    }

    private void checkEmptyOrNull() {
        if (currency == null || currency.isBlank())
            throw new IllegalArgumentException("currency cannot be null or empty");
    }

    private void validateAllowedCharacters() {
        currency.chars()
                .mapToObj(c -> (char) c)
                .forEach(c -> {
                    if (!ALLOWED_CHARACTERS.contains(c.toString()))
                        throw new IllegalArgumentException(
                                String.format("%s contains invalid character %s", currency, c));
                });
    }

    private void validateSymbolsQuantity() {
        Map<Character, Integer> characterMap = new HashMap<>();
        for (Character c : currency.toCharArray()) {
            if(Character.isDigit(c)) continue;

            int chCount = characterMap.get(c) == null ? 0 : characterMap.get(c);
            characterMap.put(c, chCount + 1);
        }

        Stream.of(MANDATORY_LETTERS)
                .forEach(c -> checkSymbolsAndSpaces(characterMap.get(c), LETTER_NUMBER));

        checkSymbolsAndSpaces(characterMap.get(SPACE), SPACE_NUMBER);
    }

    private void checkSymbolsAndSpaces(Integer actual, int expected) {
        if (actual == null || actual != expected)
            throw new IllegalArgumentException(String.format("%s is in the wrong format", currency));
    }

    private void validateCurrencyValue() {
        String[] currencyParts = currency.split(" ");

        for(String part : currencyParts) {
            String currencyValue = part.substring(0, part.length() - 1);
            try {
                Integer.parseInt(currencyValue);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(String.format("%s currency value is not correct", currency));
            }
        }
    }


}
