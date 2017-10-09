pipeline {
    agent any
    tools { 
        maven
        jdk 
    }

    stages {
        stage('Initialize') {
            step{
                sh 'echo "PATH = ${PATH}"; echo "M2_HOME = ${M2_HOME}'
            }
        }
        stage('build') { 
            steps { 
              sh 'mvn --version'
            }
        }
    }
}

