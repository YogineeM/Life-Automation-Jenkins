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

            // Send email on success
            emailext (
                subject: "✅ Job Success: ${env.JOB_NAME}",
                body: """
                    <p>The Jenkins job '<b>${env.JOB_NAME}</b>' has finished successfully.</p>
                    <p>Check build details: <a href='${env.BUILD_URL}'>${env.BUILD_URL}</a></p>
                """,
                to: "yogineemondkar03@gmail.com","imabhayakauab2525@gmail.com",
                mimeType: 'text/html'
            )
        }

        failure {
            echo '❌ Jenkins Job failed!'

            // Send email on failure
            emailext (
                subject: "❌ Job Failed: ${env.JOB_NAME}",
                body: """
                    <p>The Jenkins job '<b>${env.JOB_NAME}</b>' has FAILED.</p>
                    <p>Check build details: <a href='${env.BUILD_URL}'>${env.BUILD_URL}</a></p>
                """,
                to: "yogineemondkar03@gmail.com","imabhayakauab2525@gmail.com",
                mimeType: 'text/html'
            )
        }
    }
}
