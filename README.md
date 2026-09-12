# Java CI/CD Pipeline with Jenkins, SonarQube & Nexus

## Overview

This project demonstrates an end-to-end CI/CD pipeline for a Java Maven application using GitHub, Jenkins, SonarQube, and Nexus Repository.

The pipeline automates the software delivery workflow from source code checkout through build, testing, code-quality analysis, packaging, and artifact deployment.

## CI/CD Workflow

GitHub
   |
   v
Jenkins
   |
   +---- Build
   |
   +---- Unit Tests
   |
   +---- SonarQube Analysis
   |          |
   |          v
   |     Quality Gate
   |
   +---- Package
   |
   v
Nexus Repository

## Technologies Used

- Java 21
- Maven 3.9.x
- Jenkins
- GitHub
- SonarQube
- Nexus Repository
- JUnit 4
- Linux

## Project Structure

my-cicd-app/
├── Jenkinsfile
├── pom.xml
├── README.md
├── .gitignore
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── rajat/
    │               └── app/
    │                   └── App.java
    └── test/
        └── java/
            └── com/
                └── rajat/
                    └── app/
                        └── AppTest.java

The Maven target/ directory contains generated build files and is excluded from Git.

## Jenkins Pipeline

The CI/CD pipeline is defined in the Jenkinsfile.

### Pipeline Stages

### 1. Checkout

Jenkins checks out the application source code from the GitHub repository.

### 2. Build

Maven compiles the Java application.

    mvn clean compile

### 3. Test

JUnit tests are executed using Maven.

    mvn test

Example successful test result:

    Tests run: 1
    Failures: 0
    Errors: 0
    Skipped: 0

### 4. SonarQube Analysis

The application is analyzed using SonarQube for code quality and security analysis.

The configured SonarQube project key is:

    my-cicd-app

Jenkins integrates with SonarQube using the Jenkins withSonarQubeEnv configuration.

SonarQube authentication is handled through Jenkins-managed credentials and is not stored in the source code.

### 5. Quality Gate

After SonarQube analysis, Jenkins waits for the SonarQube Quality Gate result.

The pipeline proceeds only after the Quality Gate stage completes successfully.

    SonarQube Analysis
            |
            v
       Quality Gate
            |
            v
      Continue Pipeline

### 6. Package

After successful analysis and Quality Gate validation, Maven packages the application as a JAR.

    mvn package -DskipTests

Generated artifact:

    my-cicd-app-1.0.0.jar

### 7. Deploy to Nexus

The generated Maven artifact is deployed to Nexus Repository.

    mvn deploy -DskipTests

The project uses Maven release and snapshot repository configurations defined in pom.xml.

Nexus authentication is handled externally through Jenkins/Maven configuration.

## Maven Configuration

The project uses Java 21:

    <maven.compiler.source>21</maven.compiler.source>
    <maven.compiler.target>21</maven.compiler.target>

The SonarQube project key is configured as:

    <sonar.projectKey>my-cicd-app</sonar.projectKey>

## SonarQube Maven Plugin Configuration

During implementation, Maven initially failed to resolve the SonarQube Maven plugin when using:

    mvn sonar:sonar

The issue was resolved by configuring the Jenkins user's Maven settings with the SonarQube Maven plugin group:

    <pluginGroups>
        <pluginGroup>org.sonarsource.scanner.maven</pluginGroup>
    </pluginGroups>

This allows Maven to resolve the sonar prefix to:

    org.sonarsource.scanner.maven:sonar-maven-plugin

without modifying the existing Jenkins pipeline command.

## Jenkins Configuration

The Jenkins pipeline uses managed tools for:

    JDK21
    Maven3

The SonarQube server is configured in Jenkins as:

    MySonarQube

SonarQube authentication is handled using a Jenkins-managed credential.

Nexus deployment credentials are also managed outside the source code.

## Security

No passwords, API tokens, or other sensitive credentials should be committed to this repository.

Credentials for SonarQube and Nexus are managed through Jenkins and Maven configuration.

The repository intentionally does not contain:

- SonarQube tokens
- Nexus passwords
- Jenkins credentials
- Private keys
- Generated build artifacts

## Artifact Management

The pipeline produces the following Maven artifact:

    my-cicd-app-1.0.0.jar

After successful completion of the CI/CD pipeline, the artifact is deployed to the configured Nexus Maven repository.

## Successful Pipeline

The completed pipeline performs:

    GitHub
       |
       v
    Checkout
       |
       v
    Build
       |
       v
    Test
       |
       v
    SonarQube Analysis
       |
       v
    Quality Gate
       |
       v
    Package
       |
       v
    Nexus Repository

All stages are automated through Jenkins.

## Troubleshooting Experience

During implementation, the following issues were identified and resolved:

### Maven Sonar Plugin Resolution

Initial error:

    No plugin found for prefix 'sonar'

Resolution:

Configured the SonarQube Maven plugin group in the Jenkins user's Maven settings.

### SonarQube Authentication

The scanner initially returned:

    HTTP 401 Unauthorized

The Jenkins SonarQube configuration was verified and connected to the appropriate SonarQube authentication credential.

### SonarQube Server Configuration

The SonarQube integration was verified against the self-hosted SonarQube server configured in Jenkins.

## Key Learning Outcomes

This project demonstrates practical experience with:

- CI/CD pipeline automation using Jenkins
- Jenkins Declarative Pipelines
- GitHub integration
- Maven build automation
- Java application compilation
- JUnit unit testing
- SonarQube integration
- SonarQube Quality Gates
- Nexus Repository integration
- Maven artifact management
- Jenkins credential management
- Linux administration
- Maven plugin troubleshooting
- CI/CD troubleshooting
- Secure handling of deployment credentials

## Screenshots

Screenshots demonstrating the working environment can be added to this section.

Recommended screenshots:

1. Jenkins successful pipeline
2. SonarQube project and Quality Gate
3. Nexus Repository containing the deployed JAR
4. GitHub repository structure

Make sure screenshots do not expose passwords, tokens, or other sensitive information.

## Author

Rajat Goel

GitHub: https://github.com/rajatgoel999

## Project Summary

A practical Java CI/CD implementation demonstrating automated software delivery from source control through build, testing, code-quality validation, packaging, and artifact deployment.

GitHub → Jenkins → Maven → JUnit → SonarQube → Quality Gate → Nexus
