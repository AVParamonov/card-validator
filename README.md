# Bank card validator
Card validation application helps to determine 
if bank card number is valid and card's type.


## Building app
To build executable .jar from the project directory run command:

*mvn clean package*


## Running app
To launch application (after build) from the project directory run command:

*java -jar target/card-validator-0.1-SNAPSHOT.jar*

After successful launch you can find entry page of application at:

**http://localhost:8888/validator**


## Authorization
To be able to send requests to app for authorization use default Spring-Security credentials:

username: **user**

password: (you can find default password in form of *UUID* after application launch 
as value of line 'Using default security password:' from Spring-Security output in terminal)

