pipeline {
    agent any

    triggers {
        githubPush()
    }

    tools {
        jdk 'JDK-22'
        maven 'Maven-3'
    }

    options {
        timestamps()
        timeout(time: 20, unit: 'MINUTES')
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests package'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn -B org.codehaus.mojo:exec-maven-plugin:3.6.4:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install chromium"'
                sh 'mvn -B test'
            }
            post {
                always {
                    junit testResults: 'target/surefire-reports/TEST-*.xml', allowEmptyResults: true
                }
            }
        }

        stage('Deploy') {
            steps {
                archiveArtifacts artifacts: 'target/Playwright-*.jar', fingerprint: true
            }
        }
    }
}
