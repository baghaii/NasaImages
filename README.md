# Setup
To begin this project, inside Android Studio, I went to Android Studio > Preferences > 
Build, Execution, and Deployment > Build Tools and set my Gradle JDK to zulu-19. Gradle 8.0 does 
not want to work with JDKs below 17, and I use sdkman to choose my favorite Java version, which is 
generally "any version that Gradle will play nicely with." 

# Libraries
Hilt - for dependency injection (could have used Dagger)
Retrofit - for networking (seems pretty standard)

