 node {
     stage('Clone repository') {
         checkout scm
     }

     stage('Build BackEnd') {
        sh'''
        mvn clean compile package
        '''
     }

     stage('Build image') {
         app = docker.build("teichae/jenkins:$BUILD_NUMBER") #docker image build 및 이름을 teicahe/jenkins:빌드번호 설정
     }
}