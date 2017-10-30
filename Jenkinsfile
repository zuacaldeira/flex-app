pipeline {
    agent any
    tools{
        maven 'Maven5'
        jdk 'JDK8'
    }
    stages {
        stage('DEVELOPMENT') {
            when {branch 'dev'}
            steps{
                sh './scripts/startDevelopment.sh'
                sh './scripts/build.sh'
                sh './scripts/test.sh'
                sh './scripts/archive.sh'
                sh './scripts/deployToDevelopment.sh'
            }
        }

        stage('RELEASE') {
            when {branch 'release'}
            steps{
                sh './release.sh'
            }
        }

        stage('PRODUCTION') {
            when {branch 'master'}
            steps{
                sh './scripts/startProduction.sh'
                sh './scripts/build.sh'
                sh './scripts/test.sh'
                sh './scripts/testITs.sh' 
                sh './scripts/archive.sh'
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