pipeline {
    agent any
    environment {
        DISABLE_AUTH = 'true'
    }
    stages {
        stage('Build') {
            steps {
                sh 'ssh -t 127.0.0.1 "sudo scripts/buildSkipTests"'
            }
        }

        stage('Unit Tests') {
            steps {
                sh './runUnitTests'
            }
        }

        stage('Integration Tests') {
            steps{
                sh './runIntegrationTests' 
            }
        }

        stage('Deploy - Staging Server') {
            steps {
                sh '/.deployToDevelopment' 
            }
        }

        stage('Sanity check') {
            steps {
                input "Does the staging environment look ok?"
            }
        }

        stage('Deploy - Production Server') {
            steps {
                sh '/.deployToProduction' 
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