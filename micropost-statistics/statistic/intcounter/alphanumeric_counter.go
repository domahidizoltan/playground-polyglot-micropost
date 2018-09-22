package intcounter

import (
	stat "../../statistic"
	"log"
	"regexp"
)

type alphanumericStatisticCounter struct {
	key   stat.KeyType
	regex *regexp.Regexp
}

//NewAlphanumericStatisticCounter factory for alphanumeric statistic calculator
func NewAlphanumericStatisticCounter() *alphanumericStatisticCounter {
	regex, _ := regexp.Compile(`[a-zA-Z0-9]`)

	return &alphanumericStatisticCounter{
		key:   stat.Alphanumeric,
		regex: regex,
	}
}

func (it *alphanumericStatisticCounter) Key() stat.KeyType {
	return it.key
}

func (it *alphanumericStatisticCounter) Count(text string) int {
	results := it.regex.FindAllStringIndex(text, -1)
	log.Println("processed", it.key)
	return len(results)
}
