
pipeline {
    agent any
 
    environment {
        TOMCAT_URL = 'http://localhost:7070/'
        TOMCAT_USERNAME = 'admin'
        TOMCAT_PASSWORD = 'admin'
        WAR_FILE = 'target/FingerTips-0.0.1-SNAPSHOT.war'
    }
 
    stages {
        stage('Checkout') {
            steps {
                // Checkout the Git repository
                git branch: 'main', url: 'https://github.com/abhay-1403/FingerTipsVersion.git'
            }
        }
        stage('Build') {
            steps {
                script {
                    // Clean, compile, and package the Spring Boot application
                    bat 'mvn clean compile package'
                }
            }
        }
 
        stage('Test') {
            steps {
                script {
                    // Run tests
                    bat 'mvn test'
                }
            }
        }
         stage("SonarQube analysis") {
            steps {
                bat '''
                    mvn sonar:sonar ^
                    -Dsonar.host.url=http://localhost:9000/ ^
                    -Dsonar.login=squ_8031352cf6d56ee20956031e6ffc82ae45ced46c ^
                    -Dsonar.projectName=FingerTips ^
                    -Dsonar.java.binaries=. ^
                    -Dsonar.projectKey=FingerTipsNew
                '''
            }
        }
        stage('Deploy') {
             steps {
                script {
                    // Undeploy the existing application
                    bat "curl ${env.TOMCAT_URL}/manager/text/undeploy?path=/New --user ${env.TOMCAT_USERNAME}:${env.TOMCAT_PASSWORD}"
                // Deploy the new WAR file to Tomcat
            bat "curl -T ${env.WAR_FILE} ${env.TOMCAT_URL}/manager/text/deploy?path=/New --user ${env.TOMCAT_USERNAME}:${env.TOMCAT_PASSWORD}"
        }
    }
}
    }
}
