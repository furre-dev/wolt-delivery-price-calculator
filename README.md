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
  "cart_value": 5200,
  "user_lat": 59.34829878585902,
  "user_lon": 18.030581581937327,
  "venue_slug": "home-assignment-venue-stockholm"
}
```

#### _And responds with:_
```json
{
  "total_price": 10900,
  "small_order_surcharge": 4800,
  "cart_value": 5200,
  "delivery": {
    "fee": 900,
    "distance": 186
  }
}
```

The `venue_slug` can only be one of the following values:

- `home-assignment-venue-helsinki`
- `home-assignment-venue-stockholm`
- `home-assignment-venue-berlin`
- `home-assignment-venue-tokyo`

**PS:** _The venue slug is used to determine the venue, so please make sure it matches one of the available options._

---

## How to Run Locally

To run the Kotlin Spring Boot project locally, follow these steps:

## 1. Install Java
Ensure that Java is installed on your system because it is required to run the Spring Boot application. You can verify this by running:

```bash
java -version
```

If Java is not installed, you can download it from the official Oracle website or install it via a package manager based on your OS.

## 2. Install Gradle
If Gradle is not installed on your system, follow the appropriate steps to install it:

### macOS:
If you have Homebrew installed, you can install Gradle by running:

```bash
brew install gradle
```

### Linux:
**Ubuntu/Debian:**

```bash
sudo apt update
```
```bash
sudo apt install gradle
```

**Fedora:**

```bash
sudo dnf install gradle
```

### Windows:
Download and install Gradle from the official website.  
Alternatively, if you have Chocolatey installed, you can run:

```bash
choco install gradle
```

## 3. Open the Project in IntelliJ IDEA
Launch **IntelliJ IDEA** and open your Kotlin Spring Boot project.  
IntelliJ IDEA will automatically recognize Gradle as the build system if the project has a `build.gradle` or `settings.gradle` file.

## 4. Sync Gradle Dependencies
Once the project is opened in IntelliJ IDEA, the IDE should prompt you to sync the Gradle dependencies. If it doesn’t, you can trigger the sync manually:

- Click on **"View" > "Tool Windows" > "Gradle"**.
- In the **Gradle** window, click the **"Refresh"** button to sync your dependencies.

This will download all the necessary dependencies specified in the `build.gradle` file.

## 5. Build the Project
To build the project using Gradle, you can use the following command in your terminal (ensure you're in the project directory):

```bash
gradle build
```

This command will compile the code and create an executable JAR file.

## 6. Run the Spring Boot Application
Once the build is complete, you can run the Spring Boot application with the following command:

```bash
gradle bootRun
```

This will start the Spring Boot application locally. You should see log output in the terminal indicating that the application is running, and it will be accessible at `http://localhost:8080` by default (unless configured otherwise).

## 7. Run the Application from IntelliJ IDEA
Alternatively, you can run the Spring Boot application directly from IntelliJ IDEA:

- Click the **green Run** button in the top right corner of IntelliJ IDEA.
- Select **"Run 'BootRun'"** or **"Run 'Application'"** if the configuration is set up.

This will execute the application and show the output in the **Run** window of IntelliJ.

## 8. Verify the Application is Running
Once the application starts, you can open your browser and navigate to `http://localhost:8080` to verify that your Spring Boot application is running locally.

## 9. Test the Endpoint
You can now test the endpoint using a tool like Postman or cURL. For example:

```bash 
curl -X POST http://localhost:8080/api/v1/delivery-order-price \
    -H "Content-Type: application/json" \
    -d '{"cart_value": 5200, "user_lat": 59.34829878585902, "user_lon": 18.030581581937327, "venue_slug": "home-assignment-venue-stockholm"}'

```

---

## Some tools & dependencies

- **Input Validation** using `spring-boot-starter-validation`:
  The project ensures that incoming data adheres to the expected format and constraints by using Spring Boot's validation framework. This helps catch any invalid inputs early in the request cycle.


- **JSON Parsing** with Google’s **Gson** to convert incoming JSON into Kotlin objects:
  Gson is used to parse JSON strings and convert them into Kotlin data objects, making it easier to work with the data in a structured way within the application.


- **Distance Calculation** using the **Haversine formula**:
  The Haversine formula is applied to compute the great-circle distance between the user's location and the venue, based on their respective latitude and longitude values. This is used to calculate the delivery distance and associated delivery fee.

---

## Resources Used

- **Haversine Formula for distance calculation:** [GitHub Gist](https://gist.github.com/jferrao/cb44d09da234698a7feee68ca895f491)


- **Spring Boot Required Request Body Fields:** [Stack Overflow](https://stackoverflow.com/questions/61630030/spring-boot-required-requestbody-class-fields)


- **Latitude and Longitude Ranges:** [Google Maps API](https://developers.google.com/maps/documentation/javascript/reference/coordinates)


- **Converting JSON to Kotlin Object:** [Baeldung](https://www.baeldung.com/kotlin/json-convert-data-class)


- **Enabling ResponseStatusException to show error reason:** [Stack Overflow](https://stackoverflow.com/questions/62561211/spring-responsestatusexception-does-not-return-reason)

---

## Videos watched
- [**Kotlin Design Patterns & Best Practices**](https://www.youtube.com/watch?v=G6FY8jHiDVY)


- [**How to build a REAL Webapp with Kotlin & Spring Boot**](https://www.youtube.com/watch?v=7iJ0NWRaWic)


- [**Factory Design Pattern in Kotlin**](https://youtu.be/1VWYP3S12Do?si=aSFPf7awRx7_OPH3)

---

## What I have asked ChatGPT?

- The difficulty of Kotlin for a developer with good TypeScript & Java knowledge.


- Kotlin Design Patterns & Best Practices


- The mindset for optimal Kotlin code


- Difference between class and data class.


- How to fetch data from an external API endpoint using Spring boot.


- Minor questions about Kotlin/Java tools like BigDecimal