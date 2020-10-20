package org.oldfashionpound;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class OldPoundCalculatorTest {

    OldPoundCalculator underTest;

    @BeforeEach
    void setUp() {
        underTest = new OldPoundCalculator();
    }

    @Test
    void isShouldAddTwoCurrencies() {
        // Given
        String currency = "5p 17s 8d";
        String toAdd = "3p 4s 10d";
        String expected = "9p 2s 6d";

        // When
        String result = underTest.add(currency, toAdd);

        // Then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "3p 4s 10d, 2p 12s 10d",
            "3p 4s 31d, 2p 11s 1d",
            "3p 19s 12d, 1p 17s 8d",
            "3p 18s 5d, 1p 19s 3d"
    })
    void isShouldSubtractCurrencies(String toSubtract, String expected) {
        // Given
        String currency = "5p 17s 8d";

        // When
        String result = underTest.subtract(currency, toSubtract);

        // Then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "5p 17s 8d, 2, 11p 15s 4d",
            "2p 8s 12d, 3, 7p 7s 0d",
    })
    void isShouldMultiplyCurrencies(String initialCurrency, int value, String expected) {
        // Given
        // When
        String result = underTest.multiply(initialCurrency, value);

        // Then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "5p 17s 8d, 3, 1p 19s 2d (2d)",
            "18p 16s 1d, 15, 1p 5s 0d (1s 1d)",
            "10p 8s 6d, 2, 5p 4s 3d"
    })
    void isShouldDivideCurrencies(String initialCurrency, int value, String expected) {
        // Given
        // When
        String result = underTest.divide(initialCurrency, value);

        // Then
        assertThat(result).isEqualTo(expected);
    }
}