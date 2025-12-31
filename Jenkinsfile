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

                    // Split into lines and remove unwanted lines (command echoes)
                    def lines = result.readLines()
                    def cleanedLines = lines.findAll { 
                        !it.startsWith('C:\\') && it.trim() != '' && !it.startsWith('?') 
                    }

                    // Join back into single string
                    def outputText = cleanedLines.join('\n').trim()

                    // Highlight only the day name after colon
                    def parts = outputText.split(":", 2)
                    if (parts.length == 2) {
                        def beforeColon = parts[0] + ":"
                        def afterColon = parts[1].trim()
                        env.JAVA_OUTPUT = "${beforeColon} <span style='color:orange; font-weight:bold;'>${afterColon}</span>"
                    } else {
                        env.JAVA_OUTPUT = outputText
                    }
                }
            }
        }
    }

    post {
        success {
            echo '✅ Jenkins Job completed successfully!'

            // Beautiful success email
            emailext (
                subject: "✅ [SUCCESS] Jenkins Job: ${env.JOB_NAME}",
                body: """
                    <div style="font-family: Arial, sans-serif; line-height:1.6;">
                        <h2 style="color:green;">✅ Job Success!</h2>
                        <p>The Jenkins job '<b>${env.JOB_NAME}</b>' has finished successfully.</p>
                        <p>Check build details: <a href='${env.BUILD_URL}'>View Build</a></p>
                        <hr style="border:1px solid #ccc;">
                        <p><b>🎉 Today's Output:</b></p>
                        <p style="font-size:16px; color:green;">${env.JAVA_OUTPUT}</p>
                    </div>
                """,
                to: "yogineemondkar03@gmail.com, imabhayakauab2525@gmail.com",
                mimeType: 'text/html'
            )
        }

        failure {
            echo '❌ Jenkins Job failed!'

            // Beautiful failure email
            emailext (
                subject: "❌ [FAILURE] Jenkins Job: ${env.JOB_NAME}",
                body: """
                    <div style="font-family: Arial, sans-serif; line-height:1.6;">
                        <h2 style="color:red;">❌ Job Failed!</h2>
                        <p>The Jenkins job '<b>${env.JOB_NAME}</b>' has <b>FAILED</b>.</p>
                        <p>Check build details: <a href='${env.BUILD_URL}'>View Build</a></p>
                        <hr style="border:1px solid #ccc;">
                        <p><b>⚠️ Today's Output:</b></p>
                        <p style="font-size:16px; color:red;">${env.JAVA_OUTPUT}</p>
                    </div>
                """,
                to: "yogineemondkar03@gmail.com, imabhayakauab2525@gmail.com",
                mimeType: 'text/html'
            )
        }
    }
}
