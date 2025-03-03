
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
            stage('Build') {
                steps {
                    echo "Building for branch: ${branch}"
                    echo "this buiding from terra repo"
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

