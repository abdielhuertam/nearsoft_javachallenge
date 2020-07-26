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
import java.util.Map.Entry;
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
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
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
            //out.println("<h1>Servlet shortener at " + request.getContextPath() + "</h1>");
            out.println("<form action=\"shortener\" method=\"post\">");
            out.println("<input type=\"text\" name=\"url\">");
            out.println("<input type=\"submit\" value=\"Generate short URL\">");
            out.println("</form>");
            out.println("<br>");
            out.println("<br>");
            out.println("<form action=\"shortener\" method=\"get\">");
            out.println("<input type=\"text\" name=\"alias\">");
            out.println("<input type=\"submit\" value=\"Search Url by Alias\">");
            out.println("</form>");
            out.println("<br>");
            out.println("Redirect to:");
            out.println("</body>");
            out.println("</html>");
        }
    }
    private void response(HttpServletResponse resp, String normalUrl, int method)
                    throws IOException {
            PrintWriter out = resp.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Shortener</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<form action=\"shortener\" method=\"post\">");
            out.println("<input type=\"text\" name=\"url\">");
            out.println("<input type=\"submit\" value=\"Generate short URL\">");
            out.println("</form>");
            if(1 == method){
                out.println("alias:"+normalUrl);
            }
            out.println("<br>");
            out.println("<br>");
            out.println("<form>");
            out.println("<input type=\"text\" name=\"alias\">");
            out.println("<input type=\"submit\" value=\"Search Url by Alias\">");
            out.println("</form>");
            out.println("<br>");
            if(2 == method){
                out.println("Redirect to:"+normalUrl);
                //resp.sendRedirect(normalUrl);
            }
            out.println("</body>");
            out.println("</html>");;
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
    
    public String saveUrl(String url) {
            String alias = "";
            if(!savedUrls.containsKey(url)) {
                    if(url.contains("google")) {
                       alias = generateAlias(5);
                        savedUrls.put(url, alias );
                    }
                    else if(url.contains("yahoo")) {
                        alias = generateAlias(7);
                        savedUrls.put(url, alias );
                    }
                    else if(!url.equals("")){
                        alias = generateSpecialAlias(url);
                        savedUrls.put(url, alias);
                    }
                    else {
                        alias = "Empty request";
                    }
            }
            else{
                alias=savedUrls.get(url);
            }
            return alias;
    }
    
    public String searchUrl(String alias){
        String url = "";
        for (Entry<String, String> entry : savedUrls.entrySet()){
            if (entry.getValue().equals(alias)) {
                return entry.getKey();
            }
        }
        return "Url Not Found";
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
        String alias = request.getParameter("alias");
        if(alias == null){
            response(response,"",0);
        }
        else{
            response(response,searchUrl(alias),2);
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
       String normalUrl = request.getParameter("url");
       if(normalUrl == null){
           response(response,"",0);
        }
        else{
           String alias = saveUrl(normalUrl);  
           response(response,alias,1);
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
