pipeline{
  agent any
  tools {
    maven 'maven-3.6.3'
    }
    stages{
        stage('gitclone'){

            steps{
            git branch: 'main', credentialsId: '44e44954-cc82-4cbe-9585-0028053c6005', url: 'https://github.com/SafaeSourouri/dev-automation.git'
            }
        }
       stage('build'){
            steps{
                sh 'mvn compile '
            }
        }
        stage('test'){
            steps{
                sh 'mvn test '
            }
            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }
        stage('package'){
            steps{
                sh 'mvn package'
            }
        }

    }
}