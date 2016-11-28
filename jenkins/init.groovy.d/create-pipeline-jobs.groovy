import jenkins.model.Jenkins
import org.jenkinsci.plugins.workflow.job.WorkflowJob


WorkflowJob job = Jenkins.instance.createProject(WorkflowJob, 'seed')

def pipeline = """
    node {
        git "https://github.com/glonnon/jenkins-pipeline-global-libs-demo.git"
        sh 'ls'
        jobDsl targets: 'job-dsl/seed.groovy' 

        jobDsl targets: 'job-dsl/seed.groovy',
           removedJobAction: 'DELETE',
           removedViewAction: 'DELETE',
    }
"""
job.definition = new org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition(pipeline, true)

job.scheduleBuild(0)

println 'seed job created and scheduled'