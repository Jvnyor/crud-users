# Rest API to create user admin login and authenticate user admin

Clone the project

```
git clone https://github.com/Jvnyor/crud-users.git
```

Download Maven dependencies

```
mvn clean install
```

Start Spring Boot Application

```
mvn spring-boot:run
```

Generate JAR file

```
mvn clean package
```

Command to start Application using JAR file

```
java -jar target/frete-0.0.1-SNAPSHOT.jar
```

## Database MySQL User Root
- Username: root
- Password: root

#### Or use H2 Database (check the application.properties file)

*src/main/resources/aplications.properties*

## Requests

#### JSON POST Method:
```
{
    "firstName":"Firstname",
    "lastName":"Lastname",
    "username":"user",
    "password":"password"
}
```

#### Path's:

/api/users/registration - Create a User Admin - POST

/api/users/admin - Return list of all Users - GET 

/api/users/admin/{id} - Replace a User by Id - PUT

/api/users/admin/id/{id} - Return a User by Id - GET

/api/users/admin/username/{username} - Return a User by Username - GET

/api/users/admin/{id} - Delete User by Id - DELETE

### Use to requests Postman or Swagger UI.
