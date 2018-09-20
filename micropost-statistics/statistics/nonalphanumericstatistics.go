package statistics

import (
	"regexp"
)

type nonAlphaNumericStatistics struct {
	totalNonAlphaNumeric int
	_regex *regexp.Regexp
}

func newNonAlphaNumericStatistics() *nonAlphaNumericStatistics {
	_regex, _ := regexp.Compile(`[^a-zA-Z0-9]`)	

	return &nonAlphaNumericStatistics {
		totalNonAlphaNumeric: 0,
		_regex: _regex,
	}
}

func (stat *nonAlphaNumericStatistics) Process(text string) {
	results := stat._regex.FindAllStringIndex(text, -1)
	stat.totalNonAlphaNumeric = len(results)
}
