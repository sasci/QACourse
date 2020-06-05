pipeline{
    agent any
    stage ("run gradle") {
      steps {
        echo 'executing gradle...'
        withGradle(){
          sh '.gradlew -v'
        }
      }
    }
}
