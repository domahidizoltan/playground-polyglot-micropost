package intcounter

import (
	stat "../../statistic"
	"log"
	"regexp"
)

type nonAlphanumericStatisticCounter struct {
	key   stat.KeyType
	regex *regexp.Regexp
}

//NewNonAlphanumericStatisticCounter factory for non-alphanumeric statistic calculator
func NewNonAlphanumericStatisticCounter() *nonAlphanumericStatisticCounter {
	regex, _ := regexp.Compile(`[^a-zA-Z0-9]`)

	return &nonAlphanumericStatisticCounter{
		key:   stat.NonAlphanumeric,
		regex: regex,
	}
}

func (it *nonAlphanumericStatisticCounter) Key() stat.KeyType {
	return it.key
}

func (it *nonAlphanumericStatisticCounter) Count(text string) int {
	results := it.regex.FindAllStringIndex(text, -1)
	log.Println("processed", it.key)
	return len(results)
}
