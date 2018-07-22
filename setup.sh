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
    echo ">>> Installing Kubernetes DIND cluster..."
    #https://github.com/kubernetes-sigs/kubeadm-dind-cluster
    ./scripts/dind-cluster-v1.8.sh up
    export PATH="$HOME/.kubeadm-dind-cluster:$PATH"
}

function disable_registry_authentication_for {
    CONTAINERS=( "$@" )
    CONFIG_FILE="/etc/docker/daemon.json"
    echo ">>> Disable Docker registru authentication..."
    for CONTAINER in "${CONTAINERS[@]}"; do
        if ! docker exec ${CONTAINER} grep -q insecure-registries ${CONFIG_FILE}; then
            echo "disable and restart ${CONTAINER}"
            docker exec ${CONTAINER} sh -c "echo '{ \"insecure-registries\" : [\"registry:5000\"] }' > ${CONFIG_FILE} && service docker restart"
        fi
    done
}

function add_db_role {
    echo ">>> Add role to table"
    docker exec micropost_db sh -c "PGPASSWORD=pass psql -U micropost -d micropost -h localhost -c \
        \"CREATE ROLE $(whoami) WITH SUPERUSER PASSWORD 'pass' LOGIN\";"
}

function start_containers {
    echo ">>> Starting Docker containers..."
    (cd scripts \
        && docker-compose up -d)

    (echo "Waiting to start up containers..." && sleep 10 && add_db_role)
}

function install_service_images {
    echo ">>> Building micropost-service"
    (cd micropost-service \
        && ./gradlew clean shadowJar \
        && docker build -t domahidizoltan/micropost-service:latest . \
        && docker tag domahidizoltan/micropost-service:latest localhost:5000/micropost-service:latest \
        && docker push localhost:5000/micropost-service:latest)
}

function install {
    install_micronaut
    install_kubernetes  #dashboard at http://localhost:8080/api/v1/namespaces/kube-system/services/kubernetes-dashboard:/proxy
    disable_registry_authentication_for kube-master kube-node-1 kube-node-2
    start_containers
    install_service_images  #images at http://localhost:5000/v2/_catalog
}

install
