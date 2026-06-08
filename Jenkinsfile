pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Run Automation Suite') {
            steps {
                // Shut down any stale instances and spin up the complete automated ecosystem
                sh 'docker-compose down'
                sh 'docker-compose up --abort-on-container-exit --exit-code-from jenkins-runner'
            }
        }
    }
    
    post {
        always {
            sh 'docker-compose down'
            junit '**/target/surefire-reports/*.xml'
        }
    }
}
