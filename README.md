📲 Flutter Chat App with FCM & Node.js Backend
This guide helps you test push notifications (General, Incoming Call, and Chat Message) using a Flutter app connected to a Node.js backend via Firebase Cloud Messaging (FCM).

🎥 Demo
📽️ Watch the demo:
https://drive.google.com/file/d/1W1I69SxzMg-F5iSTokmd0aLQ3Yvbc6eA/view?usp=sharing

✅ Requirements
Two terminal windows:

One for the Flutter app

One for the Node.js backend

A tool like Postman, Insomnia, or curl

A real Android/iOS device or an emulator with Google Play Services

🚀 Setup Instructions
1. Start the Backend Server
bash
Copy
Edit
cd "C:\chat app\notification_backend"
node server.js
2. Run the Flutter App
In a separate terminal:

bash
Copy
Edit
cd "C:\chat app\chat_app"
flutter run
Once launched, copy the FCM Token from the home screen of the app.

🔁 Sending Test Notifications
Use Postman, Insomnia, or curl to send POST requests to the backend.

🔔 General Notification
URL: http://localhost:3000/send-notification

Method: POST

Body:

json
Copy
Edit
{
  "token": "<YOUR_FCM_TOKEN>",
  "title": "Test Alert",
  "body": "This is a general test notification.",
  "payload": { "screen": "history" }
}
📞 Incoming Call Notification
URL: http://localhost:3000/send-call-notification

Method: POST

Body:

json
Copy
Edit
{
  "token": "<YOUR_FCM_TOKEN>",
  "callerName": "John Doe",
  "callType": "Video Call",
  "callId": "call_12345"
}
💬 Chat Message Notification
URL: http://localhost:3000/send-chat-message

Method: POST

Body:

json
Copy
Edit
{
  "token": "<YOUR_FCM_TOKEN>",
  "senderName": "Alice",
  "messageContent": "Hello from the backend! How are you?\nThis is a multi-line message."
}
🔍 Observe App Behavior
App State	Expected Behavior
Foreground	Notifications appear as banners. Chat screen updates directly. Badge count increases.
Background/Killed	Notifications appear in lock screen/notification shade. Tap navigates to Call or Chat screen. Badge count clears.

🧪 Troubleshooting
🔧 Backend Issues
Module not found:
Run in notification_backend:

bash
Copy
Edit
npm install firebase-admin express body-parser cors
Invalid FCM token:
Get a new FCM token from the Flutter app and verify it was copied correctly.

Priority issue:

pgsql
Copy
Edit
Invalid JSON payload received. Unknown name 'priority' at 'message'
✅ Fix: priority: "high" must be inside the android (and apns for iOS) block in server.js.

📱 Android Notification Issues
Check the following files exist:

android/app/src/main/res/drawable/caller_avatar.png

android/app/src/main/res/drawable/ic_notification_icon.png

android/app/src/main/res/raw/ringtone.mp3

In AndroidManifest.xml:

Ensure permissions:

xml
Copy
Edit
<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
MainActivity settings:

xml
Copy
Edit
android:showWhenLocked="true"
android:turnScreenOn="true"
Notification channel:

Importance should be set to Importance.max for call_channel.

Run with verbose logs:

bash
Copy
Edit
flutter run -v
🍎 iOS Notification Issues
In ios/Podfile:

ruby
Copy
Edit
pod 'Firebase/Messaging'
Run:

bash
Copy
Edit
cd ios
pod install
Verify AppDelegate.swift is set up correctly for Firebase + APNs.

Xcode → Signing & Capabilities:

✅ Enable:

Push Notifications

Background Modes → Remote notifications

For VoIP call functionality, consider using PushKit (advanced setup).

🔗 Deep Linking Issues
Ensure the backend sends payload with valid format (used with Uri.parse in Flutter).

Android:

AndroidManifest.xml should include the correct intent-filter with:

xml
Copy
Edit
<data android:scheme="myapp" android:host="chat" />
Flutter:

Call MyApp.router.go() in both:

onMessageOpenedApp

onDidReceiveNotificationResponse

⚙️ Build Troubleshooting
After making significant changes, always run:

bash
Copy
Edit
flutter clean
flutter pub get
In build.gradle.kts, ensure:

kotlin
Copy
Edit
ndkVersion = "23.1.7779620" // example
coreLibraryDesugaringEnabled = true
📚 Summary
This guide walks you through end-to-end testing of a Flutter-based chat app with FCM push notifications backed by a Node.js server. It covers setup, sending test notifications, and troubleshooting for both Android and iOS platforms.
