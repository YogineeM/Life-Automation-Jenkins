pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                echo 'Building project...'
                bat 'mvn clean package'
            }
        }

        stage('Run Festival Service') {
            steps {
                echo 'Running Festival Service...'
                bat 'java -cp target/classes com.lifeautomation.reminder.FestivalService'
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
