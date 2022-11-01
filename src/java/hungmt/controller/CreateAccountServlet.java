/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungmt.controller;

import hungmt.registration.RegistrationCreateError;
import hungmt.registration.RegistrationDAO;
import hungmt.registration.RegistrationDTO;
import hungmt.utils.MyApplicationConstant;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PC
 */
public class CreateAccountServlet extends HttpServlet {

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
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName  = request.getParameter("txtFullname");
        RegistrationCreateError errors = new RegistrationCreateError();
        ServletContext context = request.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        String url = siteMaps.getProperty(MyApplicationConstant.CreateAccountFeatures.ERROR_PAGE);
        boolean fountErr = false;
        try  {
            //1.  Check all user's constrainst
            if (username.trim().length() < 6 || username.trim().length() > 20) {
                fountErr = true;
                errors.setUsernameLenghtErr("Username required input from 6 to 20 characters");
            }
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                fountErr = true;
                errors.setPasswordLenghtErr("Password required input from 6 to 30 characters");
            }else if (!confirm.trim().equals(password)) {
                fountErr = true;
                errors.setConfirmNoMatched("Confirm must match password");
            }
                if (fullName.trim().length() < 2 || fullName.trim().length() > 50) {
                fountErr = true;
                errors.setFullNameLenghtErr("Full name required input from 2 to 50 characters");
            }
                
            if (fountErr) {
                //2.store errors and fw to error page
                request.setAttribute("CREATE_ERROR", errors);
            } else {
                //2. call DAO
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO dto = new RegistrationDTO(username, password, fullName, false);
                boolean result = dao.createAccount(dto);
                if (result) {
                    url = siteMaps.getProperty(MyApplicationConstant.CreateAccountFeatures.LOGIN_PAGE);
                }//insert action is successfully
                
            }
            
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            if (msg.contains("duplicate")) {
                errors.setUsernameIsExisted(username + " is existed");
                request.setAttribute("CREATE_ERROR", errors);
            }
           log("CreateAccountServlet_SQL " + msg);
        } catch (NamingException ex) {
            log("CreateAccountServlet_Naming " + ex.getMessage());
        }finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
