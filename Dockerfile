FROM maven:3.8.1-openjdk-16 as builder

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src /app/src

RUN mvn package -DskipTests

FROM openjdk:16-jdk

WORKDIR /app
COPY --from=builder /app/target/Booking_Plane_App-1.0-SNAPSHOT.jar /app/booking-plane.jar

CMD ["java", "-jar", "booking-plane.jar"]
