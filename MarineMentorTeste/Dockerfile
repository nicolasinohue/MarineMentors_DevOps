# Utilizando a imagem base do OpenJDK
FROM openjdk:17-jdk-slim

# Criar um grupo e um usuário não root
RUN groupadd -r appgroup && useradd -r -s /bin/false -g appgroup appuser

# Definindo o diretório de trabalho
WORKDIR /app

# Copiando o arquivo JAR da aplicação para o container
COPY target/Challenge-0.0.1-SNAPSHOT.jar app.jar

# Ajustar permissões do arquivo JAR
RUN chown -R appuser:appgroup /app

# Definindo variáveis de ambiente
ENV SPRING_PROFILES_ACTIVE=prod
ARG JAR_FILE=app.jar

# Executar a aplicação como o usuário não root
USER appuser

# Executando a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
