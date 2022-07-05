pipeline {
    agent any

    tools { 
        maven 'Maven 3.3.9' 
        jdk 'jdk8' 
    }
    stages {
        stage('chrome'){
			
			steps{
               sh("wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \ && echo \"deb http://dl.google.com/linux/chrome/deb/ stable main\" >> /etc/apt/sources.list.d/google.list")
               sh "apt-get update && apt-get -y install google-chrome-stable"
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