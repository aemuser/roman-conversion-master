package com.service.romanconversion.controller;

import com.service.romanconversion.service.RomanService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


/**
 * Controller Class that has the service endpoints and delegates request
 */
@Slf4j
@RestController
@Validated
@NoArgsConstructor
@EnableCaching
public class RomanController {

    @Autowired
    private RomanService romanService;

    /**
     * getRomanValue - takes an input (integer), returns the corresponding Roman Numeral
     * @param query input param
     * @return ResponseEntity
     */
    @GetMapping(path = "/romannumeral")
    public @ResponseBody ResponseEntity<Object> getRomanValue(final @RequestParam( name = "query") @Min(value = 1)
                                                          @Max(value = 3999) int query) {
        log.debug("Calling RomanService getRomanValueForInteger");
        return new ResponseEntity<>(romanService.getRomanValueForInteger(query), HttpStatus.OK);
    }

    /**
     * Handle ConstraintViolationException Exception, send back meaningful message
     * @param exception ConstraintViolationException
     * @return String errorMessage
     */
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    private String handleConstraintViolationException(ConstraintViolationException exception) {
        log.error("ConstraintViolationException ::", exception);
        return "Invalid Input. Please verify that the input parameter is an integer between 1 and 3999";
    }

    /**
     * Handle NumberFormatException, send back a meaningful message
     * @param exception NumberFormatException
     * @return errorMessage String
     */
    @ExceptionHandler({NumberFormatException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    private String handleNumberFormatException(NumberFormatException exception) {
        log.error("NumberFormatException ::", exception);
        return "Invalid Input. Please verify that the input parameter is an integer between 1 and 3999";
    }
}
