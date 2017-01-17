<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="icon" type="image/png" href="images/logo.png">
        <title>Mon compte</title>
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
        <jsp:useBean id="client" scope="session" class="Modele.ClientModele" />
        <c:choose>
            <c:when test="${empty sessionScope.sessionUtilisateur }" >
                <section>	
                    <div class="accueil">
                        <div>
                            <div class="col-4 inscrire">
                                <form action="./ConnexionServlet" method="POST">
                                    <label>Login :</label><input type="text" placeholder="Nom d'utilisateur" name="login">
                                            ${form.erreurs['email']}
                                    <label>Mot de passe :</label><input type="password" placeholder="Mot de passe" name="motdepasse"></li>
                                            ${form.erreurs['motdepasse']}
                                        <a href=""><input type="submit" class="button" value="Se connecter"></a>
                                                <c:if test="${!empty requestScope.intervalleConnexions}">
                                            <li>Vous ne vous êtes pas connecté depuis ce navigateur depuis ${requestScope.intervalleConnexions}</li>
                                            </c:if>
                                            <c:if test="${empty requestScope.intervalleConnexions}">
                                            <li>Se souvenir de moi <input type="checkbox" name="memoire" /></li>
                                            </c:if>
                                </form> 
                            </div>

                            <a class="col-4 inscrire" href="./InscriptionServlet" title="Inscription">Se créer un compte</a>
                        </div>
                    </div>
                </section>
            </c:when>
            <c:otherwise>
                <section>	
                    <div class="col-4 inscrire">
                        <div>                    
                                <c:if test="${client.typeMembre.equals('client')}">
                                    <h1> Bienvenue <c:out value="${client.login}"/> ! ${client.typeMembre}</h1>
                                    <p><a href="./InfoClientServlet">Voir mon compte</></p>
                                </c:if>
                                                
                                    <c:if test="${client.typeMembre.equals('admin')}">
                                    <h1> Bienvenue <c:out value="${client.login}"/> ! ${client.typeMembre}</h1>
                                    <p><a href="">Gérer le site</></p>
                                            </c:if>

                                        <li><a href="./DeconnexionServlet"><div class="button"><span>Déconnexion</span></div></a></li>                                    
                        </div>
                    </div>
                </section>
            </c:otherwise>
        </c:choose>
    </body>
</html>
