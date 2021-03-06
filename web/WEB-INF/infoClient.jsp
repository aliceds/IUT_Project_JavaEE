
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="icon" type="image/png" href="images/logo.png">
    <title>Infos Client</title>
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
        <h1>Mon profil </h1>
            <form action="./InfoClientControle" method ="POST">
                <h2>Votre nom : <c:out value="${client.nom}"/></h2><input type="text" name="modifNom"/>
                <input type="submit" value="Modifier" class="button"/>
            </form>
            <form action="./InfoClientControle" method ="POST">
                <h2>Votre prenom : <c:out value="${client.prenom}"/></h2><input type="text" name="modifPrenom"/>
                <input type="submit" value="Modifier" class="button"/>
            <form action="./InfoClientControle" method ="POST">
                <h2>Votre email : <c:out value="${client.email}"/></h2><input type="text" name="modifEmail"/>
                <input type="submit" value="Modifier" class="button"/>
            </form>
            <form action="./InfoClientControle" method ="POST">
                <h2>Votre cellulaire : <c:out value="${client.cell}"/></h2><input type="text" name="modifCell"/>
                <input type="submit" value="Modifier" class="button"/>
            </form>
            <form action="./InfoClientControle" method ="POST">
                <h2>Votre login : <c:out value="${client.login}"/></h2><input type="text" name="modifLogin"/>
                <input type="submit" value="Modifier" class="button"/>
            </form>
            <form action="./InfoClientControle" method ="POST">
                <h2>Votre mot de passe : <c:out value="${client.pass}"/></h2><input type="text" name="modifPass"/>
                <input type="submit" value="Modifier" class="button"/>
            </form>
                <li><p>Vous êtes connectés avec l'adresse IP suivante : "<%= request.getCookies()[0].getValue() %>"</p></li>
            </div>
</body>
</html>
