/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import discount_classes.DiscountCodeEntity;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdbc_discount.DAO_Discount;
import simplejdbc.DAOException;
import simplejdbc.DataSourceFactory;

/**
 *
 * @author vanat
 */
@WebServlet(name = "DiscountCodeController", urlPatterns = {"/DiscountCodeController"})
public class DiscountCodeController extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            DAO_Discount dao = new DAO_Discount(DataSourceFactory.getDataSource());
            
            String action = request.getParameter("action");
            String code = request.getParameter("code");
            String taux = request.getParameter("taux");
            List<DiscountCodeEntity> discountCodes = dao.getCode();
            request.setAttribute("discountCodes",discountCodes);
            if (action != null) {
                if (action.equals("ADD")) {
                    request.setAttribute("code",code);
                    request.setAttribute("taux",taux);
                    dao.addCode(code, Float.parseFloat(taux));
                    discountCodes = dao.getCode();
                    request.setAttribute("discountCodes",discountCodes);
                    request.setAttribute("action",null);

                    
                } else if(action.equals("DELETE")) {
                    request.setAttribute("code",code);
                    dao.suppCode(code);
                    discountCodes = dao.getCode();
                    request.setAttribute("discountCodes",discountCodes);
                    request.setAttribute("action",null);
                }
            }
            request.getRequestDispatcher("vue/discountcode.jsp").forward(request, response);
        } catch(DAOException e) {
            
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(DiscountCodeController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(DiscountCodeController.class.getName()).log(Level.SEVERE, null, ex);
        }
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





























