package Servlets;

import Modele.CartBeanModele;
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
import javax.servlet.http.HttpSession;

public class PanierServlet extends HttpServlet {

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
            out.println("<title>Servlet panierServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet panierServlet at " + request.getContextPath() + "</h1>");
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

        String strAction = request.getParameter("action");
        if (strAction != null && !strAction.equals("")) {
            if (strAction.equals("add")) {
                addToCart(request);
            } else if (strAction.equals("Update")) {
                updateCart(request);
            } else if (strAction.equals("Delete")) {
                deleteCart(request);
            }
        }
        ConnexionBDModele cnx = new ConnexionBDModele();
        Connection connexion = cnx.getCnx();
        System.out.println("Appel connexion");

        LienBaseModele lien = new LienBaseModele();
        Statement statement = lien.getLien(connexion);

        ResultSet res = null;
        try {
            res = statement.executeQuery("SELECT * FROM produit");
        } catch (SQLException ex) {
            Logger.getLogger(Modele.class.getName()).log(Level.SEVERE, null, ex);
        }

        Modele item = new Modele();
        try {
            while (res.next()) {
                int idProduit = res.getInt("idProduit");
                String nomProduit = res.getString("nomProduit");
                int prixProduit = res.getInt("prixProduit");
                ProduitModele prod = new ProduitModele();
                prod.setIdProduit(idProduit);
                prod.setPrixProduit(prixProduit);
                prod.setNomProduit(nomProduit);
                item.ajout(prod);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Modele.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("item", item);
        request.setAttribute("liste", item.getListe());

        this.getServletContext().getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);

    }

    protected void deleteCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String strItemIndex = request.getParameter("itemIndex");
        CartBeanModele cartBean = null;
        Object objCartBean = session.getAttribute("cart");
        if (objCartBean != null) {
            cartBean = (CartBeanModele) objCartBean;

        } else {
            cartBean = new CartBeanModele();
        }
        cartBean.deleteCartItem(strItemIndex);
    }

    protected void updateCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String strQuantity = request.getParameter("quantity");
        String strItemIndex = request.getParameter("itemIndex");
        CartBeanModele cartBean = null;
        Object objCartBean = session.getAttribute("cart");
        if (objCartBean != null) {
            cartBean = (CartBeanModele) objCartBean;
        } else {
            cartBean = new CartBeanModele();
        }
        cartBean.updateCartItem(strItemIndex, strQuantity);
    }

    protected void addToCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String strModelNo = request.getParameter("modelNo");
        String strDescription = request.getParameter("description");
        String strPrice = request.getParameter("price");
        String strQuantity = request.getParameter("quantity");
        CartBeanModele cartBean = null;
        Object objCartBean = session.getAttribute("cart");

        if (objCartBean != null) {
            cartBean = (CartBeanModele) objCartBean;
        } else {
            cartBean = new CartBeanModele();
            session.setAttribute("cart", cartBean);
        }
        cartBean.addCartItem(strModelNo, strDescription, strPrice, strQuantity);

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
