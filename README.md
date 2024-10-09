# Muuvis: Movie List App
## Overview
This app is simple movie list app that fetched data from [TMDB API](https://www.themoviedb.org/). If you want to see the development log, you can [see here](https://x.com/crustaceaee/status/1841468461958180875)

## Setup
1. Register and get API KEY from [themoviedb.org](https://developer.themoviedb.org/docs/)
2. Create `Constant.kt` file and add your own `API_KEY`
```
object Constant {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val POSTER_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    const val BACKDROP_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w780"
    const val API_KEY = "YOUR_API_KEY"
}
```

## Tech Stack
- Framework: Android
- Language: Kotlin
- UI design system: Material 3
- Architecure: MVVM + Clean Architecture
- Asynchronous Programming: Coroutine Flow
- Local Database: Room
- Netwok Connection: Retrofit
- Dependency Injection: Dagger Hilt
- Single Activity Multiple Fragments
- Navigation: Jetpack Compose Navigation
- Preferences: Datastore

## Screenshots
| <img src="https://github.com/user-attachments/assets/7232b522-22ad-4f09-9d02-5e4586f7f9dc" width="250" /> | <img src="https://github.com/user-attachments/assets/5818f3e0-1bd9-4af4-8435-18839c478889" width="250" /> | <img src="https://github.com/user-attachments/assets/53578fab-8803-45bc-938b-eca4a7a16cb4" width="250" /> |
| :---: | :---: | :--- |
| <img src="https://github.com/user-attachments/assets/84f05aae-8502-4b75-bd85-693499d22420" width="250" /> | <img src="https://github.com/user-attachments/assets/6dccfc86-6513-4a20-94aa-f895b1e62c6a" width="250" /> | <img src="https://github.com/user-attachments/assets/72791b4b-03f1-419c-a6ae-46ad79c13688" width="250" />
