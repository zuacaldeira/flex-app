pipeline {
    agent any
    tools{
        maven 'Maven5'
        jdk 'JDK8'
    }
    stages {
    	stage('BUILD') {
            steps{
                sh 'mvn clean install'
            }
	}
    	stage('TEST') {
            steps{
                sh './scripts/test.sh'
            }
	}
    	stage('INTEGRATION TEST') {
            when {
	    	 anyOf {
	    	       branch 'release'
		       branch 'master'
		 }
	    }
            steps{
                sh './scripts/testITs.sh'
            }
	}
    	stage('ARCHIVE') {
            steps{
                sh './scripts/archive.sh'
            }
	}
    	stage('DEPLOYMENT DEV') {
            when {branch 'dev'}
            steps{
                sh './scripts/restartServer.sh development'
                sh './scripts/deploy.sh development'
            }
	}
    	stage('DEPLOYMENT PRODUCTION') {
            when {branch 'master'}
            steps{
                sh './scripts/restartServer.sh production'
                sh './scripts/deploy.sh production'
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