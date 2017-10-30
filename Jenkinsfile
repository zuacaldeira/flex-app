pipeline {
    agent any
    tools{
        maven 'Maven5'
        jdk 'JDK8'
    }
    stages {
        stage('Initialization Development Server') {
            when {branch 'dev'}
            steps{
                sh './scripts/startDevelopment.sh'
            }
        }

        stage('Initialization Production Server') {
            when {branch 'master'}
            steps{
                sh './scripts/startProduction.sh'
            }
        }

        stage('Build Project') {
            steps {
                sh './scripts/build.sh'
            }
        }

        stage('Unit Test Project') {
            steps {
                sh './scripts/test.sh'
            }
        }

        stage('Integration Test Project') {
            when{branch 'master'}
            steps{
                sh './scripts/testITs.sh' 
            }
        }

        stage('Archive') {
            steps{
                sh './scripts/archive.sh'
            }
        }

        stage('Deploy To Development') {
            when{branch 'dev'}
            steps {
                sh './scripts/deployToDevelopment.sh'
            }
        }

        stage('Deploy To Production') {
            when{branch 'master'}
            steps {
                sh './scripts/deployToProduction.sh'
            }
        }

    }

    post {
        always {
            echo 'One way or another, I have finished'
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