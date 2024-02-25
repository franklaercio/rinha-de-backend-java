# Rinha de Backend 2024 Q1

## Description

This project, Rinha de Backend 2024 Q1, is a banking system simulation developed in Java using the
Spring Boot framework. It provides functionalities such as account management, transaction handling,
and balance inquiries. The system is designed to handle multiple accounts and transactions
concurrently, ensuring data consistency and
reliability. The project uses an SQL database for data persistence and Maven for dependency
management. It's designed for developers who want to understand how to build a robust and scalable
banking system using Spring Boot and Java.

If you want to check more about of the challenge, please check the [Rinha de Backend 2024 Q1](https://github.com/franklaercio/rinha-de-backend-2024-q1).

## Technologies Used

- Java
- Spring Boot
- Maven
- SQL
- JUnit
- Postgres
- Docker
- Nginx

## Learning Objectives

__In this project, you will:__
- Learn how to build a banking system using Spring Boot and Java.
- Understand how to handle transactions and account management in a banking system.
- Learn how to use an SQL database for data persistence.
- Understand how to use Docker to run a database.
- Learn how to use Nginx as a reverse proxy server.

__Concurrent programming learning:__
- Understand how to handle concurrent transactions and account management.
- Learn how to ensure data consistency and reliability in a concurrent environment.
- Understand how to handle multiple requests concurrently.
- Learn how to use locks and synchronization to ensure data consistency.
- Understand how to use transactions to ensure data reliability.
- Learn about problems of deadlocks and how to avoid them.
- Understand how to use thread pools to handle multiple requests concurrently.

__Prerequisites:__
- Basic knowledge of Java and Spring Boot.
- Basic knowledge of Postgres.
- Basic knowledge of Docker.
- Basic knowledge of Nginx.
- Basic knowledge of Maven.

__Study Materials:__
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Java Documentation](https://docs.oracle.com/en/java/)
- [Postgres Documentation](https://www.postgresql.org/docs/)
- [Docker Documentation](https://docs.docker.com/)
- [Nginx Documentation](https://nginx.org/en/docs/)
- [Concurrency in Java](https://www.baeldung.com/java-concurrency)
- [Java Multithreading](https://www.geeksforgeeks.org/multithreading-in-java/)
- [Java Thread Pools](https://www.baeldung.com/thread-pool-java-and-guava)
- [Java Synchronization](https://www.baeldung.com/java-synchronized)
- [Java Transactions](https://www.baeldung.com/java-transactions)
- [Concurrency Programming](https://www.geeksforgeeks.org/java-util-concurrent-package)
- [Concurrency Problems](https://www.geeksforgeeks.org/concurrency-in-operating-system/)

## Setup and Installation

To run this project, you need to have Java 11 and Maven installed on your machine. You also need to
have a Postgres database running. You can use Docker to run the database. Here are the steps to
install and run the project:

1. Clone the repository: `bash git clone https://github.com/franklaercio/rinha-de-backend-java`
2. Navigate to the project directory: `bash cd rinha-de-backend-2024-q1`
3. Run the database using Docker: `bash docker-compose up -d`
4. Run the project using Maven: `bash mvn spring-boot:run`
5. The project will start running on `http://localhost:8080`. 
You can use Postman or any other API client to test the endpoints.
6. To stop the database, run:
7. `bash docker-compose down`
8. To run the tests, run:
9. `bash mvn test`

## Usage

- To create a transaction, send a POST request to `http://localhost:8080/clientes/{id}/transacoes`
with the following JSON body:

  ```json
  {
    "valor": "1000",
    "tipo": "d",
    "descricao": "test"
  }
  ```

- To get a statement, send a GET request to `http://localhost:8080/clientes/{id}/extrato`.

## Contributing

If you want to contribute to this project, you can follow these steps:

1. Fork the project.
2. Create a new branch with your changes:
   ```bash
   git checkout -b feature/my-feature
   ```
3. Save your changes and create a commit message telling what you did:
   ```bash
    git commit -m "My changes"
    ```
4. Push your branch:
5. ```bash
   git push origin feature/my-feature
   ```
6. Open a pull request with a description of your changes.
7. After your changes are approved, they will be merged into the project.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact Information

If you have any questions about this project, please contact me at e-mail.