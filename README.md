# XML Web Service Training Project

## Overview

A simple XML Web Service training project built with Spring Boot.

The purpose of this project is to demonstrate how to build a SOAP/XML server that receives, processes, and handles XML requests using Spring Web Services.

This project focuses on learning XML-based web service development, database integration, and running a Spring Boot application with Docker.

## Tech Stack

- Java
- Spring Boot
- Spring Web Services (SOAP)
- Spring Data JPA / Hibernate
- MySQL
- Docker Compose

## Run the Project

Make sure you have Docker installed.

```bash
docker compose up -d
```
The application will be available at: http://localhost:8080

## Configuration

The default configuration uses:
```text
Application port: 8080
Database: MySQL
Database name: xml-web-service
Database username: root
Database password: admin
```
You can modify the application or database configuration in docker-compose.yml
