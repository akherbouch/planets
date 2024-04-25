# Planets Explorer

## Description
Planets Explorer is an Android app that allows users to browse and learn more about the various planets featured in the Star Wars universe. The app fetches data from the SWAPI (Star Wars API) and provides a detailed view of each planet. It supports offline capabilities, ensuring that users can access the information even without an internet connection.

## Features
- **Browse Planets**: View a list of planets on the main screen.
- **Planet Details**: Tap on a planet to see more detailed information about it.
- **Offline Support**: Access previously loaded data even when offline.

## Technical Stack
- **Architecture**: MVVM using the latest Android recommended practices like clean architecture and a modular approach
- **UI Components**: Jetpack Compose for building modern, performant UIs.
- **Data Management**: Room database for local data caching and Retrofit for API calls.
- **Dependency Injection**: Hilt for dependency injection to improve modularity and testing.

## Performance Considerations
- Efficient data fetching and caching strategies to minimize network usage and improve responsiveness.
- Lazy loading of data to enhance scrolling performance.
- Implement pagination

## Testing
- Unit tests cover data layer with mocked API and database.
- Instrumentation tests verify the UI content

## App Structure 

```
Planets Explorer App
│
├── app
│   ├── di (Dependency injection setup)
│   └── ui (Theme, Screens, and UI components)	
│ 
├── data
│   ├── di(Dependency injection module for data)
│   ├── database (Database, DAOs)
│   ├── network (API calls, retrofit services)
│   ├── mappers (Data to Domain models mappers)
│   ├── model (Data models)
│   └── repository (Concrete implementations of the repositories)
│  
└── domain
    ├── repository (Interfaces for the repositories)
    ├── usecase (Use case implementations)
    └── model (Domain models)

```

## Future Improvements

- Add more interactive elements such as searching and sorting capabilities
- Enhance the UI/UX design with animations and transitions
- Include more detailed error handling
- Add Integration tests

