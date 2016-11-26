package org.thuron

class DockerCompose implements Serializable {
    def steps
    def DockerCompose(steps) { this.steps} 
    def up(args = "") {
        steps.sh "sudo docker-compose up ${args}"
        return this
    }
    def stop(args = "") {
        steps.sh "sudo docker-compose stop ${args}"
        return this
    }
    def build(args = "") {
        steps.sh "sudo docker-compose build ${args}"
        return this
    }
}