pipeline {
  agent any
  stages {
    stage('INITIALIZE') {
      steps {
        sh 'echo "Initializing flex-app build."'
      }
    }
    stage('BUILD') {
      steps {
        sh 'mvn clean install -DskipITs'
      }
      post {
        success {
          junit 'target/surefire-reports/**/*.xml'
          
        }
        
      }
    }
    stage('QA') {
      steps {
        sh 'mvn verify'
        jacoco()
      }
    }
    stage('DEPLOY') {
      steps {
        sh 'mvn properties:read-project-properties -Dglassfish.properties.file.argument=development glassfish:redeploy'
      }
    }
  }
  tools {
    maven 'Maven 3.5.0'
    jdk 'JDK8'
  }
}