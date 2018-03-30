# CSCI3060U Backend

## Introduction

The Back End reads in a file of items available for auction and a file containing information regarding current user accounts in the system, it processes a stream of item bidding and advertising transactions one at a time, and it writes out an updated user account and available item file at the end of the process.

## Compiling

This program is built using Gradle. It can also be built using Jetbrains IntelliJ IDEA.

#### Building via Jetbrains IntelliJ IDEA
- Open the project using IntelliJ IDEA
- Select run configuration (Run/Run Tests)
- Select build/run to start the program

#### Building via Command Line
- Open a terminal/command prompt in project root
- Run `gradlew assemble` or `gradlew.bat assemble` to prepare the build
- Run `gradlew build` or `gradlew.bat build` to build the program
	
## Running Tests

#### Running tests via Jetbrains IntelliJ IDEA
- Open the project using IntelliJ IDEA
- Select run configuration "Run Tests"
- Select run to start the program
- The results can be found in the Output Window

#### Running tests via Command Line
- Open a terminal/command prompt in project root
- Run `gradlew test` or `gradlew.bat test` to run the tests
- The results can be found in `build/reports/tests/test/index.html`

## Running Backend
- Create a `current_users.txt` file within the working directory
- Create a `available_items.txt` file within the working directory
- Create a `transactions.txt` file within the working directory
- Run the backend using IntelliJ IDEA or via a terminal/command prompt by executing `java -jar build/libs/CSCI3060U_Backend.jar`

## Authors
- Eyaz Rehman ([GitHub](http://github.com/Imposter))
- Rishabh Patel ([GitHub](http://github.com/RPatel97))
