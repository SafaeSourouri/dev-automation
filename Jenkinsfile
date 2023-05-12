pipeline{
  agent any
  tools {
    maven 'maven-3.6.3'
    }

    stages{
        stage('gitclone'){

            steps{
                echo "********************* Clone ***************************"
            git branch: 'main', credentialsId: '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', url: 'https://github.com/SafaeSourouri/dev-automation.git'
            }
        }
       stage('build'){
            steps{
                echo " ********************* Build *************************** "
                sh 'mvn compile '
            }
        }
        stage('test'){
            steps{
                echo "********************* Test ***************************"
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
                echo "********************* Package ***************************"
                sh 'mvn package'
            }
        }

      stage("Publish to Nexus Repository Manager") {
            steps {
                echo "********************* Publish ***************************"
                script {
                    pom = readMavenPom file: "pom.xml";
                    filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
                    echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                    artifactPath = filesByGlob[0].path;
                    artifactExists = fileExists artifactPath;
                    if(artifactExists) {
                        echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
                        nexusArtifactUploader(
                            nexusVersion: "nexus3",
                            protocol: "http",
                            nexusUrl: "192.168.43.221:8081",
                            groupId: pom.groupId,
                            version: pom.version,
                            repository: "app-java",
                            credentialsId: "nexus-password",
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