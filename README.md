<h1 align="center">Movie Listing</h1>

<p align="center">  
This is a small demo project for loading movies list from a file and fetching images from Flickr based on movie tittle. Android Studio 4.1 version is required.
</p>
</br>

## Download
Go to the [Releases](https://github.com/SEAbdulbasit/MoviesListingTest/releases) to download the latest APK.

## Tech stack & Open-source libraries
- Minimum SDK level 21
- JetPack
  - LiveData - notify domain layer data to views.
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
  - Repository pattern
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.
- [Moshi](https://github.com/square/moshi/) - A modern JSON library for Kotlin and Java.
- [Glide](https://github.com/bumptech/glide), [GlidePalette](https://github.com/florent37/GlidePalette) - loading images.
- [Timber](https://github.com/JakeWharton/timber) - logging.
- [Material-Components](https://github.com/material-components/material-components-android) - Material design components like ripple animation, cardView.
- Custom Views
  - [Rainbow](https://github.com/skydoves/rainbow) - An easy way to apply gradations and tinting for Android.
  - [AndroidRibbon](https://github.com/skydoves/androidribbon) - A simple way to implement a  beautiful ribbon with the shimmering on Android.
  - [ProgressView](https://github.com/skydoves/progressview) - A polished and flexible ProgressView, fully customizable with animations.
