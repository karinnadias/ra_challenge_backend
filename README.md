# API RESTful RA_Challenge_Backend
API RESTful com Spring Boot, Java 8, MongoDB e Spring Security 
### Como executar a aplicação
Certifique-se de ter o Maven instalado e adicionado ao PATH de seu sistema operacional, assim como o Git.

git clone https://github.com/karinnadias/ra_backend_challenge.git  
mvn spring-boot:run  
Acesse  http://localhost:8080/api/complain  
Utilize o usuário "user" e a senha que é impressa no terminal ao executar a aplicação para autenticar.

### APIs endpoints (acessei utilizando o Postman)
GET http://localhost:8080/api/complain [lista todas as complains]  
GET http://localhost:8080/api/complain/{id} [lista uma complain por ID]  
GET  http://localhost:8080/api/complain/qtComplainbyLocaleandComp/{LOCALE}&{COMPANY} [qtde de complains por Locale e Company]  
POST http://localhost:8080/api/complain [cadastra um novo complain]  
PUT http://localhost:8080/api/complain/{id} [atualiza os dados de uma complain]  

### Importando o projeto no Eclipse
No terminal, execute a seguinte operação:
mvn eclipse:eclipse


## No Eclipse importe o projeto como projeto Maven.
