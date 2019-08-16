FROM golang:1.11-alpine AS build

ENV GO111MODULE=on

RUN apk update && apk add --no-cache git
RUN adduser -D -g '' appuser

WORKDIR $GOPATH/src/github.com/domahidizoltan/playground-polyglot-micropost/micropost-statistics
COPY . .

RUN CGO_ENABLED=0 go build -ldflags="-w -s" -o /app/service main.go

###
FROM scratch

WORKDIR /app
COPY --from=build /app .

COPY --from=build /etc/passwd /etc/passwd
USER appuser

EXPOSE 8020
EXPOSE 8030
ENTRYPOINT ["/app/service"]