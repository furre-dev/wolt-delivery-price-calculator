# Project Overview

## Who am I?

Hi, I’m Furkan, a 24-year-old Fullstack Developer based in Stockholm. I started with Frontend development in school and later taught myself Backend development. I’m experienced with TypeScript, React, Next.js, and Node.js, and also enjoy working with Java or Python for backend tasks.

After recently graduating, I completed a six-month internship, which led to a full-time frontend developer role and gave me valuable hands-on experience in a professional setting.

Now, I’m excited to dive into Kotlin. While I’ve worked mainly with Java and Python on the backend, I find Kotlin enjoyable and believe my skills will transfer easily. I’m passionate about learning and contributing to a team that values growth and efficiency.

---

## Project Details

This project is a **Spring Boot** backend service, designed to provide a pricing model for a food delivery system. The service exposes a POST endpoint `/api/v1/delivery-order-price`, which calculates the total price for a delivery order based on several parameters, including the cart value, user’s location, and venue information.

### Input and Output

#### _The service takes in the following input:_

```json
{
  "cart_value": Int, 
  "user_lat": Double, 
  "user_lon": Double, 
  "venue_slug": String
}
```
The `venue_slug` can only be one of the following values:

- `home-assignment-venue-helsinki`
- `home-assignment-venue-stockholm`
- `home-assignment-venue-berlin`
- `home-assignment-venue-tokyo`

<span style="opacity: 40%">**PS:** The venue slug is used to determine the venue, so please make sure it matches one of the available options.</span>

#### _And responds with:_
```json
{
  "total_price": Int,
  "small_order_surcharge": Int,
  "cart_value": Int,
  "delivery": {
    "fee": Int,
    "distance": Int
  }
}
```

## Some tools & dependencies

- **Input Validation** using `spring-boot-starter-validation`:
  The project ensures that incoming data adheres to the expected format and constraints by using Spring Boot's validation framework. This helps catch any invalid inputs early in the request cycle.


- **JSON Parsing** with Google’s **Gson** to convert incoming JSON into Kotlin objects:
  Gson is used to parse JSON strings and convert them into Kotlin data objects, making it easier to work with the data in a structured way within the application.


- **Distance Calculation** using the **Haversine formula**:
  The Haversine formula is applied to compute the great-circle distance between the user's location and the venue, based on their respective latitude and longitude values. This is used to calculate the delivery distance and associated delivery fee.

## How to Run Locally

To run the Kotlin Spring Boot project locally, open the project in your favorite IDE
</br>or follow these steps:

### 1. Install dependencies
Ensure you have Java and Maven installed on your machine. You can install Maven using the following command:

```bash 
brew install maven
```

### 2. Build the project
Run the following Maven command to build the project:

```bash 
brew install maven
```

### 2. Build the project
Run the following Maven command to build the project:

```bash 
mvn clean install
```

### 3. Run the Spring Boot Application
Start the application by running the following command:

```bash 
mvn spring-boot:run
```

This will start the Spring Boot application locally, typically on port 8080 by default.

### 4. Test the Endpoint
You can now test the endpoint using a tool like Postman or cURL. For example:

```bash 
curl -X POST http://localhost:8080/api/v1/delivery-order-price \
    -H "Content-Type: application/json" \
    -d '{"cart_value": 5200, "user_lat": 59.34829878585902, "user_lon": 18.030581581937327, "venue_slug": "home-assignment-venue-stockholm"}'

```

## Resources Used

- **Haversine Formula for distance calculation:** [GitHub Gist](https://gist.github.com/jferrao/cb44d09da234698a7feee68ca895f491)


- **Spring Boot Required Request Body Fields:** [Stack Overflow](https://stackoverflow.com/questions/61630030/spring-boot-required-requestbody-class-fields)


- **Latitude and Longitude Ranges:** [Google Maps API](https://developers.google.com/maps/documentation/javascript/reference/coordinates)


- **Converting JSON to Kotlin Object:** [Baeldung](https://www.baeldung.com/kotlin/json-convert-data-class)


- **Enabling ResponseStatusException to show error reason:** [Stack Overflow](https://stackoverflow.com/questions/62561211/spring-responsestatusexception-does-not-return-reason)

## Videos watched
- [**Kotlin Design Patterns & Best Practices**](https://www.youtube.com/watch?v=G6FY8jHiDVY)


- [**How to build a REAL Webapp with Kotlin & Spring Boot**](https://www.youtube.com/watch?v=7iJ0NWRaWic)


- [**Factory Design Pattern in Kotlin**](https://youtu.be/1VWYP3S12Do?si=aSFPf7awRx7_OPH3)