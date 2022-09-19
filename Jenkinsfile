import static groovy.json.JsonOutput.*

def mailBody = "Este es el informe de vulnerabilidades encontradas solicitado"
def mailSubject = "INFORME VULNERABILIDADES"
def mailFrom = 'AlbertoFreijeCarballo@gmail.com'
def mailTo = 'Alberto.Freije@Ricoh.es'
def nombreXML = "owasp-quick-scan-report.xml"


@NonCPS
def sluper(xmlData){
    def adocSource = "";
    def tablaAlerts = "";
    def tablaAlertsDetail = "";
    def xmlContent = new XmlSlurper().parseText(xmlData)
    adocSource += ":doctype: book\n"
    adocSource += ":hardbreaks:\n"
    adocSource += ":experimental:\n"
    adocSource += ":pdf-stylesdir: /home/seluser/workspace/Selenium-Zap/templates-main/Ricoh \n"
    adocSource += ":pdf-style: template.yml \n"
    adocSource += ":Ruta_Base: ..\\ \n"
    adocSource += ":imagesdir: .\\ \n"
    adocSource += ":icons: image\n"
    adocSource += ":iconsdir: {pdf-stylesdir}\\iconos \n"
    adocSource += ":document_name: Informe ZAP \n"
    adocSource += ":version-label: Ver. \n"
    adocSource += ":revnumber: 1.0 \n"
    adocSource += ":revdate: {localdate} \n"
    adocSource += ":Titulo: OWASP ZAP \n"
    adocSource += ":Subtitulo: Generación de Informe de alertas de ZAP \n"
    adocSource += ":author: Ricoh España \n"
    adocSource += ":Subject: {Subtitulo} \n"
    adocSource += ":Keywords: devops,jenkins,docker,swarm \n"
    adocSource += ":email: alberto.freije@ricoh.es \n"
    adocSource += ":PDFFileName: Informe_Alertas_Zap_v{revnumber}.docx \n"
    adocSource += ":showtitle: \n"
    adocSource += ":toc: \n"
    adocSource += ":toc-title: Índice de contenidos \n"
    adocSource += ":toclevels: 6 \n"
    adocSource += ":sectnums: \n"
    adocSource += ":sectnumlevels: 6 \n"
    adocSource += ":chapter-label: \n"
    adocSource += ":checkedbox: pass:normal[&#9745;] \n"
    adocSource += ":uncheckedbox: pass:normal[&#9744;] \n"
    adocSource += ":SingleLeftArrow: pass:normal[&#8592;] \n"
    adocSource += ":SingleRightArrow: pass:normal[&#8594;] \n"
    adocSource += ":DoubleLeftArrow: pass:normal[&#8656;] \n"
    adocSource += ":DoubleRightArrow: pass:normal[&#8658;] \n"
    adocSource += ":WhiteSpace: pass:normal[&nbsp;] \n"
    adocSource += "= {Titulo} : {Subtitulo} \n"
    adocSource +="\n"
    adocSource += "[width=\"100%\",cols=\"10,11,30,15,34\",options=\"header\"]\n"
    adocSource += "|===\n"
    adocSource += "^.^| Versión ^.^| Fecha ^.^| Autor ^.^| Unidad - Departamento - Empresa ^.^| Observaciones\n"
    adocSource += "| {revnumber} | {revdate} | Alberto Freije Carballo | Arquitectura - Ricoh | Generación reporte de alertas Owasp Zap\n"
    adocSource += "|=== \n"
    adocSource += "== ZAP Scanning Report \n"
    adocSource += "* Sitio: " + xmlContent.site["@name"] + "\n"
    adocSource += "* Generado: " + xmlContent["@generated"] + "\n"
    adocSource +="\n"
    adocSource += "=== Alertas \n"
    adocSource += """[width=\"100%\",cols=\"60,30,10\",options=\"header\"]\n"""
    adocSource += "|===\n"
    adocSource += "^.^| Nombre ^.^| Nivel del riesgo ^.^| Numero de instancias \n"
    xmlContent.site.alerts.alertitem.each{
        e -> tablaAlerts += "| " + e.alert+ " | " + e.riskdesc + " | " + e.count + "\n";
    }
    adocSource += tablaAlerts
    adocSource += "|=== \n"
    adocSource += "=== Detalle Alertas \n"
    adocSource += """[width=\"100%\",cols=\"30,60\",options=\"header\"]\n"""
    adocSource += "|===\n"
    xmlContent.site.alerts.alertitem.each{
        e -> tablaAlertsDetail += "| " + e.riskdesc + " | " + e.name + "\n"
        tablaAlertsDetail += "| " + "Descripcion" + " | " + e.desc + "\n"
        e.instances.instance.each{
            a -> tablaAlertsDetail += "| " + "URL" + " | " + a.uri + "\n"
            tablaAlertsDetail += "| " + "Method" + " | " + a.method + "\n"
            tablaAlertsDetail += "| " + "Parameter" + " | " + a.param + "\n"
            tablaAlertsDetail += "| " + "Attack" + " | " + a.attack + "\n"
            tablaAlertsDetail += "| " + "Evidence" + " | " + a.evidence + "\n"
            
        }
        tablaAlertsDetail += "| " + "Solucion" + " | " + e.solution + "\n"
        tablaAlertsDetail += "| " + "Reference" + " | " + e.reference + "\n"
        
    }
    adocSource += tablaAlertsDetail
    adocSource += "|=== \n"
    adocSource += " \n"
    adocSource += " \n"
    adocSource += " \n"
    return adocSource
    
}

node("jenkinsSelenium"){


    stage('Clone repositories') {
        checkout scm
    }

    stage('Charge chrome driver') {
        sh("pwd") 
        sh "cp /home/seluser/chromedriver /home/seluser/workspace/Selenium-Zap"
    }

    stage('Maven') {
        sh("mvn clean verify")
    }
}

def wait_for_passive_scan_to_complete(){

        def STATUS_URL="http://zap:8090/JSON/pscan/view/recordsToScan/?apikey=change-me-9203935709&formMethod=GET"
        def SCAN_STATUS=100

        while (SCAN_STATUS != 0){
            sleep 10
            def SCAN_STATUS_RES = sh(returnStdout: true, script: ''' curl "http://zap:8090/JSON/pscan/view/recordsToScan/?apikey=change-me-9203935709&formMethod=GET" ''' ).trim();
            println(SCAN_STATUS_RES);
            def STATUS = readJSON text: SCAN_STATUS_RES;
            println("Respuesta " + STATUS.recordsToScan.getClass());
            SCAN_STATUS = STATUS.recordsToScan as Integer;


            //def STATUS = new XmlSlurper().parseText(SCAN_STATUS_RES)
            //println(STATUS)
            //SCAN_STATUS = STATUS.recordsToScan
        }
        echo Passive Scan Complete
}

node("jenkinszap"){

    wait_for_passive_scan_to_complete() 

    def microResponse = sh(returnStdout: true, script: ''' curl "http://zap:8090/JSON/core/view/sites/?apikey=change-me-9203935709" ''').trim();

    println("DEBUG: ${microResponse}");

    def cmdbMicroProps = readJSON text: microResponse;

    println("Respuesta " + cmdbMicroProps);

    // sh("curl -X GET http://zap:8090/JSON/alert/action/deleteAllAlerts/ \
    // -H 'Accept: application/json' \
    // -H 'X-ZAP-API-Key: change-me-9203935709'")

    // sh("curl -X GET http://zap:8090/JSON/core/action/shutdown/ \
    //     -H 'Accept: application/json' \
    //     -H 'X-ZAP-API-Key: change-me-9203935709'")

}






node("jenkinszap"){

        stage('Generacion Informe') {
            sh("pwd")
            sh("zap-cli --verbose  --api-key change-me-9203935709 -p 8090 report -o /zap/workspace/Selenium-Zap/owasp-quick-scan-report.xml --output-format xml")
            
        }

         stage('Build') {
           stash name: 'prueba', includes: '**/owasp-quick-scan-report.xml'
        }

}
  node("jenkinsSelenium"){

     cleanWs()
     unstash 'prueba'
     def xmlContent = readFile( file: "${WORKSPACE}/" + nombreXML)
     def adocSource = sluper(xmlContent)
     writeFile(file: "informeAlertas.adoc", text: "${adocSource}")
     sh("wget https://github.com/AlbertoFreije/templates/archive/main.zip")
     sh("unzip main.zip")
     sh("ls -la")
     sh("pwd")
     sh("asciidoctor-pdf informeAlertas.adoc -o informeAlertas.pdf")
     emailext (
         attachmentsPattern: '**/informeAlertas.pdf',
         subject: mailSubject,
         body: mailBody,
         from: mailFrom,
         to: mailTo
     )

  }

