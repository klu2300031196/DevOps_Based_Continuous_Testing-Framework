# DevOps-Based Continuous Testing Framework

## Project Overview

The **DevOps-Based Continuous Testing Framework** is a capstone project that automates application building, software testing, test report generation, and Docker-based deployment using DevOps practices.

The main objective is to identify software defects early, reduce manual testing effort, and improve the reliability of application delivery through Continuous Integration (CI).

## Architecture

Developer → GitHub Repository → GitHub Actions → Java 21 and Maven Build → Automated JUnit Tests and Selenium Browser Test → Test Report Artifact → Docker Image → Local Container Deployment

## Technologies Used

- **Programming Language:** Java 21
- **Framework:** Spring Boot 3.4.10
- **Build Tool:** Apache Maven
- **Testing:** JUnit 5, Spring Boot Test, MockMvc, Selenium WebDriver
- **Browser Testing:** Google Chrome in headless mode
- **CI/CD:** GitHub Actions
- **Containerization:** Docker and Docker Compose
- **Container Registry:** GitHub Container Registry (GHCR)
- **Version Control:** Git and GitHub

## Implemented Features

1. Automated application build using Maven.
2. Automated API testing using JUnit and Spring Boot MockMvc.
3. Selenium-based browser test for the health endpoint.
4. GitHub Actions workflow triggered by pushes and pull requests.
5. Automated test report generation and artifact upload.
6. Docker image creation for the Spring Boot application.
7. Local application deployment using Docker containers.
8. Application health verification through a REST API endpoint.
9. Docker image publishing to GHCR configured in the GitHub Actions workflow for pushes to the `main` branch.

**Current status:** The GitHub Actions workflow has completed successfully. The latest run's Docker image publishing status should be verified in the workflow logs.

## Application Endpoints

The application provides the following endpoints:

| Endpoint | Purpose |
|---|---|
| `/` | Returns the home endpoint response |
| `/api/health` | Checks application health |

Expected health response:

```json
{
  "status": "UP"
}
```

## Requirements

Install or configure the following tools:

- JDK 21
- Git
- Apache Maven 3.9 or later
- Docker Desktop
- Google Chrome for local Selenium browser testing
- GitHub account

GitHub Actions uses a hosted runner and configures Java and Chrome for automated testing.

## 1. Run the Application Locally

Open PowerShell or a terminal in the project directory.

Build and verify the project:

```bash
mvn clean verify
```

Run the Spring Boot application:

```bash
mvn spring-boot:run
```

Open the following URLs in a browser:

- Home: http://localhost:8080/
- Health: http://localhost:8080/api/health

Expected health response:

```json
{"status":"UP"}
```

## 2. Automated Testing

Run the automated tests:

```bash
mvn test
```

The project includes the following tests.

### JUnit API Tests

The `HealthControllerTest.java` file verifies:

- The home endpoint returns HTTP 200.
- The home endpoint returns the expected project name and running status.
- The health endpoint returns HTTP 200.
- The health endpoint returns status `UP`.

### Selenium Browser Test

The `SeleniumHealthTest.java` file:

1. Configures Chrome to run in headless mode.
2. Opens the health endpoint using Selenium WebDriver.
3. Reads the response displayed in the browser.
4. Verifies that the response contains the expected `UP` status.
5. Closes the browser after the test.

**Local Selenium test requirement:** The application must already be running at `http://localhost:8081/api/health` when the Selenium test executes. Ensure Chrome is installed and Selenium can start the browser.

### Test Reports

After test execution, Maven generates reports under:

```text
target/surefire-reports/
```

These reports contain test execution results, including passed and failed tests.

## 3. Continuous Integration Using GitHub Actions

The project uses a GitHub Actions workflow located at:

```text
.github/workflows/ci.yml
```

The workflow automates the following operations:

1. Checks out the source code.
2. Sets up Java 21.
3. Configures Chrome for browser testing.
4. Builds the Spring Boot application.
5. Starts the application on port `8081`.
6. Waits for the health endpoint to become available.
7. Runs the automated Maven tests.
8. Uploads JUnit test reports as a workflow artifact.
9. Builds the Docker image.
10. Logs in to GHCR on eligible pushes to the `main` branch.
11. Tags and pushes the Docker image to GHCR.

The pipeline runs when code is pushed or a pull request is opened or updated.

### View the Workflow

1. Open the project's GitHub repository.
2. Select the **Actions** tab.
3. Open the latest workflow run.
4. Review the build, test, report upload, and Docker-related steps.
5. Download the `junit-test-reports` artifact to inspect the test results.

A successful workflow indicates that the configured steps completed successfully.

## 4. Run the Application Using Docker

Make sure Docker Desktop is running.

Build and start the application:

```bash
docker compose up --build -d
```

Check running containers:

```bash
docker compose ps
```

Open the health endpoint:

http://localhost:8080/api/health

Expected response:

```json
{"status":"UP"}
```

Stop the Compose services:

```bash
docker compose down
```

**Port note:** If port `8080` is already occupied, stop the conflicting container or adjust the port mapping in `compose.yaml`.

## 5. Docker Image Publishing

The GitHub Actions workflow is configured to publish the Docker image to GitHub Container Registry.

Image repository:

```text
ghcr.io/klu2300031196/devops-based-continuous-testing-framework
```

The workflow uses the following tags:

- `latest`
- The Git commit SHA

Publishing is configured for pushes to the `main` branch. Check the workflow logs and the repository's Packages page to confirm that the image was published successfully.

## 6. Failure Handling Demonstration

Failure handling demonstrates how automated testing can prevent a faulty change from passing the CI pipeline.

1. Open `HealthControllerTest.java`.
2. Temporarily change an expected test value to an incorrect value.
3. Commit and push the change to GitHub.
4. Open the Actions tab.
5. Verify that the test step fails.
6. Restore the correct expected value.
7. Commit and push the fix.
8. Verify that the workflow succeeds again.

Use this demonstration only in a controlled test commit. Do not leave the incorrect expectation in the final project.

## 7. Project Demonstration Checklist

During the project presentation:

1. Explain the project objective and architecture.
2. Show the GitHub repository and commit history.
3. Explain the Maven build process.
4. Demonstrate the JUnit API tests.
5. Explain the Selenium browser test.
6. Show the GitHub Actions workflow.
7. Open and explain the JUnit test report artifact.
8. Demonstrate a failed test and a successful run after fixing it.
9. Show the Docker image and running application container.
10. Verify the health endpoint response.

## 8. Future Enhancements

The following features can be considered for future development:

- Static code analysis and quality checks.
- UI testing for a dedicated web interface.
- Deployment to a cloud platform.
- Kubernetes orchestration.
- Application monitoring and alerting.
- Automated rollback after an unsuccessful deployment.

These are proposed enhancements and should not be presented as implemented features unless they are actually developed and tested.

## Security and Academic Integrity

- Never commit passwords, personal access tokens, cloud keys, or other secrets.
- Use GitHub Actions secrets for credentials when required.
- Document only features that have actually been implemented and verified.
- Explain the difference between the current implementation and future enhancements.

## Conclusion

The DevOps-Based Continuous Testing Framework demonstrates how GitHub, Maven, JUnit, Selenium, GitHub Actions, and Docker can work together to automate application building, testing, report generation, and container packaging.

The project provides a foundation for continuous testing and repeatable application delivery, with additional deployment and monitoring capabilities available as future enhancements.