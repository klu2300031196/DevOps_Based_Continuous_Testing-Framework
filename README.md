# DevOps-Based Continuous Testing Framework

A starter capstone implementation aligned with the Review-2 presentation: source control, automated build/testing, test reports, container packaging, and a local deployment demonstration.

## Architecture

Developer -> GitHub -> GitHub Actions -> Maven build + JUnit tests -> test report artifact -> Docker image -> local container

**Important scope note:** this starter includes CI, automated API-level tests, report artifacts, and Docker-based local deployment. It does not yet implement Selenium browser tests, Kubernetes orchestration, cloud deployment, production monitoring, or automatic rollback. Those can be added as later modules if required by your guide.

## Requirements

- JDK 21
- Git
- Maven 3.9+ (optional if using `./mvnw`; this starter currently expects Maven installed)
- Docker Desktop for container run
- GitHub account for hosted pipeline

## 1. Run locally

From this project directory:

```bash
mvn clean verify
mvn spring-boot:run
```

Open:
- http://localhost:8080/
- http://localhost:8080/api/health

Expected health response:

```json
{"status":"UP"}
```

## 2. Test the project

```bash
mvn test
```

JUnit reports are generated in `target/surefire-reports/`.

To demonstrate failure handling, temporarily change the expected value in `HealthControllerTest.java`, commit and push. The GitHub Actions workflow should fail at the test step and must not be treated as a successful release. Restore the test afterward.

## 3. Push to GitHub

Create an empty GitHub repository, then run the following commands from this directory (replace the URL with your repository URL):

```bash
git init
git add .
git commit -m "Initial continuous testing framework"
git branch -M main
git remote add origin https://github.com/YOUR-USERNAME/YOUR-REPOSITORY.git
git push -u origin main
```

Open the repository's **Actions** tab to view the pipeline. It runs on every push and pull request. The workflow uploads JUnit test reports as an artifact even when tests fail.

If `origin` already exists, use `git remote set-url origin YOUR-REPOSITORY-URL` instead of adding it again.

## 4. Run using Docker

Make sure Docker Desktop is running, then:

```bash
docker compose up --build -d
docker compose ps
```

Open http://localhost:8080/api/health.

Stop the container:

```bash
docker compose down
```

## 5. Review demo checklist

1. Show the source repository and commit history.
2. Push a harmless code change and show the workflow starting.
3. Show build and JUnit test results.
4. Show the uploaded `junit-test-reports` artifact.
5. Intentionally make a test expectation wrong; push it and show the pipeline failing.
6. Restore the test and show a green run.
7. Build and run the application with Docker.

## 6. Suggested next modules

- Selenium UI tests for a real web page.
- Static analysis with a code-quality scanner.
- Docker image publishing to a registry.
- A deployment job that runs only after all tests pass.
- Kubernetes deployment and monitoring/rollback only if required and supported by your infrastructure.

## Security and academic integrity

Do not commit passwords, access tokens, cloud keys, or other secrets. Use GitHub Actions secrets for credentials if a later deployment stage requires them. Explain honestly which modules are implemented and which are future enhancements.
