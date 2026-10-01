# Jenkins setup

The pipeline is written for the Windows Jenkins agent currently configured on
this controller. Java 22, Maven, and Git must be available on the agent's
`PATH`. The Playwright browser is downloaded during the Test stage.

1. Install the Jenkins Pipeline, GitHub, and JUnit plugins.
2. In **Manage Jenkins > Tools**, configure a JDK named `JDK` for Java 22 and
   Maven named `Maven-3` for Maven 3.9.16, using the install paths on the host.
3. Create a Pipeline job using **Pipeline script from SCM**, Git, and
   `https://github.com/deependrasinghhhh/Playwright-Automation.git`, branch
   `*/main`, and script path `Jenkinsfile`.
4. Add a GitHub repository webhook pointing to
   `https://<public-jenkins-host>/github-webhook/` and enable the push event.
   The Jenkins host must be reachable by GitHub over HTTPS. For a private
   repository, add a Jenkins Git credential and select it in the job.
5. Save the job and run it once. Subsequent pushes start builds through the
   webhook. TestNG reports are published from `target/surefire-reports`, and
   the Deploy stage archives the packaged JAR as the pipeline artifact.

This repository has no deployment destination configured, so Deploy publishes
the build artifact to Jenkins rather than releasing it to a live environment.
