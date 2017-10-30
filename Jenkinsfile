pipeline {
    agent any
    tools{
        maven 'Maven5'
        jdk 'JDK9'
    }
    stages {
        stage('Initialization') {
            steps{
                sh './startDomain1.sh'
                sh './startProduction.sh'
            }
        }

        stage('Build') {
            steps {
                sh './build.sh'
            }
        }

        stage('Unit Tests') {
            steps {
                sh './test.sh'
            }
        }

        stage('Integration Tests') {
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
                build 'DeployToDevelopment'
            }
        }

        stage('Deploy To Production') {
            when{branch 'master'}
            steps {
                build 'DeployToProduction'
            }
        }

        stage('Conclusion') {
            steps{
                sh './undeployStopDomai1.sh'
                sh './undeployStopProduction.sh'
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