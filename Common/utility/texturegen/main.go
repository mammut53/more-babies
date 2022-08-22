package main

import (
	"fmt"
	"github.com/anthonynsimon/bild/adjust"
	"github.com/anthonynsimon/bild/blend"
	"github.com/anthonynsimon/bild/clone"
	"github.com/anthonynsimon/bild/fcolor"
	"github.com/anthonynsimon/bild/imgio"
	"github.com/anthonynsimon/bild/transform"
	"time"
)

func main() {
	start := time.Now()

	cursedClock()
	clock()

	fmt.Print("Done, took ", time.Since(start))
}

func cursedClock() {
	for i := 0; i < 64; i++ {

		fmt.Printf("Loading frame, %d\n", i)

		frame, err := imgio.Open(fmt.Sprintf("textures/input/clock/clock_%02d.png", i))
		if err != nil {
			fmt.Println(err)
			return
		}

		rotatedFrame := transform.Rotate(frame, -5.625*float64(i), &transform.RotationOptions{
			ResizeBounds: false,
			Pivot:        nil,
		})
		blendedFrame := blend.Blend(rotatedFrame, rotatedFrame, func(rgbaf64 fcolor.RGBAF64, rgbaf642 fcolor.RGBAF64) fcolor.RGBAF64 {
			cappingPoint := 0.4

			if rgbaf64.A <= cappingPoint {
				rgbaf64.A = 0
				return rgbaf64
			}

			if rgbaf64.A > cappingPoint {
				rgbaf64.A = 1
				return rgbaf64
			}

			return rgbaf64
		})
		adjustedFrame := adjust.Hue(blendedFrame, -15)

		fmt.Printf("Saving frame, %d\n", i)

		if err := imgio.Save(fmt.Sprintf("textures/output/cursed_clock/clock_%02d.png", i), adjustedFrame, imgio.PNGEncoder()); err != nil {
			fmt.Println(err)
			return
		}
	}
}

func clock() {
	base, err := imgio.Open("textures/input/clock/base/base.png")
	if err != nil {
		fmt.Println(err)
		return
	}

	for i := 0; i < 64; i++ {

		fmt.Printf("Loading frame, %d\n", i)

		frame, err := imgio.Open(fmt.Sprintf("textures/input/clock/clock_%02d.png", i))
		if err != nil {
			fmt.Println(err)
			return
		}

		resizedFrame := transform.Resize(frame, 8, 8, transform.NearestNeighbor)
		rotatedFrame := transform.Rotate(resizedFrame, 90, nil)
		paddedFrame := clone.Pad(rotatedFrame, 8, 8, clone.NoFill)
		translatedFrame := transform.Translate(paddedFrame, -1, 7)
		adjustedFrame := adjust.Hue(translatedFrame, -15)
		baseBlended := blend.Normal(base, adjustedFrame)

		fmt.Printf("Saving frame, %d\n", i)

		if err := imgio.Save(fmt.Sprintf("textures/output/cursed_clock_on_a_stick/cursed_clock_on_a_stick_%02d.png", i), baseBlended, imgio.PNGEncoder()); err != nil {
			fmt.Println(err)
			return
		}
	}
}
