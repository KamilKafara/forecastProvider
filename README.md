# ForecastProvider


## What can this tool do?

The ForecastProvider application is written in Java 17 using the Spring Boot framework, serving historical meteorological data to the user from a public API (https://open-meteo.com). 

The server issues an endpoint (/forecast) accepting longitude and latitude, for example
```
/forecast?latitude=52.52&longitude=13.41&pastDays=7"
```
The returned information includes the average amount of precipitation on a given day and the time of sunrise/sunset. 
The application should record the time and endpoint call parameters in a relational database (Postgres or H2) each time.

 
## How to start: 
### Build the project 
```
mvn package
```
Here is the docker-compose.yml that powers the whole setup.

```
version: '3.8'

services:
  application:
    container_name: forecast-provider-core-app
    build: .
    restart: always
    environment:
      - SPRING_DATASOURCE_URL=jdbc:postgresql://database:5432/forecast-provider
      - SPRING_DATASOURCE_USERNAME=admin
      - SPRING_DATASOURCE_PASSWORD=password
    ports:
      - 8080:8080
    depends_on:
      - database
      - postgres_db_client
  database:
    container_name: postgres_database
    image: postgres
    restart: always
    environment:
      - POSTGRES_HOST_AUTH_MODE=trust
      - POSTGRES_USER=admin
      - POSTGRES_PASSWORD=password
      - POSTGRES_DB=forecast-provider
    ports:
      - "5432:5432"
    volumes:
      - ./database:/var/lib/postgresql
    healthcheck:
      test: [ "CMD-SHELL", "pg_isready -U admin" ]
      interval: 10s
      timeout: 5s
      retries: 5
  postgres_db_client:
    image: dpage/pgadmin4
    environment:
      - PGADMIN_DEFAULT_EMAIL=admin@domain.com
      - PGADMIN_DEFAULT_PASSWORD=password
    restart: always
    ports:
      - "5050:80"
```

Run all containers with 
```
docker-compose up
```

### Configuration 
application.properties contains datasource to make correct connection to database

__you need rebuild project after change it__
