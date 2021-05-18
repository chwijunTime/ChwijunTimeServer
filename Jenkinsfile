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
         app = docker.build("ksh030506/chwijuntime:latest")
     }
}