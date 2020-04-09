pipeline {
    agent any 
    tools {
        maven 'maven363' 
    }
    stages{ 
        stage('Get maven version') { 
            steps {
                sh 'mvn --version'
            }
        }
    }
}