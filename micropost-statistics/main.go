package main

import (
	"log"
	"net/http"
	"./api"
)

const port = "8020"

func main() {
	processorAPI := api.NewProcessorAPI()

	http.HandleFunc("/health", processorAPI.HealthCheck)
	http.HandleFunc("/test", processorAPI.Test)

	log.Println("Server is running on port " + port)
	
	if err := http.ListenAndServe(":" + port, nil); err != nil {
		panic(err)
	}

}
