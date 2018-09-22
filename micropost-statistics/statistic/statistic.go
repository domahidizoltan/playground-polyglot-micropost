package statistic

//KeyType of statistic calculator representation
type KeyType string

var (
	//Alphanumeric for letters and numbers
	Alphanumeric KeyType = "alphanumeric"
	//NonAlphanumeric for anything except letters and numbers
	NonAlphanumeric KeyType = "nonAlphanumeric"
	//TotalWords for words
	TotalWords KeyType = "totalWords"
	//WordOccurrence for counting distinct words
	WordOccurrence KeyType = "wordOccurrence"
)

//Counter interface for statistic calculators
type Counter interface {
	Key() KeyType
}

//Message type for concurrent channels
type Message struct {
	Key KeyType
}
