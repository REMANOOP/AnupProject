

Music Album Dashboard - Android Application
Overview
This Android application is designed to showcase a dashboard of music albums with details such as artist name, album title, release year, genre, and more. Users can log in and view album details using Retrofit and Hilt for dependency injection.

Features
Login Screen: Users log in with credentials to access the dashboard.
Username: anup
password:s4680730

Dashboard: Displays a list of music albums retrieved via an API.
Album Details: Shows detailed information for each selected album.
Dependency Injection: Implemented using Hilt.
Architecture
The project follows MVVM (Model-View-ViewModel) architecture, utilizing Retrofit for network requests and Hilt for dependency injection.

Setup Instructions
Clone the Repository:

bash
Copy code
git clone <repository-url>
Open in Android Studio: Import the project into Android Studio.

Dependencies: Sync Gradle to download the required dependencies like Retrofit, Hilt, and others.

Hilt Setup:

Annotate the MyApplication class with @HiltAndroidApp.
Add @AndroidEntryPoint to all activities.
Run the Application: Ensure you have a valid API base URL in the RetrofitInstance.

Key Classes
LoginActivity: Handles user login.
DashboardActivity: Displays album data fetched from the API.
DetailsActivity: Shows detailed album information.
RetrofitInstance: Manages API calls.
ViewModel: Provides data for the dashboard and details screen.
Hilt Modules: Dependency injection for network services.
Dependencies
Retrofit
Hilt
ViewModel & LiveData
API Details
Login API: Authenticates user and returns a keypass.
Dashboard API: Fetches a list of music albums.
Author
Anup Regmi
