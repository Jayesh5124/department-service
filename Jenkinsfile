pipeline {
    agent any 
    tools {
        maven 'my-maven'
        jdk 'my-jdk'
    }
    stages {        
        stage('Clone') {
            steps {
                git url: 'https://github.com/Jayesh5124/department-service.git', branch: 'department-service'
            }
        }
        stage('Build') {
            steps {
                bat "mvn clean install -DskipTests"
            }
        }
        stage('Test') {
            steps {
                bat "mvn test"
            }
        }
        stage('Deploy') {
            steps { 
                bat "docker rm -f department-container"
                bat "docker rmi -f department-image"
                bat "docker build -t department-image ."
                bat "docker run -p 8081:8081 -d --name department-container department-image"
            }
        }
    }
}
