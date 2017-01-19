<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="icon" type="image/png" href="images/logo.png">
        <title>Mon Panier</title>
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

            <div class="col-10 panier">
                <h1>Votre panier :</h1>
                <jsp:useBean id="cart" scope="session" class="Modele.PanierModele" />
                <c:choose>
                    <c:when test="${cart.lineItemCount==0}" > 
                        Panier vide !
                    </c:when> 
                    <c:otherwise>
                        <c:forEach var="cartItem" items="${cart.cartItems}" varStatus="counter">
                            <form name="item" method="POST" action="#">
                                <c:out value="${cartItem.partNumber}"/> </BR>
                                <c:out value="${cartItem.modelDescription}"/> </BR>
                                <input type='hidden' name='itemIndex' value='<c:out value="${counter.count}"/>'><input type='text' name="quantity" value='<c:out value="${cartItem.quantity}"/>' size='2'> <input class="button" type="submit" name="action" value="Delete"><input class="button" type="submit" name="action" value="Update"></BR>

                                Prix/Unité: <c:out value="${cartItem.unitCost}"/> euros</BR>
                                Prix/Total : <c:out value="${cartItem.totalCost}"/> euros</BR></BR>
                            </form>
                        </c:forEach>   
                        Total du panier: $<c:out value="${cart.orderTotal}"/>

                        <c:choose>
                            <c:when test="${empty sessionScope.sessionUtilisateur}" >
                                Veuillez vous connecter pour le paiement.
                            </c:when>
                            <c:otherwise>
                                <a href="./PaiementControle"><button class="button"><span>Acceder au paiement</span></button></a>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="col-2 achat">
                <a href="./index" title="achat"><p>Poursuivez vos achats ici</p></a>
            </div>

        </section>

    </body>
</html>
