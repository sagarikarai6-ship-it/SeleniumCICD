pipeline {
    agent any

    tools {
        // Tells Jenkins to use the Maven configuration we set up in Tools UI
        maven 'Maven3' 
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Run Automation Suite') {
            steps {
                // We point the remote URL to host.docker.internal so the Jenkins container
                // can seamlessly talk out to the Selenium Chrome container on your machine!
                sh 'mvn clean test -Dtest=*Test -Dselenium.remote.url=http://host.docker.internal:4444/wd/hub'
            }
        }
    }
    
    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
    }
}
