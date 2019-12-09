/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author Elizabeth.Bourke
 */
public class UserController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            session.setAttribute("user", user);
        }

        String menu = request.getParameter("menu");

        switch (menu) {
            case "Login":
                gotoPage("/login.jsp", request, response);
                break;
            case "SignUp":
                gotoPage("/AddUser.jsp", request, response);
                break;
            case "Save":
                ProcessSave(request, session);
                gotoPage("/UserHomePage.jsp", request, response);
                break;
            case "Logout":
                gotoPage("/index.jsp", request, response);
                break;
            case "Process Login":
                boolean validLogin = ProcessLogin(request, session);

                if (!validLogin) {
                    String message = "invalid logon details.. try again";
                    session.setAttribute("message", message);
                    gotoPage("/login.jsp", request, response);
                } else {

                    gotoPage("/UserHomePage.jsp", request, response);
                }
                break;
            case "Update User Details":
                gotoPage("/UpdateUser.jsp", request, response);
                break;
            case "Save User Details":
                //get new user details from user
                String full_name = request.getParameter("full_name");
                String email = request.getParameter("email");
                String k_number = request.getParameter("k_number");
                int contact_number = Integer.parseInt(request.getParameter("contact_number"));
                String profile_image = request.getParameter("profile_image");
                String password = request.getParameter("password");
                String course_code = request.getParameter("course_code");
                int course_year = Integer.parseInt(request.getParameter("course_year"));
                
                //get userid
                User olduserdetails = (User) session.getAttribute("user");
                User us = new User(olduserdetails.getUser_id(),full_name, email, k_number, contact_number, profile_image, password, course_code, course_year);
                //save user details to database
                us.updateUser();
                
                //update session object (user) with new deatils
                session.setAttribute("user", us);
                gotoPage("/UserHomePage.jsp", request, response);
                break;

            default:
                gotoPage("/invalid.jsp", request, response);
                break;
        }
    }

    private boolean ProcessLogin(HttpServletRequest request, HttpSession session) {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User us = new User(email, password);
        us.Login(email, password);
        session.setAttribute("user", us);

        if (us.getUser_id() != 0) {
            return true;
        } else {
            return false;
        }

    }

    private void ProcessSave(HttpServletRequest request, HttpSession session) {
        String full_name = request.getParameter("full_name");
        String email = request.getParameter("email");
        String k_number = request.getParameter("k_number");
        int contact_number = Integer.parseInt(request.getParameter("contact_number"));
        String profile_image = request.getParameter("profile_image");
        String password = request.getParameter("password");
        String course_code = request.getParameter("course_code");
        int course_year = Integer.parseInt(request.getParameter("course_year"));

        User us = new User(full_name, email, k_number, contact_number, profile_image, password, course_code, course_year);
        us.saveToDatabase();

        session.setAttribute("user", us);
        System.out.println("userid" + us.getUser_id());
    }

    private void gotoPage(String url,
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
