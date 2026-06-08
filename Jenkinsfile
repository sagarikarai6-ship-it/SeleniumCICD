pipeline {
    agent any // Runs on the main Jenkins agent directly

    tools {
        // This links the 'Maven3' configuration we set up in Jenkins UI
        maven 'Maven3' 
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Run Specific Selenium Test') {
            steps {
                // This triggers your tests and passes the headless flag to your Java code
                sh 'mvn clean test -Dtest=*Test -Dheadless=true'
            }
        }
    }

    post {
        always {
            // Read results
            junit '**/target/surefire-reports/*.xml'
        }
    }
}
