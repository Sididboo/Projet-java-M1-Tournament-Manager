# Project-JAVA-M1-TOURNAMENT-MANAGER

## Installation

Via docker pour la BDD :
```
docker run --name tournament-manager -p 3306:3306 -e MYSQL_USER=tournament -e MYSQL_PASSWORD=tournament -e MYSQL_DATABASE=tournamentmanager -e MYSQL_ROOT_PASSWORD=root -d mysql
```

Changer le paramètre dans le fichier application.properties :
```spring.jpa.hibernate.ddl-auto=create-drop``` 
ou ```none``` si vous ne voulez pas recharger le shéma de la BDD.