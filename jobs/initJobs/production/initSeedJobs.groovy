def env = System.getenv()

job('Deploy Jobs (Production)') {
    description 'Deploy Jobs from git repository'
    scm {
        //Configure git remote
        git {
            remote {
                name('GitJobs')
                url(env['JOB_REPO_URL'])
                credentials('scmKey')
            }
            branch(env['JOB_REPO_BRANCH'])
        }
    }
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