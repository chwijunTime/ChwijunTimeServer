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
                sh 'sudo docker build -t ksh030506/chwijuntime:latest .'
                echo 'Deploy docker image'
                withDockerRegistry([ credentialsId: 'KshDocker', url: "" ]) {
                    sh 'sudo docker push ksh030506/chwijuntime:latest'
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