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

        stage('Archive') {
            steps{
                archiveArtifacts artifacts: '**/*.war', onlyIfSuccessful: true
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