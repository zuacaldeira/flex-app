pipeline {
    agent { any }
    tools { 
        maven 'Maven 3.3.9' 
        jdk 'jdk8' 
    }

    stages {
        stage('Initialize') {
            sh 'echo "PATH = ${PATH}"; echo "M2_HOME = ${M2_HOME}'
        }
        stage('build') { 
            steps { 
              sh 'mvn --version'
            }
        }
    }
}

