def call(String scmUrl, String branch, String credentialId) {
pipeline {
    agent any

    stages {
        stage('Hello') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'My_id', url: 'https://github.com/Vijipatil2002/Hospital_frontend_project.git']])
            }
        }
        stage('deploy') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'My_id', url: 'https://github.com/Vijipatil2002/Hospital_frontend_project.git']])
            }
        }
    }
  }
}
