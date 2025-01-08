# JIFS-project
JIFS is January Intercontract FullStack Application

## Launch the application

```
docker compose build
docker compose up
```

## Architecture

### Webapp

Angular 19

### Server

Java 23 with Spring Boot 3.4.1

### Database

PostGreSQL 17.2

#### Standalone

```
docker run --name postgres-jifs-db-standalone -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin123 -e POSTGRES_DB=jifsdb -p 5432:5432 -d postgres:17.2

docker start postgres-jifs-db-standalone
```

## Rest

You can find the Postman files in the docs/postman folder. You just need to import those files, and you can test the API.

## Author

* Thibault Azemar (@tazemar): thibault.azemar@gmail.com