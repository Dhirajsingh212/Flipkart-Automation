pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo "Starting build process"
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                script {
                    // Check if Maven is available on the system
                    try {
                        // Use bat instead of sh for Windows
                        if (isUnix()) {
                            sh 'mvn --version'
                            sh 'mvn clean test'
                        } else {
                            bat 'mvn --version'
                            bat 'mvn clean test'
                        }
                        echo "Build and tests completed successfully"
                    } catch (Exception e) {
                        echo "Maven not found or build failed. Error: ${e.getMessage()}"
                        currentBuild.result = 'FAILURE'
                        error("Build failed")
                    }
                }
            }
        }

        stage('Report') {
            steps {
                script {
                    // Check if test results exist before trying to publish
                    if (fileExists('**/target/surefire-reports/*.xml')) {
                        junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                    } else {
                        echo "No JUnit test results found"
                    }

                    // Check if HTML report exists before publishing
                    if (fileExists('target/ExtentReport.html')) {
                        publishHTML([
                            allowMissing: false,
                            alwaysLinkToLastBuild: true,
                            keepAll: true,
                            reportDir: 'target',
                            reportFiles: 'ExtentReport.html',
                            reportName: 'Extent Report'
                        ])
                    } else {
                        echo "ExtentReport.html not found in target directory"
                    }
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
        success {
            echo "Pipeline completed successfully!"
        }
        failure {
            echo "Pipeline failed. Please check the logs and ensure Maven is installed."
        }
    }
}