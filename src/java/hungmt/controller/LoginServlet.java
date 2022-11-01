/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungmt.controller;

import hungmt.registration.RegistrationDAO;
import hungmt.registration.RegistrationDTO;
import hungmt.utils.MyApplicationConstant;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PC
 */
public class LoginServlet extends HttpServlet {

    

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
        response.setContentType("text/html;charset=UTF-8");
        String button = request.getParameter("btAction");
        String url = MyApplicationConstant.LoginFeatures.INVALID_PAGE;
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        try {

            //1.Call model/DAO
            // New DAOObject then call method on DAO object
            RegistrationDAO dao = new RegistrationDAO();
            RegistrationDTO res = dao.checkLogin(username, password);
            //2.process result
            if (res != null) {
                url = MyApplicationConstant.LoginFeatures.SEARCH_PAGE;
                HttpSession session = request.getSession(true);
                session.setAttribute("USER", res);
//                Cookie cookie = new Cookie (username, password);
//                cookie.setMaxAge(60 * 3);
//                response.addCookie(cookie);

            }//end if user anthenticated

        } catch (SQLException ex) {
             log ("LoginServlet SQL " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log ("LoginServlet ClassNotFound " + ex.getMessage());
        } catch (NamingException ex) {
             log ("LoginServlet Naming " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
        }
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
