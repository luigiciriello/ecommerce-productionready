# ECOMMERCE-PRODUCTIONREADY

This is an educational project aimed at mastering modern microservices architecture and becoming fluent in implementing production-ready e-commerce systems.
The project serves as a sandbox to explore patterns, frameworks, and best practices used in real-world enterprise applications.

## Architecture

- API Gateway fronting domain services, responsible for routing and rate limiting.
- Domain microservices for core e-commerce capabilities like catalog, customers and orders, each owning its data in different databases and exposing APIs or events.
- Asynchronous messaging with RabbitMQ and Kafka to decouple services, support eventual consistency, and enable streaming and reactive processing where appropriate.
- Centralized configuration via Config Server and secure secrets handling to keep deployments repeatable and environment-agnostic.

## Tech Stack

- ✅ Spring Boot 3.x and Spring Cloud.
- ✅ Containerization with Docker and Kubernetes for consistent dev-to-prod workflows and scalable runtime orchestration. (Docker implemented)
- 🛠️ Spring Cloud Gateway for routing, filters, and resilience integration, with Redis-backed rate limiting. (Implemented, rate limiting to extensively test)
- 🛠️ IAM via OpenID Connect and OAuth 2.0 for authentication, token validation, and service-to-service authorization. (To implement)
- 🛠️ Messaging with RabbitMQ for command/queue patterns and Kafka/Kafka Streams for event streaming, processing, and compacted topics. (To implement)
- 🛠️ Observability with Grafana and compatible metrics, traces, and logs pipelines for actionable insights across services. (To implement)
- 🛠️ Spring config to handle different configuration per environment. (To implement)
- 🛠️ Using Spring cloud functions where it makes sense. (To implement)
