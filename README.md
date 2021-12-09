# SunshineWeather

**Overview**  
This is a simple weather app obtaining a 10 day forecast from openweathermap.org's public API.  
The app utilizes the user's location when provided permission, else it uses a default Atlanta location.

**Purpose**  
The goal is to demonstrate Android development using an MMVM pattern with appropriate unit tests, dependency injection, and databinding.  
Several TODO comments point out improvements that I would prefer to make before release in a real-world scenario.  
I opted to not used any local storage for this app as it did not seem applicable to store location since it can change, nor weather data, since it will definitely change ;).  
Had local storage been necessary, Room would have been my preferred solution.

**How to install?**  
In the APK folder, I've included a compiled debug version that can be run on phones/emulators without requiring Android Studio. You may need to permit apps from unverified
sources.  
Of course you can clone the repository and build it yourself!


