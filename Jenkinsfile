pipeline {
    
    agent any 
    
    stages {
        stage('Git Checkout'){
            steps{
                script{
                    git branch: 'feature/bugfix', url: 'https://github.com/khadar099/kb-fashion-house.git'
                    }
                }
            }
        stage('Maven build') {
            
            steps {
                
                script{
                    
                    sh 'mvn clean install'
                }
            }
        }
        stage('test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Docker image  build stage') {
            steps {
                sh 'docker image build -t kb-fashion-house:v.$BUILD_NUMBER .'
            }
        }
        stage('Tag docker image') {
            steps {
                sh 'docker image tag kb-fashion-house:v.$BUILD_NUMBER khadar3099/kb-fashion-house:v.$BUILD_NUMBER'
                }
        }
       stage ('push docker image to  dockerhub') {
            steps {
                script {
                   withCredentials([string(credentialsId: 'dockerhub-password', variable: 'dockerhub_psd')]) {
                        sh '''
                        docker login -u khadar3099 -p ${dockerhub_psd}
                        docker image push khadar3099/kb-fashion-house:v.$BUILD_NUMBER
                        docker rmi kb-fashion-house:v.$BUILD_NUMBER
                        docker rmi khadar3099/kb-fashion-house:v.$BUILD_NUMBER
                        ''' 
                        }
                    }
                }
            }
        }
    }
