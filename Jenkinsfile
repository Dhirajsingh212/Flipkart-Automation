pipeline {
    agent any

    stages {
        stage('Build & Test') {
            steps {
                echo "Starting Maven build and test"
                script {
                    if (isUnix()) {
                        sh 'mvn clean test'
                    } else {
                        bat 'mvn clean test'
                    }
                }
            }
        }

        stage('Archive Reports') {
            steps {
                script {
                    // Archive any test reports found
                    if (fileExists('**/target/surefire-reports/*.xml')) {
                        junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                    }

                    // Archive HTML report if exists
                    if (fileExists('target/ExtentReport.html')) {
                        archiveArtifacts artifacts: 'target/ExtentReport.html'
                    }
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}