package edu.vistula.gieldabackend;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateValidator {

    public static boolean isValid(String date) {
        try {
            LocalDate.parse(date);
        }
        catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
