pipeline {
    agent any // Runs on the main Jenkins agent directly

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Run Specific Selenium Test') {
            steps {
                // OPTION 1: Run EVERYTHING in your test folder
                // sh 'mvn test'

                // OPTION 2: Run a SPECIFIC test class (Replace 'MyFirstSeleniumTest' with your actual Java class name!)
                //sh 'mvn test -Dtest=*Test'
                sh 'mvn clean test -Dtest=*Test -Dheadless=true'
                // OPTION 3: Run a specific TestNG XML file if you have one configured
                // sh 'mvn test -DsuiteXmlFile=testng.xml'
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
