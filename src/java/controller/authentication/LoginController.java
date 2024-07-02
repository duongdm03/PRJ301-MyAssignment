/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.authentication;

import dal.AccountDBContext;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
public class LoginController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       request.getRequestDispatcher("view/authentication/login.jsp").forward(request, response);

    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String cid = (String) request.getSession().getAttribute("cid");
        AccountDBContext db = new AccountDBContext();
        Account account = db.getAccountByUsernamePassword(username, password,cid);
        if(account != null)
        {
            HttpSession session = request.getSession();
            
            //implement remember me!
            Cookie c_username = new Cookie("username", username);
            c_username.setMaxAge(3600*24*7);
            Cookie c_password = new Cookie("password", password);
            c_password.setMaxAge(3600*24*7);
            Cookie c_cid = new Cookie("cid", cid);
            c_cid.setMaxAge(3600*24*7);
            response.addCookie(c_username);
            response.addCookie(c_password);
            response.addCookie(c_cid);
            
            session.setAttribute("account", account);
            if(account.isIsTeacher() == true)
                response.sendRedirect(request.getContextPath() + "/view/authentication/home.jsp");
            else{
                response.sendRedirect(request.getContextPath() + "/view/authentication/home2.jsp");
            }
            //response.getWriter().println("login successful! welcome " + account.getDisplayname());
        }
        else
        {
            
            request.setAttribute("err", "Username or Password invalid !");
            //response.sendRedirect(request.getContextPath() + "/view/authentication/loginCampus.jsp");
            //response.getWriter().println("login failed!");
            request.getRequestDispatcher("logincampus").forward(request, response);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
