package org.oldfashionpound;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}