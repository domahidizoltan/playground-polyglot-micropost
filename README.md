# WIP: playground-polyglot-micropost
This is a playground project to learn some previously unused technologies such as Micronaut, Kotlin, Jooq, gRPC, Golang, React, Kubernetes

This project will contain 3 service:
- micropost-service: a Rest (Hal) service what will manage users, microposts and micropost statistics calculated by micropost-statistics service (using Micronaut, Kotlin, Jooq, gRPC)
- micropost-statistics: a simple Go service to calculate some statistics of a micropost (using Golang, gRPC)
- micropost-ui: a frontend for managing micropost (using React)

The services will be managed by Kubernetes and must be able for: service-discovery, self-healing, (auto)scaling, blue-green deployment
