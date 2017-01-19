/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Modele.ConnexionBDModele;
import Modele.LienBaseModele;
import Modele.Modele;
import Modele.ProduitModele;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Modele.PanierModele;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Cebrail
 */
public class PanierControle extends HttpServlet {

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
            out.println("<title>Servlet cartController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet cartController at " + request.getContextPath() + "</h1>");
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
        
     String strAction = request.getParameter("action");
     if(strAction!=null && !strAction.equals("")) {
         if(strAction.equals("add")) {
             addToCart(request);
         }  else if (strAction.equals("Update")) {
             updateCart(request);
         }else if (strAction.equals("Delete")) {
             deleteCart(request);
         }
     }
     ConnexionBDModele cnx = new ConnexionBDModele();
        Connection connexion = cnx.getCnx();
  
        LienBaseModele lien = new LienBaseModele();
        Statement statement = lien.getLien(connexion);
        
       
        
        ResultSet res = null;
        try {
            res = statement.executeQuery("SELECT * FROM produit" );
        } catch (SQLException ex) {
            Logger.getLogger(Modele.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Modele produit = new Modele();
        try {
            while (res.next()){
                int idProduit = res.getInt("idProduit");
               
                String photoProduit = res.getString("photoProduit");
                String nomProduit =res.getString("nomProduit");
                int prixProduit=res.getInt("prixProduit");
                ProduitModele prod = new ProduitModele();
                prod.setIdProduit(idProduit);
                prod.setPrixProduit(prixProduit);
                prod.setNomProduit(nomProduit);
                prod.setPhotoProduit(photoProduit);
                
                
                produit.ajout(prod);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Modele.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        request.setAttribute("produit", produit);
        request.setAttribute("liste", produit.getListe());
        this.getServletContext().getRequestDispatcher( "/WEB-INF/panier.jsp" ).forward( request, response );
  
        
    }
    
    protected void deleteCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String strItemIndex = request.getParameter("itemIndex");
        PanierModele PanierModele = null;
        Object ojPanier = session.getAttribute("cart");
        if(ojPanier!=null) {
            PanierModele = (PanierModele) ojPanier ;
            
        } else {
            PanierModele = new PanierModele();
        }
        PanierModele.deleteCartItem(strItemIndex);
    }
    
    protected void updateCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String strQuantity = request.getParameter("quantity");
        String strItemIndex = request.getParameter("itemIndex");
        PanierModele cartBean = null;
        Object objCartBean = session.getAttribute("cart");
        if(objCartBean!=null) {
            cartBean = (PanierModele) objCartBean ;
        } else {
            cartBean = new PanierModele();
        }
        cartBean.updateCartItem(strItemIndex, strQuantity);
    }
    
    protected void addToCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println("");
        String strModelNo = request.getParameter("modelNo");
        String strDescription = request.getParameter("description");
        String strPrice = request.getParameter("price");
        String strQuantity = request.getParameter("quantity");
        PanierModele cartBean = null;
        Object objCartBean = session.getAttribute("cart");
        
        if(objCartBean!=null) {
            cartBean = (PanierModele) objCartBean ;
        } else {
            cartBean = new PanierModele();
            session.setAttribute("cart", cartBean);
        }
        cartBean.addCartItem(strModelNo, strDescription, strPrice, strQuantity);
        
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
