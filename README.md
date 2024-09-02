# Habit tracker app

## Networking
The app communicates through a REST API defined using OpenAPI standards. 
The API specification is in the `specs/openapi.yaml` file.
This project is configured to use `org.openapi.generator` to generate 
the client based on the API specs.

To generate the API client run:
```
./gradlew openApiGenerate
```
