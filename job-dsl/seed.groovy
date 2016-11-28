def services = ['test']
def stacks = ['review','integration','staging','performance']
def stacksDesc = [ 
        """ review is the code review stack, it insures the code review compiles and passes smoke testing and 
            does statically analysis on the changes""",
        """ integration is responsbile for integrating changes into the master branch""",
        """ staging is the stack used for all system and acceptance testing occurs.""",
        """ performance should only be used for performance testing, and deployed when needed. """
]

// note: this is only for demo purposes...
def gitRepo = 'https://github.com/glonnon/jenkins-pipeline-global-libs-demo.git'

services.each {
    serviceName = it
    folderName = serviceName
    folder (folderName) 
    def curFolder = folderName
    def index = 0
    stacks.each {
        index++
        stackName = it

        // review and integration Jenkinsfiles are stored in the source repository
        // all others are stored in the operations repository
        // allows for seperation of concern/security/accountablity/etc...

        if ((stackName == 'review') || (stackName == 'integration')) {
            pipelineJob("$curFolder/$stackName") {
                description(stacksDesc[index])
                definition {
                    cps {
                        script("Jenkinsfile_$stackName")
                        sandbox()
                    }
                }
                compressBuildLog()
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
