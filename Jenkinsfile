pipeline {
    agent any

    tools {
        maven 'Maven-3'
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building project...'
                bat 'mvn clean package'
            }
        }

        stage('Run') {
            steps {
                echo 'Running Festival Service...'
                bat 'java -jar target\\LifeAutomationJenkins-0.0.1-SNAPSHOT.jar'
            }
        }
    }

    post {
        success {
            echo '✅ Jenkins Job completed successfully!'
        }
        failure {
            echo '❌ Jenkins Job failed!'
        }
    }
}
