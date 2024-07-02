/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.assignment.lecturer;

import controller.authentication.BaseRequiredAuthenticationController;
import controller.authentication.authorization.BaseRBACController;
import dal.assignment.SubjectDBContext;
import entity.Account;
import entity.Assessment;
import entity.Role;
import entity.SubjectDTO;
import entity.SubmitMarkDTO;
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
public class TeacherGradingController extends BaseRBACController {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account,ArrayList<Role> roles) throws ServletException, IOException {

        String[] scores = req.getParameterValues("scores");
        List<SubmitMarkDTO> listSubmit = new ArrayList<>();
        String exID = scores[0].split(":")[0];
        for (int i = 0; i < scores.length; i++) {
            String stuID = scores[i].split(":")[1];
            String gradeID = "Grade-" + exID + "-" + stuID;
            String score = req.getParameter("score:" + exID + ":" + stuID);
            String comment = req.getParameter("comment:" + exID + ":" + stuID);
            SubmitMarkDTO dto = new SubmitMarkDTO(gradeID, exID, stuID, score, account.getUsername(), comment);
            listSubmit.add(dto);
        }
        new SubjectDBContext().updateMark(listSubmit);

        resp.sendRedirect("grade");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account,ArrayList<Role> roles) throws ServletException, IOException {
        List<SubjectDTO> listS = new SubjectDBContext().listAllSubjectOfLecture(account.getUsername());
        List<Assessment> listA = new ArrayList<>();
        String subID = "";
        String groupID = "";
        if (req.getParameter("group") != null) {
            groupID = req.getParameter("group").split(":")[0];
            subID = req.getParameter("group").split(":")[1];

        } else {

            groupID = listS.get(0).getgID();
            subID = listS.get(0).getSubID();

        }
        
        if (req.getParameter("assesment") != null) {
            req.setAttribute("listSt", new SubjectDBContext().listAllMarkOfStudent(groupID, req.getParameter("assesment")));
        } else {
            req.setAttribute("listSt", new SubjectDBContext().listAllMarkOfStudent(groupID, new SubjectDBContext().listAssessment(subID).get(0).getAssID()));
        }

        listA = new SubjectDBContext().listAssessment(subID);
        req.setAttribute("subID", subID);
        req.setAttribute("gID", groupID);
        req.setAttribute("ass", req.getParameter("assesment"));
        req.setAttribute("listS", listS);
        req.setAttribute("listA", listA);
        req.getRequestDispatcher("../view/lecturer/grading.jsp").forward(req, resp);

    }

}
