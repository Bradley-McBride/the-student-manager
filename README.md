Student API

Below are a few sample urls for testing

Create student
http://localhost:8080/students/

Body
{
"firstName": "John",
"lastName": "Doe",
"idNumber": "838383",
"grade": "6"
}

Get student by id number
http://localhost:8080/students/838383

Get students by grade
http://localhost:8080/students/grade/5

Delete student
http://localhost:8080/students/838383

Swagger UI
http://localhost:8080/swagger-ui/index.html?url=/v3/api-docs
