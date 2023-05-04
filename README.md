# Stock Management Application

[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-black.svg)](https://sonarcloud.io/dashboard?id=SVladimir_Stock)

## Overview

The Stock Management Application is a simple yet powerful system for managing stock information. It is designed to help you track and manage stock data efficiently and effectively. The application is built using Spring Boot and provides RESTful API endpoints for managing stock and CSV data.

## Features

- Add, update, retrieve stock information through RESTful API endpoints
- Import stock data from CSV files
- API Gateway for service discovery
- Zipkin for distributed tracing

## Prerequisites

- Java 17 or later
- Docker
- Docker Compose

## Installation and Running the Application

To install and run the application, follow these steps:

1. Clone the repository:

git clone https://gitbud.epam.com/vladimir_shmalko/exStock


2. Navigate to the project folder:

cd Stock


3. Build the application using Maven:
mvn clean install --activate-profiles build-image

4. Run the application using Docker Compose: 
docker-compose up -d

This will build and start all the required services, including the Stock Management Application, API Gateway, and Zipkin.

## Usage

### API Gateway

The API Gateway is used to discover available services within the application. It is accessible at `http://localhost:8080`.

### Zipkin

Zipkin is a distributed tracing system that helps to troubleshoot latency issues in microservices. It is accessible at `http://localhost:9411`.
http://localhost:9411/zipkin/

### OpenAPI Documentation

The Stock Management Application provides OpenAPI documentation for its RESTful API endpoints. To view and interact with the API documentation, visit `http://localhost:8081/swagger-ui.html`.
http://localhost:8060/webjars/swagger-ui/index.html
http://localhost:8060/v3/api-docs

### SPRING EUREKA
Discovery server for service management
http://localhost:8061/

### StockController
Get stock history for a specific symbol:

GET /api/stock/{symbol}
http://localhost:8060/loader/api/stocks/sortlist
GET /api/stock/normalize
http://localhost:8060/loader/api/stocks/normalize
key=dateStr value=MM/DD/YYYY
GET /api/stock/{symbol}
http://localhost:8060/loader/api/stocks/{symbol}
Replace {symbol} with the desired stock symbol.
GET /api/stock/normalize
http://localhost:8060/loader/api/stocks/summary

### CSVController
Upload a CSV file containing stock data:

POST /api/csv/upload

Attach the CSV file with the request as a multipart/form-data file.



## Contributing

We welcome contributions to the Stock Management Application. If you would like to contribute, please feel free to fork the repository, make changes, and create a pull request.

## License

This project is licensed under the MIT License.