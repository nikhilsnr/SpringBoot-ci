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
                echo 'Building Spring Boot application...'
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Stop Existing Application') {
            steps {
                bat '''
                echo Checking if application is running on port %APP_PORT%...

                set FOUND=false

                for /f "tokens=5" %%a in ('netstat -ano ^| findstr :%APP_PORT%') do (
                    set FOUND=true
                    echo Killing process %%a
                    taskkill /F /PID %%a
                )

                if "%FOUND%"=="false" (
                    echo No running application found on port %APP_PORT%
                )

                echo Stop stage completed successfully
                exit /b 0
                '''
            }
        }

        stage('Start Application') {
    steps {
        bat '''
        echo JAVA_HOME used by Jenkins: %JAVA_HOME%
        echo Starting Spring Boot application using Java 17...

        cd target
        start "" "%JAVA_HOME%\\bin\\java" -jar %JAR_NAME%
        '''
    }
}

    }

    post {
        success {
            echo '✅ CI/CD pipeline executed successfully!'
        }
        failure {
            echo '❌ CI/CD pipeline failed. Check logs.'
        }
    }
}
