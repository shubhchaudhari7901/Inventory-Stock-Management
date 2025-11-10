pipeline {
    agent any

    environment {
        AWS_DEFAULT_REGION = 'eu-north-1'
        REPOSITORY_URI = '485734076576.dkr.ecr.eu-north-1.amazonaws.com/inventory-backend'
        IMAGE_TAG = "latest"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/shubhchaudhari7901/Inventory-Stock-Management.git'
            }
        }

        stage('Build with Maven') {
            steps {
                dir('backend') {
                    sh 'mvn clean package -DskipTests=false'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir('backend') {
                    script {
                        sh 'docker build -t inventory-backend .'
                    }
                }
            }
        }

        stage('Login to AWS ECR') {
            steps {
                withAWS(credentials: 'aws-credentials', region: 'eu-north-1') {
                    script {
                        sh '''
                        aws ecr get-login-password --region eu-north-1 | docker login --username AWS --password-stdin 485734076576.dkr.ecr.eu-north-1.amazonaws.com
                        '''
                    }
                }
            }
        }

        stage('Tag & Push Docker Image') {
            steps {
                script {
                    sh '''
                    docker tag inventory-backend:latest 485734076576.dkr.ecr.eu-north-1.amazonaws.com/inventory-backend:latest
                    docker push 485734076576.dkr.ecr.eu-north-1.amazonaws.com/inventory-backend:latest
                    '''
                }
            }
        }

        stage('Cleanup') {
            steps {
                sh 'docker system prune -af'
            }
        }
    }

    post {
        success {
            echo '✅ Build & Push successful!'
        }
        failure {
            echo '❌ Build failed. Check logs.'
        }
    }
}
