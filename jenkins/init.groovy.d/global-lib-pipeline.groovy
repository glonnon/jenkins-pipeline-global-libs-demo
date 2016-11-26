import jenkins.model.*

def inst = Jenkins.getInstance()
def desc = inst.getDescriptor("org.jenkinsci.plugins.workflow.libs.GlobalLibraries")

def remote = "git@bitbucket.org:thuron_llc/cicd-pipeline.git"
def credentialsId = 'bitbucket-id'
def includes = null
def excludes = null
def ignoreOnPushNotification = true

def scm = new jenkins.plugins.git.GitSCMSource('cicd',remote,credentialsId,includes,excludes,ignoreOnPushNotification)
def libraryRetriever = new org.jenkinsci.plugins.workflow.libs.SCMSourceRetriever(scm)
def config = new org.jenkinsci.plugins.workflow.libs.LibraryConfiguration("cicd",libraryRetriever)
  
config.setImplicit(true)
config.setDefaultVersion('master')
def libList = []
libList.add(config)
desc.setLibraries(libList)
desc.save()
println 'global pipeline library is configured'
