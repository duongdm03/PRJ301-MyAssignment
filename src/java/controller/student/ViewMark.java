/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.student;

import controller.authentication.BaseRequiredAuthenticationController;
import controller.authentication.authorization.BaseRBACController;
import dal.assignment.SubjectDBContext;
import entity.Account;
import entity.Role;
import entity.StudentShowMarkDTO;
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
 * @author ASUS
 */
public class ViewMark extends BaseRBACController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account,ArrayList<Role> roles) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account,ArrayList<Role> roles) throws ServletException, IOException {
        
        List<String> listS = new SubjectDBContext().getSubjectOfStudent(account.getUsername());
        req.setAttribute("listS", listS);
        req.setAttribute("sub", req.getParameter("sub"));
        if (req.getParameter("sub") != null) {
            List<StudentShowMarkDTO> listA = new SubjectDBContext().result(req.getParameter("sub"), account.getUsername());
            req.setAttribute("average", new SubjectDBContext().average(listA));
            req.setAttribute("listA", listA);

        } else {
            List<StudentShowMarkDTO> listA = new SubjectDBContext().result(listS.get(0), account.getUsername());
            req.setAttribute("listA", listA);
            req.setAttribute("average", new SubjectDBContext().average(listA));
        }

        req.getRequestDispatcher("../view/viewmark.jsp").forward(req, resp);
    }

}
