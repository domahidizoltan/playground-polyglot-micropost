package intcounter

import (
	stat "github.com/domahidizoltan/playground-polyglot-micropost/micropost-statistics/statistic"
	"log"
	"regexp"
)

type totalWordsStatisticCounter struct {
	key   stat.KeyType
	regex *regexp.Regexp
}

//NewTotalWordsStatisticCounter factory for words counter
func NewTotalWordsStatisticCounter() *totalWordsStatisticCounter {
	regex, _ := regexp.Compile(`\b\w`)

	return &totalWordsStatisticCounter{
		key:   stat.TotalWords,
		regex: regex,
	}
}

func (it *totalWordsStatisticCounter) Key() stat.KeyType {
	return it.key
}

func (it *totalWordsStatisticCounter) Count(text string) int {
	results := it.regex.FindAllString(text, -1)
	log.Println("processed", it.key)
	return len(results)
}
