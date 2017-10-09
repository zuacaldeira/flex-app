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
                jacoco buildOverBuild: true, changeBuildStatus: true, deltaBranchCoverage: '10', deltaClassCoverage: '10', deltaComplexityCoverage: '10', deltaLineCoverage: '10', deltaMethodCoverage: '10', exclusionPattern: '**/*Test*.class', maximumBranchCoverage: '50', maximumClassCoverage: '50', maximumComplexityCoverage: '50', maximumLineCoverage: '50', maximumMethodCoverage: '50', minimumBranchCoverage: '10', minimumClassCoverage: '20', minimumComplexityCoverage: '10', minimumLineCoverage: '10', minimumMethodCoverage: '10'
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml' 
                }
            }
        }
    }
}

