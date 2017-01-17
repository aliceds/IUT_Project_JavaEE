<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Paiement</title>
        <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    </head>
    <body>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<header>
            <div class="row">

                <!-- Logo & Titre --> 
                <div class="col-3">
                    <a href="./index">
                        <img id="logo" src="images/logo.png" alt="Logo">
                        <span id="titre">Football Shop</span>
                    </a>
                </div>

                <!-- Lien vers autre(s) page(s) --> 
                <div class="col-9 header_right">
                    <a href="./AboutServlet"><img class="icone" src="images/interrogation.png" alt="Qui sommes nous ?"/></a>
                    <a href="./PanierServlet"><img class="icone" src="images/panier.png" alt="Panier"/></a>
                    <a href="./CompteServlet"><img class="icone" src="images/compte.png" alt="Compte"/></a>

                    <!-- Barre de recherche --> 
                    <nav id="recherche">
                            <input id="barre" type="search" placeholder="Rechercher un modèle, une marque, une référence...">
                            <input id="loupe" type="image" src="images/loupe.png" onclick="submit()" alt="Rechercher"/>
                    </nav>
                </div>
            </div>

        </header>

        <!-- Barre de menu -->
        <nav>
            <ul id="menu">
                <li><a href="./MaillotServlet" class="case" title="Tenues">TENUES</a></li>
                <li><a href="./ChaussuresServlet" class="case" title="Chaussures">CHAUSSURES</a></li>
                <li><a href="./BallonsServlet" class="case" title="Ballons">BALLONS</a></li>
            </ul>
        </nav>
        <!-- Corps de la page -->


                                
                                    <div class="col-4 inscrire">
                                        <h2>Paiement</h2>
                                           <jsp:useBean id="cart" scope="session" class="Modele.CartBeanModele" />
                                        <form method="post" action="./PaiementControle">
                                            <label>Nom du titulaire de la carte bancaire</label><input type="text" name="nom">
                                                <span class="erreur">${form.erreurs['nom']}</span>
                                            <input type='hidden' name='total' value='<c:out value="${cart.orderTotal}"/>'>
                                            <input type='hidden' name='liste' value='<c:out value="${cart.cartItems}"/>'>
                                            <label>E-mail</label><input type="text" name="email">
                                                <span class="erreur">${form.erreurs['email']}</span>
                                            <label>Numero de telephone</label><input type="text" name="cell">
                                                 <span class="erreur">${form.erreurs['cell']}</span>
                                            <label>Adresse de livraison</label><input type="text" name="adresse">
                                                <span class="erreur">${form.erreurs['adresse']}</span>
                                            <label>Ville</label><input type="text" name="ville">
                                                 <span class="erreur">${form.erreurs['ville']}</span>
                                            <label>Code postale</label><input type="text" name="postale">
                                                 <span class="erreur">${form.erreurs['postale']}</span>
                                            <label>Code carte bleue</label><input type="text" name="bank">
                                                 <span class="erreur">${form.erreurs['bank']}</span>
                                            <input type="submit" value="Commander !"></span>
                                           
                                           <span class="erreur"> ${result} </span>
                                                            
                                        </form>
                                    </div>
                                


    </body>
</html>




