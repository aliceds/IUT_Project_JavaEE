<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="icon" type="image/png" href="images/logo.png">
    <title>Inscription</title>
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
		<form method="post" action="./InscriptionControle">         
			<label>Pseudo: </label><input type="text" name="login"/><br/>
                            <span class="erreur">${form.erreurs['login']}</span>
			<label>Mot de passe: </label><input type="password" name="pass"/><br/>
                            <span class="erreur">${form.erreurs['pass']}</span>
			<label>Confirmation du mot de passe: </label><input type="password" name="repass"/><br/>
                            <span class="erreur">${form.erreurs['repass']}</span>
			<label>Adresse e-mail: </label><input type="text" name="email"/><br/>
                            <span class="erreur">${form.erreurs['email']}</span>
			<label>Nom : </label><input type="text" name="nom"/><br/>
                            <span class="erreur">${form.erreurs['nom']}</span>
			<label>Prénom </label><input type="text" name="prenom"/><br/>
                            <span class="erreur">${form.erreurs['prenom']}</span>
			<label>Téléphone: </label><input type="text" name="cell"/><br/>
                            <span class="erreur">${form.erreurs['cell']}</span>
			<input type="submit" value="S'inscrire"/>                                              
		</form>
                        <h1> ${result} </h1>
		</div>
                         

	</body>
</html>		