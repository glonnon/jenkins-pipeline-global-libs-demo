FROM jenkinsci/jenkins:latest
ARG JENKINS_PLUGINS
RUN install-plugins.sh $JENKINS_PLUGINS
ARG JENKINS_BETA_PLUGINS
RUN JENKINS_UC=http://updates.jenkins-ci.org/experimental/  install-plugins.sh $JENKINS_BETA_PLUGINS
COPY init.groovy.d/* /usr/share/jenkins/ref/init.groovy.d/

