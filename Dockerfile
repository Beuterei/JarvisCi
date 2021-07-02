FROM jenkins/jenkins:2.289.2-lts-alpine

# Set user
USER jenkins

# Init first user (runs on first startup)
COPY init.groovy.d /var/jenkins_home/init.groovy.d

# Init some jobs (runs on first startup)
COPY jobs/initJobs/development /usr/share/jenkins/ref/initJobs

# Init some jobs (runs on first startup)
COPY jobs/localJobs /usr/share/jenkins/ref/localJobs

# Base configuration Jenkins (runs on first startup)
COPY configs/base /var/jenkins_home/casc_configs

# Copy plugin list and custom plugion list if there are any
COPY *custom.plugins.txt plugins.txt /tmp/jenkins/

# Override file
RUN echo "" > /usr/share/jenkins/ref/plugins.txt
# Plugin list (used by install-plugins script)
RUN cd /tmp/jenkins/ && for file in *plugins.txt; do (cat "${file}"; echo)>> /usr/share/jenkins/ref/plugins.txt; done

# install plugins
RUN jenkins-plugin-cli -f /usr/share/jenkins/ref/plugins.txt