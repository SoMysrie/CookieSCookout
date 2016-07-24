-- phpMyAdmin SQL Dump
-- version 4.4.10
-- http://www.phpmyadmin.net
--
-- Client :  localhost:3306
-- Généré le :  Dim 24 Juillet 2016 à 15:56
-- Version du serveur :  5.5.42
-- Version de PHP :  5.6.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Base de données :  `CookieSCookout`
--

-- --------------------------------------------------------

--
-- Structure de la table `COMPOSE`
--

CREATE TABLE `COMPOSE` (
  `idRecipe` int(11) NOT NULL,
  `idIngredient` int(11) NOT NULL,
  `qty` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `COMPOSE`
--

INSERT INTO `COMPOSE` (`idRecipe`, `idIngredient`, `qty`, `note`) VALUES
(1, 13, '500 g', 'fraîches de préférence'),
(1, 14, '1 beau filet', 'frais (ou surgelé) coupé en dés'),
(1, 15, '35 cl', ''),
(1, 16, '1 noix', 'fondu'),
(1, 17, NULL, NULL),
(1, 18, NULL, NULL),
(1, 19, NULL, NULL),
(1, 7, '1 pincée', ''),
(2, 9, '250 g', ''),
(2, 10, '100', ''),
(2, 20, '1/2 L', NULL),
(2, 7, '1 pincée', ''),
(2, 8, '2 cuillères à soupe', ''),
(3, 5, '100', ''),
(3, 11, NULL, NULL),
(3, 22, NULL, NULL),
(3, 7, NULL, NULL),
(3, 10, '5', 'fermier'),
(2, 12, '1 cuillère à soupe', ''),
(5, 30, '2 cuillères à café', 'bouillon japonais'),
(5, 29, 'un tout petit peu', ''),
(5, 27, NULL, NULL),
(5, 26, '3-4', 'sorte de petit poireau japonais, peut être remplacé par un petit poireau normal'),
(5, 25, '225 grammes', 'japonais'),
(5, 24, '1 oignon', 'coupé en fines lamelles'),
(5, 23, NULL, NULL),
(5, 7, '1 pincée', ''),
(5, 10, '4', ''),
(5, 28, 'un tout petit peu', ''),
(5, 8, NULL, NULL),
(6, 12, NULL, NULL),
(6, 7, NULL, NULL),
(6, 23, NULL, NULL),
(6, 26, NULL, NULL),
(7, 16, NULL, NULL),
(7, 23, NULL, NULL),
(7, 18, NULL, NULL),
(7, 17, NULL, NULL),
(8, 12, NULL, NULL),
(8, 29, NULL, NULL),
(8, 30, NULL, NULL),
(8, 1, NULL, NULL),
(9, 10, '100', ''),
(9, 5, NULL, NULL),
(10, 6, NULL, NULL),
(10, 9, NULL, NULL),
(11, 6, NULL, NULL),
(11, 5, NULL, NULL),
(11, 4, NULL, NULL),
(9, 13, NULL, NULL),
(9, 9, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `Ingredient`
--

CREATE TABLE `Ingredient` (
  `idIngredient` int(11) NOT NULL,
  `nameIngredient` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `Ingredient`
--

INSERT INTO `Ingredient` (`idIngredient`, `nameIngredient`) VALUES
(1, 'Tomate'),
(2, 'Tofu frit'),
(3, 'Pomme de terre'),
(4, 'Poivron vert'),
(5, 'Poivron rouge'),
(6, 'Poivron jaune'),
(7, 'Sel'),
(8, 'Sucre'),
(9, 'Farine Blanche'),
(10, 'Oeuf'),
(11, 'Huile de friture'),
(12, 'Huile d''olive'),
(13, 'tagliatelles'),
(14, 'filet de saumon'),
(15, 'crème fraîche'),
(16, 'beurre'),
(17, 'poivre'),
(18, 'muscade'),
(19, 'parmesan râpé'),
(20, 'lait d''amande'),
(21, 'nutella'),
(22, 'Poulet'),
(23, ' ailes de poulet'),
(24, ' oignon'),
(25, ' riz'),
(26, ' tiges de ciboule'),
(27, ' huile de sésame'),
(28, ' saké'),
(29, ' sauce soja'),
(30, 'dashi'),
(31, ' glace');

-- --------------------------------------------------------

--
-- Structure de la table `Recipe`
--

CREATE TABLE `Recipe` (
  `idRecipe` int(11) NOT NULL,
  `imgRecipe` varchar(255) NOT NULL,
  `titleRecipe` varchar(255) NOT NULL,
  `contentRecipe` text NOT NULL,
  `scoreRecipe` int(11) NOT NULL,
  `pollRecipe` int(11) NOT NULL,
  `urlRecipe` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `Recipe`
--

INSERT INTO `Recipe` (`idRecipe`, `imgRecipe`, `titleRecipe`, `contentRecipe`, `scoreRecipe`, `pollRecipe`, `urlRecipe`) VALUES
(1, 'http://images.cuisineaz.com/origin/34ca0b3e-b998-4162-8e4c-7be8e716bbf6.jpg', 'Tagliatelles au saumon frais', 'Préparation de la recette :Cuire les pâtes à l''eau bouillante salée selon le temps indiqué sur le paquet. Pendant ce temps, faire fondre le beurre dans une casserole et y ajouter la crème.Quand la crème est bien chaude, y plonger les dés de saumon, ils doivent cuire à la chaleur de la crème.Quand ils sont cuits (ils doivent se détacher), ajouter le sel, le poivre et la muscade moulue.Egoutter les pâtes, les disposer dans un grand plat ronde et napper de crème au saumon.Servir avec du parmesan.Remarques : C''est très nourrissant et convient aussi comme plat principal (pour 4 personnes dans ce cas).', 5, 23432, 'http://www.marmiton.org/recettes/recette_tagliatelles-au-saumon-frais_11354.aspx'),
(2, 'http://www.mamanatable.com/wp-content/uploads/2015/02/Crepe_Sweet.jpg', 'Crêpe', 'Mettre la farine dans un saladier avec le sel et le sucre. Faire un puits au milieu et y verser les oeufs légèrement battus à la fourchette. Commencer à incorporer doucement la farine avec une cuillère en bois.Quand le mélange devient épais, ajouter le lait froid petit à petit, on peut utiliser un fouet mais toujours doucement pour éviter les grumeaux.2 Quand tout le lait est mélangé, la pâte doit être assez fluide, si elle vous paraît trop épaisse, rajouter en peu de lait.Ajouter ensuite le beurre fondu, mélanger bien.Pour finir Cuire les crêpes dans une poêle chaude (pas besoin de matière grasse, elle est déjà dans la pâte). Verser une petite louche de pâte dans la poêle, faire un mouvement de rotation pour répartir la pâte sur toute la surface, poser sur le feu et quand le tour de la crêpe se colore en roux clair, il est temps de la retourner. Laisser cuire environ une minute de ce côté et la crêpe est prête. Répéter jusqu''à épuisement de la pâte.', 3, 432, 'http://cuisine.journaldesfemmes.com/recette/333415-crepes'),
(3, ' /Users/SophieLand/Desktop/CookieSCookout/src/Site/public/img/cooking/cooking_10.jpg', 'Fatala', 'blablabl', 3, 121234, 'lien'),
(5, ' /Users/SophieLand/Desktop/CookieSCookout/src/Site/public/img/cooking/cooking_1.png', 'Riz cantonais', '1/ Chauffer 2 cuillères à soupe d''huile de sésame dans la poêle et faire revenir les oignons et le poulet et laisser dorer doucement à feu doux.2/ Verser le contenu de la poêle dans une marmite chauffée à feu doux et ajouter 2 cuillères à soupe de sucre, 2 cuillères à soupe de saké et 2 cuillères à soupe de sauce soja.3/ Ajouter de l''eau dans la marmite (environ 2 fois le volume des ingrédients) et ajouter le dashi, laisser mijoter à feu vif. Ecumer régulièrement le bouillon jusqu''à ce qu''il réduise de moitié. 4/Une fois le bouillon prêt, verser le contenu de la marmite dans un moule rectangulaire en utilisant une passoire pour ne garder que le liquide, puis mettre le moule au frigo (compter une heure ou deux).5/ Une fois le bouillon solidifié, lancer la cuisson du riz : laver le riz à l''eau froide pour le débarrasser de l''excédent d''amidon, puis le faire cuire dans une casserole avec une phalange d''eau au dessus du niveau du riz, pendant 20 minutes - ou, plus simple, utiliser un cuiseur de riz.6/ Battre les 4 oeufs dans un bol avec une cuillère à soupe de sucre et une pincée de sel. Verser dans une poêle chauffée à feu moyen et brouiller les oeufs. Les réserver dans un bol. 7/ Démouler le bouillon solidifié et le couper en petits cubes d''1 cm.8/ Verser les oeufs et les cubes de bouillon sur le riz fumant et terminer par une poignée de ciboule découpée en fines rondelles.9/ Bon appétit !', 10, 123, 'http://animedigitalnetwork.fr/news-anime-manga/170-food-wars-recette-n-1-riz-furikake-revisite-par-soma'),
(6, '/img/cooking/cooking_1.png', 'Riz furikake revisité par Sôma', '', 0, 0, ''),
(7, '/img/cooking/cooking_2.png', 'Rôti en lamelle de boeuf à la Sôma', '', 0, 0, ''),
(8, '/img/cooking/cooking_3.png', 'Maque-Burger vite fait du Yukihira', '', 0, 0, ''),
(9, '/img/cooking/cooking_4.png', 'Maquereau onigiri chazuke du Yukihira', '', 0, 0, ''),
(10, '/img/cooking/cooking_5.png', 'Bento', '', 0, 0, ''),
(11, '/img/cooking/cooking_6.png', 'Tempura', '', 0, 0, ''),
(12, '/img/cooking/cooking_7.png', 'California Rolls', '', 0, 0, ''),
(13, '/img/cooking/cooking_8.png', 'Fish & Chips', '', 0, 0, ''),
(14, '/img/cooking/cooking_9.png', 'Couscous au poulet', '', 0, 0, ''),
(15, '/img/cooking/cooking_10.png', 'Fajitas', '', 0, 0, '');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `COMPOSE`
--
ALTER TABLE `COMPOSE`
  ADD KEY `idRecipe` (`idRecipe`),
  ADD KEY `idIngredient` (`idIngredient`);

--
-- Index pour la table `Ingredient`
--
ALTER TABLE `Ingredient`
  ADD PRIMARY KEY (`idIngredient`);

--
-- Index pour la table `Recipe`
--
ALTER TABLE `Recipe`
  ADD PRIMARY KEY (`idRecipe`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `Ingredient`
--
ALTER TABLE `Ingredient`
  MODIFY `idIngredient` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=32;
--
-- AUTO_INCREMENT pour la table `Recipe`
--
ALTER TABLE `Recipe`
  MODIFY `idRecipe` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=26;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `COMPOSE`
--
ALTER TABLE `COMPOSE`
  ADD CONSTRAINT `compose_ibfk_1` FOREIGN KEY (`idIngredient`) REFERENCES `Ingredient` (`idIngredient`),
  ADD CONSTRAINT `compose_ibfk_2` FOREIGN KEY (`idRecipe`) REFERENCES `Recipe` (`idRecipe`);
