//def call(){
pipeline {
    agent any
    tools {
 	 	jdk 'JAVA_HOME'
  		maven 'MAVEN_HOME'
	}
	parameters {
    choice(
      description: 'Run flyway database migration using latest master branch from prices in what environment?',
      name: 'environment',
      choices: ['TEST','PRE', 'PRO']
    )
  }

    stages {
        stage('Build') {
            steps {
			 echo 'Building the aplication...'
			 bat 'mvn clean install'
               
            }
			
        }
		stage('Deploy to Tomcate server') {
            steps {
                echo 'Deploy to Tomcate server.....'
				deploy adapters: [tomcat9(credentialsId: 'TOMCAT', path: '', url: 'http://localhost:8080/')], contextPath: null, war: '**/*.war'
            }
        }
    }
	
}
//}
