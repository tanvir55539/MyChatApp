# MyChatApp

MyChatApp is an Android chat application built using **Kotlin** and **Jetpack Compose**, with **Firebase** integration for user authentication, real-time messaging, and data storage. The app provides a smooth, intuitive interface for chatting with other users, viewing profiles, and managing user information.

---

## Features

- **User Authentication**: Secure login and registration using Firebase Authentication.
- **Real-time Chat**: Send and receive messages instantly using Firebase Firestore.
- **Profile Management**: Users can view and update their profile information (profile picture).
- **Modern UI**: Jetpack Compose UI with material design.
- **Navigation**: Clean screen navigation using Jetpack Navigation Compose.
- **Chat List**: Displays all users except the currently logged-in user.
- **Message Persistence**: Messages are stored in Firestore for retrieval across sessions.

---

## Screens

### 1. Login / Registration Screen
User authentication flow.

| Login | Registration |
|-------|--------------|
| <img src="https://github.com/user-attachments/assets/17834931-b6b1-4574-831b-91ccb9c3c387" width="200" /> | <img src="https://github.com/user-attachments/assets/730a4588-f82b-4427-9d6c-62c57b458b61" width="200" /> |

### 2. Chat List Screen
Displays all available users and last messages.  

<img src="https://github.com/user-attachments/assets/6e56841f-348e-4be8-887d-5c592b900751" width="200" />

### 3. Chat Screen
Individual chat between two users with message input.  

<img src="https://github.com/user-attachments/assets/26f98b79-f493-4a84-9fe0-5c4811a8e9e5" width="200" />

### 4. Profile Screen
Displays user information and profile picture.  

<img src="https://github.com/user-attachments/assets/3f080b1e-1d4b-4040-b06d-7fbe4ae844dd" width="200" />

### 5. Settings Screen
Manage user preferences.  

<img src="https://github.com/user-attachments/assets/a1c9c561-9484-4aa5-8607-4eb3d4dda11d" width="200" />

---

## Technologies Used

- **Kotlin** – Primary programming language.
- **Jetpack Compose** – Modern Android UI toolkit.
- **Firebase** – Backend services for authentication and Firestore database.
- **Coil** – Image loading library for Compose.
