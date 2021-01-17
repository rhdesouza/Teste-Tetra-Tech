# Teste-Tetra-Tech
Teste Tetra Tech IBGE

### Projeto Maven
Back-end:
- Spring Boot v.2.4.2;

Documentação Serviços:
- Swagger v.2.9.2
	
### Compilação do Projeto
- Abara como projeto Maven Existente no Eclipse;
- Execute o goal `mvn install` para geração do .jar (gerado na pasta target) após execução dos testes;
- Execute o arquivo .jar com o comando "java -jar tetraTech-0.0.1-SNAPSHOT.jar";
- Para testes e documentação do serviço, acesse: `http://localhost:8080/swagger-ui.html`.

### Testes
Foi implemnetado um teste simples de disponibilidade do servico de terceiro (IBGE).
