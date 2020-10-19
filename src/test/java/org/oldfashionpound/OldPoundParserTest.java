package org.oldfashionpound;

import org.junit.jupiter.api.*;
import org.mockito.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;

class OldPoundParserTest {

    OldPoundParser underTest;

    @Mock
    OldPoundValidator oldPoundValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void isShouldReturnTheOldPoundCurrencyObject() {
        // Given
        String currency = "10p 20s 30d";
        OldPoundCurrency expected = new OldPoundCurrency(11, 2, 6);
        doNothing().when(oldPoundValidator).validateCurrencyString();

        underTest = new OldPoundParser(oldPoundValidator, currency);

        // When
        OldPoundCurrency oldPoundCurrency = underTest.parseCurrencyString();

        // Then
        assertThat(oldPoundCurrency).usingRecursiveComparison().isEqualTo(expected);
    }
}