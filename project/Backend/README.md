# README #

## Beschreibung
Spring Boot-Backend zum üK223 für die Gruppenspezifische Arbeit der Erstellung von Benutzerprofilen.

## Start Projekt Spring Boot
Voraussetzungen:
<ul>
    <li>Java 18
    <li>Docker/Datenbankverbindung
</ul>

### Setup

#### Docker command
```
docker run --name postgres_db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres
```
Start:
```
Gradle -> demo -> Tasks -> application -> bootRun
```


### Troubleshooting

#### Docker


```
org.postgresql.util.PSQLException: ERROR: relation "role_authority" does not exist
```
Simply restart the application. Hibernate sometimes does not initialize the tables fast enough an causes thios error. restarting the application fixes this.

#### Spring Boot

```
Cause: error: invalid source release: 18
```
Falls in der Projekt Struktur schon die Version Java Version 18 ausgewählt wurde, aber der error immer noch besteht:

File -> Settings -> Build, Execution, Deployment -> Build Tools -> Gradle -> Gradle JVM auf version 18
