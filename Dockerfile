# Maven və Java quraşdırılmış uyğun əsas imicin istifadə edin
FROM maven:3.8.1-openjdk-16 as builder

# İş üçün iş qovluğunu təyin edin
WORKDIR /app

# Maven konfiqurasiya fayllarını kopyalayın
COPY pom.xml .

# Zərur tələbləri endirin
RUN mvn dependency:go-offline -B

# Mənbə kodunu kopyalayın
COPY src /app/src

# Proyekti bina edin
RUN mvn package -DskipTests

# İkinci mərhələ runtime imici üçün
FROM openjdk:16-jdk

# İş üçün iş qovluğunu təyin edin
WORKDIR /app

# Builder mərhələsindən inşa olunmuş JAR faylını kopyalayın
COPY --from=builder /app/target/Booking_Plane_App-1.0-SNAPSHOT.jar /app/booking-plane.jar

# Tətbiqi işlətmək üçün əmr
CMD ["java", "-jar", "booking-plane.jar"]
