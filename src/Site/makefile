# Installe les dépendances
dep: package.json
	npm install

# Démarre l'application
start: 
	npm start

# On supprime toutes les modules importés
clean: 
	rm -rf node_modules

# Installe les dépendances et démarre l'application
all: dep start

# Ré-importe toutes les dépendances et démarre l'application
buildstart: clean all