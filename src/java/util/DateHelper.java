/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import DTO.DayDTO;
import DTO.LessionDTO;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class DateHelper {

    public static List<DayDTO> workingDayOfYear(int year) {
        List<DayDTO> list = new ArrayList<>();
        int week = 1;
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        calendar.set(Calendar.YEAR, year);
        LocalDate date = LocalDate.of(year, 1, calendar.get(Calendar.DAY_OF_WEEK)); // Java 8 date
        int initialWeekOfyear = date.get(WeekFields.of(Locale.US).weekOfWeekBasedYear());
        int weekOfYear = 0;
        StringBuilder lastDate = new StringBuilder();
        int lastDay = 0;
        do {
            LocalDate firstDayOfWeek = date.with(WeekFields.of(Locale.ENGLISH).dayOfWeek(), calendar.get(Calendar.DAY_OF_WEEK));
            date = date.plusWeeks(1);
            LocalDate lastWorkingDayOfWeek = date.with(WeekFields.of(Locale.ENGLISH).dayOfWeek(), 1L);
            StringBuilder result = new StringBuilder();
            result.append(firstDayOfWeek.format(DateTimeFormatter.ofPattern("dd/MM")));
            result.append(" to ");
            result.append(lastWorkingDayOfWeek.format(DateTimeFormatter.ofPattern("dd/MM")));
            DayDTO dayDTO = new DayDTO(weekOfYear,result.toString(), getDateBetween(firstDayOfWeek,lastWorkingDayOfWeek));
            list.add(dayDTO);
            weekOfYear++;
            System.out.println(result.toString());
        } while ((weekOfYear = date.get(WeekFields.of(Locale.ENGLISH).weekOfWeekBasedYear())) != initialWeekOfyear);
        return list;
        
        
    }

    public static void main(String[] args) {
        for (DayDTO dayDTO : new DateHelper().workingDayOfYear(2023)) {
            System.out.println(dayDTO.getContent());
        }
       
    }

    public static List<LessionDTO> getDateBetween(LocalDate start, LocalDate end) {
        List<LessionDTO> totalDates = new ArrayList<>();
        while (!start.isAfter(end)) {
            totalDates.add(new LessionDTO(start, null));
            start = start.plusDays(1);
        }
        return totalDates;
    }

    public static int getWeek(List<DayDTO> list, LocalDate date) {
        for (DayDTO i : list) {
            if (i.isSelected(date)) {
                return i.getWeek();
            }
        }
        return -1;
    }

    public static int getIndexByWeek(List<DayDTO> list, int week) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getWeek() == week) {
                return i;
            }
        }
        return -1;
    }
}
