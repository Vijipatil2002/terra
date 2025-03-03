
def call(String scmUrl, String branch, String credentialId) {
    pipeline {
        agent any
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
                }
            }
        }
    }
}

