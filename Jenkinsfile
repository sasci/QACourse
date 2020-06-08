pipeline{
    agent any
    stages{
//         stage ("run gradle") {
//           steps {
//             echo 'executing gradle...'
//             withGradle(){
//               sh './gradlew clean test'
//             }
//           }
//         }
//
//         stage ('Cucumber Reports') {
//               steps {
//                     cucumber buildStatus:'UNSTABLE',
//                     fileIncludePattern:'**/cucumber.json',
//                     jsonReportDirectory:'target'
//
//               }
//         }

        stage('Test API Rest') {
                steps {
                    sh '/usr/local/lib/node_modules/newman run newman/Advanced_workflow.postman_collection.json -d newman/routes.json -r junit,html --reporter-junit-export report/newman/junit/newman.xml --reporter-html-export report/newman/html/index.html'

                    publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'report/newman/html', reportFiles: 'index.html', reportName: 'Newman API Test', reportTitles: ''])
                }
            }


    }
}
