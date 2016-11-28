def services = ['test']
def stacks = ['review','integration','staging','performance']

// note: this is only for demo purposes...
def gitRepo = 'https://github.com/glonnon/jenkins-pipeline-global-libs-demo.git'

services.each {
    serviceName = it
    folderName = serviceName
    folder (folderName) 
    def curFolder = folderName
    stacks.each {
        stackName = it

        // review and integration Jenkinsfiles are stored in the source repository
        // all others are stored in the operations repository
        // allows for seperation of concern/security/accountablity/etc...

        if ((stackName == 'review') || (stackName == 'integration')) {
            pipelineJob("$curFolder/$stackName") {
                definition {
                    cps {
                        script("Jenkinsfile_$stackName")
                        sandbox()
                    }
                }
            }
        } 
        else {
            pipelineJob("$curFolder/$stackName") {
                definition {
                    cpsScm {
                        scm { 
                            git(gitRepo,stackName)
                        }
                        scriptPath("cicd/Jenkinsfile_$stackName")
                    }
                }
            }

        }
    }

    // release multibranch
    // all releases are done on a branch
/*
    multibranchPipelineJob(serviceName) {
        triggers { 
            cron "2 * * * *"
        }
        branchSources {
            git {
                remote gitRepo
                includes 'releases/**'
            }
        }
        orphanedItemStrategy {
            discardOldItems {
                numToKeep(20)
            }
        }
    }
    */
}
