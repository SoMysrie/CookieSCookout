-- Structure de la table Recipe
CREATE TABLE Recipe
(
  idRecipe      int(11)      NOT NULL PRIMARY KEY AUTO_INCREMENT ,
  imgRecipe     varchar(255) NOT NULL,
  titleRecipe   varchar(255) NOT NULL,
  contentRecipe text         NOT NULL,
  scoreRecipe   int(11)      NOT NULL,
  pollRecipe    int(11)      NOT NULL,
  urlRecipe     varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Contenu de la table Recipe
INSERT INTO
	Recipe ( imgRecipe, titleRecipe, contentRecipe, scoreRecipe, pollRecipe, urlRecipe )
VALUES
	(
		'URL IMAGE',
		'Tagliatelles au saumon frais',
		'Préparation de la recette :\r\n\r\nCuire les pâtes à l''eau bouillante salée selon le temps indiqué sur le paquet. Pendant ce temps, faire fondre le beurre dans une casserole et y ajouter la crème.\r\n\r\nQuand la crème est bien chaude, y plonger les dés de saumon, ils doivent cuire à la chaleur de la crème.\r\nQuand ils sont cuits (ils doivent se détacher), ajouter le sel, le poivre et la muscade moulue.\r\n\r\nEgoutter les pâtes, les disposer dans un grand plat ronde et napper de crème au saumon.\r\n\r\nServir avec du parmesan.\r\n\r\nRemarques : C''est très nourrissant et convient aussi comme plat principal (pour 4 personnes dans ce cas).',
		5,
		23432,
		'http://www.marmiton.org/recettes/recette_tagliatelles-au-saumon-frais_11354.aspx'
	),
	(
		'URL IMAGE',
		'Crêpes',
		'Mettre la farine dans un saladier avec le sel et le sucre. Faire un puits au milieu et y verser les oeufs légèrement battus à la fourchette. Commencer à incorporer doucement la farine avec une cuillère en bois.\r\n\r\nQuand le mélange devient épais, ajouter le lait froid petit à petit, on peut utiliser un fouet mais toujours doucement pour éviter les grumeaux.\r\n2 Quand tout le lait est mélangé, la pâte doit être assez fluide, si elle vous paraît trop épaisse, rajouter en peu de lait.\r\n\r\nAjouter ensuite le beurre fondu, mélanger bien.\r\nPour finir Cuire les crêpes dans une poêle chaude (pas besoin de matière grasse, elle est déjà dans la pâte). \r\n\r\nVerser une petite louche de pâte dans la poêle, faire un mouvement de rotation pour répartir la pâte sur toute la surface, poser sur le feu et quand le tour de la crêpe se colore en roux clair, il est temps de la retourner. Laisser cuire environ une minute de ce côté et la crêpe est prête. Répéter jusqu''à épuisement de la pâte.',
		3,
		432,
		'http://cuisine.journaldesfemmes.com/recette/333415-crepes'
	)
;