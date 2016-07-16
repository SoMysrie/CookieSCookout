-- Connaitre tous les ingrédients d'une recette
SELECT
	C.qty            AS QUANTITY,
	C.note           AS NOTE    ,
	I.nameIngredient AS INGREDIENT
FROM
	COMPOSE    AS C,
	Ingredient AS I
WHERE
	C.idRecipe = 1 AND
	C.idIngredient = I.idIngredient
;

-- Affiche le nombre d'ingrédient d'une recette
SELECT 
	COUNT(*) AS SIZE
FROM 
	COMPOSE    AS C,
	Ingredient AS I
WHERE
	C.idRecipe = 1 AND
	C.idIngredient = I.idIngredient
;

-- Afficher une recette avec ses ingrédients
SELECT
	R.idRecipe      AS ID_RECIPE      ,
	R.imgRecipe     AS IMG_RECIPE     ,
	R.titleRecipe   AS TITLE_RECIPE   ,
	R.contentRecipe AS CONTENT_RECIPE ,
	R.scoreRecipe   AS SCORE_RECIPE   ,
	R.pollRecipe    AS POLL_RECIPE    ,
	R.urlRecipe     AS URL_RECIPE     ,
	C.qty           AS QUANTITY_ING   ,
	C.note          AS NOTE_ING       ,
	I.idIngredient  AS ID_INGREDIENT  
FROM
	Recipe AS R,
	COMPOSE AS C,
	Ingredient  AS I
WHERE
	C.idRecipe     = R.idRecipe AND
	C.idIngredient = I.idIngredient AND
	R.idRecipe = 1
;
 
-- Supprimer tous les ingrédients d'une recette puis la recette en elle même
DELETE FROM COMPOSE WHERE idRecipe = 1 ;
DELETE FROM Recipe  WHERE idRecipe = 1 ;
