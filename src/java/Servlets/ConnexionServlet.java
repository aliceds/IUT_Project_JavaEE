package Servlets;

import Modele.ClientModele;
import Modele.ConnexionFormulaireModele;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.joda.time.*;
import javax.servlet.http.Cookie;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

public class ConnexionServlet extends HttpServlet {

    public static final String ATT_USER = "client";
    public static final String ATT_FORM = "form";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String VUE = "/CompteServlet";
    public static final String FORMAT_DATE = "dd/MM/yyyy HH:mm:";
    public static final String CHAMP_MEMOIRE = "memoire";
    public static final int COOKIE_MAX_AGE = 60 * 60 * 24 * 365;

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
            out.println("<title>Servlet Connexion</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Connexion at " + request.getContextPath() + "</h1>");
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

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
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

        ConnexionFormulaireModele form = new ConnexionFormulaireModele();

        ClientModele client = form.connecterUtilisateur(request);

        HttpSession session = request.getSession();

        if (form.getErreurs().isEmpty()) {
            session.setAttribute(ATT_SESSION_USER, client);

        } else {
            session.setAttribute(ATT_SESSION_USER, null);
        }
        
        setCookie(request,response,client.getLogin(),COOKIE_MAX_AGE);
        request.setAttribute(ATT_FORM, form);
        request.setAttribute(ATT_USER, client);
        session.setAttribute("client", client);


        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }

    private static void setCookie(HttpServletRequest request,HttpServletResponse response, String nom, int maxAge) {
        Cookie cookie = new Cookie(nom, "");
        cookie.setValue(request.getRemoteAddr());
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
    
    private static String getCookieValue( HttpServletRequest request, String nom ) {
        Cookie[] cookies = request.getCookies();
        if ( cookies != null ) {
            for ( Cookie cookie : cookies ) {
                if ( cookie != null && nom.equals( cookie.getName() ) ) {
                    return cookie.getValue();
                }
            }
        }
        return null;
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
