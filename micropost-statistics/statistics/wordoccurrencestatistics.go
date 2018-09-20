package statistics

import (
	"regexp"
	"strings"
)

type wordOccurrenceStatistics struct {
	wordOccurrence map[string]int
	_regex *regexp.Regexp
}

func newWordOccurrenceStatistics() *wordOccurrenceStatistics {
	_regex, _ := regexp.Compile(`(\w+)`)	

	return &wordOccurrenceStatistics {
		wordOccurrence: make(map[string]int),
		_regex: _regex,
	}
}

func (stat wordOccurrenceStatistics) Process(text string) {
	results := stat._regex.FindAllString(text, -1)
	for i := 0; i < len(results); i++ {
		word := strings.ToLower(results[i])
		if val, found := stat.wordOccurrence[word]; !found {
			stat.wordOccurrence[word] = val
		}
		stat.wordOccurrence[word] ++
	}
}
