pipeline{
    agent any
    stages {
        stage ("run gradle") {
          steps {
            echo 'executing gradle...'
            withGradle(){
              sh 'gradle test'
            }
          }
        }

        stage ("run gradle") {
          steps {
            cucumber buildStatus:"UNSTABLE",
            fileIncludePattern:"**/cucumber.json",
            jsonReportDirectory:"report"
            }
          }
        }
    }    
}
