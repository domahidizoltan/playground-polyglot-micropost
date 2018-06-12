#!/bin/sh

PROJECT_PATH=$(pwd)
MN_IMAGE=domahidizoltan/micronaut-core

(cd scripts && docker build -t ${MN_IMAGE} -f Dockerfile-micronaut .)

alias mn='docker run -v $PROJECT_PATH:/project -w /project -it ${MN_IMAGE} mn'