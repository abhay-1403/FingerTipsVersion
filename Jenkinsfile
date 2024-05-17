pipeline {
    agent any

    environment {
            TOMCAT_URL = 'http://localhost:8080/'
            TOMCAT_USERNAME = 'admin'
            TOMCAT_PASSWORD = 'admin'
            WAR_FILE = 'target/FingerTips-0.0.1-SNAPSHOT.war'
       }

    stages {
        stage('Build') {
            steps {
                script {
                    // Clean, compile, and package the Spring Boot application
                    sh 'mvn clean compile package'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    // Run tests
                    sh 'mvn clean test'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    // Deploy the WAR file to Tomcat
                    withCredentials([usernamePassword(credentialsId: 'your-credentials-id', usernameVariable: 'TOMCAT_USERNAME', passwordVariable: 'TOMCAT_PASSWORD')]) {
                        sh "curl -T ${WAR_FILE} ${TOMCAT_URL}/manager/text/deploy?path=/New --user ${TOMCAT_USERNAME}:${TOMCAT_PASSWORD}"
                    }
                }
            }
        }
    }
}