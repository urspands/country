# Walmart-Countries
Walmart-Countries App is developed using Kotlin language and MVVM design pattern. This app makes api calls to https://gist.githubusercontent.com/ to get the list of countries and its details
 - Features
   - Gets the list of countries with its details and displays it.
   - Shows progress bar while loading data from server.
   - Shows empty state text if the data from server is empty or error.
   - Written unit tests for MainViewModel 
 
 
 - Libraries Used
   - Retrofit - for network api calls.
   - Livedata - for saving UI state on configuration changes.
   - Coroutines - for launching suspending calls to perform tasks in another thread.
   - Mockito - for Unit testing the viewModel class.
  
 - Limitations
   - Did not use Compose
   - Did not use Dagger/HILT for Dependency injection. Implemented manual DI.
   - Shows a toast with exception message if an error occurs.
