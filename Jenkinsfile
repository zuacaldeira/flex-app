pipeline {
    agent any
    tools{
        maven 'Maven5'
        jdk 'JDK8'
    }
    stages {
    	stage('BUILD') {
            steps{
                sh 'mvn clean install -DskipTests'
            }
	}
    	stage('TEST') {
            steps{
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
                sh 'mvn verify'
            }
	}
    	stage('DEPLOYMENT DEV') {
            when {branch 'dev'}
            steps{
                sh '~/Servers/glassfish4-latest/glassfish/bin/asadmin restart-domain development'
                sh 'mvn properties:read-project-properties -DENVIRONMENT=development glassfish:redeploy'
            }
	}
    	stage('DEPLOYMENT PRODUCTION') {
            when {branch 'master'}
            steps{
                sh '~/Servers/glassfish4-latest/glassfish/bin/asadmin restart-domain production'
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