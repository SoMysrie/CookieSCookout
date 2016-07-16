-- Structure de la table COMPOSE

CREATE TABLE COMPOSE
(
  idRecipe     int(11)      NOT NULL,
  idIngredient int(11)      NOT NULL,
  qty          varchar(255) NULL,
  note         varchar(255) NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

-- Contenu de la table COMPOSE
INSERT INTO
	COMPOSE ( idRecipe, idIngredient, qty, note )
VALUES
	( 1 , 13 , '500 g', 'fraîches de préférence'),
	( 1 , 14 , '1 beau filet de', 'frais (ou surgelé) coupé en dés'),
	( 1 , 15 , '35 cl de', null ),
	( 1 , 16 , '1 noix de', null ),
	( 1 , 17 , null , null ),
	( 1 , 18 , null , null ),
	( 1 , 19 , null , null ),
	( 1 , 7  , null , null ),
	( 2 , 9  , '250 g de', null ),
	( 2 , 10 , '4', null ),
	( 2 , 20 , '1/2 L', null ),
	( 2 , 7  , '1 pincée de', null ),
	( 2 , 8  , '2 cuillères à soupe de ', null ),
	( 2 , 16 , '50 g de', 'fondu');

-- Index pour la table COMPOSE
ALTER TABLE COMPOSE
  ADD KEY idRecipe ( idRecipe ),
  ADD KEY idIngredient ( idIngredient )   ;

-- Contraintes pour la table COMPOSE
ALTER TABLE COMPOSE
  ADD FOREIGN KEY (idIngredient)    REFERENCES Ingredient (idIngredient),
  ADD FOREIGN KEY (idRecipe)        REFERENCES Recipe (idRecipe);
