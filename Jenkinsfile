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

        stage('Deploy To Development Server') {
            when {branch 'dev'}
            steps{echo  'TODO: Deploy to DEVELOPMENT'}
        }

        stage('Deploy To Development Server') {
            when {branch 'release'}
            steps{echo  '"TODO: Release"'}
        }

        stage('Deploy Production Server') {
            when {branch 'master'}
            steps{echo 'TODO: Deploy to PRODUCTION'}
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