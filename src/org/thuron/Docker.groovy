package org.thuron

class Docker implements Serializable {
    def steps
    def Docker(steps) { this.steps} 
    def run(args = "") {
        steps.sh "sudo docker run ${args}"
        return this
    }
    def build(args = "") {
        steps.sh "sudo docker build ${args}"
        return this
    }
    def push(args = "") {
        steps.sh "sudo docker push ${args}"
        return this
    }
    def tag(from,to) {
        steps.sh "sudo docker tag ${from} ${to}""
        return this
    }
}