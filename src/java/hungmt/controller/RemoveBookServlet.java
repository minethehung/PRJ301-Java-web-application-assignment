/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungmt.controller;

import hungmt.cart.CartObject;
import hungmt.utils.MyApplicationConstant;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PC
 */
public class RemoveBookServlet extends HttpServlet {
   
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
        try  {
           //1. cust goes to his/her cart place
            HttpSession session = request.getSession(false);
            if (session != null) {
                //2.cust takes his/her cart
                CartObject cart = (CartObject)session.getAttribute("CART");
                if (cart != null) {
                    //3.cust check exiested items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        //4. Cust take selected item
                        String[] selectedItems = request.getParameterValues("chkItem");
                        if (selectedItems != null) {
                            //5. Cust remove items from cart
                            for (String item : selectedItems) {
                                cart.removeBookFromCart(item);
                            }//end reomve all selected items from cart
                            session.setAttribute("CART", cart);
                        }// cust has already chosen items
                    }//end items has existed
                }// cart has existed
            }//cart place has exist
        }finally {
            //6.refrest view cart - call view cart again
            response.sendRedirect(MyApplicationConstant.RemoveBookFeatures.VIEW_CART_SERVLET);
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
