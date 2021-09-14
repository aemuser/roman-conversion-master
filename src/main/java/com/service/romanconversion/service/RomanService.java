package com.service.romanconversion.service;

import com.service.romanconversion.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.SortedMap;
import java.util.TreeMap;

/**
  This service class hosts the logic tied to Roman conversion
 */
@Service
@Slf4j
public class RomanService {

    private final TreeMap<Integer,String> romanLiteralMap =new TreeMap<>();

    /**
     * Function To Convert Input(integer) to Roman Numeral
     * @param inputVal integer
     * @return Result
     */
    @Cacheable("results")
    public Result getRomanValueForInteger(final int inputVal) throws NumberFormatException {
        log.debug("Calling getRomanValueForInteger for input {}", inputVal);
        int inputNum=inputVal;
        StringBuilder romanLiteral= new StringBuilder();
        while(inputNum>0) {
            int key= romanLiteralMap.floorKey(inputNum);
            romanLiteral.append(romanLiteralMap.get(key));
            inputNum=inputNum-key;
        }
        log.debug("getRomanValueForInteger :: Input integer value {} ", romanLiteral);
        return new Result(inputVal,romanLiteral.toString());
    }

    /**
     * This method will run once on startup to populate the romanLiteralMap
     * which will be used a reference
     */
    @EventListener(ApplicationReadyEvent.class)
    public void executeOnStartUp() {
        romanLiteralMap.put(1,"I");
        romanLiteralMap.put(4,"IV");
        romanLiteralMap.put(5,"V");
        romanLiteralMap.put(9,"IX");
        romanLiteralMap.put(10,"X");
        romanLiteralMap.put(40,"XL");
        romanLiteralMap.put(50,"L");
        romanLiteralMap.put(90,"XC");
        romanLiteralMap.put(100,"C");
        romanLiteralMap.put(400,"CD");
        romanLiteralMap.put(500,"D");
        romanLiteralMap.put(900,"CM");
        romanLiteralMap.put(1000,"M");
        log.debug("executeOnStartUp :: romanLiteralMap has been populated {}", romanLiteralMap.size());
    }

    /**
     * Getter
     * @return romanLiteralMap
     */
    public SortedMap<Integer, String> getRomanLiteralMap() {
        return romanLiteralMap;
    }
}
