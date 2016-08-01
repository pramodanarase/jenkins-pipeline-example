node('master') {
    stage '1: copy artifact'
    def workspace = pwd()
    bat "copy D:\\test\\XLCore-20160722-1345\\XL-8.8.00.0.01-SNAPSHOT.zip ${workspace}\\"
    echo 'in copy artifact'
    echo 'branch is' + BRANCH

    dir("E:\\ci-scenario-test\\") {
        stash includes: "antlib\\ant-contrib-1.0b3.jar,scripts\\cxl\\**,conf\\" + BRANCH + "\\scenario\\" + SCENARIO_NAME + "\\**,conf\\" + BRANCH + "\\hostConf\\" + HOST_NAME + "\\**", name: 'scenario'
    }

    //we can use ant for below steps
    stage '3: deploy server'
    //copy script to workspace
    dir("${workspace}") {
        unstash name: 'scenario'
    }

    def antHome = tool 'ant-1.9.7'
    bat "${antHome}/bin/ant -f ${workspace}\\scripts\\cxl\\start-cxl-server.xml -Dworkspace=" + workspace + " -DSCENARIO_NAME=" + SCENARIO_NAME + " -DBRANCH=" + BRANCH + " -DHOST_NAME=" + HOST_NAME + " -DDEPLOY_INSTALL_FOLDER=" + DEPLOY_INSTALL_FOLDER + " -DDEPLOY_SERVER_NAME=" + DEPLOY_SERVER_NAME
    echo 'in deploy server'

}