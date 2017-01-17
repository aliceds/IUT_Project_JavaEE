<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
        <link rel="icon" type="image/png" href="images/logo.png"/>
        <title>Accueil</title>
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

        <section>
            <h1 id="titreindex">Bienvenue sur Football Shop</h1>
            <ul id="liste">
                <li><a href="./MaillotServlet" title="Tenues">Tenue</a></li>
                <li><a href="./ChaussuresServlet" title="Chaussures">Chaussures</a></li>
                <li><a href="./BallonsServlet" title="Ballons">Ballons</a></li>
            </ul></a>
            <h3 id="text">
                Ce site vous propose une large gamme de produit footbalistique. 
                Les tarifs attractifs de ce site vont vous permettre d'acheter des tenues, des chaussures et des ballons.
                Football Shop est le site qui vous habillera pour vos entrainements et vos matchs.
            </h3>
        </section>

    </body>
</html>
