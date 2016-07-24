-- phpMyAdmin SQL Dump
-- version 4.4.10
-- http://www.phpmyadmin.net
--
-- Client :  localhost:3306
-- Généré le :  Dim 24 Juillet 2016 à 22:02
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
(26, 32, NULL, NULL),
(26, 33, NULL, NULL),
(26, 34, NULL, NULL),
(26, 35, NULL, NULL),
(26, 36, NULL, NULL),
(26, 37, NULL, NULL),
(26, 38, NULL, NULL),
(29, 39, NULL, NULL),
(29, 40, NULL, NULL),
(29, 41, NULL, NULL),
(29, 42, NULL, NULL),
(29, 43, NULL, NULL),
(29, 44, NULL, NULL),
(29, 37, NULL, NULL),
(29, 38, NULL, NULL),
(28, 39, NULL, NULL),
(28, 33, NULL, NULL),
(28, 45, NULL, NULL),
(28, 46, NULL, NULL),
(28, 47, NULL, NULL),
(28, 48, NULL, NULL),
(28, 49, NULL, NULL),
(27, 49, NULL, NULL),
(27, 50, NULL, NULL),
(27, 33, NULL, NULL),
(27, 52, NULL, NULL),
(27, 53, NULL, NULL),
(27, 54, NULL, NULL),
(27, 55, NULL, NULL),
(27, 35, NULL, NULL),
(26, 56, NULL, NULL),
(30, 60, NULL, NULL),
(30, 61, NULL, NULL),
(30, 62, NULL, NULL),
(30, 63, NULL, NULL),
(30, 64, NULL, NULL),
(30, 66, NULL, NULL),
(30, 65, NULL, NULL),
(30, 67, NULL, NULL),
(38, 71, NULL, NULL),
(38, 72, NULL, NULL),
(38, 73, NULL, NULL),
(38, 74, NULL, NULL),
(38, 75, NULL, NULL),
(38, 76, NULL, NULL),
(38, 77, NULL, NULL),
(38, 78, NULL, NULL),
(37, 80, NULL, NULL),
(37, 81, NULL, NULL),
(37, 82, NULL, NULL),
(32, 41, NULL, NULL),
(32, 40, NULL, NULL),
(32, 39, NULL, NULL),
(45, 147, NULL, NULL),
(45, 144, NULL, NULL),
(45, 136, NULL, NULL),
(44, 127, NULL, NULL),
(44, 126, NULL, NULL),
(44, 125, NULL, NULL),
(44, 124, NULL, NULL),
(43, 129, NULL, NULL),
(43, 130, NULL, NULL),
(43, 112, NULL, NULL),
(42, 138, NULL, NULL),
(42, 137, NULL, NULL),
(42, 140, NULL, NULL),
(41, 130, NULL, NULL),
(41, 129, NULL, NULL),
(41, 145, NULL, NULL),
(40, 126, NULL, NULL),
(40, 116, NULL, NULL),
(39, 113, NULL, NULL),
(39, 118, NULL, NULL),
(36, 37, NULL, NULL),
(36, 45, NULL, NULL),
(36, 34, NULL, NULL),
(36, 73, NULL, NULL),
(35, 83, NULL, NULL),
(35, 99, NULL, NULL),
(35, 101, NULL, NULL),
(34, 108, NULL, NULL),
(34, 107, NULL, NULL),
(34, 109, NULL, NULL),
(33, 118, NULL, NULL),
(33, 119, NULL, NULL),
(33, 120, NULL, NULL),
(33, 123, NULL, NULL),
(33, 122, NULL, NULL),
(31, 116, NULL, NULL),
(31, 112, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `Ingredient`
--

CREATE TABLE `Ingredient` (
  `idIngredient` int(11) NOT NULL,
  `nameIngredient` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=148 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `Ingredient`
--

INSERT INTO `Ingredient` (`idIngredient`, `nameIngredient`) VALUES
(32, 'steak de bœuf '),
(33, 'oignon'),
(34, 'beurre'),
(35, 'vin rouge'),
(36, 'sauce soja'),
(37, 'sel'),
(38, 'poivre'),
(39, 'maquereau'),
(40, 'huile d''olive'),
(41, 'riz blanc japonais'),
(42, 'thé de kombucha salé'),
(43, 'shio kombu émincé'),
(44, 'herbes mitsuba'),
(45, 'oeuf'),
(46, 'chapelure'),
(47, 'huile de sésame'),
(48, 'sauce ponzu'),
(49, 'pommes de terres'),
(50, 'champignons'),
(51, 'oignon blanc'),
(52, 'romarin'),
(53, 'bacon'),
(54, 'sake'),
(55, 'persil'),
(56, 'ailes de poulet'),
(57, 'tiges de ciboule'),
(58, 'sucre'),
(59, 'dashi'),
(60, 'noix'),
(61, 'germes de blé'),
(62, 'champignons de Paris'),
(63, 'purée de noisette'),
(64, 'genmai miso'),
(65, 'grains de poivre noir'),
(66, 'levure maltée'),
(67, 'eau'),
(68, 'champignons de Paris'),
(69, 'vinaigre de pomme'),
(70, 'poire'),
(71, 'grains d''avoine'),
(72, 'banane'),
(73, 'lait de coco'),
(74, 'noix de coco'),
(75, 'graines de lin moulues'),
(76, 'vanille'),
(77, 'fruits rouges'),
(78, 'baies de goji'),
(79, 'noix'),
(80, 'lentilles jaunes'),
(81, 'lait d''amande'),
(82, 'carottes'),
(83, 'aubergine'),
(84, 'poivron rouge'),
(85, 'gousses d''ail'),
(86, 'graines de coriandre'),
(87, 'épices ras-el-hanout'),
(88, 'cumin entier'),
(89, 'piment'),
(90, 'riz rond'),
(91, 'vinaigre de riz'),
(92, 'mirin'),
(93, 'graines de chia'),
(94, 'flocons d''avoine'),
(95, 'flocon de sarrasin'),
(96, 'graines de sarrasin entières'),
(97, 'tamari'),
(98, 'raisins secs'),
(99, 'potimarron'),
(100, 'tofu'),
(101, 'choux de Bruxelles'),
(102, 'avocatpurée de cajou'),
(103, 'levure maltée'),
(104, 'miso brun'),
(105, 'farine de riz'),
(106, 'sucre de coco'),
(107, 'dattes'),
(108, 'okara d''amande'),
(109, 'cacao cru'),
(110, 'graines de sésame'),
(111, 'fèves de cacao'),
(112, 'chocolat noir'),
(113, 'riz complet'),
(114, 'poudre de noisette toastée'),
(115, 'purée d''amande blanche'),
(116, 'huile de coco'),
(117, 'bicarbonate de soude'),
(118, 'endive'),
(119, 'pois chiches'),
(120, 'graines de courges'),
(121, 'graines de sésame'),
(122, 'moutarde'),
(123, 'colza'),
(124, 'cranberries'),
(125, 'rhubarbe'),
(126, 'sirop d''agave'),
(127, 'vodka'),
(128, 'byaourt soja'),
(129, 'poudre d''amandes'),
(130, 'farine de coco'),
(131, 'farine de pois chiches'),
(132, 'coulis de tomates'),
(133, 'sauce tomates'),
(134, 'tofu rosso'),
(135, 'ail'),
(136, 'paprika'),
(137, 'crème de soja'),
(138, 'feuilles de lasagnes'),
(139, 'maïzena'),
(140, 'arrow root'),
(141, 'vanille'),
(142, 'sucre de canne'),
(143, 'courgette'),
(144, 'graines de chanvre'),
(145, 'purée de noix de cajou'),
(146, 'myrtille'),
(147, 'framboise');

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
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `Recipe`
--

INSERT INTO `Recipe` (`idRecipe`, `imgRecipe`, `titleRecipe`, `contentRecipe`, `scoreRecipe`, `pollRecipe`, `urlRecipe`) VALUES
(26, '/img/cooking/cooking_1.png', 'RIZ FURIKAKE REVISITÉ PAR SÔMA', '1/ Chauffer 2 cuillères à soupe d''huile de sésame dans la poêle et faire revenir les oignons et le poulet et laisser dorer doucement à feu doux.\r\n<br>\r\n2/ Verser le contenu de la poêle dans une marmite chauffée à feu doux et ajouter 2 cuillères à soupe de sucre, 2 cuillères à soupe de saké et 2 cuillères à soupe de sauce soja.\r\n<br>\r\n3/ Ajouter de l''eau dans la marmite (environ 2 fois le volume des ingrédients) et ajouter le dashi, laisser mijoter à feu vif. Ecumer régulièrement le bouillon jusqu''à ce qu''il réduise de moitié.\r\n<br>\r\n4/Une fois le bouillon prêt, verser le contenu de la marmite dans un moule rectangulaire en utilisant une passoire pour ne garder que le liquide, puis mettre le moule au frigo (compter une heure ou deux).\r\n<br>\r\n5/ Une fois le bouillon solidifié, lancer la cuisson du riz : laver le riz à l''eau froide pour le débarrasser de l''excédent d''amidon, puis le faire cuire dans une casserole avec une phalange d''eau au dessus du niveau du riz, pendant 20 minutes - ou, plus simple, utiliser un cuiseur de riz.\r\n<br>\r\n6/ Battre les 4 oeufs dans un bol avec une cuillère à soupe de sucre et une pincée de sel. Verser dans une poêle chauffée à feu moyen et brouiller les oeufs. Les réserver dans un bol.\r\n<br>\r\n7/ Démouler le bouillon solidifié et le couper en petits cubes d''1 cm.\r\n<br>\r\n8/ Verser les oeufs et les cubes de bouillon sur le riz fumant et terminer par une poignée de ciboule découpée en fines rondelles.\r\n<br>\r\n9/ Bon appétit !\r\n<br>', 12, 12, 'http://animedigitalnetwork.fr/news-anime-manga/170-food-wars-recette-n-1-riz-furikake-revisite-par-soma'),
(27, '/img/cooking/cooking_2.png', 'LE VRAI FAUX RÔTI DE PORC DE SÔMA', '1/ Préparer votre bandeau. Une étape cruciale. Sans votre bandeau toute l''opération est vouée à l''échec. On vous aura prévenus.\r\n<br>\r\n2/ Eplucher les pommes de terre.\r\n<br>\r\n3/ Laver les pommes de terre puis les couper en deux ou en quatre.\r\n<br>\r\n4/ Hacher les champignons. Possible de remplacer par 1/4 de tête de chou-fleur comme l''a fait Ani-TAY ci-dessous.\r\n<br>\r\n5/ Emincer l''oignon. Pour une vrai technique de pro, trancher finement l''oignon dans un sens sans aller jusqu''au bout, et le trancher ensuite dans l''autre sens. Vous obtiendrez ainsi directement de fines hachures à la façon de Sôma.\r\n<br>\r\n6/ Cuire les pommes de terre à la vapeur jusqu''à ce qu''elles soient tendres (15-20 minutes).\r\n<br>\r\n7/ Faire revenir l''oignon et les champignons à la poêle avec un peu de beurre jusqu''à ce qu''ils deviennent bien dorés.\r\n<br>\r\n8/ Get in the zone.\r\n<br>\r\n9/ Ecraser légèrement les pommes de terre dans un large bol à l''aide d''une cuillère en bois. Attention : ne pas trop écraser les pommes de terre pour éviter que le mélange devienne mousseux. Ajouter une pincée de sel.\r\n<br>\r\n10/ Ajouter les oignons et les champignons à la préparation. Mélanger de façon uniforme. Laisser refroidir le mélange quelques minutes puis lui donner la forme d''un rôti.\r\n<br>\r\n11/ Réserver un peu de romarin.\r\n<br>\r\n12/ Envelopper entièrement le "rôti" avec les tranches épaisses de bacon en essayant de le recouvrir intégralement - du mieux que vous pouvez.\r\n<br>\r\n13/ Ficeler le rôti et y glisser quelques brins de romarin. Enfourner à 180°C pendant 30 à 40 minutes, jusqu''à ce que le bacon soit bien doré. Penser à le retourner à mi-cuisson.\r\n<br>\r\n14/ Porter le vin rouge à ébullition dans une poêle chauffée à feu moyen, le faire réduire légèrement puis ajouter le beurre. Ajouter ensuite le sake et la sauce soja. Continuer la cuisson pendant 2-3 minutes.\r\n<br>\r\n18/ Lorsque la cuisson du rôti est terminée, enlever la ficelle et le romarin. Verser délicatement la sauce au vin rouge sur le rôti préalablement placé dans une assiette. Ajouter du persil pour décorer.\r\n<br>\r\n19/ Servir !\r\n<br>', 12, 12, 'http://animedigitalnetwork.fr/news-anime-manga/174-food-wars-recette-n-2-le-vrai-faux-roti-de-porc-de-soma'),
(28, '/img/cooking/cooking_3.png', 'MAQUE-BURGER VITE FAIT DU YUKIHARA', '1/ Dans un bol, mélanger les miettes de maquereau, l''oignon émincé, l''oeuf et la chapelure. Réserver le jus restant de la conserve.\r\n<br>\r\n2/ Malaxer le mélange à la main jusqu''à ce que la préparation soit uniforme, et former deux steaks.\r\n<br>\r\n3/ Faire dorer les steaks des deux côtés dans une poêle à feu moyen avec l''huile de sésame.\r\n<br>\r\n4/ Une fois dorés, couvrir la poêle et réduire le feu pendant 2-3 minutes, afin de rendre les steaks bien tendres, puis les réserver dans une assiette\r\n<br>\r\n5/ Dans la même poêle, à feu moyen, chauffer la sauce ponzu et le jus de maquereau pendant 2 minutes. Ajouter un peu de fécule de pomme de terre pour épaissir.\r\n<br>\r\n6/ Verser la sauce sur les steaks.\r\n<br>\r\n7/ C''est prêt ! Vous pouvez agrémenter d''un peu de daikon râpé et de shiso en guise de décoration si le coeur vous en dit.\r\n<br>', 12, 12, 'http://animedigitalnetwork.fr/news-anime-manga/186-food-wars-recette-n-3-maque-burger-vite-fait-du-yukihara'),
(29, '/img/cooking/cooking_4.png', 'LE MENU SECRET N°20 DU YUKIHIRA', '1/ Saler et poivrer le poisson, masser délicatement pour faire pénétrer. Bien faire chauffer l''huile d''olive dans une poêle avant d''y déposer le poisson côté peau.\r\n<br>\r\n2/ A l''aide d''une petite cuillère, récupérer l''huile et la verser sur le poisson tout au long de cuisson afin que ce dernier soit uniforme. Dès que le poisson blanchit, le retourner rapidement et cuire l''autre côté.\r\n<br>\r\n3/ Une fois le poisson cuit, le retirer du feu et le couper en morceaux de taille égale.\r\n<br>\r\n4/ Mélanger les lamelles de shio kombu avec le riz japonais cuit.\r\n<br>\r\n5/ Mouiller puis saler vos mains. Faire un petit tas de riz dans la main, ajouter un ou plusieurs morceaux de poisson. Superposer un autre petit tas de riz, puis rouler entre les mains pour faire une boule. Donner une forme triangulaire à l''onigiri petit à petit en mettant une main à plat et en formant un angle avec l''autre.\r\n<br>\r\n6/ Déposer les onigiris chacun dans un bol et verser le shio kombucha chaud. Décorer avec les herbes mitsuba ou le persil.\r\n<br>\r\n7/ C''est prêt !', 12, 12, 'http://animedigitalnetwork.fr/news-anime-manga/205-food-wars-recette-n-4-le-menu-secret-n-20-du-yukihira'),
(30, '/img/cooking/cooking_5.jpg', 'Raw Tex-Mex : Tortillas & Chili sin Carne', 'La veille ou deux heures avant, faites tremper les graines de lin dans l’eau.\r\n<br>\r\nCommencez par retirer les feuilles des épis de maïs et ôter la barbe.\r\n<br>\r\nA l’aide d’un couteau, ‘plumez’ vos épis de leurs grains et récoltez ceux-ci.\r\n<br>\r\nMettez ensuite l’ensemble des ingrédients (graines de lin avec leur eau comprises) dans le bol d’un mixeur et mixez afin d’obtenir un mélange homogène. Plus vous mixerez, plus vous obtiendrez des tortillas ‘lisses’ : de mon côté, j’aime bien conserver un aspect légèrement rustique en mixant un peu moins longtemps.\r\n<br>\r\nRépartissez ensuite la pâte obtenue en 12 cercles sur 3 feuilles de paraflexx (papier cuisson ou plaque huilée pour la version au four).\r\n<br>\r\nA l’aide d’une spatule, étalez la pâte uniformément en couches de 2 à 3 mm d’épaisseur \r\n<br>\r\nEnfournez ensuite au déshydrateur à 45°C pendant 3h-3h30 environ. Pour la version four : réglez votre four à basse température et enfournez pour 1h30-2h.\r\n<br>\r\nRetournez ensuite vos 3 feuilles de paraflexx sur 3 plateaux ‘nus’ du déshydrateur et soulevez ensuite très délicatement les feuilles. Si vous avez des difficultés à décoller vos tortillas, c’est que celles-ci ne sont pas assez sèches et nécessitent encore 1h de déshydratation. Réenfournez ensuite pour 2h. \r\n<br>\r\nPour la version four : retournez délicatement vos tortillas sur l’autre face et enfournez pour 1h. Les tortillas doivent pouvoir se plier facilement et ne doivent pas être cassantes.\r\n<br>', 12, 12, 'https://antigonexxi.com/2012/09/03/crrrawzy-tex-mex/'),
(31, '/img/cooking/cooking_6.jpg', 'Le moelleux au chocolat PARFAIT ♥ { sans gluten,produits laitiers,sucre raffiné }', 'Dans un récipient, mélangez les jaunes d’œufs avec le sucre de coco.Ajoutez ensuite l''huile de coco et la purée d''amande et mélangez à nouveau.\r\n<br>\r\nFaire fondre le chocolat au bain marie et pendant ce temps, montez les blancs en neige dans un autre récipient.\r\n<br>\r\nQuand le chocolat a fondu, l''ajouter au mélange sucre de coco-jaune d''oeufs-huile de coco-purée d''amande et mélangez le tout. Ajoutez ensuite la farine de riz, la poudre de noisette, le bicarbonate et le vinaigre de cidre et mélangez à nouveau.\r\n<br>\r\nDernière étape, incorporez délicatement et au fur et à mesure les blancs montés en neige à la préparation ( pour ne pas les casser et obtenir un gâteau plus léger ) en mélangeant à l''aide d''une spatule sans oublier d''ajouter la pincée de sel.\r\n<br>\r\nHuilez un moule à gâteau ( en verre de préférence ) avec de l''huile de coco et versez y la préparation.\r\n<br>\r\nMettez le gâteau dans le four préalablement chauffé à 180° et le faire cuire pendant environ 25 à 30mn. ( J''obtiens le résultat souhaité à 27 minutes de cuisson avec un four à chaleur tournante ).', 0, 0, 'http://saravissantebeaute.blogspot.fr/2014/12/le-moelleux-au-chocolat-parfait-sans.html'),
(32, '/img/cooking/cooking_11.jpg', '1001 FAÇONS DE FAIRE DES SUSHIS VÉGANES MAISON', '1 à 2h à l’avance, lavez le riz dans une passoire ou un chinois jusqu’à ce que l’eau devienne claire. Égouttez le riz.\r\nVersez le riz dans une casserole. Couvrez d’eau (environ 1 cm au-dessus du riz pour du riz blanc ; 2-3 cm pour du riz complet) et portez à ébullition. Baissez le feu, ne remuez pas et laissez cuire à feu doux-moyen et à couvert jusqu’à ce que le riz ait absorbé l’eau. Une fois cuit, laissez le riz reposer 10 minutes.\r\nDans un petit bol, mélangez le sel, le vinaigre et le mirin (ou le sucre).\r\nA l’aide d’une cuillère, déposez et répartissez le riz dans un plat à gratin (ou un plat à fond plat) en veillant à ne pas écraser les grains. Arrosez du mélange obtenu et remuez délicatement. Recouvrez le riz d’un linge et laissez reposer.', 0, 0, 'https://antigonexxi.com/2016/02/26/1001-recettes-sushis-veganes/'),
(33, '/img/cooking/cooking_8.jpg', 'VEGAN WINTER BOWLS', 'Préchauffez votre four à 180°C.\r\nFaites cuire votre riz selon les indications habituelles.\r\nDécoupez votre potimarron en tranches et coupez vos choux de Bruxelles en deux. Réservez dans un bol. Taillez 4 tranches de tofu.\r\nPréparez la marinade : pressez l’ail et l’orange, et mélangez intimement l’ensemble des ingrédients.\r\nVersez la marinade sur le potimarron et les choux de Bruxelles et mélangez intimement (avec les mains !). Disposez les légumes sur une plaque revêtue de papier cuisson. Déposez les tranches de tofu sur la marinade restée au fond du bol et mélangez délicatement. Déposez sur la plaque de cuisson. Enfournez le tout 30-35 minutes, en retournant le tofu et les choux à mi-cuisson et en ajoutant un peu de marinade restante (ou, à défaut, d’eau) si le tofu vous paraît sec.\r\nPeu avant la fin de la cuisson, nettoyez le kale, ôtez les côtes et hachez-le (comme expliqué ici).\r\nPrélevez 1-2 tranches de potimarron et placez-la dans un mixer avec 1 CàS de sauce soja et 1 CàS d’huile de sésame grillée. Ajoutez 2-3 CàS d’eau, ou plus au besoin. Mixez le tout pour obtenir une sauce fluide. Massez le kale avec 2-3 CàS de la sauce obtenue.\r\nAssemblez vos bols : répartissez le riz dans les bols, ajoutez le kale, les légumes et le tofu rôti, ainsi que l’avocat coupé en dés. Versez quelques cuillères de sauce, parsemez de graines de sésame et de kumquats en tranches fines, et servez sans attendre. Régalez-vous !\r\n', 0, 0, 'https://antigonexxi.com/2016/01/19/vegan-winter-bowls/'),
(34, '/img/cooking/cooking_9.jpg', 'Barres énergetiques crues & vegan au chocolat à base d''okara { + autres parfums }', 'Dans un mixeur, versez l''okara ( bien séchée ! ) et les dattes dénoyautées puis mixez jusqu''à obtenir une pâte homogène. Ensuite, ajoutez la poudre de cacao cru, les graines de sésame puis mélangez à nouveau. A l''aide d''un mortier et d''un pilon, écrasez des fèves de cacao afin de les réduire en éclat et incorporez les au mélange.\r\nSur du papier sulfurisé, formez des petits tas de 50g pour avoir des barres de même taille et roulez-les en boudins. A l''aide de deux plaques par exemple, pressez tous les côtés de la barres afin de former des barres bien rectangulaires. \r\n> Mettez les barres au frais 2-3h pour qu''elles durcissent <', 0, 0, 'http://saravissantebeaute.blogspot.fr/2015/12/barres-energetiques-crues-vegan-au.html'),
(35, '/img/cooking/cooking_10.jpg', 'SOUPE DE POTIMARRON & GRANOLA SALÉ AU FROMAGE {vegan + sans gluten}', 'Après l’avoir nettoyé à l’eau, coupez le potimarron en deux, enlevez les graines, puis coupez-le en gros cubes (pas besoin de l’éplucher, z’êtes fous !).\r\nDéposez les cubes dans une grande casserole à fond épais (une marmite en fonte ou une casserole basse-température, c’est le pied). Ajoutez les pommes de terre coupées en quarts, ainsi que le romarin. Salez et poivrez à votre convenance. Couvrez d’eau sans dépasser la hauteur du potimarron. Menez à ébullition, puis baissez le feu et laissez mijoter à feu doux et à demi-couvert (couvercle, mais 1 cm d’air) pendant 45 minutes environ ou jusqu’à ce que le potimarron soit très tendre.\r\nAjoutez le lait, l’ail et la purée de noisette, et mixez le tout. Ajoutez les noix et mélangez à la cuillère.\r\nServez sans attendre, parsemé de granola (et, au besoin, accompagné d’un peu de crème de cajou !).', 0, 0, 'https://antigonexxi.com/2014/11/06/soupe-de-potimarron-granola-sale-au-fromage-vegan-sans-gluten/'),
(36, '/img/cooking/cooking_12.jpg', 'Délicieuses crêpes sucrées/salées sans gluten, sans produits laitiers, sans sucre raffiné ', 'La recette est on ne peut plus simple ! Mettez la farine de riz dans un récipient puis faire un puit au milieu. Incorporez les œufs dans le puit puis ajoutez la moitié du lait d''amande. Mélangez puis ajoutez le reste de lait d''amande, l''huile d''olive, le sel et le sucre jusqu''à obtenir une pâte fluide sans grumeaux.\r\n\r\nCouvrez et laissez reposer la pâte 1h à température ambiante avant de faire les crêpes.', 0, 0, 'http://saravissantebeaute.blogspot.fr/2015/01/delicieuses-crepes-sucreessalees-sans.html'),
(37, '/img/cooking/cooking_13.jpg', 'SOUPE ORIENTALE AUX LENTILLES DORÉES', 'Si tel est le cas, je vous propose alors un petit plat vite fait bien fait qui vous aidera, une fois de plus, à combattre le petit temps frisquet et, bien sûr, à lutter contre le coup de mou lié à la grisaille. Quelques saveurs épicées, de beaux légumes de presqu’été, et il ne vous en faudra pas beaucoup plus pour vous réchauffer le corps !\r\n\r\nDois-je ajouter que chaque portion de ma recette vous apportera plus de la moitié de vos besoins quotidiens en protéines ? Accompagnez cette soupe de tranches de pain complet, et vous aurez des protéines complètes en un seul repas !\r\n\r\nEt presque autant de vos besoins en fer ? (la seconde prétendue ‘bête noire’ des végéta*iens !). Celui-ci sera d’ailleurs encore mieux absorbé si vous l’accompagner d’une petite portion de vitamice C : jus de citron, agrumes, crudités… Vous avez l’embarras du choix !\r\n\r\nSi vous avez décidé de faire du bien à votre corps autant qu’à vos papilles, alors cette recette est pour vous !', 0, 0, 'https://antigonexxi.com/2012/04/09/et-tes-proteines/'),
(38, '/img/cooking/cooking_14.jpg', 'RAWBERRY BREAKFAST', 'La veille au soir, mettez à tremper l’avoine dans de l’eau fraîche. Je fais également tremper mes amandes pour une meilleure digestibilité (et un croquant sans pareil !)\r\nLe matin, rincez bien vos grains d’avoine à l’eau claire et égouttez-les.\r\nPlacez ensuite les ingrédients de la crème dans le bol d’un mixeur et mixez jusqu’à obtenir un mélange homogène. Si celui-ci ne vous paraît pas assez sucré, alors ajoutez du sirop d’agave ou un autre édulcorant naturel, selon vos goûts.\r\nMélangez ensuite les accompagnements à la crème comme bon vous semble : à la façon d’un parfait, en fraisier, comme topping… Vous êtes libres !', 0, 0, 'https://antigonexxi.com/2012/07/08/rawberry-breakfast/'),
(39, '/img/cooking/cooking_15.jpg', 'Tartelettes Forestières de Poire aux Noix', 'Mettez les noix à tremper dans de l’eau fraîche pendant 1h environ.\r\nRincez-les bien et déposez-en les 2/3 dans le bol d’un mixer avec le reste des ingrédients du fond de tarte. Hachez le 1/3 restant grossièrement et réservez.\r\nMixez pour obtenir une pâte homogène, ajoutez ensuite les noix hachées et mélangez intimement.\r\nRépartissez la pâte obtenue sur chaque assiette dans deux emporte-pièce légèrement huilés et pressez délicatement. Déposez ensuite au réfrigérateur pour 1h.\r\nPendant ce temps, préparez la marinade : diluez le miso dans l’eau et le vinaigre de pomme.\r\nÔtez les pieds des champignons et coupez les chapeaux en fines lamelles. Déposez celles-ci dans une assiette creuse et imprégnez-les de marinade. Laissez reposer à température ambiante pendant 1h.\r\nCoupez la poire en deux, épluchez-la et ôtez-en le cœur. Hachez grossièrement l’une des deux moitiés et répartissez-la sur les deux fonds de tarte.\r\nEgouttez légèrement les champignons et répartissez-les sur les tartares de poire.\r\nRetirez délicatement les emporte-pièce. Saupoudrez de levure maltée, décorez de lamelles de poire et de canneberges fraîches ou séchées, et servez.', 0, 0, 'https://antigonexxi.com/2013/01/02/le-2e-jour-de-lannee/'),
(40, '/img/cooking/cooking_16.jpg', 'SMOOTHIE BOWL', 'Encore plus nourrissant que le smoothie, laissez-moi vous présenter le smoothie bowl. C’est comme son nom l’indique, un smoothie dans un bol … Mais pas n’importe quel smoothie, non. Il s’agit d’un smoothie agrémenté de superfoods pour faire le plein d’énergie ! Le smoothie bowl est personnalisable à l’infini, en fonction de vos goûts et de vos besoins.\r\n\r\n \r\n\r\nLa base (protéinée) : mixez\r\n\r\n125 g de yaourt de soja aromatisé au goût que vous préférez (myrtille, framboise, vanille …)\r\n1 cs de graines de chanvre\r\n1 cs de purée de noix de cajou et du sirop d’agave selon vos goûts.\r\n1 banane (ou + si vous avez un plus gros appétit que moi) coupée en rondelles\r\nLe topping (glucides et céréales)\r\n\r\nAgrémentez votre base de smoothie d’une ou deux portions de fruits (banane, myrtilles, framboises),\r\nD’une portion de flocons de sarrasin (riz, millet, avoine … privilégiez les céréales complètes)\r\nEt enfin, de superfoods (cranberries, goji, graines de sésame, graines de courge, pistaches, noix …).', 0, 0, 'http://www.galasblog.com/smoothie-bowl-un-petit-dejeuner-hyper-proteine-et-ultra-vitamine/'),
(41, '/img/cooking/cooking_17.jpg', 'GÂTEAU AU CHOCOLAT VEGAN & HEALTHY … A LA COURGETTE !', 'Faîtes fondre le chocolat au bain marie avec l''huile de coco.\r\nMélangez les ingrédients secs : farines, maïzena, sucre, vanille, levure.\r\nAjoutez la courgette râpée et votre mélange chocolat / huile, mélangez.\r\nEcrasez vos noix, noisettes et ajoutez les à votre préparation.\r\nVersez votre préparation dans un moule à brownie ou à gâteau.\r\nFaites le cuire entre 15 et 30 minutes à 180°C (mon four chauffe très lentement, je fais cuire mon gâteau 30 min à 220°C).', 0, 0, 'http://www.galasblog.com/gateau-au-chocolat-vegan-healthy-a-la-courgette/'),
(42, '/img/cooking/cooking_18.jpg', 'LES LASAGNES VEGAN (ET SANS GLUTEN)', 'Emiettez le tofu rosso et découpez les champignons.\r\nDans un grand bol, mélangez le tofu, les champignons, la sauce tomates et le coulis de tomates.\r\nAjoutez les épices, mélangez.\r\nPrenez un grand plat et disposez des feuilles de lasagnes dans le fond.\r\nRecouvrez de votre mélange tomates / tofu / champignons.\r\nAjoutez un peu de crème soja et des graines de sésame puis recouvrez de feuilles de lasagnes.\r\nRecommencez jusqu''en haut de votre plat.\r\nFaites cuir 30 minutes à 180°C.', 0, 0, 'http://www.galasblog.com/jai-teste-les-lasagnes-vegan-et-sans-gluten-de-cherry-pepper/'),
(43, '/img/cooking/cooking_19.jpg', 'PETITS FONDANTS AU CHOCOLAT (VEGAN ET SANS GLUTEN)', 'Faites fondre le chocolat et le beurre au bain marie.\r\nA froid, ajoutez chaque ingrédient dans cet ordre et en mélangeant entre chaque ajout : yaourt de soja, purée de noisettes, sucre, poudre d''amandes, farine de coco, farine de pois chiches.\r\nGarnissez vos petits moules à muffins de pâte.\r\nEnfournez 35 minutes à 180°C (plus ou moins en fonction de votre four, personnellement, je mets le mien à 220°C, il chauffe de moins en moins).', 0, 0, 'http://www.galasblog.com/ma-recette-de-petits-fondants-au-chocolat-vegan-et-sans-gluten/'),
(44, '/img/cooking/cooking_20.jpg', 'COCKTAIL CRANBERRIES/RHUBARBE HEALTHY', 'Coupez la rhubarbe en petits dés.\r\nFaites macérer les cranberries et les dés de rhubarbe dans 150 ml d''eau pendant 1h.\r\nMixez les cranberries et la rhubarbe avec l''eau puis filtrez.\r\nAjoutez le sirop d''agave, le jus de citron, la vodka et mélangez.\r\nC''est prêt !', 0, 0, 'http://www.galasblog.com/cocktail-cranberriesrhubarbe-healthy-a-lheure-de-lapero/'),
(45, '/img/cooking/cooking_21.jpg', 'SALADE MINIMALISTE NOURRISSANTE', 'Faites griller les graines de courge au four pendant 5 minutes (optionnel)\r\nDécoupez les endives, le tofu et mélangez le tout avec les pois chiches.\r\nPréparez la sauce, mélangez la moutarde et l''huile de colza, ajoutez ensuite la sauce soja et continuez de mélanger.\r\nSortez les graines de courge du four et ajoutez les à la salade. Saucez à votre convenance.\r\nSalez et poivrez si besoin.', 0, 0, 'http://www.galasblog.com/salade-minimaliste-nourrissante-vegan-healthy-sans-gluten/');

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
  MODIFY `idIngredient` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=148;
--
-- AUTO_INCREMENT pour la table `Recipe`
--
ALTER TABLE `Recipe`
  MODIFY `idRecipe` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=46;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `COMPOSE`
--
ALTER TABLE `COMPOSE`
  ADD CONSTRAINT `compose_ibfk_1` FOREIGN KEY (`idIngredient`) REFERENCES `Ingredient` (`idIngredient`),
  ADD CONSTRAINT `compose_ibfk_2` FOREIGN KEY (`idRecipe`) REFERENCES `Recipe` (`idRecipe`);
