FROM maven:3.6.3-openjdk-11-slim

WORKDIR /backend

COPY ./ /backend

CMD ["mvn", "spring-boot:run"]