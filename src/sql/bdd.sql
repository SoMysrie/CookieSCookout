-- phpMyAdmin SQL Dump
-- version 4.4.10
-- http://www.phpmyadmin.net
--
-- Client :  localhost:3306
-- Généré le :  Jeu 28 Avril 2016 à 23:50
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
  `contents` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `SiteWeb`
--

INSERT INTO `SiteWeb` (`img`, `title`, `contents`) VALUES
('/Site/public/img/cooking/cooking_1.png', 'Riz furikake revisité par Sôma', '7 ailes de poulet + 4 oeufs + riz'),
('/Site/public/img/cooking/cooking_2.png', 'Rôti en lamelle de boeuf de Sôma', '7 pommes de terre + 1 boeuf');
