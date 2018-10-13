echo ">> import dependencies"
go get github.com/imdario/mergo
go get google.golang.org/grpc

echo ">> generate protobuf"
protoc --go_out=plugins=grpc,import_path=proto:. proto/*.proto

echo ">> run integration test"
go test statisticprocessor/statistic_processor.go \
        statisticprocessor/int_processor.go \
        statisticprocessor/map_processor.go \
        statisticprocessor/statistic_processor_test.go

echo ">> run the application"
go run main.go
