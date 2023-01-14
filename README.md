# Project Java M1 - Tournament Manager

Ce projet est une API en Java Spring Boot permettant d'organiser et piloter des tournois.

## Auteurs

- *Romain J* ([@Skollid](https://www.github.com/Skollid))
- *Hugo M* ([@GOHU6](https://www.github.com/GOHU6))

## Installation

Via docker pour la BDD

```bash
docker run --name tournament-manager -p 3306:3306 -e MYSQL_USER=tournament -e MYSQL_PASSWORD=tournament -e MYSQL_DATABASE=tournamentmanager -e MYSQL_ROOT_PASSWORD=root -d mysql
```
Pour lancer la H2Database
```bash
Dans le fichier .env -> mettre une valeur différente de prod
```
Paramètre d'intéraction avec la BDD

Changer la(les) valeur(s) des paramètres pour l'intéraction avec la BDD dans la class DatabaseConfig :
```bash
"hibernate.hbm2ddl.auto" -> "create", puis "none" (ou autre valeur à votre convenance)
```

## Comptes pour Spring Security
username: user 
password: user
role: USER

username: admin
password: admin
role: ADMIN

## API Reference
Les points de terminaison de l'API sont accessibles via HTTP. 

Par défaut, les points de terminaison de l'API utilisent le format suivant :
```bash
http://localhost:8080
```

### Routes
La liste des routes sont à l'adresse "http://localhost:8080/swagger-ui/index.html#".