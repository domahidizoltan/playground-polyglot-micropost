package mapcounter

import (
	stat "github.com/domahidizoltan/playground-polyglot-micropost/micropost-statistics/statistic"
	"log"
	"regexp"
	"strings"
)

type wordOccurrenceStatisticCounter struct {
	key   stat.KeyType
	regex *regexp.Regexp
}

//NewWordOccurrenceStatisticCounter factory for word occurrence statistic calculator
func NewWordOccurrenceStatisticCounter() *wordOccurrenceStatisticCounter {
	regex, _ := regexp.Compile(`(\w+)`)

	return &wordOccurrenceStatisticCounter{
		key:   stat.WordOccurrence,
		regex: regex,
	}
}

func (it *wordOccurrenceStatisticCounter) Key() stat.KeyType {
	return it.key
}

func (it wordOccurrenceStatisticCounter) Count(text string) map[string]int {
	results := it.regex.FindAllString(text, -1)
	wordMap := make(map[string]int)
	log.Println("processed", it.key)

	for i := 0; i < len(results); i++ {
		word := strings.ToLower(results[i])
		if val, found := wordMap[word]; !found {
			wordMap[word] = val
		}
		wordMap[word]++
	}

	return wordMap
}
