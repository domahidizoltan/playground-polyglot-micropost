package main

import (
	"fmt"
	"./statistics"
)

func main() {
	result := statistics.Calculate("Hello,my mY Hello World")
	fmt.Println(result)
}
