node("jenkinsSelenium"){

    stage('Clone repositories') {
         checkout scm
    }

    stage('Maven') {
         sh("mvn clean verify")
    }
}