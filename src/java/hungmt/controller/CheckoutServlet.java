/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungmt.controller;

import hungmt.cart.CartObject;
import hungmt.order.OrderDAO;
import hungmt.orderdetail.OrderDetailDAO;
import hungmt.orderdetail.OrderDetailDTO;
import hungmt.product.ProductDAO;
import hungmt.product.ProductDTO;
import hungmt.utils.MyApplicationConstant;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
public class CheckoutServlet extends HttpServlet {

   

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
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    Map<String, Integer> cartItems = cart.getItems();
                    float totalSum = 0;
                    if (cartItems != null) {
                        List<OrderDetailDTO> checkoutList = null;
                        ProductDAO productDAO = new ProductDAO();
                        for (String key : cartItems.keySet()) {
                            ProductDTO productDTO = productDAO.getBookBySKU(key);
                            String sku = productDTO.getSku();
                            int quantity = cartItems.get(key);
                            float price = productDTO.getPrice();
                            float total = quantity * price;
                            if (checkoutList == null) {
                                checkoutList = new ArrayList<>();
                            }
                            checkoutList.add(new OrderDetailDTO(sku, quantity, price, total));
                            totalSum += total;
                        }
                        OrderDAO orderDAO = new OrderDAO();
                        int identity = orderDAO.insertNewRecord(totalSum);
                        if (identity > 0) {
                            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                            for (OrderDetailDTO dto : checkoutList) {
                                dto.setOrderID(identity);
                                orderDetailDAO.insertNewRecord(dto);
                            }
                        }
                        session.removeAttribute("CART");
                    }
                }
            }
        } catch (NamingException ex) {
            log("CheckoutServlet  Naming" + ex.getMessage());
        } catch (SQLException ex) {
            log("CheckoutServlet  SQL" + ex.getMessage());
        } finally {
            response.sendRedirect(MyApplicationConstant.CheckoutFeatures.VIEW_SHOPPING_PAGE);
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
