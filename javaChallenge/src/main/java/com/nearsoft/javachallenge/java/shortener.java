/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nearsoft.javachallenge.java;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author abdiel.huerta
 */
public class shortener extends HttpServlet {
    
    public static HashMap<String, String> savedUrls = new HashMap<String, String>();
    
    
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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Shortener</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet shortener at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    public String generateSpecialAlias(String url) {
            String alias = "";
            Pattern pat = Pattern.compile("[^a-z]|[aeiou]");
            Matcher mat = pat.matcher(url.toLowerCase());
            alias = mat.replaceAll("");
            return alias;
    }
    
    public String generateAlias(int length){
        String alias = "";
        for (int i = 0; i < length; i++) {
            int rnd = (int) (Math.random() * 54); 
            char c = (rnd < 26) ? 'A' : 'a';
            alias=alias +(char) (c + rnd % 26);
	}
        return alias;
    }
    
    public void saveUrl(String url) {
            if(!savedUrls.containsKey(url)) {
                    if(url.contains("google")) {
                        savedUrls.put(url, generateAlias(5));
                    }
                    else if(url.contains("yahoo")) {
                        savedUrls.put(url, generateAlias(7));
                    }
                    else {
                        savedUrls.put(url, generateSpecialAlias(url));
                    }	
            }
            else {
                    System.out.println("Saved Before");
            }
            System.out.println(savedUrls);
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
