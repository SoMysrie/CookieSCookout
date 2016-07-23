-- Structure de la table Ingredient
CREATE TABLE Ingredient
(
  idIngredient   int(11)      NOT NULL PRIMARY KEY AUTO_INCREMENT,
  nameIngredient varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

-- Contenu de la table Ingredient
INSERT INTO
	Ingredient ( nameIngredient )
VALUES
( 'Tomate' ),
( 'Tofu frit (en barquette)' ),
( 'Pomme de terre' ),
( 'Poivron vert' ),
( 'Poivron rouge' ),
( 'Poivron jaune' ),
( 'Sel' ),
( 'Sucre' ),
( 'Farine Blanche' ),
( 'Oeufs' ),
( 'Huile de friture' ),
( 'Huile d''olive' ),
( 'tagliatelles' ),
( 'filet de saumon frais' ),
( 'crème fraîche' ),
( 'beurre' ),
( 'poivre' ),
( 'muscade' ),
( 'parmesan râpé' ),
( 'lait d''amande') ;