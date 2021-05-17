pipeline {
    // 스테이지 별로 다른 거
    agent any

    environment {
            registry = "ksh030506/chwijuntime"
            registryCredential = 'KshDocker'
            dockerImage = ''
    }

    triggers {
        pollSCM('*/3 * * * *')
    }

    stages {

        // 레포지토리를 다운로드 받음
        stage('Prepare') {
            agent any
            steps {
                echo 'Clonning Repository'
                git url: 'https://github.com/chwijunTime/ChwijunTimeServer.git',
                branch: 'main',
                //Global credentials에 입력한 ID
                credentialsId: 'KshGitHub'
            }
        }

        stage('Build Backend') {
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

        stage('Docker Build') {
            agent any
            steps {
                sh 'sudo docker build -t ksh030506/$registry .'
            }
        }

        stage('Docker Push') {
            agent any
            steps {
                withDockerRegistry([ credentialsId: registryCredential, url: "" ]) {
                    sh 'sudo docker push ksh030506/$registry'
                }
            }
        }

        stage('Docker clean') {}
        agent any
        steps {
           sh "sudo docker rmi ksh030506/$registry"
        }
    }

}