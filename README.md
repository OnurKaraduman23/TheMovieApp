## The Movie App

This project leverages **Clean Architecture** and **MVVM (Model-View-ViewModel)** for modern Android development. This architecture ensures that the code is more manageable, testable, and maintainable. The project is primarily divided into three main layers: **UI (features package)**, **Domain**, and **Data**.

### Layers and Responsibilities

<img align="right" width="215" src="https://github.com/Honor13/TheMovieApp/assets/53227891/22fa5672-9886-4e8f-b83d-bd7e4ecb9f02">

#### UI Layer
The UI layer handles the user interface and user interactions. It interacts with the ViewModel classes and updates the UI using data binding. The ViewModel acts as an intermediary between the UI and the Domain layer. Key features in this layer include:

- **Movie List with Pagination**: Displays a list of movies with pagination support to load data incrementally as the user scrolls.
- **Movie Search**: Allows users to search for movies using keywords.
- **Favorites Management**: Enables users to add or remove movies from their favorites list.

#### Domain Layer
The Domain layer encapsulates the business logic and application requirements. This layer contains **Use Case classes**, which represent the operations that the user can perform. The Use Cases are called by the ViewModel and handle the necessary data processing before returning the results. Key responsibilities of this layer include:

- Centralizing business logic.
- Managing interactions between the Data and UI layers.
- Enhancing code reusability and testability.

Example use cases include fetching detailed movie information, handling movie search operations, and managing the favorites list.

#### Data Layer

<img align="right" width="215" src="https://github.com/Honor13/TheMovieApp/assets/53227891/2a1b1f62-45a1-4e7e-bfd6-de2239c6e0d4">

The Data layer is responsible for interacting with data sources. It retrieves data from remote servers (remote APIs) and local databases (Room) and provides it to the Domain layer. **Repository classes** abstract the data sources and offer a consistent data access interface to the Domain layer. Key functionalities include:

- Fetching movie details from remote APIs.
- Searching for movies using remote data sources.
- Managing local favorites using Room database.

### Local Database (Room)
The local database is managed using **Room**, which provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite. It is used for storing favorite movies and ensuring that data is available offline.

### Technologies Used
- **Kotlin**: For the programming language.
- **Coroutines**: For asynchronous programming.
- **Flow**: For data streams.
- **Room**: For local database management.
- **Retrofit**: For network operations.
- **Hilt**: For dependency injection.
- **Navigation Component**: For handling navigation within the app.
- **Material Design**: For implementing material design components.
- **Coil**: For image loading.
- **CircleImageView**: For displaying circular images.
- **Paging**: For implementing pagination in the movie list.

### Project Structure
The project structure follows a modular approach, separating concerns and responsibilities across different packages and classes:

## Screenshots
| Home                                                                                             | Details                                                                                          | Favorites                                                                                         | Search                                                                                           |
|--------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------|
| <img src="https://github.com/Honor13/TheMovieApp/assets/53227891/22fa5672-9886-4e8f-b83d-bd7e4ecb9f02" alt="Home" width="200px"/>   | <img src="https://github.com/Honor13/TheMovieApp/assets/53227891/eb7d2bea-ecd8-4a64-8bc1-0e53f408d6cf" width="200px"/> | <img src="https://github.com/Honor13/TheMovieApp/assets/53227891/cd9fa911-68fb-4a4a-85d3-e4a37f8af37b" alt="Favorites" width="200px"/> | <img src="https://github.com/Honor13/TheMovieApp/assets/53227891/1727ab44-53d1-46a7-ae5f-9caab3eced2c" alt="Search" width="200px"/>   |

### Commit Guidelines
This project follows **atomic commit principles**, meaning that each commit is a small, self-contained unit of change that addresses a single concern. This approach helps maintain a clean and understandable project history, makes code reviews more effective, and simplifies potential rollbacks.

### Conclusion
This architecture ensures clean, manageable, and modular code. The Domain layer centralizes business logic and abstracts data sources, making the code more maintainable and testable. This structure significantly improves the sustainability and scalability of large and complex projects.

