package org.thuron

class Make implements Serializable {
    def steps
    def Make(steps) { this.steps} 
    def all(args = "") {
        steps.sh "make all ${args}"
        return this
    }
    def goal(goal = "") {
        steps.sh "make ${goal}"
        return this
    }
    def clean(args = "") {
        steps.sh "make clean ${args}"
        return this
    }
}