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
                script {
                    // Run the jar and capture output
                    def result = bat(
                        script: 'java -jar target\\LifeAutomationJenkins-0.0.1-SNAPSHOT.jar',
                        returnStdout: true
                    ).trim()

                    // Remove unwanted leading characters (like '? ')
                    result = result.replaceFirst(/^\\?\\s*/, '')

                    // Optional: highlight only the day name
                    // e.g., split at colon and color the part after colon
                    def parts = result.split(":", 2)
                    if (parts.length == 2) {
                        def beforeColon = parts[0] + ":"
                        def afterColon = parts[1].trim()
                        env.JAVA_OUTPUT = "${beforeColon} <span style='color:orange; font-weight:bold;'>${afterColon}</span>"
                    } else {
                        env.JAVA_OUTPUT = result
                    }
                }
            }
        }
    }

    post {
        success {
            echo '✅ Jenkins Job completed successfully!'

            // Send email on success with formatted output
            emailext (
                subject: "✅ Job Success: ${env.JOB_NAME}",
                body: """
                    <p>The Jenkins job '<b>${env.JOB_NAME}</b>' has finished successfully.</p>
                    <p>Check build details: <a href='${env.BUILD_URL}'>${env.BUILD_URL}</a></p>
                    <hr>
                    <p><b>Today's Output:</b></p>
                    <p style="font-size:16px;">
                        ${env.JAVA_OUTPUT}
                    </p>
                """,
                to: "yogineemondkar03@gmail.com, imabhayakauab2525@gmail.com",
                mimeType: 'text/html'
            )
        }

        failure {
            echo '❌ Jenkins Job failed!'

            // Send email on failure with formatted output
            emailext (
                subject: "❌ Job Failed: ${env.JOB_NAME}",
                body: """
                    <p>The Jenkins job '<b>${env.JOB_NAME}</b>' has FAILED.</p>
                    <p>Check build details: <a href='${env.BUILD_URL}'>${env.BUILD_URL}</a></p>
                    <hr>
                    <p><b>Today's Output:</b></p>
                    <p style="font-size:16px; color:red;">
                        ${env.JAVA_OUTPUT}
                    </p>
                """,
                to: "yogineemondkar03@gmail.com, imabhayakauab2525@gmail.com",
                mimeType: 'text/html'
            )
        }
    }
}
