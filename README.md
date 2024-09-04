# slack-app-kotlin

- An example of a slack app using Kotlin and Spring Boot

## Usage

```bash
./gradlew bootRun
```

### Swagger
- http://localhost:39990/actuator/swagger-ui

### Curl

```bash
curl -X 'POST' \
  'http://localhost:9990/slack/send/test' \
  -H 'accept: */*' \
  -d ''
```