FROM openjdk:17          
COPY gradle gradle
COPY gradlew .	             # gradlew 복사
COPY build.gradle .          # build.gradle 복사
COPY src src                 # 웹 어플리케이션 소스 복사
COPY settings.gradle .       # settings.gradle 복사
RUN chmod +X ./gradlew       # gradlew에 실행권한 부여
RUN ./gradlew bootJar        # gradlew를 통해 실행 가능한 jar파일 생성

FROM openjdk:17
LABEL authors="choeyeonho"

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8500
ENTRYPOINT ["java","-jar","/app.jar"]
