<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="icon" type="image/png" href="images/logo.png">
        <title>Ballons</title>
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
        <div class="col-12">
           <!-- <section id="ballon">
                <div class="trait_dessus"></div> 
                <h1 class="titre"> Adidas : </h1>
                <ul class="tri">-->
                    <c:forEach items="${liste}" var="produit" >
                        <section class="test">	
                            <div>
                                <div id="ballon">
                                    <form name="model1" method="POST" action="./PanierControle">							
                                        <a href="./ProductServlet?i=<c:out value="${produit['idProduit']}"/>"><img class="ballons"src="images\ballons<c:out value="${produit['photoProduit']}"/>" alt=""></a>                                      
                                        <div class="case">
                                            <input type="hidden" name="modelNo" value="<c:out value="${produit['nomProduit']}"/>">
                                            <p class="nomproduit"><c:out value="${produit['nomProduit']}"/></p>
                                            <input type="hidden" size="2" value="1" name="quantity">

                                            <input type="hidden" name="price" value="<c:out value="${produit['prixProduit']}"/>">
                                            <p class="prixactuel">Prix :<c:out value="${produit['prixProduit']}"/> euros</p> 
                                            
                                            <input type="hidden" name="action" value="add">
                                            <input class="button" type="submit" name="addToCart" value="Ajouter !" >
                                            
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </section>		
                    </c:forEach>   
           <!--
                </ul>
                <div class="trait_dessus"></div> 
                <h1 class="titre"> Nike : </h1>
                <ul class="tri">

                </ul>

            </section>
            -->
        </div>

    </body>
</html>