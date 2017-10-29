pipeline {
    agent any
    tools{
        maven 'Maven 3.5.0'
        jdk 'JDK8'
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn install -DskipTests'
            }
        }

        stage('Unit Tests') {
            steps {
                sh 'mvn test -DskipITs'
            }
        }

        stage('Integration Tests') {
            steps{
                sh 'mvn verify' 
            }
        }

        stage('Deploy - Staging Server') {
            steps {
                echo 'TODO' 
            }
        }

        stage('Sanity check') {
            steps {
                input "Does the staging environment look ok?"
            }
        }

        stage('Deploy - Production Server') {
            steps {
                echo 'TODO' 
            }
        }
    }

    post {
        always {
            echo 'One way or another, I have finished'
            deleteDir() /* clean up our workspace */
        }
        success {
            echo 'I succeeeded!'
        }
        unstable {
            echo 'I am unstable :/'
        }
        failure {
            echo 'I failed :('
        }
        changed {
            echo 'Things were different before...'
        }
    }
}