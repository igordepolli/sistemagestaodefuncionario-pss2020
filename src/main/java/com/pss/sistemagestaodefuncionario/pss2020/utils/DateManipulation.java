package com.pss.sistemagestaodefuncionario.pss2020.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateManipulation {

    public static LocalDate stringToLocalDate(String dateString) throws ParseException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate ld = LocalDate.parse(dateString, dtf);
       
        return ld;
    }

    public static String localDateToString(LocalDate date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        return dateFormat.format(date);
    }

}
