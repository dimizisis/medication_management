# 💊 Medication Management

A Spring Boot `REST` API for managing medications.  
Built as a demo project.

---

## 🚀 Features

- Manage medications with:
  - `POST /api/v1/medications` → create a medication
  - `GET /api/v1/medications` → list all medications
  - `GET /api/v1/medications/{id}` → get medication by ID

---

## 🛠 Tech Stack

- `Java` 25
- `Spring Boot` 3.x
  - `Spring Web`
  - `Spring Data JPA`
  - `Spring Validation`
- `MapStruct`
- `Flyway`
- `PostgreSQL` / `H2`
- `JUnit` 5, `Mockito`, `AssertJ`

---

## ⚙️ Getting Started

### Prerequisites
- `Java` 25
- `Maven` 3.9+
- `H2`

### Setup

1. **Clone the repo**
```bash
   git clone https://github.com/<your-username>/medication-api.git
   cd medication-api
```

2. Run the application
```bash
./mvnw spring-boot:run
```

3. API available at
```bash
http://localhost:8080/api/v1/medications
```
