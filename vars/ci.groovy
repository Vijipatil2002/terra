def call(String scmUrl, String branch, String credentialId) {
       pipeline {
        agent any
    
        stages {
            stage('Hello') {
                steps {
                     script {
                        git branch: branch, 
                            credentialsId: credentialId, 
                            url: scmUrl
                    }
                }
            }
        }
        post {
            success {
                build job: 'cd-job', wait: true
            }
        }
    }

}
