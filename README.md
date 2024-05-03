Bu projede Maven projesini Docker, Jenkins ve GitHub arasında birbirine bağlayarak attığımız commitleri otomatik olarak güncellemesini sağladık. Bu yaklaşım sayesinde Jenkins, GitHub üzerinde yapılan değişiklikleri izler ve Docker'da projeyi güncelleyerek otomatik olarak dağıtım sağlar. Bu şekilde yazılım geliştirme sürecini otomatize etmek ve daha hızlı bir şekilde geri bildirim almak mümkün olur.




In this project, we automated the updating of Maven projects between Docker, Jenkins, and GitHub by connecting them. With this approach, Jenkins tracks changes made on GitHub and automatically updates the project in Docker for distribution. This automation streamlines the software development process and enables faster feedback cycles.
//////

jenkins pipeline script

pipeline{

    agent any
    
    tools{
    
        maven 'maven' 
        
    }
    
    stages{
    
        stage('Build Maven'){
        
           steps {
           
                    checkout scmGit(
                    
                        branches: [[name: '*/main']],
                        
                        userRemoteConfigs: [[url: 'https://github.com/Ahmetaygun/docker_jenkins_github']]
                        
                    )

                    
                    bat 'mvn clean install'
                    
                    
                }
                
        }
        
         stage('Stop and Remove Existing Container') {
         
             steps {
             
                 script {


                    bat 'docker stop docker_jenkins_github'
                    
 
                    bat 'docker rm docker_jenkins_github'
                    

                 }
                 
              }
              
        }
      
      
        stage('Build docker image'){
        
            steps{
            
                script{
                
                    docker.build("docker_jenkins_github:${env.BUILD_NUMBER}")
                    
                }
                
            }
            
        }
        
        stage('Push image to hub'){
        
            steps{
            
                script{
                
                    docker.image("docker_jenkins_github:${env.BUILD_NUMBER}").run("-d -p 8081:8081 --name docker_jenkins_github")
                    
                }
                
            }
            
        }
        
       
    }
}
