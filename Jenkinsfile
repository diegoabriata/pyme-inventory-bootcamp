pipeline {
    agent any

    tools {
        jdk 'JDK21'
	maven 'Maven3'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and test') {
            steps {
                sh 'mvn clean verify'
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
