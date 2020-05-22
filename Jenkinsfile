pipeline {
    agent any
    tools {
        maven 'MavenP'
        jdk 'ibm-java-8'
    }
    environment {
    //Use Pipeline Utility Steps plugin to read information from pom.xml into env variables
    APP_NAME = readMavenPom().getArtifactId()
    VERSION = readMavenPom().getVersion()
    DMGR_HOST='fush.net'
    }
    stages {
        stage ('Git checkout') {
            steps {
                git branch: 'jenkins-build', credentialsId: 'credentials', url: 'http://gitlab.fush.com/fush'
            }
        }
        stage ('Maven build') {
            steps {
                sh 'mvn clean install -U -DskipTests' 
            }
        }
        stage ('Sonar check') {
            steps {
                withSonarQubeEnv(installationName: 'SonarQube') {
                    sh 'mvn sonar:sonar' 
                }
            }
        }
       stage ('Ansible playbook invoke') {
             environment {
               EAR_PATH = sh(script: 'find "$PWD" -name "*.ear"', , returnStdout: true).trim()
            }
            steps {
                ansiblePlaybook(
                    playbook: '/opt/ansible_WAS_deploy/deployEAR.yml', 
                    inventory: '/opt/ansible_WAS_deploy/inventory', 
                    extraVars: [
                        appName: '$APP_NAME',
                        appVersion: '$VERSION',
                        earPath: '$EAR_PATH',
                        dmgr_host: '$DMGR_HOST'
                        
                    ]
                )
            }
        }
        stage ('Maven publish') {
            steps {
                sh 'mvn deploy -Dmaven.test.skip=true' 
            }
        }

    }
}
