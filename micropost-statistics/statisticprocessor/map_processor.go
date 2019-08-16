package statisticprocessor

import (
	stat "github.com/domahidizoltan/playground-polyglot-micropost/micropost-statistics/statistic"
	mc "github.com/domahidizoltan/playground-polyglot-micropost/micropost-statistics/statistic/mapcounter"
)

type mapStatisticCounter interface {
	stat.Counter
	Count(text string) map[string]int
}

type mapStatisticMessage struct {
	Key   stat.KeyType
	Value map[string]int
}

type mapStatisticProcessor struct {
	channel  chan mapStatisticMessage
	counters []mapStatisticCounter
}

func newMapStatisticProcessor() *mapStatisticProcessor {
	return &mapStatisticProcessor{
		counters: []mapStatisticCounter{
			mc.NewWordOccurrenceStatisticCounter(),
		},
	}
}

func (it *mapStatisticProcessor) getCounters() []stat.Counter {
	size := len(it.counters)
	counters := make([]stat.Counter, size)

	for i := 0; i < size; i++ {
		counters[i] = it.counters[i].(stat.Counter)
	}
	return counters
}

func (it *mapStatisticProcessor) createChannel() {
	it.channel = make(chan mapStatisticMessage)
}

func (it *mapStatisticProcessor) closeChannel() {
	close(it.channel)
}

func (it *mapStatisticProcessor) sendMessage(mapStat stat.Counter, text string) {
	it.channel <- mapStatisticMessage{
		Key:   mapStat.Key(),
		Value: mapStat.(mapStatisticCounter).Count(text),
	}
}

func (it *mapStatisticProcessor) receiveMessage(result *Result) {
	res := <-it.channel
	switch res.Key {
	case stat.WordOccurrence:
		result.WordOccurrence = res.Value
	}
}
