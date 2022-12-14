/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungmt.controller;

import hungmt.registration.RegistrationDAO;
import hungmt.utils.MyApplicationConstant;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PC
 */
public class UpdateAccountServlet extends HttpServlet {
    
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
        String urlRewriting = MyApplicationConstant.UpdateAccountFeatures.ERROR_PAGE;
        String username = request.getParameter("txtUsername").trim();
        String password = request.getParameter("txtPassword").trim();
        boolean isAdmin = (request.getParameter("chkAdmin") != null);
        String searchValue = request.getParameter("lastSearchValue");
        try  {
            RegistrationDAO dao = new RegistrationDAO();
            boolean result = dao.updateAccount(username, password, isAdmin);
            if (result) {
                 urlRewriting = "searchLastnameServlet"
                                    + "?btAction=Search"
                                    + "&txtSearchValue=" + searchValue;
            }
        }
        catch (ClassNotFoundException ex) {
            log ("UpdateAccountServlet ClassNotFound " + ex.getMessage());
        } catch (SQLException ex) {
            log ("UpdateAccountServlet SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log ("UpdateAccountServlet Naming " + ex.getMessage());
        }        
        finally {
            response.sendRedirect(urlRewriting);
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
