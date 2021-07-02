job('Deploy Jobs (Develop)') {
    description 'Deploy Jobs from localJobs'
    //Set custom workspace where the Jobs are located (I do not like this solution, but there was nothing better)
    customWorkspace('/usr/share/jenkins/ref/localJobs')
    steps {
        dsl {
            //Set Wildcard
            external('**/*.groovy')

            //Actions for removed items
            removeAction('DELETE')
            removeViewAction('DELETE')
        }
    }
}