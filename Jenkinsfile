pipeline{
    agent any
    stages {
        stage ("run gradle") {
          steps {
            echo 'executing gradle...'
            withGradle(){
              sh '.gradlew -v'
            }
          }
        }
    }    
}
