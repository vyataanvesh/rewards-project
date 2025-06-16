# Rewards Program

## Build & Run

```bash
./mvnw clean package
java -jar target/rewards-0.0.1-SNAPSHOT.jar
```

## Endpoints

- `GET /api/rewards`
- `GET /api/rewards/{customerId}`
- `GET /api/transactions`
- `POST /api/transactions`

## H2 Console

URL: `http://localhost:8080/h2-console`  
JDBC URL: `jdbc:h2:mem:rewardsdb`

## Swagger UI

URL: `http://localhost:8080/swagger-ui.html`
