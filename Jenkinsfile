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

     stage('Push image') {
        docker.withRegistry('https://registry.hub.docker.com', 'docker-hub') {
            app.push()
        }
     }

     stage('Code Deploy') {
        sh '''sudo docker stop chwijuntime || true && sudo docker rm chwijuntime || true'''
        sh ''' sudo docker rmi -f `docker images | awk '$1 ~ /chwijuntime/ {print $3}'`'''
        sh '''sudo docker run -d -p 8082:8082 --name chwijuntime ksh030506/chwijuntime:latest'''
     }
}