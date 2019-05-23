
### Kotlin microservice starter kit with Maven

#### Test
```shell
$ mvn test
```

#### Packaging
```shell
$ mvn clean package
$ java -jar target/Yoben-1.0-SNAPSHOT-jar-with-dependencies.jar
```

#### Run
```shell
$ mvn exec:java
```

#### Run with args
```shell
$ mvn exec:java -Dexec.args="argument1 argment2 argument3"
```

#### Docker

build
```shell
$ docker build -t yoben .
```
run
```shell
$ docker run --rm -p 8000:8000 yoben
```