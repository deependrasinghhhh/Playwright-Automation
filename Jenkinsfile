pipeline {
    agent any

    triggers {
        githubPush()
    }

    options {
        timestamps()
        timeout(time: 20, unit: 'MINUTES')
    }

    stages {
        stage('Build') {
            steps {
                bat 'mvn -B -DskipTests package'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn -B org.codehaus.mojo:exec-maven-plugin:3.6.4:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install chromium"'
                bat 'mvn -B test'
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
