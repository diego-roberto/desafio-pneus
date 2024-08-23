# Desafio Técnico | Back-end | Java

<h3>
<img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white"/>
<img src="https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot"/>
<img src="https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white"/>
<img src="https://img.shields.io/badge/PostgeSQL-003545?style=for-the-badge&logo=postgre&logoColor=white"/>
<img src="https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white"/>
</h3>

API em Java 17 com spring-boot 3.3.2</br>
Base de dados PostgreSQL 13
</br>

## Executando em ambiente local com Docker 🐋
A partir da pasta raiz do projeto, onde se encontra o arquivo docker-compose.yml, execute o comando para iniciar o container:
> docker-compose up --build
>

Feito isso, verifique se os containers estão em execução e faça uma requisição na API utilizando Postman, ou outro software de sua preferência:
> localhost:8080/veiculos/findAll
>
> localhost:8080/veiculos/findByPlaca
>
</br>

### Exemplo de requisição:

<a href="https://postman.com" target="_blank" rel="noreferrer">
  <img src="https://www.vectorlogo.zone/logos/getpostman/getpostman-icon.svg" alt="postman" width="28"
    height="28" />
</a>

<p align="center">
  <img width="800" height="320" src="desafio-pneus/src/main/resources/assets/postman-ex.png">
</p>

</br>

O container postgresql é dependência do container da API, então irá iniciar antes do build, automaticamente.
Senão, utilize o comando abaixo antes de executar o backend novamente:
> docker-compose up -d postgresql
>

</br>

## Executando em ambiente local com Maven
### Na raiz da pasta do backend, execute:
> mvn install
>
> mvn spring-boot:run 
> 

#### Ou utilize a IDE de sua preferência.

</br>

# Modelagem de dados

<p align="center">
  <img width="460" height="400" src="desafio-pneus/src/main/resources/assets/db.png">
</p>

# Estrutura dos Containers

<p align="center">
  <img width="480" height="280" src="desafio-pneus/src/main/resources/assets/docker.png">
</p>