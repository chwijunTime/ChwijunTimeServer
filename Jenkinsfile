pipeline {
    // 스테이지 별로 다른 거
    agent any

    environment {
            registry = "ksh030506/chwijuntime"
            registryCredential = 'kshdocker'
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
            post {
                success {
                    echo 'Successfully Cloned Repository'
                }

                //성공/실패 둘다 출력
                always {
                    echo "i tried..."
                }

                cleanup {
                    echo "after all other post condition"
                }
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

            post {
                //이거 안넣어 주면 다음 파이프라안으로 넘어감
                failure {
                    error 'This pipeline stops here...'
                }
            }
        }

        stage('Build & Deploy & clean docker image') {
            agent any
            steps {
                echo 'Build & Deploy docker image'
                sh 'sudo docker build -t $registry:latest .'
                withDockerRegistry([ credentialsId: registryCredential, url: "" ]) {
                    sh 'sudo docker push ksh030506/$registry:latest'
                }
                sh "sudo docker rmi $registry"
            }

            post {
            //이거 안넣어 주면 다음 파이프라안으로 넘어감
            failure {
                error 'This pipeline stops here...'
                }
            }
        }
    }

}