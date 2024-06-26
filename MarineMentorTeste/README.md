# MarineMentors
Aplicação Dockerizada com Java Spring Boot e Oracle DB

## Visão Geral
Este projeto é um portal de telemedicina construído usando Java Spring Boot, com artigos contendo fotos e vídeos, e interação da comunidade. A aplicação é containerizada usando Docker e inclui um banco de dados Oracle para persistência de dados.

## Funcionalidades
- Operações CRUD para usuários
- Artigos com fotos e vídeos
- Interação da comunidade
- Aplicação dockerizada para fácil implantação
- Integração com Oracle DB

## Pré-requisitos
Antes de começar, certifique-se de que você tem os seguintes requisitos atendidos:

- Docker e Docker Compose instalados na sua máquina
- Java JDK 17 instalado
- Maven instalado

## Instalação
1. Clone o repositório:

    ```bash
    git clone https://github.com/nicolasinohue/merine-mentors.git
    cd marine-mentors
    ```

2. Construa a aplicação:

    ```bash
    docker-compose build
    ```

3. Inicie os containers Docker:

    ```bash
    docker-compose up -d
    ```

## Uso
### Acessando a Aplicação
- **Executar container com o comando bash**

    ```bash
    docker-compose run --rm app bash
    ```

### Verificando os Containers
- Verifique o status dos containers em execução:

    ```bash
    docker-compose ps
    ```

### Visualizando Logs
- Visualize os logs do container da aplicação:

    ```bash
    docker-compose logs app
    ```

### Encerrando a Aplicação
- Utilize os comandos de encerramento para excluir containers, imagens e volumes criados:

    ```bash
    docker-compose down -v --rmi all
    ```

## Contribuindo
Para contribuir com este projeto, siga estas etapas:

1. Faça um fork deste repositório.
2. Crie uma branch:

    ```bash
    git checkout -b <nome_da_branch>
    ```

3. Faça suas alterações e commit:

    ```bash
    git commit -m '<mensagem_do_commit>'
    ```

4. Envie para a branch original:

    ```bash
    git push origin <nome_do_projeto>/<local>
    ```

5. Crie a pull request.

Alternativamente, consulte a [documentação do GitHub sobre como criar uma pull request](https://docs.github.com/pt/github/collaborating-with-issues-and-pull-requests/creating-a-pull-request).


