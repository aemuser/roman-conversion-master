package com.service.romanconversion.controller;
import com.service.romanconversion.model.Result;
import com.service.romanconversion.service.RomanService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Controller Class that has all the mappings defined
 */
@Slf4j
@RestController
@Validated
@NoArgsConstructor
public class RomanController {

    @Autowired
    private RomanService romanService;

    public void RomanController(final RomanService romanService) {
        this.romanService = romanService;
    }

    /**
     * getRomanValue - takes an input (integer), returns the corresponding Roman Numeral
     * @param query
     * @return
     */
    @GetMapping(path = "/romannumeral")
    public @ResponseBody ResponseEntity getRomanValue(final @RequestParam( name = "query", required = true)
                                                          @Min(value = 1, message = "Input Param Must Be More Than 1")
                                                          @Max(value = 3999, message = "Input Param Must Be Less" +
                                                                  "Than 3999") int query) {
        log.debug("Calling RomanService getRomanValueForInteger");
        return new ResponseEntity<Object>(romanService.getRomanValueForInteger(query), HttpStatus.OK);
    }

    @ExceptionHandler({ConstraintViolationException.class, Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    private String handleConstraintViolationException(ConstraintViolationException e) {
        log.error("Constraint Violation Exception ", e);
        return "There has been an error while processing your request. Please verify that the input parameter is an " +
                "integer between 1 and 3999";
    }
}
