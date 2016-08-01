package com.tpt.utilities

import com.tpt.ci.Utility
import javaposse.jobdsl.dsl.Job


class MyUtilities {
    static void addEmail(Job job, String type, String file, def out) {
        String filePath = "cxl/\\\$HOST_NAME/\\\$DEPLOY_INSTALL_FOLDER/"
        String preSendScript = Utility.readFile("ReadEmailIDFromFile.groovy", out)
        String defaultContent = null
        switch (type) {
            case "robot":
                filePath = "robot/\\\$ROBOT_HOST_NAME/"
                preSendScript = preSendScript.replaceAll("HOST_NAME", "ROBOT_HOST_NAME")
                defaultContent = "\${SCRIPT,template=\"robot_report.groovy\"}"
                break;
            case "database":
                filePath = "database/"
                break;
                defalut:
                filePath = "cxl/\\\$HOST_NAME/\\\$DEPLOY_INSTALL_FOLDER/"
                break;
        }
        String prefix = "\\\$JENKINS_HOME/userContent/conf/\\\$BRANCH/scenario/\\\$SCENARIO_NAME/"
        filePath = prefix.concat(filePath).concat(file)
        preSendScript = preSendScript.replaceAll("#filePath#", filePath)

        job.with {
            publishers {
                extendedEmail {
                    recipientList('dummy@tpt.com')
                    if (defaultContent != null)
                        setDefaultContent(defaultContent)
                    setPreSendScript(preSendScript)
                    triggers {
                        failure {
                            sendTo {
                                recipientList()
                            }
                        }
                        fixedUnhealthy {
                            sendTo {
                                recipientList()
                            }
                        }
                    }
                }
            }
        }
    }

    static void addEmailForBuildFlow(Job job) {
        job.with {
            publishers {
                extendedEmail {
                    recipientList('cc:cxlteam@tpt.com')
                    triggers {
                        failure {
                            sendTo {
                                recipientList()
                                developers()
                            }
                        }
                        fixedUnhealthy {
                            sendTo {
                                recipientList()
                                developers()
                            }
                        }
                    }
                }
            }
        }
    }

    static void addEmailToDev(Job job) {
        job.with {
            publishers {
                extendedEmail {
                    recipientList('dummy@tpt.com')
                    triggers {
                        failure {
                            sendTo {
                                developers()
                            }
                        }
                        fixedUnhealthy {
                            sendTo {
                                developers()
                            }
                        }
                    }
                }
            }
        }
    }
}