package api

import (
	"../statisticprocessor"
	"net/http"
	"log"
	"encoding/json"
)

type processorAPI struct {
	processor *statisticprocessor.Processor
}

//NewProcessorAPI factory for processing incoming text
func NewProcessorAPI() *processorAPI {
	return &processorAPI {
		processor: statisticprocessor.NewProcessor(),
	}
}

func (it *processorAPI) HealthCheck(w http.ResponseWriter, r *http.Request) {
	w.Write([]byte("OK"))
}

func (it *processorAPI) Test(w http.ResponseWriter, r *http.Request) {
	texts, ok := r.URL.Query()["text"]
	if !ok || len(texts) < 1 {
		log.Println("Url param 'text' is missing")
	}

	text := texts[0]
	result := it.processor.Process(text)
	jsonRes, err := json.Marshal(result)
	if err != nil {
		log.Println("Could not serialize result to JSON", err)
	}

	w.Write([]byte(jsonRes))
}
