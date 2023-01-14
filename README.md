# Project Java M1 - Tournament Manager

Ce projet est une API en Java Spring Boot permettant d'organiser et piloter des tournois.

## Auteurs

- *Romain J* ([@Skollid](https://www.github.com/Skollid))
- *Hugo M* ([@GOHU6](https://www.github.com/GOHU6))

## Installation

### Branches
- v1-sans-bonus
- v1-avec-bonus

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
"hibernate.hbm2ddl.auto" -> "create-drop", "none" (ou autre valeur à votre convenance)
```

## API Reference
Les points de terminaison de l'API sont accessibles via HTTP. 

Par défaut, les points de terminaison de l'API utilisent le format suivant :
```bash
http://localhost:8080
```


**Joueur :**
```
| Method                  | Description                                  |
| ----------------------- | -------------------------------------------- |
|  GET  /v1/player        |  Visualiser tous les joueurs                 |
| ----------------------- | -------------------------------------------- |
|  GET  /v1/player/${id}  |  Visualiser les joueurs par id               |
```
```
| Method                  | Description                                  | 
| ----------------------- | -------------------------------------------- | 
|  POST /v1/player        |  Créer des joueurs                           |  
```
>[Commande Json pour le POST](#1)
```
| Method                  | Description                                  |
| ----------------------- | -------------------------------------------- |
|  PUT  /v1/player/${id}  |  Modifier les données d'un joueur par son id |
```
>[Commande Json pour le PUT](#2)

**Equipe :**
```
| Method                  | Description                                   |
| ----------------------- | --------------------------------------------- |
|  GET  /v1/team          |  Visualiser toutes les équipes                |
| ----------------------- | --------------------------------------------- |
|  GET  /v1/team/${id}    |  Visualiser les équipes par id                |
```
```
| Method                  | Description                                   |
| ----------------------- | --------------------------------------------- |
|  POST /v1/team          |  Créer des équipes                            |
```
>[Commande Json pour le POST](#3)
```
| Method                  | Description                                   |
| ----------------------- | --------------------------------------------- |
|  PUT  /v1/team/${id}    |  Modifier les données d'une équipe par son id |
```
>[Commande Json pour le PUT](#4)

**Tournois :**
```
| Method                     | Description                                    |
| -------------------------- | ---------------------------------------------- |
|  GET  /v1/tournament       |  Visualiser tous les tournois                  |
| -------------------------- | ---------------------------------------------- |
|  GET  /v1/tournament/${id} |  Visualiser les tournois par id                |
```
```
| Method                     | Description                                    |
| -------------------------- | ---------------------------------------------- |
|  POST /v1/tournament       |  Créer des tournois                            |
```
>[Commande Json pour le POST](#5)
```
| Method                     | Description                                    |
| -------------------------- | ---------------------------------------------- |
|  PUT  /v1/tournament/${id} |  Modifier les données d'un tournois par son id |
```
>[Commande Json pour le PUT](#6)


## Commande JSON

#### <a name="1"></a>Joueur Json POST
```
{
  "pseudo":"...",
  "postalAddress":"..."
}
```
#### <a name="2"></a>Joueur Json PUT
```
{
  "id":...,
  "pseudo":"..."
}
```
#### <a name="3"></a>Equipe Json POST
```
{
  "teamName":"...",
  "playerIds":[...,...]
}
```
#### <a name="4"></a>Equipe Json PUT
```
{
  "id":...,
  "teamName":"...",
  "playerIds":[...,...]
}
```
#### <a name="5"></a>Tournois Json POST
```
{
  "subject":"...",
  "description":"...",
  "nameState":null,
  "dateBegin":"...",
  "teamIds":[...]
}
```
#### <a name="6"></a>Tournois Json PUT
```
{
  "id":...,
  "subject":"...",
  "description":"...",
  "nameState":...,
  "dateBegin":"...",
  "teamIds":[...]
}
```
