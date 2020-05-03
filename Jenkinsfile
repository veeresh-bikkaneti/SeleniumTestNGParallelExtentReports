pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        sh 'jenkins/build.sh'
      }
    }

    stage('test') {
      steps {
        sh 'jenkins/test-all.sh'
      }
    }

  }
}