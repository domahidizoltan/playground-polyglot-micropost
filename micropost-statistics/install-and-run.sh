echo ">> import dependencies"
go get github.com/imdario/mergo

echo ">> run integration test"
go test statisticprocessor/statistic_processor.go \
        statisticprocessor/int_processor.go \
        statisticprocessor/map_processor.go \
        statisticprocessor/statistic_processor_test.go

echo ">> run the application"
go run main.go
