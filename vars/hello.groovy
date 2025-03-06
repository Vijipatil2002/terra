
def call(String scmUrl, String branch, String credentialId) {
    pipeline {
        agent any
        tools{
            maven 'maven'
        }
        stages {
            stage('Checkout') {
                steps {
                    script {
                        git branch: branch, 
                            credentialsId: credentialId, 
                            url: scmUrl
                    }
                }
            }
             stage('Build'){
                steps {
                            bat 'mvn clean package'
                    }
                    post {
                    success {
                        echo 'Archiving the artifacts'
                        archiveArtifacts artifacts: '**/target/*.war'
                    }
                }
            }
            stage('Deploy') {
                steps {
                    echo "Deploying branch: ${branch}"
                    deploy adapters: [tomcat9(credentialsId: 'c750fc63-ccd9-4a9a-9ecc-7896891b4123', path: '', url: 'http://localhost:8082/')], contextPath: 'spare', war: '**/*.war'
                }
            }
        }
    }
}

