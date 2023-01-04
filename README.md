# Project-JAVA-M1-TOURNAMENT-MANAGER

## Installation

Via docker pour la BDD :
```
docker run --name tournament-manager -p 3306:3306 -e MYSQL_USER=tournament -e MYSQL_PASSWORD=tournament -e MYSQL_DATABASE=tournamentmanager -e MYSQL_ROOT_PASSWORD=root -d mysql
```

### Pour lancer la H2Database :
Dans le fichier .env -> mettre une autre valeur que prod

### Paramètre d'intéraction avec la BDD :
Changer la(les) valeur(s) des paramètres pour l'intéraction avec la BDD dans la class DatabaseConfig :

"hibernate.hbm2ddl.auto" -> "create-drop", "none" ou autre valeur à votre convenance.