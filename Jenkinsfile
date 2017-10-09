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
                sh 'mvn --version'
                sh 'mvn clean install'
                jacoco changeBuildStatus: true, deltaBranchCoverage: '10', deltaClassCoverage: '10', deltaComplexityCoverage: '10', deltaLineCoverage: '10', deltaMethodCoverage: '10', exclusionPattern: '**/*Test*.class', maximumBranchCoverage: '10', maximumClassCoverage: '10', maximumComplexityCoverage: '10', maximumLineCoverage: '10', maximumMethodCoverage: '10'
            }
        }
    }
}

