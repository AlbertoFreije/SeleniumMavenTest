pipeline {
    agent any

    tools { 
        maven 'Maven 3.3.9' 
        jdk 'jdk8' 
    }
    stages {
        stage('chrome'){
			
			steps{
               sh("apt-get install -y wget")
               sh("wget -q https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb")
               sh("apt-get install ./google-chrome-stable_current_amd64.deb")
		    }
		}	
        stage('Maven'){
			
			steps{
               sh("pwd")
               sh "mvn clean verify"
               sh "mvn test"
		    }
		}	
    }
}