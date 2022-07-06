node("jenkinsSelenium"){

    stage('Clone repositories') {
         checkout scm
    }

    stage('Charge chrome driver') {
         sh "mv /home/seluser/chromedriver /home/seluser/workspace/Selenium-Zap"
    }

    stage('Maven') {
         sh("mvn clean verify")
    }
}