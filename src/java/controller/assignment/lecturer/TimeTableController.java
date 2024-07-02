/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.assignment.lecturer;

import DTO.DayDTO;
import controller.authentication.BaseRequiredAuthenticationController;
import controller.authentication.authorization.BaseRBACController;

import dal.assignment.LessionDBContext;
import dal.assignment.TimeSlotDBContext;
import entity.Account;
import entity.Lession;
import entity.Role;
import entity.TimeSlot;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DateHelper;
import util.DateTimeHelper;
import static util.DateTimeHelper.convertUtilToSql;

/**
 *
 * @author sonnt
 */
public class TimeTableController extends BaseRBACController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account,ArrayList<Role> roles) throws ServletException, IOException {
        String arr[] = null;
        Date from = null;
        Date to = null;
        int selectYear = Integer.parseInt(req.getParameter("selectYear"));
        try {

            if (req.getParameter("action") != null && req.getParameter("action").equals("week")) {

                String selectDate = req.getParameter("selectDate");
                arr = selectDate.split(" ");

            } else {
                arr = DateHelper.workingDayOfYear(selectYear).get(0).getContent().split(" ");

            }
            req.setAttribute("a", arr);
            if (arr[0].split("/")[1].equals("12")) {

                from = new SimpleDateFormat("dd/MM/yyyy").parse(arr[0] + "/" + (selectYear - 1));

            } else {
                from = new SimpleDateFormat("dd/MM/yyyy").parse(arr[0] + "/" + selectYear);
            }
            to = new SimpleDateFormat("dd/MM/yyyy").parse(arr[2] + "/" + selectYear);

        } catch (ParseException ex) {
            Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<DayDTO> listW = DateHelper.workingDayOfYear(selectYear);
        List<String> list = new ArrayList<>();
        for (DayDTO w : listW) {
            list.add(w.getContent());
        }
        req.setAttribute("listW", list);
        LessionDBContext lessDB = new LessionDBContext();
        ArrayList<Lession> lessions = lessDB.getLessionBy(account.getUsername(), DateTimeHelper.convertUtilToSql(from), DateTimeHelper.convertUtilToSql(to));
        req.setAttribute("dates", DateTimeHelper.toList(DateTimeHelper.convertUtilToSql(from), DateTimeHelper.convertUtilToSql(to)));
        req.setAttribute("year", LocalDate.now().getYear());
        req.setAttribute("selectYear", selectYear);
        req.setAttribute("selectDate", req.getParameter("selectDate"));
        req.setAttribute("from", from);
        req.setAttribute("to", to);
        req.setAttribute("slots", new TimeSlotDBContext().list());
        req.setAttribute("lessions", lessions);
        req.getRequestDispatcher("../view/lecturer/timetable.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account,ArrayList<Role> roles) throws ServletException, IOException {

        TimeSlotDBContext timeDB = new TimeSlotDBContext();
        ArrayList<TimeSlot> slots = timeDB.list();
        int year = LocalDate.now().getYear();
        req.setAttribute("selectYear", year);
        String raw_from = req.getParameter("from");
        String raw_to = req.getParameter("to");
        Date from = null;
        Date to = null;
        java.util.Date today = new java.util.Date();
        List<DayDTO> listW = DateHelper.workingDayOfYear(year);
        List<String> list = new ArrayList<>();
        for (DayDTO w : listW) {
            list.add(w.getContent());
        }
        req.setAttribute("listW", list);
        String[] arr = DateHelper.workingDayOfYear(year).get(0).getContent().split(" ");
        try {
            if (arr[0].split("/")[1].equals("12")) {
                from = new SimpleDateFormat("dd/MM/yyyy").parse(arr[0] + "/" + (year - 1));
            } else {
                from = new SimpleDateFormat("dd/MM/yyyy").parse(arr[0] + "/" + year);
            }
            to = new SimpleDateFormat("dd/MM/yyyy").parse(arr[2] + "/" + year);
        } catch (ParseException ex) {
            Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, ex);
        }

        LessionDBContext lessDB = new LessionDBContext();
        ArrayList<Lession> lessions = lessDB.getLessionBy(account.getUsername(), DateTimeHelper.convertUtilToSql(from), DateTimeHelper.convertUtilToSql(to));
        req.setAttribute("dates", DateTimeHelper.toList(DateTimeHelper.convertUtilToSql(from), DateTimeHelper.convertUtilToSql(to)));
        req.setAttribute("year", year);
        req.setAttribute("from", from);
        req.setAttribute("to", to);
        req.setAttribute("slots", slots);
        req.setAttribute("lessions", lessions);
        req.getRequestDispatcher("../view/lecturer/timetable.jsp").forward(req, resp);
    }


}
