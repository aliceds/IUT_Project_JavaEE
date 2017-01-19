
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
            <c:if test="${empty listeC}">
                <p>Ce client n'a pas encore commandé.</p>
            </c:if>

            <c:forEach items="${listeC}" var="liste">
                <form action="./CommandeAdminServlet" method ="POST">
                    <section>	

                        <div class="case1b imgb">
                            <div> 							
                                Commande <c:out value="${liste['idCommande']}"/>
                                <div>
                                    <input type="hidden" name="idCommande" value="<c:out value="${liste['idCommande']}"/>">
                                    <p class="nomproduit">Client numero <c:out value="${liste['idClient']}"/></p>


                                    <div class="prix ">


                                        <span class="prixactuel"><c:out value="${liste['montant']}"/>€</span> 

                                    </div>
                                    <div class="prix ">


                                        <span class="prixactuel">Date de commande <c:out value="${liste['dateCommande']}"/></span> 

                                    </div>
                                    <div class="prix ">


                                        <span class="prixactuel">Date de livraison <c:out value="${liste['dateLivraison']}"/></span> 

                                    </div>

                                    <div class="prix ">


                                        <span class="prixactuel">Intitule <c:out value="${liste['intitule']}"/></span> 

                                    </div>
                                    <input type="submit" value="Supprimer" class="button">										
                                </div>
                            </div>
                        </div>

                    </section>		
                </c:forEach>        
        </div>
    </body>
</html>
