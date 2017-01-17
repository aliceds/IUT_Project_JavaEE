package Servlets;

import Modele.ConnexionBDModele;
import Modele.LienBaseModele;
import Modele.Modele;
import Modele.ProduitModele;
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

public class BallonsServlet extends HttpServlet {

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
            out.println("<title>Servlet ballonsServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ballonsServlet at " + request.getContextPath() + "</h1>");
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
        ConnexionBDModele cnx = new ConnexionBDModele();
        Connection connexion = cnx.getCnx();
        System.out.println("Appel connexion");

        LienBaseModele lien = new LienBaseModele();
        Statement statement = lien.getLien(connexion);

        String commande = "SELECT * FROM produit";
        ResultSet res = null;
        try {
            res = statement.executeQuery("SELECT * FROM produit WHERE idProduit<8");
        } catch (SQLException ex) {
            Logger.getLogger(Modele.class.getName()).log(Level.SEVERE, null, ex);
        }

        Modele produit = new Modele();
        try {
            while (res.next()) {
                int idProduit = res.getInt("idProduit");
                String photoProduit = res.getString("photoProduit");
                String nomProduit = res.getString("nomProduit");
                int prix = res.getInt("prixProduit");
                ProduitModele prod = new ProduitModele();
                prod.setPrixProduit(prix);
                prod.setIdProduit(idProduit);
                prod.setNomProduit(nomProduit);
                prod.setPhotoProduit(photoProduit);

                produit.ajout(prod);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Modele.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("ballons", produit);
        request.setAttribute("liste", produit.getListe());
        this.getServletContext().getRequestDispatcher("/WEB-INF/ballons.jsp").forward(request, response);

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
