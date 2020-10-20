package org.oldfashionpound.currency;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class OldPoundCurrencyTest {

    OldPoundCurrency underTest;

    @Test
    void isShouldReturnTheFormattedCorrectAmountOfOldPoundsCurrency() {
        // Given
        int pounds = 1;
        int shillings = 5;
        int pence = 10;
        String expected = "1p 5s 10d";

        // When
        underTest = new OldPoundCurrency(pounds, shillings, pence);

        // Then
        assertThat(underTest.getAmount()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "11, 0p 0s 11d",
            "15, 0p 1s 3d",
            "45, 0p 3s 9d",
            "79, 0p 6s 7d",
            "240, 1p 0s 0d",
            "267, 1p 2s 3d"
    })
    void isShouldNormalizePence(int testPence, String expected) {
        // Given
        int pounds = 0;
        int shillings = 0;

        // When
        underTest = new OldPoundCurrency(pounds, shillings, testPence);

        // Then
        assertThat(underTest.getAmount()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "20, 1p 0s 0d",
            "35, 1p 15s 0d",
            "45, 2p 5s 0d"
    })
    void isShouldNormalizeShillings(int testShillings, String expected) {
        // Given
        int pounds = 0;
        int pence = 0;

        // When
        underTest = new OldPoundCurrency(pounds, testShillings, pence);

        // Then
        assertThat(underTest.getAmount()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "35, 20, 1p 16s 8d",
            "200, 53, 10p 4s 5d"
    })
    void isShouldNormalizeShillingsAndPence(int testShillings, int testPence, String expected) {
        // Given
        int pounds = 0;

        // When
        underTest = new OldPoundCurrency(pounds, testShillings, testPence);

        // Then
        assertThat(underTest.getAmount()).isEqualTo(expected);
    }

    @Test
    void isShouldAdd() {
        // Given
        OldPoundCurrency currency = new OldPoundCurrency(5, 17, 8);
        OldPoundCurrency amountToAdd = new OldPoundCurrency(3, 4, 10);
        String expected = "9p 2s 6d";

        // When
        currency.add(amountToAdd);
        String result = currency.getAmount();

        // Then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "3, 4, 10, 2p 12s 10d",
            "3, 4, 31, 2p 11s 1d",
            "3, 19, 12, 1p 17s 8d",
            "3, 18, 5, 1p 19s 3d"
    })
    void isShouldSubtract(int pounds, int shillings, int pence, String expected) {
        // Given
        OldPoundCurrency currency = new OldPoundCurrency(5, 17, 8);
        OldPoundCurrency amountToSubtract = new OldPoundCurrency(pounds, shillings, pence);

        // When
        currency.subtract(amountToSubtract);
        String result = currency.getAmount();

        // Then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "5, 17, 8, 2, 11p 15s 4d",
            "2, 8, 12, 3, 7p 7s 0d",
    })
    void isShouldMultiply(int pounds, int shillings, int pence, int value, String expected) {
        // Given
        OldPoundCurrency currency = new OldPoundCurrency(pounds, shillings, pence);

        // When
        currency.multiply(value);
        String result = currency.getAmount();

        // Then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "5, 17, 8, 3, 1p 19s 2d, (2d)",
            "18, 16, 1, 15, 1p 5s 0d, (1s 1d)",
            "10, 8, 6, 2, 5p 4s 3d, ()"
    })
    void isShouldDivide(
            int pounds, int shillings, int pence, int value, String expected, String reminder) {

        // Given
        OldPoundCurrency currency = new OldPoundCurrency(pounds, shillings, pence);

        // When
        currency.divide(value);
        String result = currency.getAmount();
        String reminderResult = currency.getFormattedReminder();

        // Then
        assertThat(result).isEqualTo(expected);
        assertThat(reminderResult).isEqualTo(reminder);
    }
}