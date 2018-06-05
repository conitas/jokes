# Demonstration Project delivers some Chuck Norris Jokes

![Travis CI master-branch](https://travis-ci.org/itasgmbh/jokes.svg?branch=master)

Caution: early ALPHA stage!

This tiny spring boot micro service demonstrates usage of Spring MVC Rest, Spring MongoDB Data Repository and runs on Azure CosmosDB with Mongo dialect or on Mongo DB directly (untested yet).  

  
## Compile, test and build docker image
  ```bash 
    gradle docker
  ```

## Push docker image to docker.io
1. Login to docker.io:
   ```bash
     docker login docker.io -u itasgmbhde -p ...
   ```  
1. Push image to docker.io
   ```bash
     gradle dockerPush
   ```
 
## Running
  ```bash
 docker run -it --rm -e "spring.data.mongodb.uri=mongodb://<user>:<password>@<host>:<port>/<databaseName>?ssl=true" -p 8080:8080 itasgmbhde/jokes
  ```
Enjoy this amazing app listening at http://localhost:8080 !

## TODOs: 
1. Azure CosmosDB delivered to $sample aggregate always the same resultset on the repeated reads.
1. Also Where aggregate couldn't be used together with $sample. This caused a very-very bad implementation of the random joke delivery.
