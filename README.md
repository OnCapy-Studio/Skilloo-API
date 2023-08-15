<img src="https://raw.githubusercontent.com/uFelps/assets/665a51ad1876f167df4b43810003f012d5295794/Skilloo-API/Logotipo.svg" width="80">



# SKILLOO API
Bem-vindo(a) ao repositório da Skilloo API desenvolvida como parte do Trabalho de Conclusão de Curso (TCC) na Etec de Itaquera. Este repositório contém exclusivamente a parte do backend, responsável por lidar com a lógica de negócios, manipulação de dados e comunicação com o banco de dados. Neste contexto, a Skilloo API é a interface pela qual os sitemas frontend podem interagir com a aplicação, solicitando e enviando dados.


## Sobre
Este repositório é uma API REST  apresenta o desenvolvimento de uma ferramenta de gestão do tempo para professores chamada "Skilloo". O objetivo do projeto é criar um aplicativo web que possibilite a organização do tempo e melhore a produtividade dos professores no gerenciamento de suas tarefas, além de auxiliar no processo de ensino e aprendizagem. O aplicativo diminui a necessidade do professor de utilizar documentos físicos que podem ser facilmente perdidos, disponibilizando funções de criação de tarefas, estabelecimento de prioridades, criação de lembretes, agendamento de salas, anotações e controle de aulas.

&nbsp;

## Tecnologias Utilizadas

Neste projeto, foram empregadas as seguintes tecnologias e ferramentas: Java, Ecossistema Spring e MySQL.
<p align="left"> <a href="https://www.java.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="40" height="40"/> </a> <a href="https://spring.io/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="40" height="40"/> </a><a href="https://www.mysql.com/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/mysql/mysql-original-wordmark.svg" alt="mysql" width="40" height="40"/></a></p>

&nbsp;


## Como Utilizar | Docker
 
Requisitos: Ter o [Docker](https://www.docker.com/products/docker-desktop/) instalado em sua máquina.
\
\
Você pode conferir a imagem docker da API no [Dockerhub](https://hub.docker.com/r/ufelps/skilloo-api)

\
1 | Copie o código no seu terminal e baixe a imagem
```
docker pull ufelps/skilloo-api:v1
```

2 | Confira se a imagem foi baixada com o seguinte comando
```
docker images
```

3 | Instancie um contêiner da imagem docker
```
docker run -p 8080:8080 --name skilloo-api ufelps/skilloo-api:v1
```
Após isso, a API estará rodando na porta 8080 de sua máquina.



&nbsp;

## Cloud | Render
É possível acessar a API na nuvem através da seguinte URL:
```
https://skilloo-api.onrender.com
```

&nbsp;

## Rotas | Postman
Acesse o [Postman](https://www.postman.com/red-comet-846596/workspace/skilloo-api/collection/23677590-26bf0c51-0eea-4dbb-ba5f-a9a3942ec0bf?action=share&creator=23677590) para ver as rotas da API.
\
<br>
<i>Todos os Tokens utilizados no postman não são válidos, são apenas para teste.</i>
<br>
&nbsp;

Após acessa o link, você pode conferir as rotas clicando em <i>View complete documentation</i>

<img src="https://github.com/uFelps/assets/blob/main/Skilloo-API/inicio-postman.png?raw=true" width="600">

<br>

Aqui é a home do postman, você pode navegar pelas rotas de professores e gestores pelas pastas na direita.
\
Também é possível definir o Environment para Skilloo-API no canto superior direito. Dessa forma, você consegue acesso a todas as variáveis de ambiente da workspace.

<img src="https://github.com/uFelps/assets/blob/main/Skilloo-API/home-postman.png?raw=true" width="600">

<br>

Após selecionar uma rota, é possível visualizar como utilizar essa requisição:

<img src="https://github.com/uFelps/assets/blob/main/Skilloo-API/ex_rota.png?raw=true" width="600">

<br>

Caso seja necessário, você pode abrir a requisição para testar, verificar o token utilizado, ou conferir os parâmetros passados no body da requisição:

<img src="https://github.com/uFelps/assets/blob/main/Skilloo-API/ex2_rota.png?raw=true" width="600">

<br>

Variáveis de ambiente da workspace:
\
<i>Estes tokens só são utilizaveis na API de teste, caso esteja usando a API de prod com banco de dados independente, será necessário realizar a autenticação do usuário.</i>
<img src="https://github.com/uFelps/assets/blob/main/Skilloo-API/variaveis_postman.png?raw=true" width="600">