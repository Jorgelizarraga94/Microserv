# 1. Usamos una imagen base oficial de Java (Ajustá el '17' o '21' según tu versión)
FROM eclipse-temurin:17-jdk-alpine

# 2. Creamos un directorio dentro del contenedor para nuestra app
WORKDIR /app

# 3. Copiamos el archivo .jar generado por Maven/Gradle dentro del contenedor
# (Asegurate de haber ejecutado 'mvn clean package' antes)
COPY target/*.jar app.jar

# 4. Comando que se ejecutará al iniciar el contenedor
ENTRYPOINT ["java", "-jar", "app.jar"]