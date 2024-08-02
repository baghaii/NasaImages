# Setup
To begin this project, inside Android Studio, I went to Android Studio > Preferences > 
Build, Execution, and Deployment > Build Tools and set my Gradle JDK to zulu-19. Gradle 8.0 does 
not want to work with JDKs below 17, and I use sdkman to choose my favorite Java version, which is 
generally "any version that Gradle will play nicely with." 

# Libraries
- Hilt - for dependency injection (could have used Dagger)
- Retrofit - for networking (seems pretty standard)

# Architecture
This project uses an MVVM architecture for the MainActivity and implements the UI using JetpackCompose.

# Running the application
You can `gradlew assembleDebug` and then go look for the apk in app > build > outputs > apk > debug
and install that on your device.

# Things I wish I had gotten to but did not...
- The database
- Playing with the Paging3 library to figure out how to make pagination work
- Supporting html text in the description fields because some of the descriptions use html tags.

# Link to preview video

[!<img src=https://github.com/user-attachments/assets/2d082dfa-7be1-424b-b660-36e8fc6d1b21 width=400>](https://youtube.com/shorts/Uq3Ls8EHTzo?feature=share)
