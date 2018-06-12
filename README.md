# WIP: playground-polyglot-micropost
This is a playground project to learn some previously unused technologies such as Micronaut, Kotlin, Jooq, gRPC, Avro, Golang, Ruby on Rails, Kubernetes

This project will contain 3 service:
- micropost-service: a Rest (Hal) service what will manage users, microposts and micropost statistics calculated by micropost-statistics service (using Micronaut, Kotlin, Jooq, gRPC, Avro)
- micropost-statistics: a simple Go service to calculate some statistics of a micropost (using Golang, gRPC, Avro)
- micropost-ui: a frontend for managing micropost (using Ruby on Rails)

The services will be managed by Kubernetes and must be able for: service-discovery, self-healing, (auto)scaling, blue-green deployment
