# JIFS-project
JIFS is January Intercontract FullStack Application

## Database

```
docker run --name postgres-jifs-db-standalone -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin123 -e POSTGRES_DB=jifsdb -p 5432:5432 -d postgres:17.2

docker start postgres-jifs-db-standalone
```
