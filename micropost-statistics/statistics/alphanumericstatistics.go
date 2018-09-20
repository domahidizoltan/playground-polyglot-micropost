package statistics

import (
	"regexp"
)

type alphaNumericStatistics struct {
	totalAlphaNumeric int
	_regex *regexp.Regexp
}

func newAlphaNumericStatistics() *alphaNumericStatistics {
	_regex, _ := regexp.Compile(`[a-zA-Z0-9]`)	

	return &alphaNumericStatistics {
		totalAlphaNumeric: 0,
		_regex: _regex,
	}
}

func (stat *alphaNumericStatistics) Process(text string) {
	results := stat._regex.FindAllStringIndex(text, -1)
	stat.totalAlphaNumeric = len(results)
}
