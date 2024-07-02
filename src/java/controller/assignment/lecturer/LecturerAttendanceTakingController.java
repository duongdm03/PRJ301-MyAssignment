/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.assignment.lecturer;

import controller.authentication.BaseRequiredAuthenticationController;
import controller.authentication.authorization.BaseRBACController;

import dal.assignment.LessionDBContext;
import entity.Account;
import entity.Attendence;
import entity.Lession;
import entity.Role;
import entity.Student;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sonnt
 */
public class LecturerAttendanceTakingController extends BaseRBACController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account,ArrayList<Role> roles) throws ServletException, IOException {
        String leid = req.getParameter("id");
        StudentDBContext db = new StudentDBContext();
        ArrayList<Student> students = db.getStudentsByLessionId(leid);
        Lession lession = new Lession();
        lession.setId(leid);
        ArrayList<Attendence> atts = new ArrayList<>();
        List<Attendence> finalList = new ArrayList<>();
        for (Student student : students) {
            Attendence att = new Attendence();
            att.setStudent(student);
            att.setLession(lession);
            att.setDescription(req.getParameter("description"+student.getId()));
            att.setPresent(req.getParameter("present"+student.getId()).equals("yes"));
            atts.add(att);
        }
        LessionDBContext lesDB = new LessionDBContext();
        finalList = new LessionDBContext().getListAttendance(leid, atts);
        lesDB.takeAttendance(leid, finalList);
        resp.sendRedirect("atts?id="+leid);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account,ArrayList<Role> roles) throws ServletException, IOException {
        String leid =req.getParameter("id");
        LessionDBContext lesDB = new LessionDBContext();
        ArrayList<Attendence> atts = lesDB.getAttendencesByLession(leid);
        req.setAttribute("atts", atts);
        req.getRequestDispatcher("../view/lecturer/attent.jsp").forward(req, resp);
        
    }
   
    

}
