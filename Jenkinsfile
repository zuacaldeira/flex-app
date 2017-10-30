pipeline {
    agent any
    tools{
        maven 'Maven5'
        jdk 'JDK9'
    }
    stages {
        stage('Initialization Development Server') {
            when {branch 'dev'}
            steps{
                sh './startDevelopment.sh'
            }
        }

        stage('Initialization Production Server') {
            when {branch 'master'}
            steps{
                sh './startProduction.sh'
            }
        }

        stage('Build Project') {
            steps {
                sh './build.sh'
            }
        }

        stage('Unit Test Project') {
            steps {
                sh './test.sh'
            }
        }

        stage('Integration Test Project') {
            when{branch 'master'}
            steps{
                sh './testITs.sh' 
            }
        }

        stage('Archive') {
            steps{
                sh './archive.sh'
            }
        }

        stage('Deploy To Development') {
            when{branch 'dev'}
            steps {
                sh './deployToDevelopment.sh'
            }
        }

        stage('Deploy To Production') {
            when{branch 'master'}
            steps {
                sh './deployToProduction.sh'
            }
        }

        stage('Terminate Development') {
            when{branch 'dev'}
            steps{
                sh './undeployDevelopment.sh'
                sh './stopDevelopment.sh'
            }
        }

        stage('Terminate Production') {
            when{branch 'master'}
            steps{
                sh './undeployProduction.sh'
                sh './stopProduction.sh'
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