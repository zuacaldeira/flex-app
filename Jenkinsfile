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
        sh 'mvn clean install'
        jacoco(exclusionPattern: '**/*Test*.class')
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
      }
    }
    stage('RELEASE') {
      steps {
        sh 'mvn release:clean release:prepare -Prelease'
      }
    }
  }
  tools {
    maven 'Maven 3.5.0'
    jdk 'JDK8'
  }
}