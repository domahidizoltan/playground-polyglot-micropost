package statistics

import (
	"regexp"
)

type totalWordsStatistics struct {
	totalWords int
	_regex *regexp.Regexp
}

func newTotalWordsStatistics() *totalWordsStatistics {
	_regex, _ := regexp.Compile(`\b\w`)	

	return &totalWordsStatistics {
		totalWords: 0,
		_regex: _regex,
	}
}

func (stat *totalWordsStatistics) Process(text string) {
	results := stat._regex.FindAllString(text, -1)
	stat.totalWords = len(results)
}
