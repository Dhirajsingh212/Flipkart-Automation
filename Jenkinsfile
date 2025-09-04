pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo "Starting build process"
                checkout scm
            }
        }

        stage('Build & Test with Docker') {
            steps {
                script {
                    // Use Docker container for Maven build
                    docker.image('maven:3.9.6-eclipse-temurin-17').inside('-v /var/run/docker.sock:/var/run/docker.sock') {
                        sh 'mvn clean test'
                        echo "Code executed in docker container"
                    }
                }
            }
        }

        stage('Report') {
            steps {
                junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'

                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target',
                    reportFiles: 'ExtentReport.html',
                    reportName: 'Extent Report'
                ])
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
