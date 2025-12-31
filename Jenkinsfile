pipeline {
    agent any

    triggers {
        cron('H 7 * * *')   // runs daily at 7 AM
    }

    stages {

        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Run Festival Service') {
            steps {
                bat 'java -jar target/LifeAutomationJenkins-0.0.1-SNAPSHOT.jar'
            }
        }
    }

    post {
        success {
            echo '🎉 Jenkins Job executed successfully!'
        }
        failure {
            echo '❌ Jenkins Job failed!'
        }
    }
}
