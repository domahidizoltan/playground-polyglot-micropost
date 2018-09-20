package statistics

import (
	"log"
	"encoding/json"
)

//Statistics interface for statistic operations
type Statistics interface {
	Process(text string)
}

//Result data holder for operation result
type Result struct {
	AlphaNumeric int `json:"alphaNumeric"`
	NonAlphaNumeric int `json:"nonAlphaNumeric"`
	Words int `json:"words"`
	WordOccurrence map[string]int `json:"wordOccurrence"`
}

func (res Result) String() string {
	jsonRes, err := json.Marshal(res)
	if err != nil {
		log.Panic("Could not serialize result to JSON", err)
	}

	return string(jsonRes)
}

//Calculate the statistics for the given text
func Calculate(text string) Result{
	statCounters := []Statistics{ 
		newAlphaNumericStatistics(), 
		newNonAlphaNumericStatistics(), 
		newTotalWordsStatistics(), 
		newWordOccurrenceStatistics(),
	}

	//TODO use channels
	for i := 0; i < len(statCounters); i++ {
		statCounters[i].Process(text)
	}

	return Result {
		AlphaNumeric: (statCounters[0].(*alphaNumericStatistics)).totalAlphaNumeric,
		NonAlphaNumeric: (statCounters[1].(*nonAlphaNumericStatistics)).totalNonAlphaNumeric,
		Words: (statCounters[2].(*totalWordsStatistics)).totalWords,
		WordOccurrence: (statCounters[3].(*wordOccurrenceStatistics)).wordOccurrence,
	}

}
