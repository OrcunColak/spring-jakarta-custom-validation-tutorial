# Read me

The original idea is from  
https://medium.com/@cat.edelveis/creating-spring-boot-custom-validators-17790c33447e

Also see
https://medium.com/@RoussiAbel/error-handling-in-spring-web-using-rfc-9457-specification-f2cc8398e285

# Enable Problem Details

In application.properties file set

1. spring.mvc.problemdetails.enabled=true or
2. Extend from ResponseEntityExceptionHandler and declare it as an @ControllerAdvice

# Swagger

http://localhost:8080/swagger-ui/index.html