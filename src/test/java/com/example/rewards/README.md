# Rewards Program

A Spring Boot application that computes customer reward points based on transaction history.

---

## Overview

Customers earn points on each purchase:

- **2 points** for every dollar spent over $100  
- **1 point** for every dollar spent between $50 and $100  
- **Example:** $120 → (2×20) + (1×50) = **90 points**

This app calculates **monthly** and **total** points per customer over any period.

---
## Getting Started

1. **Clone the repo**  
   ```bash
   git clone https://github.com/your-username/rewards.git
   cd rewards
   ```

## Running the Application

### Using Maven

```bash
./mvnw clean spring-boot:run
```

### Using the JAR

```bash
./mvnw package
java -jar target/rewards-0.0.1-SNAPSHOT.jar
```

The app listens on **http://localhost:8080** by default.

---

## API Endpoints

| Method | URL                         | Description                          |
| ------ | --------------------------- | ------------------------------------ |
| GET    | `/api/rewards`              | All customers’ monthly & total points |
| GET    | `/api/rewards/{customerId}` | Single customer’s points             |
| GET    | `/api/transactions`         | List all transactions                |
| POST   | `/api/transactions`         | Add a new transaction                |

---

## Sample Requests & Responses

### Add a Transaction

```bash
curl -X POST http://localhost:8080/api/transactions   -H "Content-Type: application/json"   -d '{"customerId":1,"amount":120.0,"transactionDate":"2025-01-10"}'
```

**Response:**
```json
{
  "id": 4,
  "customerId": 1,
  "amount": 120.0,
  "transactionDate": "2025-01-10"
}
```

### Fetch Rewards for Customer #1

```bash
curl http://localhost:8080/api/rewards/1
```

**Response:**
```json
{
  "customerId": 1,
  "monthlyPoints": {
    "2025-01": 90,
    "2025-02": 50
  },
  "totalPoints": 140
}
```

## Database Console

Access the H2 console at:  
`http://localhost:8080/h2-console`

- **JDBC URL**: `jdbc:h2:mem:rewardsdb`  
- **User**: (none) / **Password**: (none)

---

## Swagger UI

Interactive API docs at:  
`http://localhost:8080/swagger-ui.html`

---

## Testing

Run unit and integration tests:

```bash
./mvnw test
```

- **Service Tests**: validate point calculations and aggregation  
- **Controller Tests**: verify endpoint behavior

---

## Packaging

Build an executable JAR:

```bash
./mvnw clean package
```

Result: `target/rewards-0.0.1-SNAPSHOT.jar`

---
