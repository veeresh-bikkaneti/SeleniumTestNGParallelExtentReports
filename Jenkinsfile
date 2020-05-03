pipeline {
  agent any
  stages {
    stage('building') {
      steps {
        sh 'mvn clean clean test -DsuiteXmlFile=testngFramework.xml'
      }
    }

  }
}