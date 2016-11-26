FROM jenkins:latest

RUN install-plugins.sh $JENKINS_PLUGINS

ENV JENKINS_UC http://updates.jenkins-ci.org/experimental/
RUN install-plugins.sh $JENKINS_BETA_PLUGINS

COPY init.groovy.d/* /usr/share/jenkins/ref/init.groovy.d/

