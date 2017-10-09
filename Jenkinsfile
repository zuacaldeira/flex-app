pipeline {
    agent any
    tools { 
        maven "Maven 3.5.0"
        jdk "JDK8"
    }

    stages {
        stage('Initialize') {
            steps{
                sh 'echo "PATH = ${PATH}"; echo "M2_HOME = ${M2_HOME}"'
            }
        }
        stage('build') { 
            steps { 
                sh 'mvn -Dmaven.test.failure.ignore=true install' 
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml' 
                }
            }
        }
    }
}

