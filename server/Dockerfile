# Étape 1 : Utiliser une image OpenJDK 23 et installer Maven
FROM openjdk:23-jdk-slim AS build

# Installer Maven
RUN apt-get update && apt-get install -y maven

# Copier le fichier pom.xml et le répertoire src dans le conteneur
WORKDIR /app
COPY ./server/pom.xml /app/
COPY ./server/src /app/src

# Construire l'artefact JAR de Spring Boot
RUN mvn clean package -DskipTests

# Étape 2 : Créer une image d'exécution minimale avec OpenJDK 23
FROM openjdk:23-jdk-slim

# Copier le fichier JAR depuis l'étape de build
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Exposer le port de l'application Spring Boot
EXPOSE 8080

# Démarrer l'application Spring Boot
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
