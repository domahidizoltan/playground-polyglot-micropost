package proto

import (
	"log"
	"fmt"
	"golang.org/x/net/context"
	sp "github.com/domahidizoltan/playground-polyglot-micropost/micropost-statistics/statisticprocessor"
	"sync"
)

//PostStatisticsService to process incoming requests
type PostStatisticsService struct {
	processor *sp.Processor
}

//NewPostStatisticsService factory
func NewPostStatisticsService() *PostStatisticsService {
	return &PostStatisticsService {
		processor: sp.NewProcessor(),
	}
}

//GeneratePostStatistics to process statistics request
func (it *PostStatisticsService) GeneratePostStatistics(ctx context.Context, request *PostStatisticsRequest) (*PostStatisticsResponse, error) {
	logMessage := fmt.Sprintf("processing request for postId=%d with content=%s", request.PostId, request.Content)
	log.Println(logMessage)

	resultChannel := make(chan *sp.Result)
	var wg sync.WaitGroup

	wg.Add(1)
	go func(request *PostStatisticsRequest) {
		defer wg.Done()
		resultChannel <- it.processor.Process(request.Content)
	}(request)

	response := it.makeResponse(request.PostId, resultChannel)

	wg.Wait()
	close(resultChannel)

	return response, nil
}

func (it *PostStatisticsService) makeResponse(postId int32, resultChannel chan *sp.Result) *PostStatisticsResponse {
	result := <-resultChannel

	wordOccurrenceMap := make(map[string]int32)
	for k,v := range result.WordOccurrence {
		wordOccurrenceMap[k] = int32(v)
	  }

	response := &PostStatisticsResponse{
		PostId: postId,
		Alphanumeric: int32(result.Alphanumeric),
		NonAlphanumeric: int32(result.NonAlphanumeric),
		TotalWords: int32(result.TotalWords),
		WordOccurrence: wordOccurrenceMap,
	}

	return response
}
