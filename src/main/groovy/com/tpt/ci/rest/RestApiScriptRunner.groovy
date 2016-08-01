package com.tpt.ci.rest

import com.tpt.ci.Utility
import javaposse.jobdsl.dsl.ConfigFile
import javaposse.jobdsl.dsl.ConfigFileType
import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.dsl.GeneratedItems
import javaposse.jobdsl.dsl.MemoryJobManagement
import javaposse.jobdsl.plugin.JenkinsJobManagement

import static com.tpt.ci.GlobalVar.*

String pattern = System.getProperty("pattern")
String baseUrl = System.getProperty('baseUrl')
String username = System.getProperty('username')
String password = System.getProperty('password')
String workspace = System.getProperty('workspace')// password or token

if (!pattern || !baseUrl) {
    println 'usage: -Dpattern=<pattern> -DbaseUrl=<baseUrl> [-Dusername=<username>] [-Dpassword=<password>]'
    System.exit 1
}

println("pattern: " + pattern )
RestApiJobManagement jm = new RestApiJobManagement(baseUrl)

if (username && password) {
    jm.setCredentials username, password
}

/* update and mock maven setting config file */
String settingsName = 'maven-user-settings'
ConfigFile configFile = new ConfigFile(ConfigFileType.MavenSettings, jm);
configFile.setName(settingsName);
jm.createOrUpdateConfigFile(configFile,false)

List files = new FileNameFinder().getFileNames('.', pattern)
GeneratedItems item = null
String  text = ''
for(String  fileName : files){
    println "\nprocessing file: $fileName"
    File file = new File(fileName)
    text = file.text
    if(fileName.contains('seed_job') && baseUrl.contains("localhost:")){
       text = Utility.updateSeedJob(file,workspace)
        println "\nremoved scm section from seed job"
    }
    item =DslScriptLoader.runDslEngine(text, jm)


}


