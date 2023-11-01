# Projeto Sistema de Pedidos

Este repositório contém o código do projeto do Tech Challenge .

## Pré-requisitos

Certifique-se de ter instalado em sua máquina:

- Java JDK
- Apache Maven
- Docker
- Docker Compose

## Passos para criar o JAR

1. Clone o repositório:

    ```bash
    git clone https://github.com/seu-usuario/nome-do-repositorio.git
    ```

2. Navegue até o diretório do projeto:

    ```bash
    cd nome-do-repositorio
    ```

3. Execute o comando Maven para construir o arquivo JAR:

    ```bash
    mvn clean package -DskipTests
    ```

4. O arquivo JAR será gerado no diretório `target/`.

## Executar o Docker Compose

1. No diretório onde está o arquivo `docker-compose.yml`, execute:

    ```bash
    docker-compose up
    ```

Isso irá construir a imagem para execução da aplicação e iniciará os serviços conforme configurado
no `docker-compose.yml`.

## Observações

Certifique-se de que as configurações e nomes de arquivos estejam de acordo com o seu projeto. Estes são exemplos
genéricos e podem variar dependendo da estrutura do seu projeto e de suas necessidades específicas.
