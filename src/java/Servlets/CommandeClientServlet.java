/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Modele.ListeCommandeModele;
import Modele.CommandeModele;
import Modele.ClientModele;
import Modele.ConnexionBDModele;
import Modele.LienBaseModele;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ducro
 */
public class CommandeClientServlet extends HttpServlet {

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
            out.println("<title>Servlet CommandeClientServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CommandeClientServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        ClientModele client = (ClientModele) session.getAttribute("client");
        String pseudo = client.getLogin();
        ConnexionBDModele cnx = new ConnexionBDModele();
        Connection connexion = cnx.getCnx();
        System.out.println("Appel connexion");

        LienBaseModele lien = new LienBaseModele();
        Statement statement = lien.getLien(connexion);

        //String commande = "SELECT * FROM commande where idClient=(select idClient from utilisateur where pseudo='"+pseudo+"')";
        ResultSet res = null;
        try {
            res = statement.executeQuery("SELECT * FROM commande where idClient=(select idClient from utilisateur where pseudo='" + pseudo + "');");
        } catch (SQLException ex) {
        }

        ListeCommandeModele liste = new ListeCommandeModele();
        try {
            while (res.next()) {
                int idCommande = res.getInt("idCommande");
                System.out.println(idCommande);
                int idClient = res.getInt("idClient");
                int montant = res.getInt("montantCommande");

                String debut = res.getString("dateCommande");
                String fin = res.getString("dateLivraison");

                String intitule = res.getString("intitule");

                CommandeModele commande = new CommandeModele();
                commande.setIdCommande(idCommande);
                commande.setIdClient(idClient);
                commande.setMontant(montant);

                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date dateD = formatter.parse(debut);

                java.sql.Date sqlDate = new java.sql.Date(dateD.getTime());
                commande.setDateCommande(sqlDate);

                dateD = formatter.parse(fin);
                sqlDate = new java.sql.Date(dateD.getTime());
                commande.setDateLivraison(sqlDate);

                commande.setIntitule(intitule);

                liste.ajout(commande);

            }
        } catch (SQLException ex) {
        } catch (ParseException ex) {
        }
        request.setAttribute("liste", liste);
        request.setAttribute("listeC", liste.getListe());

        this.getServletContext().getRequestDispatcher("/WEB-INF/gestionCommandeClient.jsp").forward(request, response);
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
