package statisticprocessor

import (
	"testing"
	"reflect"
)

const text = `
How much wood could a woodchuck chuck 
If a woodchuck could chuck wood? 
As much wood as a woodchuck could chuck, 
If a woodchuck could chuck wood.
`

func TestStatisticProcessor(test *testing.T) {
	expectedWordOccurrenceMap := map[string]int{
		"a": 4,
		"as": 2,
		"chuck": 4,
		"could": 4,
		"how": 1,
		"if": 2,
		"much": 2,
		"wood": 4,
		"woodchuck": 4,
	}

	processor := NewProcessor()
	result := processor.Process(text)

	if result.Alphanumeric != 115 {
		test.Error("Alphanumeric must be 115 but it was", result.Alphanumeric)
	}
	if result.NonAlphanumeric != 34 {
		test.Error("NonAlphanumeric must be 34 but it was", result.NonAlphanumeric)
	}
	if result.TotalWords != 27 {
		test.Error("TotalWords must be 27 but it was", result.TotalWords)
	}
	if !reflect.DeepEqual(result.WordOccurrence, expectedWordOccurrenceMap) {
		test.Errorf("WordOccurrence must be %v but was %v", expectedWordOccurrenceMap, result.WordOccurrence)
	}
}
