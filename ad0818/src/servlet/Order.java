/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.*;
import static java.lang.System.out;
import java.sql.*;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Items;

/**
 *
 * @author HikariHasegawa
 */
//@WebServlet(name = "Order", urlPatterns = {"/Order"})
public class Order extends HttpServlet {

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
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        List<Items> Items = (ArrayList) session.getAttribute("Items");
        int sum = 0;

        for (int count = 0; count < Items.size(); count++) {//num?:変数名
            String numbers = (String) request.getParameter("num" + count);//numbers:個数
            //System.out.println("★☆numbers渡す値:"+numbers);
            String numbers2 = convertFullwidthDigitToHalfWidthDigit(numbers);//半角に変換後の数値
            //System.out.println("★☆numbers2:" + numbers2);

            session.setAttribute("num" + count, numbers2);
            if (!numbers.equals("")) {
                int price = Items.get(count).getPrice();
                System.out.println(price);
                System.out.println("numbers:" + numbers);
                sum += Integer.parseInt(numbers2) * price;
            }
        }
        session.setAttribute("sum", sum);
        
        if (sum == 0) {//１つも個数を入力していない状態で注文ボタンを押しても、画面は移動しない
            response.sendRedirect("/ad0818/OrderList");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/order.jsp");
            dispatcher.forward(request, response);
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
        System.out.println("◆◆GET!◆◆");
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
        System.out.println("◆◆POST!◆◆");
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
    //               convertFullwidthDigitToHalfwidthDigit
    //               convertFullwidthDigitToHalfWidthDigit

    private String convertFullwidthDigitToHalfWidthDigit(String str) {//全角数字を半角に直す
        if (str == null) {
            throw new IllegalArgumentException("Method argument 'string is null.");
        }

        StringBuffer stringBuffer = new StringBuffer(str);
        for (int i = 0; i < stringBuffer.length(); i++) {
            char character = stringBuffer.charAt(i);
            if (character >= '０' && character <= '９') {
                stringBuffer.setCharAt(i, (char) (character - '０' + '0'));
            }
        }
        return stringBuffer.toString();
    }

}
