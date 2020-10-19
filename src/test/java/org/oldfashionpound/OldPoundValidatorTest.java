package org.oldfashionpound;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OldPoundValidatorTest {

    OldPoundValidator underTest;

    @Test
    void isShouldPassTheValidation() {
        // Given
        String currency = "1p 1s 1d";
        underTest = new OldPoundValidator(currency);

        // When
        // Then
        underTest.validateCurrencyString();
    }

    @Test
    void isShouldThrowIfCurrencyIsEmpty() {
        // Given
        String currency = "";
        underTest = new OldPoundValidator(currency);

        // When
        // Then
        assertThatThrownBy(()-> underTest.validateCurrencyString())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("currency cannot be null or empty");
    }

    @Test
    void isShouldThrowIfCurrencyIsBlank() {
        // Given
        String currency = "      ";
        underTest = new OldPoundValidator(currency);

        // When
        // Then
        assertThatThrownBy(()-> underTest.validateCurrencyString())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("currency cannot be null or empty");
    }

    @Test
    void isShouldThrowIfCurrencyIsNull() {
        // Given
        String currency = null;
        underTest = new OldPoundValidator(currency);

        // When
        // Then
        assertThatThrownBy(()-> underTest.validateCurrencyString())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("currency cannot be null or empty");
    }

    @Test
    void isShouldThrowIfCurrencyContainsNotAllowedCharacters() {
        // Given
        String currency = "1p 1s 1z";
        underTest = new OldPoundValidator(currency);

        // When
        // Then
        assertThatThrownBy(()-> underTest.validateCurrencyString())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1p 1s 1z contains invalid character z");
    }

    @ParameterizedTest
    @CsvSource({
            "1p 1 1d",
            "1 1s 1d",
            "1p 1s 1",
            "1pp 1s 1d",
            "1d     1d 1d",
            "1p1s 1d",
    })
    void isShouldThrowIfCurrencyIsInTheWrongFormat(String currency) {
        // Given
        underTest = new OldPoundValidator(currency);

        // When
        // Then
        assertThatThrownBy(()-> underTest.validateCurrencyString())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(currency + " is in the wrong format");
    }

    @ParameterizedTest
    @CsvSource({
            "p1 1s 1d",
            "1p s1 1d",
            "1p 1s d1",
    })
    void isShouldThrowIfSymbolsAreNotInTheCorrectPosition(String currency) {
        // Given
        underTest = new OldPoundValidator(currency);

        // When
        // Then
        assertThatThrownBy(() -> underTest.validateCurrencyString())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(currency + " currency value is not correct");
    }
}