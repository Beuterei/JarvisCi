#!groovy

import jenkins.model.*
import hudson.security.*
import hudson.security.csrf.DefaultCrumbIssuer

def instance = Jenkins.getInstance()

// Automate Admin Setup
// Create Admin User
def hudsonRealm = new HudsonPrivateSecurityRealm(false)
def env = System.getenv()

hudsonRealm.createAccount(env['JENKINS_USERNAME'], env['JENKINS_PASSWORD'])
instance.setSecurityRealm(hudsonRealm)

instance.save()