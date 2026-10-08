# Arkansas

Login against `POST https://dummyjson.com/auth/login`.

**Stack:** Jetpack Compose · MVVM · Use cases · Hilt/Dagger · Ktor · Room · DataStore · Glide · Navigation 3

## Feature-based structure
```
app                     Application, MainActivity, Navigation 3 host
feature/auth/
  domain/               model, repository interface, use cases
  data/                 Ktor remote source + DTOs, mapper, repository impl, Hilt module
  presentation/         login + home screens, ViewModels, NavKeys
feature/products/        Same layering: domain / data (Ktor + Room cache) / presentation
core/network            Ktor HttpClient (separate module)
core/database           Room database, DAO
core/datastore          Session tokens (DataStore)
core/designsystem       Theme, Glide image component
build-logic             Gradle convention plugins
```
Flow: `Composable -> ViewModel -> UseCase -> Repository -> (Ktor | Room | DataStore)`

Demo login: `emilys` / `emilyspass`
# Arkansas
