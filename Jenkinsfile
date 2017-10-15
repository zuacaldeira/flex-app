pipeline {
    agent any
    tools { 
        maven "Maven 3.5.0"
        jdk "JDK8"
    }

    stages {
        stage('INITIALIZE') {
            steps{
                sh 'echo "Initializing flex-app build."'
            }
        }
        stage('BUILD') { 
            steps { 
                sh 'mvn clean install' 
                jacoco exclusionPattern: '**/*Test*.class'
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml' 
                }
            }
        }
    }
}

