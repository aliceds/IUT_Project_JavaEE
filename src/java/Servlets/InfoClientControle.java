/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Modele.ClientModele;
import Modele.ConnexionBDModele;
import Modele.ConnexionFormulaireModele;
import Modele.LienBaseModele;
import static Servlets.ConnexionServlet.ATT_USER;
import static Servlets.ConnexionServlet.VUE;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Cebrail
 */
public class InfoClientControle extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controlleurModifInfos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet controlleurModifInfos at " + request.getContextPath() + "</h1>");
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
       HttpSession session = request.getSession();
        
        ConnexionBDModele cnx = new ConnexionBDModele();
        Connection connexion = cnx.getCnx();
        LienBaseModele lien = new LienBaseModele();
        Statement statement = lien.getLien(connexion);
        
        String email = request.getParameter( "modifEmail" );
        ClientModele client=(ClientModele) session.getAttribute("client");
        String login=client.getLogin();
        String nom = request.getParameter( "modifNom" );
        String prenom = request.getParameter( "modifPrenom" );
        String cell = request.getParameter( "modifCell" );
        String login2 = request.getParameter( "modifLogin" );
        String pass = request.getParameter( "modifPass" );
        ResultSet res = null;
        
        if ( email != null && email.trim().length() != 0 ) {
  
        
        
        try {
            statement.executeUpdate("UPDATE utilisateur set email='"+email+"' WHERE pseudo='"+login+"' ;" );
            client.setEmail(email);
        } catch (SQLException ex) {
            Logger.getLogger(InfoClientControle.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        if ( nom != null && nom.trim().length() != 0 ) {
  
         res = null;
        
        try {
            statement.executeUpdate("UPDATE utilisateur set nom='"+nom+"' WHERE pseudo='"+login+"' ;" );
            client.setNom(nom);
        } catch (SQLException ex) {
            Logger.getLogger(InfoClientControle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
        }
        
        if ( prenom != null && prenom.trim().length() != 0 ) {
  
         res = null;
        
        try {
            statement.executeUpdate("UPDATE utilisateur set prenom='"+prenom+"' WHERE pseudo='"+login+"' ;" );
            client.setPrenom(prenom);
        } catch (SQLException ex) {
            Logger.getLogger(InfoClientControle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
        }
        
        if ( cell != null && cell.trim().length() != 0 ) {
  
         res = null;
        
        try {
            statement.executeUpdate("UPDATE utilisateur set cell='"+cell+"' WHERE pseudo='"+login+"' ;" );
            client.setCell(cell);
        } catch (SQLException ex) {
            Logger.getLogger(InfoClientControle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
        }
        
        if ( pass != null && pass.trim().length() != 0 ) {
  
         res = null;
        
        try {
            statement.executeUpdate("UPDATE utilisateur set mdp='"+pass+"' WHERE pseudo='"+login+"' ;" );
            client.setPass(pass);
        } catch (SQLException ex) {
            Logger.getLogger(InfoClientControle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
        }
        
        if ( login2 != null && login2.trim().length() != 0 ) {
  
         res = null;
        
        try {
            res=statement.executeQuery("select pseudo from utilisateur where pseudo='"+login2+"'");
            if(!res.next()){
            
            statement.executeUpdate("UPDATE utilisateur set pseudo='"+login2+"' WHERE pseudo='"+login+"' ;" );
            client.setLogin(login2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InfoClientControle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
        }
        request.setAttribute( ATT_USER, client );
        session.setAttribute("client", client);
    
        this.getServletContext().getRequestDispatcher( "/InfoClientServlet" ).forward( request, response );
    
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
