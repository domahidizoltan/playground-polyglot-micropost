#!/bin/bash

function install_micronaut {
    echo ">>> Installing Micronaut..."
    PROJECT_PATH=$(pwd)
    MN_IMAGE=domahidizoltan/micronaut-core

    (cd scripts \
        && docker build -t ${MN_IMAGE} -f Dockerfile-micronaut .)

    alias mn="docker run -v $PROJECT_PATH:/project -w /project -it ${MN_IMAGE} mn"
}

function install_kubernetes {
    echo ">>> Installing Kubernetes in Docker (KIND) cluster..."
    #https://kind.sigs.k8s.io/
    curl -Lo ./kind-linux-amd64 https://github.com/kubernetes-sigs/kind/releases/download/v0.4.0/kind-linux-amd64
    chmod +x kind-linux-amd64
    sudo mv kind-linux-amd64 /usr/local/bin/kind

    kind delete cluster
    kind create cluster --config scripts/kind-config.yml
    export KUBECONFIG="$(kind get kubeconfig-path --name="kind")"

    #kubectl
    curl -LO https://storage.googleapis.com/kubernetes-release/release/v1.15.0/bin/linux/amd64/kubectl
    chmod +x ./kubectl
    sudo mv ./kubectl /usr/local/bin/kubectl
}


function add_db_role {
    echo ">>> Add role to table"
    docker exec micropost_db sh -c "PGPASSWORD=pass psql -U micropost -d micropost -h localhost -c \
        \"CREATE ROLE $(whoami) WITH SUPERUSER PASSWORD 'pass' LOGIN\";"
}

function start_containers {
    echo ">>> Starting Docker containers..."
    docker-compose -f scripts/docker-compose.yml up -d

    (echo "Waiting to start up containers..." && sleep 10 && add_db_role)
}

function install_service_images {
    echo ">>> Building micropost-service"
    (cd micropost-service \
        && docker build -t domahidizoltan/micropost-service:kind . \
        && kind load docker-image domahidizoltan/micropost-service:kind)

    echo ">>> Building micropost-statistics"
    (cd micropost-statistics \
        && docker build -t domahidizoltan/micropost-statistics:kind . \
        && kind load docker-image domahidizoltan/micropost-statistics:kind)
}

function install {
    install_micronaut
    install_kubernetes
    start_containers
    install_service_images

    echo ">>> Finished installing. Run this before connecting to k8s cluster:"
    echo "export KUBECONFIG=\"$(kind get kubeconfig-path --name=\"kind\")\""
}

install
