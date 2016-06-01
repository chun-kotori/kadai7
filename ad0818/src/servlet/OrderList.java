/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.*;
import static java.lang.System.out;
import java.sql.*;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.getItemListLogic;
import model.Items;

/**
 *
 * @author HikariHasegawa
 */
//@WebServlet(name = "OrderList", urlPatterns = {"/OrderList"})
public class OrderList extends HttpServlet {
    private static final long serialVirsionUID = 1L;
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
        //request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrderList</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderList at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        getItemListLogic getItemListLogic=new getItemListLogic();
        List<Items> Items=getItemListLogic.execute();
        //request.setAttribute("Items", Items);
        //System.out.println("getget");

        Connection conn=null;
        String url="jdbc:derby://localhost:1527/db0818";
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            conn=DriverManager.getConnection(url,"db","db");
            
            Statement stmt=conn.createStatement();
            String sql="SELECT * FROM ITEM";
            ResultSet rs=stmt.executeQuery(sql);
            
            while(rs.next()){
                String itemId=rs.getString("itemId");
                String itemName=rs.getString("itemName");
                int price=rs.getInt("price");
                
                session.setAttribute("Items", Items);
            }
            //session.setAttribute("userId", "a0001");
            System.out.println("★userId:"+session.getAttribute("userId"));
            rs.close();
            stmt.close();
        }catch(Exception e){
            out.println(e);
        }
        
        RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/orderList.jsp");
        dispatcher.forward(request,response);
        //processRequest(request, response);
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
        request.setCharacterEncoding("utf-8");

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
