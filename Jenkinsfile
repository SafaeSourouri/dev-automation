pipeline{
  agent any
  tools {
    maven 'maven-3.6.3'
    }
   environment{
        NEXUS_VERSION= "nexus3"
        NEXUS_PROTOCOL="http"
        NEXUS_URL="192.168.43.221:8081"
        NEXUS_REPOSITORY="java-app"
        NEXUS_CREDENTIAL_ID="NEXUS_CRED"
    }
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

             stage("Publish to Nexus Repository Manager") {
            steps {
                script {
                    pom = readMavenPom file: "pom.xml";
                    filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
                    echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                    artifactPath = filesByGlob[0].path;
                    artifactExists = fileExists artifactPath;
                    if(artifactExists) {
                        echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
                        nexusArtifactUploader(
                            nexusVersion: NEXUS_VERSION,
                            protocol: NEXUS_PROTOCOL,
                            nexusUrl: NEXUS_URL,
                            groupId: pom.groupId,
                            version: pom.version,
                            repository: NEXUS_REPOSITORY,
                            credentialsId: NEXUS_CREDENTIAL_ID,
                            artifacts: [
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: artifactPath,
                                type: pom.packaging],
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: "pom.xml",
                                type: "pom"]
                            ]
                        );
                    } else {
                        error "*** File: ${artifactPath}, could not be found";
                    }
                }
            }
        }

    }
}
