pipeline {
    agent any

    tools {
        jdk 'JDK21'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and test') {
            steps {
                sh './mvnw clean verify'
            }
        }

        stage('Publish JaCoCo report') {
            steps {
                archiveArtifacts artifacts: 'target/site/jacoco/**', fingerprint: true
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}
