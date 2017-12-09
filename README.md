# restfulweb
restful web example with spring cloud

## TODO
- i18n
- validation
- ~~relation between entity 1 and entity 2~~
- ~~login success or failure, write to log and db~~
- ~~loginClient enum supports~~

## Notes
- docker run without as root (i.e. sudo), when dockerRedis bean create error
    - https://docs.docker.com/engine/installation/linux/linux-postinstall/
- DockerTestContainer on Win10
    - https://www.testcontainers.org/usage/windows_support.html
 
## Knowledge
- [spring security audit event actuator](http://www.baeldung.com/spring-boot-authentication-audit/)
    - actuator/auditevents: http://127.0.0.1/actuator/auditevents?after=2017-12-07T00:00:00.000%2B08:00    
- using default spring security logger listener to receive and log audit event
    * org.springframework.security.authentication.event.LoggerListener.LoggerListener
    * org.springframework.security.access.event.LoggerListener.LoggerListener

	
- todo



 


	
 	
 	
