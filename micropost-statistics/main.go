package main

import (
	proc "./statisticprocessor"
	"log"
)

func main() {
	processor := proc.NewProcessor()

	text := "Hello,my mY Hello World"
	result := processor.Process(text)
	log.Println(result)

	text2 := `
	Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut quis ipsum felis. Proin tempor commodo aliquet. Fusce venenatis auctor nulla interdum dapibus. Aliquam tristique justo justo, id sagittis lectus lacinia ac. Aliquam erat volutpat. Morbi cursus sodales dui. Integer tempus sed quam pulvinar commodo. Aenean egestas purus et augue gravida aliquam. Vestibulum interdum, sem sit amet sodales pharetra, lorem leo interdum sapien, dictum suscipit nisi erat vel lectus. Cras sit amet lectus sed mauris malesuada consectetur. Etiam nec leo non arcu pharetra maximus varius sed tortor. Fusce vulputate orci vitae mi venenatis suscipit.
	Sed sed auctor ante. Nulla semper non neque ac rutrum. Vivamus eget massa laoreet, maximus dolor quis, semper dolor. Ut nec magna in enim pulvinar blandit eu ut ante. Vestibulum eget fermentum massa. Integer ut quam ultrices, dictum urna et, viverra ipsum. Curabitur hendrerit convallis arcu, non malesuada velit scelerisque efficitur. Integer non viverra purus. Donec risus enim, commodo quis justo ac, sagittis ultricies urna. Nulla tincidunt ultricies turpis, id posuere velit rhoncus at. Suspendisse potenti. Sed in est rhoncus, dignissim quam eget, ullamcorper nibh. Etiam sollicitudin tincidunt hendrerit. Nunc egestas, ex vitae consectetur porta, est tortor molestie orci, nec accumsan velit mi at elit.
	Maecenas pretium velit nec eros posuere sodales. Curabitur suscipit eros arcu, sed laoreet sapien congue ac. Nulla id nunc metus. Donec auctor tortor at nisi fringilla interdum. Nunc ac justo orci. Suspendisse potenti. Nulla ut nibh nec quam sollicitudin pellentesque eget a tortor. In feugiat iaculis porttitor. Sed facilisis lorem non sollicitudin pharetra. Nam a ex sit amet lorem sodales feugiat in nec quam.
	Donec iaculis egestas diam eu posuere. Morbi ac quam consequat, mattis dolor ac, pulvinar felis. Nulla facilisi. Etiam tincidunt convallis elit sed tincidunt. Mauris sit amet massa vestibulum, pellentesque ante et, molestie purus. Curabitur sagittis vestibulum sapien ut efficitur. Aenean mollis ornare lorem quis viverra. Ut ligula purus, posuere non pretium vel, dignissim ac erat. Donec vel ipsum eros. Morbi eget elit urna. Quisque scelerisque, metus ac volutpat finibus, felis ligula ultricies urna, at molestie enim erat a dui. Vivamus tristique sed est vel vehicula. Morbi risus sapien, semper quis dolor eu, ullamcorper lacinia felis. Quisque hendrerit nisi nec lacus ullamcorper, at vestibulum felis dictum.
	Sed id est quis lectus vulputate egestas. Etiam quis blandit tellus. Aenean id tortor porta, mollis leo at, maximus enim. Pellentesque quis mi at metus ullamcorper congue vitae eu libero. Vivamus non fermentum quam. Donec ut odio et quam ornare porta. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Etiam porttitor nunc ut lorem lobortis semper. Aliquam venenatis quam eget facilisis auctor. Vivamus maximus ac mauris sed pellentesque. Phasellus tempus vitae urna nec consectetur. Suspendisse potenti. Phasellus et mollis lorem. In non molestie neque, eget aliquam mi. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam sit amet nisl congue, pulvinar augue non, finibus dolor.
	`
	result2 := processor.Process(text2)
	log.Println(result2)
}
