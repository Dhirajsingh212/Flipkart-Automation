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
                    // Check if TestNG results exist (your tests are using TestNG)
                    if (fileExists('**/target/surefire-reports/testng-results.xml')) {
                        echo "Found TestNG results, publishing test report"
                        publishTestResults testResultsPattern: '**/target/surefire-reports/testng-results.xml'
                    } else if (fileExists('**/target/surefire-reports/*.xml')) {
                        echo "Found JUnit XML results, publishing test report"
                        junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                    } else {
                        echo "No test results found in surefire-reports directory"
                        // List files to help debug
                        if (isUnix()) {
                            sh 'find target -name "*.xml" -type f || echo "No XML files found"'
                        } else {
                            bat 'dir target\\surefire-reports\\ /s || echo "Directory not found or empty"'
                        }
                    }

                    // Check if HTML report exists before publishing
                    if (fileExists('target/ExtentReport.html')) {
                        echo "Found ExtentReport.html, archiving as artifact"
                        archiveArtifacts artifacts: 'target/ExtentReport.html', fingerprint: true
                        echo "ExtentReport.html archived as build artifact"
                    } else {
                        echo "ExtentReport.html not found in target directory"
                        // List target directory contents to help debug
                        if (isUnix()) {
                            sh 'ls -la target/ || echo "Target directory not found"'
                        } else {
                            bat 'dir target\\ || echo "Target directory not found"'
                        }
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