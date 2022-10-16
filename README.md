# ApiPunk-Didactic 🍺
A project with didactic purposes that implements solutions for the development of android applications.

## How to build it? 🔧
Clone the project and run it on Android Studio.

## How is it done? 👷
I have used a **Clean Architecture** by **layers**. That means the project has some modules to separate their scope in that order:
- **app** is the Android module, and there are Android frameworks and data source implementations.
- **usecases** is a Kotlin module that stores use cases for the Android module.
- **data** is a Kotlin module that stores repositories and datasources.
- **domain** is a Kotlin module that stores data classes to save the data the app collects from the server and database.

The presentation pattern used is **MVVM** with **Jetpack Compose**.

## Reasoning 🤔
- I have used a clean architecture to separate layers and have a scalable project.
- I have used **Jetpack Compose** to gain knowledge about this modern toolkit for building native UI.
- I have implemented **MVVM** as a design pattern because it is easier to follow the UDF.
- I have used Kotlin **coroutines** because we can run many on a single thread due to the suspension that doesn't block the main thread and are the standard way in Android.
- I have used **Kotlin serialization** because it comes from the Kotlin language to serialize data from the REST API service.
- I have used **Retrofit** as an API REST client because it is recommended and easy to use.
- I have applied **Hilt** as DI and **Room** as DB because they are the recommended options by google for Android projects.
- I have added **Coil** to load image resources due to its stability and use.
- I have coded the project as understandable as possible to be comprehensible by following `SOLID` principles.
- I have created unit tests to test viewmodels and usecases.

## TO-DO 👨‍🔧
- Add test UI.
- Plus test coverage for unit test.
- Improve errors handlers.