# Exemplo de Serviço Producer MQ em Java Spring

## Bibliotecas, dependências e frameworks

Essa aplicação utiliza:
- Spring Boot 2.7.0
- Java 1.8
- Maven 4.0
- Docker
- MQ Spring Starter 2.6.7


## Configuração e Execução do Projeto

Para a execução desse projeto é necessário:
- Docker instalado na máquina, conectado a uma instância do IBM MQ.
- Postman instalado localmente, para execução dos testes.


## Spring Boot 2.7.0
O Spring Boot é um framework open source  de desenvolvimento de aplicações web, que suporta as linguagens Java, Kotlin e Groovy, porém nesse projeto apenas a linguagem Java será utilizada.
A criação de um projeto REST de maneira mais simples para o envio de mensagens ao MQ foi a principal utilização do Spring Boot nessa aplicação.

## Maven
O Maven é uma ferramenta utilizada para gerenciar projetos Java, e nesse projeto em questão ela auxiliou no build e no gerenciamento das dependências.

## Docker
O Docker é um serviço PaaS (Platform as a Service) que utiliza da virtualização para empacotar o software em pacotes chamados containers. No projeto atual ele foi utilizado para se conectar a uma instância pré configurada do IBM MQ.

Através do IBM MQ for Developers é possível facilmente configurar um servidor MQ local através do Docker.
Para isso, com o Docker aberto, utilize o comando abaixo:

```dockerfile
docker run --env LICENSE=accept --env MQ_QMGR_NAME=QM1
--publish 1414:1414
--publish 9443:9443
--detach
ibmcom/mq
```

Após utilizá-lo, verifique se funcionou utilizando o comando `docker ps`, o resultado obtido deve ser algo próximo disso:

![docker-ps](https://gitlab.com/ocpoc/chapter-mq/bsad-srv-consumer-mq-spring/uploads/b311cbff7dad5caf9cc2226b72f97e5a/img-docker.PNG)


## MQ Spring Starter 2.6.7
O MQ Spring Starter é uma ferramenta do IBM MQ que facilita a configuração do MQ em projetos que utilizam o framework Spring.


## Dependências e Propriedades do Projeto

### Dependências (pom.xml)

Inicialmente adicione as dependências a seguir no arquivo pom.xml:

```java
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
</dependency>
<dependency>
    <groupId>com.ibm.mq</groupId>
    <artifactId>mq-jms-spring-boot-starter</artifactId>
    <version>2.6.7</version>     
</dependency>
<dependency>
    <groupId>com.twilio.sdk</groupId>
    <artifactId>twilio</artifactId>
    <version>8.28.0</version>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```



## Application.properties

Nessa parte será necessário adicionar as configurações do servidor do MQ.
```java
ibm.mq.queueManager=QM1
ibm.mq.channel=DEV.ADMIN.SVRCONN
ibm.mq.connName=localhost(1414)
ibm.mq.user=admin
ibm.mq.password=passw0rd
```