import jenkins.model.Jenkins
import org.jenkinsci.plugins.workflow.job.WorkflowJob


WorkflowJob job = Jenkins.instance.createProject(WorkflowJob, 'seed')
job.definition = new org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition("node { jobDsl targets: '/home/jenkins_home/seed.groovy' }", true)
job.scheduleBuild(0)

println 'seed job created and scheduled'