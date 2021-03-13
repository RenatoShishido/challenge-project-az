# Desafio AZ

&nbsp;

---

&nbsp;
## (FRONTEND)

### Inciar o setup do projeto 

```
npm install
```

### Compilar e executar o codigo (PORT 8080)

```
npm run serve
```

&nbsp;

---

&nbsp;
## (BACKEND)
### Inciar o setup do projeto (PORT 3000)
Voce precisa importar o projeto na sua IDE para rodar o projeto

### Alterer as configuracoes de banco de dados no arquivo application.properties
Mudando para a URL, usuario e senha do seu banco.
```
spring.datasource.platform=postgres
spring.datasource.url=jdbc:postgresql://localhost:5432/doacao
spring.datasource.username=postgres
spring.datasource.password=postgres
```
### Com aplicacao iniciada no banco de dados executa esta QUERY para alterar os dados
Esta query esta no arquivo banco.sql dentro da pasta raiz do projeto.
```
ALTER TABLE utensilio
	ADD CONSTRAINT FK_utensilio_id
	    FOREIGN KEY (cliente_id)
	    REFERENCES cliente(email)
	    ON DELETE CASCADE ON UPDATE NO ACTION;
```


&nbsp;

---

&nbsp;
