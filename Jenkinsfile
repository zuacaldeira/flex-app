pipeline {
    agent any
    tools{
        maven 'Maven5'
        jdk 'JDK8'
    }
    stages {
    	stage('BUILD') {
            steps{
                task 'Build'
                sh 'mvn clean install -DskipTests'
            }
	}
    	stage('TEST') {
            steps{
                task 'Unit Test'
                sh 'mvn test -DskipITs'
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
                task 'Integration Test'
                sh 'mvn verify'
            }
	}
        stage("ARCHIVE") {
            steps{
                task 'Create war archive'
                sh 'mvn war:war'
            }
        }

    	stage('DEPLOYMENT DEV') {
            when {branch 'dev'}
            steps{
                task 'Start Staging Server'
                sh '~/Servers/glassfish4-latest/glassfish/bin/asadmin restart-domain development'
                task 'Deployment to Staging Server'
                sh 'mvn properties:read-project-properties -DENVIRONMENT=development glassfish:redeploy'
            }
	}
    	stage('DEPLOYMENT PRODUCTION') {
            when {branch 'master'}
            steps{
                task 'Start Production Server'
                sh '~/Servers/glassfish4-latest/glassfish/bin/asadmin restart-domain production'
                task 'Deployment to Production Server'
                sh 'mvn properties:read-project-properties -DENVIRONMENT=production glassfish:redeploy'
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