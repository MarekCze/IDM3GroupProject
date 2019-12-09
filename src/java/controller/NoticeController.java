/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Notices;
import model.User;

/**
 *
 * @author Elizabeth.Bourke
 */
public class NoticeController extends HttpServlet {

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
        if (menu == null) {
            menu = "home";
        }

        switch (menu) {

            case "home":

                Notices notices = new Notices();
                ArrayList<Notices> allnotices = new ArrayList<>();
                allnotices = notices.getAllNotices();
                session.setAttribute("allnotices", allnotices);
                gotoPage("/Homepage.jsp", request, response);
                break;
            case "Create Notice":
                gotoPage("/CreateNotice.jsp", request, response);
                break;
            case "Save Notice":
                notices = new Notices();
                ProcessSaveNotice(request, session);
                ArrayList<Notices> userNotices = new ArrayList<>();
                userNotices = notices.getUserNotices(user.getUser_id());
                session.setAttribute("userNotices", userNotices);
                gotoPage("/DisplayUserNotice.jsp", request, response);
                break;
            default:
                gotoPage("/invalid.jsp", request, response);
                break;
        }
    }
    
    private void ProcessSaveNotice(HttpServletRequest request, HttpSession session){
        String image = request.getParameter("image");
        String shortDescription = request.getParameter("shortDescription");
        String longDescription = request.getParameter("longDescription");
        User u = (User)session.getAttribute("user");
        int userId = u.getUser_id();
        
        Notices n = new Notices(image, shortDescription, longDescription, userId);
        n.saveToDB();
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
