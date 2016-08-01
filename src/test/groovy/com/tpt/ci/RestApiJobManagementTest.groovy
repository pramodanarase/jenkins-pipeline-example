package com.tpt.ci

import javaposse.jobdsl.dsl.ConfigFile
import javaposse.jobdsl.dsl.ConfigFileType
import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.dsl.GeneratedItems
import javaposse.jobdsl.dsl.JobManagement
import javaposse.jobdsl.plugin.JenkinsJobManagement
import org.jenkinsci.plugins.configfiles.maven.MavenSettingsConfig
import org.junit.ClassRule
import org.jvnet.hudson.test.JenkinsRule
import org.jvnet.hudson.test.recipes.WithPlugin
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll


class RestApiJobManagementTest extends Specification {
    @Shared
    @ClassRule
    JenkinsRule jenkinsRule = new JenkinsRule()


    @Unroll
    def 'test script #file.name'(File file) {
        given:

        def jobManagement = new JenkinsJobManagement(System.out, [:], new File('.'))

        /* update and mock maven setting config file */
        String settingsName = 'maven-user-settings'       
        ConfigFile configFile = new ConfigFile(ConfigFileType.MavenSettings, jobManagement);
        configFile.setName(settingsName);
        jobManagement.createOrUpdateConfigFile(configFile,true);        
        
        when:
        new DslScriptLoader(jobManagement).runScript(file.text)

        then:
        noExceptionThrown()

        where:
        file << jobFiles
    }

    @WithPlugin("multiple-scms.hpi")
    static List<File> getJobFiles() {
        List<File> files = []
        new File('jobs').eachFileRecurse {
            if (it.name.endsWith('dsl.groovy')) {
                files << it
            }
        }
        files
    }
}


