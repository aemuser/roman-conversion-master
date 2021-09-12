package com.service.romanconversion.model;

import lombok.*;
import java.io.Serializable;

/**
 Result Model Class
 */

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Result implements Serializable {
    private int input;
    private String output;

    /**
     * Constructor
     * @param input
     * @param output
     */
    public Result(final int input, final String output) {
        this.input = input;
        this.output = output;
    }
}
