pipeline {
    agent any

    tools { 
        maven 'Maven 3.3.9' 
        jdk 'jdk8' 
    }
    stages {
        stage('Maven'){
			
			steps{
               sh "mvn clean verify"
		    }
		}	
}