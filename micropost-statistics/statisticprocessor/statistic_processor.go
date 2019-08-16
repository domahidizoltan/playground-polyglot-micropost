package statisticprocessor

import (
	stat "github.com/domahidizoltan/playground-polyglot-micropost/micropost-statistics/statistic"
	"encoding/json"
	"github.com/imdario/mergo"
	"log"
	"sync"
)

//Result for storing processed statistics data
type Result struct {
	Alphanumeric    int            `json:"alphanumeric"`
	NonAlphanumeric int            `json:"nonAlphanumeric"`
	TotalWords      int            `json:"totalWords"`
	WordOccurrence  map[string]int `json:"wordOccurrence"`
}

func (res Result) String() string {
	jsonRes, err := json.Marshal(res)
	if err != nil {
		log.Println("Could not serialize result to JSON", err)
	}

	return string(jsonRes)
}

type processorTemplate interface {
	getCounters() []stat.Counter
	createChannel()
	closeChannel()
	sendMessage(stat stat.Counter, text string)
	receiveMessage(result *Result)
}

//Processor type for statistic processors
type Processor struct {
	processors []processorTemplate
}

//NewProcessor factory for processor
func NewProcessor() *Processor {
	return &Processor{
		processors: []processorTemplate{
			newIntStatisticProcessor(),
			newMapStatisticProcessor(),
		},
	}
}

func (it *Processor) Process(text string) *Result {
	result := new(Result)
	resultChannel := make(chan *Result)
	processorSize := len(it.processors)
	var wg sync.WaitGroup

	for i := 0; i < processorSize; i++ {
		wg.Add(1)
		go func(processor processorTemplate) {
			defer wg.Done()
			resultChannel <- processTemplate(processor, text)
		}(it.processors[i])
	}

	for i := 0; i < processorSize; i++ {
		res := <-resultChannel
		if err := mergo.Merge(result, res); err != nil {
			log.Println("Could not merge results", err)
		}
	}

	wg.Wait()
	close(resultChannel)
	return result
}

func processTemplate(template processorTemplate, text string) *Result {
	counterSize := len(template.getCounters())
	result := new(Result)
	var wg sync.WaitGroup
	template.createChannel()

	for i := 0; i < counterSize; i++ {
		wg.Add(1)
		go func(stat stat.Counter) {
			defer wg.Done()
			template.sendMessage(stat, text)
		}(template.getCounters()[i])
	}

	for x := 0; x < counterSize; x++ {
		template.receiveMessage(result)
	}

	wg.Wait()
	template.closeChannel()
	return result
}
