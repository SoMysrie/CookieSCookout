-- phpMyAdmin SQL Dump
-- version 4.4.10
-- http://www.phpmyadmin.net
--
-- Client :  localhost:3306
-- Généré le :  Mar 14 Juin 2016 à 19:20
-- Version du serveur :  5.5.42
-- Version de PHP :  5.6.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Base de données :  `CookieSCookout`
--

-- --------------------------------------------------------

--
-- Structure de la table `SiteWeb`
--

CREATE TABLE `SiteWeb` (
  `img` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `recipe` text NOT NULL,
  `ingredients` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `SiteWeb`
--

INSERT INTO `SiteWeb` (`img`, `title`, `recipe`, `ingredients`) VALUES
('/Site/public/img/cooking/cooking_1.png', 'Riz furikake revisité par Sôma', 'Tremper les ailes de poulet dans les oeufs et faire cuire le riz', '7 ailes de poulet + 4 oeufs + riz'),
('/Site/public/img/cooking/cooking_2.png', 'Rôti en lamelle de boeuf de Sôma', 'Mettre le boeuf au four et cuire les pommes de terre', '7 pommes de terre + 1 boeuf');
