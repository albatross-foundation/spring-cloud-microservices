FROM java:8

MAINTAINER Tran Manh <tranmanh.vn90@gmail.com>

ADD target/instagram-config*.jar app.jar

RUN bash -c 'touch /app.jar'

ENTRYPOINT ["java","-jar","/app.jar"]