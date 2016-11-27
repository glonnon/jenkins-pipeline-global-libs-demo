def services = ['test']
def stacks = ['review','integration','staging','performance']


services.each {
    serviceName = it
    folderName = serviceName
    folder (folderName) 
    stacks.each {
        stackName = it

        // review and integration Jenkinsfiles are stored in the source repository
        // all others are stored in the operations repository
        // allows for seperation of concern/security/accountablity/etc...

        if (stacks == 'review') || (stacks == 'integration') {
            pipelineJob('example') {
                definition {
                    cps {
                        script(readFileFromWorkspace('Jenkinsfile_${stack}'))
                        sandbox()
                    }
                }
            }
        } 
        else {
            pipelineJob('example') {
                definition {
                    cpsScm {
                        scm { 
                            git
                        }
                        scriptPath('cicd/Jenkinsfile_${stack}')
                    }
                }
            }

        }
    }

    // release multibranch
    // all releases are done on a branch

multibranchPipelineJob('example') {
    branchSources {
        git {
            remote('https://github.com/jenkinsci/job-dsl-plugin.git')
            credentialsId('github-ci')
            includes('releases')
        }
    }
    orphanedItemStrategy {
        discardOldItems {
            numToKeep(20)
        }
    }
}

}
