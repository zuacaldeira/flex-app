pipeline {
    agent any
    tools{
        maven 'Maven5'
        jdk 'JDK8'
    }
    stages {
    	
    	stage('BUILD') {
            steps{
                sh './scripts/build.sh'
            }
	}
    	stage('TEST') {
            steps{
                sh './scripts/test.sh'
            }
	}
    	stage('INTEGRATION TEST') {
            when anyOf{branch 'release', branch 'master'}
            steps{
                sh './scripts/testITs.sh'
            }
	}
    	stage('ARCHIVE') {
            steps{
                sh './scripts/archive.sh'
            }
	}
    	stage('DEPLOYMENT_DEV') {
            when {branch 'dev'}
            steps{
                sh './scripts/startDevelopment.sh'
                sh './scripts/deployToDevelopment.sh'
            }
	}
    	stage('DEPLOYMENT_PRODUCTION') {
            when {branch 'master'}
            steps{
                sh './scripts/startProduction.sh'
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