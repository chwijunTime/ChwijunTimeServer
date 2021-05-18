pipeline {
    agent any

    triggers {
        pollSCM('*/3 * * * *')
    }


    stages {

        stage('Prepare') {
            agent any
            steps {
                echo 'Clonning Repository'
                git url: 'https://github.com/chwijunTime/ChwijunTimeServer.git',
                branch: 'main',
                credentialsId: 'KshGitHub'
            }
        }

        stage('Build & Compile & Package') {
            agent any
            steps {
                echo 'Build Backend'
                dir ('./'){
                    sh'''
                    mvn clean compile package
                    '''
                }
            }
        }

        stage('Build & Push docker image') {
            agent any
            steps {
                echo 'Build docker image'
                echo 'Deploy docker image'
                withCredentials([usernamePassword( credentialsId: 'KshDocker', usernameVariable: 'ksh030506', passwordVariable: 'ksh03050621!')]) {
                    bat "docker login -u ksh030506 -p ksh03050621! https://registry.hub.docker.com/"
                    withDockerRegistry([url: "https://registry.hub.docker.com" ,credentialsId: "KshDocker"]) {
                        sh 'sudo docker build -t ksh030506/chwijuntime:latest .'
                        sh 'sudo docker push ksh030506/chwijuntime:latest'
                    }
                }
            }
        }

        stage('Docker Deploy') {
            agent any
            steps {
                echo 'Stop And Remove Container'
                sh '''sudo docker stop chwijuntime || true && sudo docker rm chwijuntime || true'''

                echo "Remove Image"
                sh '''sudo docker rmi -f ksh030506/chwijuntime:latest'''

                echo 'run'
                sh '''sudo docker run -d -p 8082:8082 --name chwijuntime ksh030506/chwijuntime:latest'''
            }
        }
    }

}