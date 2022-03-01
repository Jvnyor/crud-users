# Rest API to create user admin login and authentication user admin

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

/api/users/admin/{id} - Return a User by Id - GET

/api/users/admin/find?name={name} - Return a User by name - GET

/api/users/admin/{id} - Delete User by Id - DELETE

### Use to requests Postman or Swagger UI.
