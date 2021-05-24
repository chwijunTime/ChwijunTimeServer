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
        sh '''
            sudo chmod 666 /var/run/docker.sock
        '''
        app = docker.build("${REPOSITORY_NAME}/${CONTAINER_NAME}:latest")
     }

     stage('Push image') {
        docker.withRegistry('https://registry.hub.docker.com', 'docker-hub') {
            app.push()
        }
     }

     stage('Code Deploy') {
        sh '''sudo docker stop ${CONTAINER_NAME} || true && sudo docker rm ${CONTAINER_NAME} || true'''
        sh '''sudo docker rmi -f `docker images | awk '$1 ~ /chwijuntime/ {print $3}'`'''
        sh '''sudo docker run -d -p 8082:8082 --name ${CONTAINER_NAME} ${REPOSITORY_NAME}/${CONTAINER_NAME}:latest -e H2_PATH=${H2_PATH}
        '''
     }
}