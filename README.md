# CountDownTimer - Android Dev Challenge Compose

<!--- Replace <OWNER> with your Github Username and <REPOSITORY> with the name of your repository. -->
<!--- You can find both of these in the url bar when you open your repository in github. -->
![Workflow result](https://github.com/<OWNER>/<REPOSITORY>/workflows/Check/badge.svg)


## :scroll: Description
Countdown timer using Jetpack Compose 🚀 for the #AndroidDevChallenge [week 2](https://android-developers.googleblog.com/2021/03/android-dev-challenge-2.html). Click on the counter to insert the time, then click the play icon.

## :bulb: Motivation and Context
<!--- Optionally point readers to interesting parts of your submission. -->
<!--- What are you especially proud of? -->
Happy to have tested some of the beta01 animation APIs 🙌.

Some interesting stuff to peek into:

* Have a look to the [CountdownArc](https://github.com/JorgeCastilloPrz/android-dev-challenge-compose/blob/f7819685e7eaa28b1bc203b5ab7ab723932ba1f7/app/src/main/java/com/example/androiddevchallenge/CountdownArc.kt#L40) composable for an example of `animateFloatAsState` used for animating the arc sweep angle.
* [This line](https://github.com/JorgeCastilloPrz/android-dev-challenge-compose/blob/f7819685e7eaa28b1bc203b5ab7ab723932ba1f7/app/src/main/java/com/example/androiddevchallenge/Countdown.kt#L54) on the `Countdown` composable for an example on how to animate color via `animateColorAsState`, so the inner circle changes color following the color of the sweep edge. To calculate the color for a given percent [I'm using `lerp`](https://github.com/JorgeCastilloPrz/android-dev-challenge-compose/blob/f7819685e7eaa28b1bc203b5ab7ab723932ba1f7/app/src/main/java/com/example/androiddevchallenge/CountdownArc.kt#L50).
* [The method `startCounter`](https://github.com/JorgeCastilloPrz/android-dev-challenge-compose/blob/f7819685e7eaa28b1bc203b5ab7ab723932ba1f7/app/src/main/java/com/example/androiddevchallenge/CountdownTimer.kt#L60) in the `CountDownTimer` composable is the one in charge of updating the timer state so it gets recomposed every second. It uses a `CoroutineScope` as a result of `rememberCoroutineScope` so it can trigger the job after user interaction (button click) and cancel the previous ongoing job to avoid leaks. The complete animation is driven by the remaining time in seconds.

## :camera_flash: Screenshots
<!-- You can add more screenshots here if you like -->
<img src="/results/screenshot_1.png" width="260">&emsp;<img src="/results/screenshot_2.png" width="260">

## License
```
Copyright 2020 The Android Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```