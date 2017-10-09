pipeline {
    agent any 

    stages {
        stage('Build') { 
            steps { 
                sh 'echo "Building"' 
            }
        }
        stage('Test'){
            steps {
                sh 'echo "Testing"' 
                junit 'reports/**/*.xml' 
            }
        }
        stage('Deploy') {
            steps {
                sh 'echo "Deploying"' 
            }
        }
    }
}

