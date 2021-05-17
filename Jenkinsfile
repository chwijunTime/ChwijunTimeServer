pipeline {
    // 스테이지 별로 다른 거
    agent any

    triggers {
        pollSCM('*/3 * * * *')
    }

    environment {
      AWS_ACCESS_KEY_ID = credentials('awsAccessKeyId')
      AWS_SECRET_ACCESS_KEY = credentials('awsSecretAccessKey')
      AWS_DEFAULT_REGION = 'ap-northeast-2'
      HOME = '.' // Avoid npm root owned
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
    }

}