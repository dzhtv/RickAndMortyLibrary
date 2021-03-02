# RickAndMortyLibrary

Simple project for getting character info from https://rickandmortyapi.com/ about Rick and Morty tv show
#

![character_list](https://github.com/dzhtv/RickAndMortyLibrary/blob/master/screenshots/character_list_400.jpg) ![character_detail](https://github.com/dzhtv/RickAndMortyLibrary/blob/master/screenshots/character_detail_400.jpg)

# Tech stack

- minSdkVersion 21+
- Kotlin based + Coroutines for asynchronous
- JetPack
    - ViewModel
    - LiveData
    - Lifecycle
- Architecture
    - MVVM (View - DataBinding - ViewModel - Model)
    - Repository pattern (without domain layer)
    - Dagger2 - for dependency injection
- Retrofit2 + GSON - for REST APIs
- OkHttp2 - implementing interceptor, logging and mocking web server
- Glide - loading images


Glide - loading images, display pictures from Character.kt
