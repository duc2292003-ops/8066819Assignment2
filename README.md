📋 Project Overview
This is an Android application that demonstrates best practices in modern Android development, including:
User authentication (login/signup)
Dashboard with entity list management
RESTful API integration
MVVM architecture pattern
Dependency injection with Hilt
Unit testing

🛠️ Tech Stack
Language: Kotlin
Minimum SDK: 24 (Android 7.0)
Target SDK: 36
Architecture: MVVM (Model-View-ViewModel)
Dependency Injection: Dagger Hilt
Networking: Retrofit 2 + Gson
Async Operations: Coroutines + LiveData
UI: ViewBinding, RecyclerView
Testing: JUnit 4, Mockito, Coroutines Test

📁 Project Structure
app/
├── src/
│   ├── main/
│   │   ├── java/com/example/s8066819assignment2/
│   │   │   ├── data/
│   │   │   │   ├── api/        # API service interfaces
│   │   │   │   └── model/      # Data models
│   │   │   └── ui/
│   │   │       ├── login/      # Login screen
│   │   │       └── dashboard/  # Dashboard screen
│   │   └── res/
│   │       ├── layout/         # XML layouts
│   │       ├── drawable/       # UI resources
│   │       └── values/         # Colors, strings, themes
│   └── test/
│       └── java/               # Unit tests
└── build.gradle.kts

🚀 Getting Started
Prerequisites
Before you begin, ensure you have the following installed:
Android Studio: Narwhal (2025.1.3) or later
JDK: Java 11 or higher
Gradle: 8.0+ (included with Android Studio)
Git: For cloning the repository
Installation Steps
Clone the repository
git clone <repository-url>
cd S8066819Assignment2
Open in Android Studio
Launch Android Studio
Select File → Open
Navigate to the project folder and click OK
Sync Gradle
Android Studio will automatically prompt to sync Gradle
If not, click File → Sync Project with Gradle Files
Wait for all dependencies to download
Build the project
./gradlew build
Or use Android Studio: Build → Make Project
Running the Application
On Emulator
Create an Android Virtual Device (AVD):
Open Device Manager in Android Studio
Click Create Device
Choose a device (e.g., Pixel 6)
Select a system image (API 24 or higher)
Click Finish
Run the app:
Click the green Run button (▶️) in the toolbar
Or press Shift + F10
Select your emulator from the device list
On Physical Device
Enable Developer Options on your device:
Go to Settings → About Phone
Tap Build Number 7 times
Go back to Settings → Developer Options
Enable USB Debugging
Connect your device via USB
Run the app:
Click the green Run button (▶️)
Select your device from the list

🧪 Running Tests
Unit Tests
Run all unit tests:
./gradlew test
Or in Android Studio:
Right-click on app/src/test folder
Select Run 'Tests in 'com.example.s8066819assignment2''
Run Individual Test Files
Option 1: Using Android Studio
Open the test file (e.g., LoginViewModelTest.kt)
Click the green arrow (▶️) next to the class name
Select Run 'LoginViewModelTest'
Option 2: Using Gradle
./gradlew test --tests "com.example.s8066819assignment2.LoginViewModelTest"
View Test Results
Test results appear in the Run tab at the bottom
Detailed HTML reports: app/build/reports/tests/testDebugUnitTest/index.html

📦 Dependencies
Core Dependencies
// Android Core
androidx.core:core-ktx:1.13.1
androidx.appcompat:appcompat:1.7.0
com.google.android.material:material:1.12.0

// Networking
com.squareup.retrofit2:retrofit:2.9.0
com.squareup.retrofit2:converter-gson:2.9.0

// Dependency Injection
com.google.dagger:hilt-android:2.48

// ViewModel & LiveData
androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2
androidx.lifecycle:lifecycle-livedata-ktx:2.6.2
Testing Dependencies
// Unit Testing
junit:junit:4.13.2
org.mockito:mockito-core:5.3.1
org.mockito.kotlin:mockito-kotlin:5.0.0
androidx.arch.core:core-testing:2.2.0
org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3

🎨 Features
1. Login Screen
Modern, clean UI design
Email/username input validation
Password field with visibility toggle
"Forgot Password" link
Sign up navigation
2. Dashboard
Entity list display using RecyclerView
Pull-to-refresh functionality
Loading states
Error handling

🏗️ Architecture
MVVM Pattern
Model: Data classes in data/model/
View: Activities/Fragments with ViewBinding
ViewModel: Business logic and state management
Key Components
ApiService: Retrofit interface for API calls
interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
    
    @GET("entities")
    suspend fun getEntities(@Header("Authorization") token: String): Response<List<Entity>>
}
LoginViewModel: Handles login logic
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {
    // Login logic with LiveData
}

🐛 Troubleshooting
Common Issues
1. Build Errors after Gradle Sync
./gradlew clean
./gradlew build --refresh-dependencies
2. Kapt Errors
Make sure test files are in src/test/java/ NOT in src/main/java/
3. Cannot Resolve Symbol Errors
File → Invalidate Caches → Invalidate and Restart
4. Dependency Download Issues
Check internet connection
Try different Gradle mirror in settings.gradle.kts
5. Test Failures
Ensure you have the latest dependencies
Check if all model classes exist
Verify API service methods match test expectations

📝 Code Quality
Running Code Analysis
./gradlew lint
Code Formatting
Android Studio uses Kotlin official style guide
Format code: Ctrl + Alt + L (Windows) or Cmd + Option + L (Mac)

🔒 Security Notes
Never commit API keys or sensitive data
Use environment variables for production endpoints
Implement proper token storage (SharedPreferences or encrypted storage)
