pipeline {
    agent any

    tools {
        jdk 'JAVA_HOME'
        maven 'Maven-3'
    }

    environment {
        APP_NAME = "git"
        JAR_NAME = "git-0.0.1-SNAPSHOT.jar"
        APP_PORT = "8081"
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/nikhilsnr/SpringBoot-ci.git'
            }
        }

        stage('Build Application') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Stop Existing Application') {
            steps {
                bat '''
                echo Checking if application is running...
                for /f "tokens=5" %%a in ('netstat -ano ^| findstr :%APP_PORT%') do (
                    echo Killing process %%a
                    taskkill /F /PID %%a
                )
                '''
            }
        }

        stage('Start Application') {
            steps {
                bat '''
                echo Starting Spring Boot Application...
                cd target
                start java -jar %JAR_NAME%
                '''
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
