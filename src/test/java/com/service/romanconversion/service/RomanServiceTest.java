package com.service.romanconversion.service;

import com.service.romanconversion.model.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for Roman Service Class
 */
@SpringBootTest
@AutoConfigureMockMvc
class RomanServiceTest {

    @Autowired
    private RomanService romanService;

    /**
     * Test to see if the RomanLiteralMap is indeed populated.
     */
    @Test
    void test_RomanServicePopulateMap() {
        romanService.executeOnStartUp();
        assertFalse(romanService.getRomanLiteralMap().isEmpty());
    }

    /**
     * Test the values in Result Object
     */
    @Test
    void test_RomanServiceRomanConversion() {
        Result result = romanService.getRomanValueForInteger(54);
        assertEquals("LIV", result.getOutput());
    }
}
