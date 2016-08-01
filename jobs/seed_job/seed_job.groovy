import com.tpt.ci.GlobalVar
import com.tpt.utilities.MyUtilities

import static com.tpt.ci.GlobalVar.*



def out = getProperty("out")

GlobalVar.init(out)

def job=job("ts-automation-seed-job") {
    label("master")
    logRotator {
        artifactNumToKeep(2)
    }


    scm {
        git {
            remote {
                url("https://github.com/pramodanarase/jenkins-pipeline-example.git")
                credentials("${GIT_CREDENTIAL}")
                branch("${GIT_BRANCH}")
            }
            browser {
            }
        }
    }

    steps {
        maven {
            mavenInstallation("${MAVEN_VERSION}")
            providedSettings("${MAVEN_USER_SETTINGS}")
            goals("clean package -DskipTests")
        }
        dsl {
            external("jobs/8_4_plus/*dsl.groovy")
            removeAction("DELETE")
            removeViewAction("DELETE")
            ignoreExisting(false)
            lookupStrategy("JENKINS_ROOT")
            additionalClasspath("target/**/*.jar")
        }
    }
}

listView("Seed-View"){
    jobs {
        names("ts-automation-seed-job")
    }
    /* jobFilters {
         regex {
             matchValue(RegexMatchValue.DESCRIPTION)
             regex(/.*-project-B-.*//*)
         }
     }*/
    columns {
        status()
        weather()
        name()
        lastSuccess()
        lastFailure()
    }
}

//add email on failure
MyUtilities.addEmailToDev(job);