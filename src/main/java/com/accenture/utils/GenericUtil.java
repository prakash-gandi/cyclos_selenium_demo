package com.accenture.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Formatter;

public class GenericUtil {
    public double extractAmount(String amount) throws ParseException {
        String cleanStr = amount.replace("$", "").replace(",", ""); // remove the dollar sign and commas
        return Double.parseDouble(cleanStr); // convert to a double
    }
    public String extractNumberFromString(String str){
        String orderNumber = str.replaceAll("[^0-9]", ""); // removes all non-numeric characters
        return orderNumber; // prints the extracted order number
    }
}
