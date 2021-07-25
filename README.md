# MATCH: Social Networking Platform

![demo](/screenshots/animation.gif)

Team: Ivan Chowdhury, Min JO, Sara Huang, Hanoch Goldfarb


## Description
MATCH is a web app that allows users to find friends with similar interests in their area. MATCH's friend-matching algorithm utilizes user geolocation data, hobbies, and other personal preferences to optimize results. The app also features PBKDF2 encryption, cookies, and account management.

### Features

- Account management: registration, login, friends list, and customizable profile

- Accept or reject matches

- Chat with friends

- PBKDF2 encryption

- Cookies



### Tech Stack
- **Web Server**: Spark (embedded Jetty web server)

- **Server**: Spark, JDBC

- **Database**: MySQL



## Getting Started

### Prerequisites
- [Java JDK 8 or above](https://www.oracle.com/java/technologies/javase-downloads.html)
- [MySQL](https://www.mysql.com/downloads/)
- [Apache Maven](http://maven.apache.org/download.cgi)

### Running Locally

1. Start MySQL and run a server. Record your MySQL credentials.
2. Edit the credentials in the beginning of `MatchJDBC.java` to match your MySQL credentials
3. Run `MatchApp.java`
4. Navigate to `https://localhost:8080` in your browser. If this doesn't work, try https://199.98.27.118:8080/

### Testing

To do an **integration test**, run the bash script `test`. This will try creating a new user and checking that their profile page returns the expected results.

## Instructions

- **Creating a new user:** From the homepage select `Register a new account.`. Fill out all the fields, click `Get Location`, and then click `Register`.

- **Viewing a friend's profile:** Clicking on a name in your list of friends will show you their profile.

- **Rejecting a match:** When a viewing a friend's profile, select `Reject`. They will no longer appear on your list of friends and you will no longer appear on theirs.

- **Messaging a friend:** When a viewing a friend's profile, type in the textbox and click `Send Message`. Previously sent messages will also be shown on the page.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details

# Happy Matching!
![logo](https://cdn.pixabay.com/photo/2017/09/02/04/35/fire-2706299_960_720.jpg)
