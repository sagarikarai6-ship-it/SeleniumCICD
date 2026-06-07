pipeline {
    agent {
        docker {
            // This is a pre-made Docker image that includes Maven, Java, and Google Chrome
            image 'markhobson/maven-chrome:jdk-11'
            // This speeds up your builds by caching your Maven dependencies on your local machine
            args '-v /root/.m2:/root/.m2'
        }
    }

    stages {
        stage('Environment Check') {
            steps {
                // Just to verify everything is installed inside the container
                sh 'mvn -version'
                sh 'google-chrome --version'
            }
        }

        stage('Run Selenium Tests') {
            steps {
                // This triggers your Eclipse/Maven test suite in headless mode
                // (Make sure your WebDriver code uses ChromeOptions to set headless mode!)
                sh 'mvn clean test'
            }
        }
    }

    post {
        always {
            // This grabs your TestNG or JUnit XML reports so Jenkins can display the graph
            junit '**/target/surefire-reports/*.xml'
        }
    }
}
