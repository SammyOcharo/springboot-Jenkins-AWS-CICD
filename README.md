# springboot-Jenkins-AWS-CICD


# Spring Boot CI/CD Pipeline with Jenkins, Docker, AWS ECS & ECR

## Overview

This project automates the build, test, and deployment of a Spring Boot application using a Continuous Integration/Continuous Deployment (CI/CD) pipeline. The entire process is streamlined using Jenkins, Docker, Amazon Web Services (AWS), and Elastic Container Service (ECS). This pipeline ensures that every code change pushed to GitHub automatically triggers the pipeline, builds the application, packages it into a Docker image, pushes the image to AWS Elastic Container Registry (ECR), and finally deploys it to ECS.

---

## CI/CD Pipeline Setup

### 1. GitHub Integration with Jenkins

We configured Jenkins to integrate with GitHub by setting up webhooks. This ensures that whenever there is a push event (commit to the `main` branch) on GitHub, Jenkins automatically triggers a build job. The configuration steps include:

- **GitHub Webhook Setup**: A webhook was created on GitHub to notify Jenkins about changes in the repository. The webhook URL points to Jenkins' GitHub webhook endpoint (`http://<your_jenkins_url>/github-webhook/`).
- **Jenkins GitHub Plugin**: We installed the **GitHub plugin** on Jenkins to enable webhook integration and pull the latest code whenever a change is pushed to the GitHub repository.

### 2. Jenkins Configuration

The Jenkins pipeline is defined in a `Jenkinsfile`, which automates the process of building, testing, and deploying the application. The pipeline is broken down into the following stages:

- **Checkout Stage**: Pulls the latest changes from GitHub.
- **Build Stage**: Uses **Maven** to build the Spring Boot application.
- **Docker Image Build**: The application is packaged into a Docker image, tagged with the latest version.
- **Push to AWS ECR**: The Docker image is pushed to **AWS ECR** (Elastic Container Registry).
- **Deployment to ECS**: The image is then deployed to **AWS ECS** (Elastic Container Service) for hosting the Spring Boot application.

### 3. Automating Builds with Jenkins

To automate the build process on every push to GitHub, we added the **GitHub hook trigger for GITScm polling** option in the Jenkins job configuration. This ensures Jenkins is triggered automatically when changes are detected in the repository.

---

## Docker Setup

### 1. Dockerizing the Application

We containerized the Spring Boot application using **Docker**. The `Dockerfile` defines the following steps:

- Use a base `openjdk` image as the runtime.
- Copy the Spring Boot JAR file into the container.
- Expose port `8080` for the application.
- Define the entry point for the application, which starts the Spring Boot server.

**Dockerizing** the app makes it portable and deployable in any environment (in this case, AWS ECS).

### 2. Docker Build Process in Jenkins

Once the build stage is complete, the pipeline uses the `docker.build` command to build a Docker image from the `Dockerfile`. Afterward, the image is tagged with the AWS ECR repository URL (`aws_account_id.dkr.ecr.region.amazonaws.com/repository_name`) and the version tag (e.g., `latest`).

---

## AWS Setup

### 1. Elastic Container Registry (ECR)

We used **Amazon ECR** to store the Docker images securely. **ECR** provides a fully managed Docker container registry that we can use to store and retrieve Docker images.

- Jenkins authenticates with ECR using AWS CLI to push Docker images.
- The `aws ecr get-login-password` command is used to retrieve authentication credentials, which are then passed to `docker login` to authenticate Docker to ECR.

### 2. Elastic Container Service (ECS)

**Amazon ECS** is used to run the Docker containers. ECS simplifies the deployment of containerized applications on AWS. We configured ECS to run a Docker container from the image stored in ECR.

The ECS setup involves:

- **Creating a Cluster**: ECS clusters organize and manage the deployed containerized services.
- **Task Definition**: A task definition specifies the Docker image to use and resource allocation (memory, CPU) for the container.
- **Service Deployment**: A service ensures that the desired number of instances of the application are running.

Once the image is pushed to ECR, the ECS task is updated with the latest image from the registry.

---

## Steps to Set Up the CI/CD Pipeline

### Step 1: Prepare the Repository

Clone the repository and create your own branch if needed. The repository contains the necessary files including:

- **Jenkinsfile**: The pipeline definition file used by Jenkins.
- **Dockerfile**: The file used to build the Docker image of the Spring Boot application.

### Step 2: Configure Jenkins

- Install the **GitHub plugin** and **Docker plugin** on Jenkins.
- Create a new Jenkins pipeline job and configure the job to use the GitHub repository URL.
- Add the `Jenkinsfile` to the pipeline configuration.

### Step 3: Create and Configure AWS Resources

- Set up **Amazon ECR** to store your Docker images.
- Set up **Amazon ECS** to deploy the Docker containers.
- Create a task definition in ECS with the necessary configurations for running the container.

### Step 4: Configure Webhook

Set up a **GitHub webhook** to notify Jenkins whenever changes are pushed to the repository.

### Step 5: Test the Pipeline

After everything is set up, push a change to GitHub and ensure that Jenkins automatically triggers the pipeline. The pipeline should build the application, create a Docker image, push it to ECR, and deploy it to ECS.

---

## 🎯 Conclusion

By setting up a CI/CD pipeline with **Jenkins**, **Docker**, **AWS ECS**, and **ECR**, we’ve automated the process of building, testing, and deploying a Spring Boot application. Now, every time a change is pushed to the repository, the entire pipeline runs automatically, from building the app to deploying it on ECS.

This setup reduces manual intervention and speeds up deployment, making the workflow more efficient and reliable.

---

## 🚀 Troubleshooting

If the Jenkins pipeline doesn’t trigger after pushing changes to GitHub, check the following:

1. **Webhook Setup**: Ensure the webhook is correctly configured in GitHub.
2. **Permissions**: Verify that Jenkins has proper permissions to access GitHub and AWS services.
3. **Jenkins Logs**: Check the Jenkins system logs for any errors related to SCM polling or webhooks.

If the ECS service is not responding, ensure:

1. **Security Group**: The security group attached to the ECS instances allows traffic on port `8080`.
2. **ECS Task Configuration**: The ECS task definition is correctly pointing to the Docker image stored in ECR.
3. **Container Logs**: Check ECS task and container logs for any errors during startup.

---

## 🖼 Demo & Badges

![CI/CD Pipeline](https://img.shields.io/badge/CI%2FCD-Pipeline-blue)
![Docker Build](https://img.shields.io/badge/Docker-Build-orange)

---
