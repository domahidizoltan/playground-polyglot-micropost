package statisticprocessor

import (
	stat "github.com/domahidizoltan/playground-polyglot-micropost/micropost-statistics/statistic"
	ic "github.com/domahidizoltan/playground-polyglot-micropost/micropost-statistics/statistic/intcounter"
)

type intStatisticCounter interface {
	stat.Counter
	Count(text string) int
}

type intStatisticMessage struct {
	Key   stat.KeyType
	Value int
}

type intStatisticProcessor struct {
	channel  chan intStatisticMessage
	counters []intStatisticCounter
}

func newIntStatisticProcessor() *intStatisticProcessor {
	return &intStatisticProcessor{
		counters: []intStatisticCounter{
			ic.NewAlphanumericStatisticCounter(),
			ic.NewNonAlphanumericStatisticCounter(),
			ic.NewTotalWordsStatisticCounter(),
		},
	}
}

func (it *intStatisticProcessor) getCounters() []stat.Counter {
	size := len(it.counters)
	counters := make([]stat.Counter, size)

	for i := 0; i < size; i++ {
		counters[i] = it.counters[i].(stat.Counter)
	}
	return counters
}

func (it *intStatisticProcessor) createChannel() {
	it.channel = make(chan intStatisticMessage)
}

func (it *intStatisticProcessor) closeChannel() {
	close(it.channel)
}

func (it *intStatisticProcessor) sendMessage(intStat stat.Counter, text string) {
	it.channel <- intStatisticMessage{
		Key:   intStat.Key(),
		Value: intStat.(intStatisticCounter).Count(text),
	}
}

func (it *intStatisticProcessor) receiveMessage(result *Result) {
	res := <-it.channel
	switch res.Key {
	case stat.Alphanumeric:
		result.Alphanumeric = res.Value
	case stat.NonAlphanumeric:
		result.NonAlphanumeric = res.Value
	case stat.TotalWords:
		result.TotalWords = res.Value
	}
}
