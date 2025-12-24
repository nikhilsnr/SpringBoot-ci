pipeline {
    agent any

    tools {
        jdk 'JAVA_HOME'
        maven 'Maven-3'
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
    }

    post {
        success {
            echo 'Spring Boot build SUCCESS'
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
        failure {
            echo 'Spring Boot build FAILED'
        }
    }
}
