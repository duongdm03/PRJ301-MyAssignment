/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author sonnt
 */
public class DateTimeHelper {
    
 
     public static Date getBeginningOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // Set the first day of the week (Monday in this case)
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        // Set the calendar to the beginning of the week
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        // Reset hour, minutes, seconds and millis
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
     
    public static Date addDaysToDate(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days); // Adds the specified number of days to the date
        return calendar.getTime();
    }
    public static void main(String[] args) throws ParseException {
        System.out.println(DateTimeHelper.toList(convertUtilToSql(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2024")),convertUtilToSql( new SimpleDateFormat("dd/MM/yyyy").parse("07/01/2024"))));
    }
    public static ArrayList<java.sql.Date> toList(java.sql.Date from, java.sql.Date to)
    {
        ArrayList<java.sql.Date> list = new ArrayList<>();
        Date temp = from;
        while(!temp.after(to))
        {
            list.add(convertUtilToSql(temp));
            temp = addDaysToDate(temp, 1);
        }
        return list;
    }
     
    public static java.sql.Date convertUtilToSql(Date utilDate) {
        if (utilDate != null) {
            return new java.sql.Date(utilDate.getTime());
        }
        return null;
    }
    public static String getWeekdayFromDate(Date date) {
        // Define an array of weekday names
        String[] weekdays = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        
        // Create a Calendar instance and set it to the given date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        
        // Get the weekday index (0 for Sunday, 1 for Monday, ..., 6 for Saturday)
        int weekdayIndex = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        
        // Return the weekday name corresponding to the weekday index
        return weekdays[weekdayIndex];
    }
    
    

}
