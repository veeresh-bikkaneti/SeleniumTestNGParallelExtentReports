pipeline {
  agent any
  stages {
    stage('building') {
      steps {
        sh 'mvn clean install clean test -DsuiteXmlFile=CrossBrowserParallelSuite.xml'
      }
    }

  }
}