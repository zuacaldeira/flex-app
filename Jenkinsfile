pipeline {
    agent any
    tools { 
        maven "Maven 3.3.3"
        jdk "jdk8"
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

