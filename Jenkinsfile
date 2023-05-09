pipeline{
  agent any
  tools {
    maven 'maven-3.6.3'
    }
   /*environment{
        NEXUS_VERSION= 'nexus3'
        NEXUS_PROTOCOL='http'
        NEXUS_URL='192.168.43.221:8081'
        NEXUS_REPOSITORY='java-app'
        NEXUS_CREDENTIAL_ID='NEXUS_CRED'
    }*/
    stages{
        //CLONE
        stage('gitclone'){

            steps{
            git branch: 'main', credentialsId: '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', url: 'https://github.com/SafaeSourouri/dev-automation.git'
            }
        }
        //BUILD
       stage('build'){
            steps{
                sh 'mvn compile '
            }
        }
        //test
        stage('test'){
            steps{
                sh 'mvn test '
            }

        }
        //PACKAGAE

        stage('package'){
            steps{
                sh 'mvn package'
            }
             post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }

        //NEXUS

        stage ('Publish to Nexus') {
            steps {
               // container ('maven')
                sh 'mvn deploy -DaltDeploymentRepository=nexus::default::http://192.168.43.221:8081/repository/app-java/'


            }
        }

    }
}
