package main

import (
	"strconv"
	"os"
	"net"
	"log"
	"google.golang.org/grpc"
	"net/http"
	"./api"
	pb "./proto"
)

const defaultHost = "localhost"
const defaultHttpPort = 8020
const defaultGrpcPort = 8030

func main() {
	finish := make(chan bool)

	go serveHttp()
	go serveGrpc()

	<-finish
}

func serveHttp() {
	processorAPI := api.NewProcessorAPI()
	http.HandleFunc("/health", processorAPI.HealthCheck)
	http.HandleFunc("/test", processorAPI.Test)
	
	host := getHost(defaultHttpPort)
	log.Println("Http server is running on " + host)
	if err := http.ListenAndServe(host, nil); err != nil {
		log.Fatalf("Failed to listen on http: %v", err)
	}
}

func serveGrpc() {
	grpcServer := grpc.NewServer()
	service := pb.NewPostStatisticsService()
	pb.RegisterPostStatisticsServer(grpcServer, service)
		
	host := getHost(defaultGrpcPort)
	log.Println("gRPC server is running on " + host)
	grpcListener, err := net.Listen("tcp", host)
	if err != nil {
		log.Fatalf("Failed to listen on grpc: %v", err)
	}
	grpcServer.Serve(grpcListener)
}

func getEnv(key, fallback string) string {
    if value, ok := os.LookupEnv(key); ok {
        return value
    }
    return fallback
}

func getHost(port int) string {
	return getEnv("HOST", defaultHost) + ":" + getEnv("HTTP_PORT", strconv.Itoa(port))	
}
