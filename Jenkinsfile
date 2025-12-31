stage('Build') {
    steps {
        echo 'Building project...'
        withMaven(maven: 'Maven-3') {
            bat 'mvn clean package'
        }
    }
}
