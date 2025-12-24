pipeline {
    agent any

    tools {
        jdk 'JAVA_HOME'
        maven 'Maven-3'
    }

    environment {
        DEPLOY_DIR = 'D:\\deploy\\springboot-ci'
        JAR_NAME = 'git-0.0.1-SNAPSHOT.jar'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn clean test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Deploy') {
    steps {
        echo 'Deploying Spring Boot application'

        bat """
        if exist %DEPLOY_DIR%\\stop.bat call %DEPLOY_DIR%\\stop.bat
        copy /Y target\\%JAR_NAME% %DEPLOY_DIR%
        call %DEPLOY_DIR%\\start.bat
        """
    }
}


    }

    post {
        success {
            echo 'CI/CD pipeline SUCCESS'
        }
        failure {
            echo 'CI/CD pipeline FAILED'
        }
    }
}
