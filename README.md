# User API 
Projeto criado com base em uma aplicação simples para o processo seletivo da empresa<br>
**Attus Procuradoria Digital**.

---

#### User
- Criação de usuário e endereço;
- Listagem de todos os usuários e seus respectivos endereços;
- Pesquisa do usuário pelo Id;
- Edição do Usuário pelo Id em específico (nome e data de nascimento).

#### Address:
- Listagem de todos os endereços;
- Pesquisa de um endereço pelo Id;
- Ediçao de um endereço pelo Id;
- Adição de um endereço com id de um usuário.

---
1. **Instalação de Dependências:**

```bash
mvn install
```
Este comando irá baixar as dependências do projeto e construir o projeto. Ele executa as fases `compile`, `test`, e `package` do ciclo de vida do Maven. O artefato construído geralmente será colocado no diretório `target` do projeto.

2. **Configuração do Banco de Dados PostgreSQL:**
 
**Observação:**
O PostgreSQL deve sempre estar aberto. Caso contrário, a API encerrará sua execução, informando que não encontrou o banco de dados.

- Instale o [PostgreSQL](https://www.postgresql.org/download/)
- Configure a senha do banco de dados no arquivo `src/main/resources/application.properties` na propriedade `spring.datasource.password`. Esta senha deve corresponder a mesma configurada no PostgreSQL.
- Configure o usuário do banco de dados no arquivo `src/main/resources/application.properties` na propriedade `spring.datasource.username`. Este usuário deve corresponder o mesmo configurado no PostgreSQL.
- Abra o PostgreSQL e crie um **Banco de Dados** chamado `userSystem` somente assim a aplicação fara a conexão com o database ou se preferir mude o nome no arquivo `src/main/resources/application.properties` na propriedade `spring.datasource.url`.


3. **Execução do Projeto:**

- Use o seguinte comando para executar o projeto:

```bash
mvn spring-boot:run
```

- Se quiser parar de executar aperte as teclas ```ctrl + c```
- Se no terminal perguntar ``Deseja finalizar o arquivo em lotes (S/N)?`` responda ``S``

4. **Configuração de requisições HTTP (Para Testes)**

- Instale o [Insominia](https://insomnia.rest/) ou um de sua preferência;
- configure e importe dentro do Insominia o projeto [User API](https://drive.google.com/drive/folders/1-7chJvRbEn4hRji5RVLdQBWtbgjZep0G?usp=sharing).

5. **Limpeza do Projeto:**

```bash
mvn clean
```

Este comando remove os arquivos gerados durante a compilação e construção do projeto. Isso é útil se você deseja limpar o projeto antes de construir novamente.
