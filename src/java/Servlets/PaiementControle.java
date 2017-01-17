/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Modele.CommandeModele;
import Modele.ClientModele;
import Modele.ConnexionBDModele;
import Modele.LienBaseModele;
import Modele.PaiementFormulaireModele;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PaiementControle extends HttpServlet {

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
            out.println("<title>Servlet paymentControler</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet paymentControler at " + request.getContextPath() + "</h1>");
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
        this.getServletContext().getRequestDispatcher("/WEB-INF/paiement.jsp").forward(request, response);
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

        PaiementFormulaireModele form = new PaiementFormulaireModele();
        int idClient = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar debut = Calendar.getInstance();
        debut.setTime(new Date());

        String debut1 = sdf.format(debut.getTime());

        Calendar fin = Calendar.getInstance();
        fin.setTime(new Date());

        int randInt = 5 + (int) (Math.random() * ((10 - 5) + 1));

        fin.add(Calendar.DATE, randInt);
        String fin1 = sdf.format(fin.getTime());

        String total = request.getParameter("total");
        String liste = request.getParameter("liste");
        System.out.println(total);
        HttpSession session = request.getSession();
        ClientModele clt = (ClientModele) session.getAttribute("client");
        String login = clt.getLogin();
        System.out.println(login);
        CommandeModele commande;
        try {
            commande = form.stockerCommande(request);
            String result = form.getResultat();
            request.setAttribute("form", form);
            request.setAttribute("commande", commande);
            request.setAttribute("result", result);

            if ("Succès du paiement, votre commande est en cours !".equals(result)) {
                System.out.println("OK");

                ConnexionBDModele cnx = new ConnexionBDModele();
                Connection connexion = cnx.getCnx();

                LienBaseModele lien = new LienBaseModele();
                Statement statement = lien.getLien(connexion);

                ResultSet res = null;
                try {
                    System.out.println("SELECT idPseudo from utilisateur where pseudo='" + login + "'");
                    res = statement.executeQuery("SELECT idPseudo from utilisateur where pseudo='" + login + "'");
                    if (res.next()) {
                        idClient = res.getInt(1);

                        System.out.println("idclt " + idClient);
                        System.out.println("total " + total);
                        System.out.println("debut " + debut1);
                        System.out.println("fin " + fin1);
                        System.out.println("intitule " + liste);
                        liste = "Commande du client " + login;
                        statement.execute("INSERT INTO commande VALUES (NULL, '" + idClient + "', '" + total + "', '" + debut1 + "', '" + fin1 + "', '" + liste + "'); ");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

        } catch (Exception ex) {
            Logger.getLogger(PaiementControle.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/paiement.jsp").forward(request, response);

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
