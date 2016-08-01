package com.tpt.ci

import hudson.model.AbstractProject
import hudson.model.Item
import jenkins.model.Jenkins
import org.apache.commons.io.FileUtils
import org.apache.commons.io.IOUtils

import java.util.regex.Matcher
import java.util.regex.Pattern


class Utility {

    static Jenkins jenkins = Jenkins.getInstance()

    static def String getWorkspaceDir(String jobName, Object out) {

        Item item = jenkins.getItemByFullName(jobName)
        if (item != null) {
            // jenkins.getRawWorkspaceDir()  //${ITEM_ROOTDIR}/workspace
            AbstractProject job = item
            File file = job.getRootDir()
            out.println("Workspace Dir:" + file.getPath() + "/workspace for job:" + jobName)
            return file.getPath() + "/workspace"
        }
        return ""
    }

    /** read file and convert to string **/
    static def String readFile(String fileName, Object out) {
        InputStream inputStream = Utility.class.getClassLoader().getResourceAsStream(fileName)

        String fileContents = getString(inputStream)
        // out.println("fileContents:" + fileContents)
        if (fileContents != null)
            return fileContents
        return ""
    }

    static def getString(InputStream obj) {
        String theString = IOUtils.toString(obj, "UTF-8");
    }

    static def copyFileToWorkspace(String[] fileNames, String jobName, Object out) {
        InputStream inputStream = null
        String workspace = getWorkspaceDir(jobName, out)
        String destFilePath = ""
        File destFile = null

        if (workspace != null && workspace.length() != 0) {
            for (String fileName : fileNames) {
                inputStream = Utility.class.getClassLoader().getResourceAsStream("scripts/" + fileName)
                out.println("inputStream" + inputStream)
                destFilePath = workspace + "/" + fileName
                destFile = new File(destFilePath)
                if (inputStream != null) {
                    FileUtils.copyInputStreamToFile(inputStream, destFile)
                } else {
                    out.println("ERROR- file not found" + fileName)
                }
            }
        }
    }


    static def String getUserContentDir(Object out) {
        File file = jenkins.getRootDir()
        out.println("RootDir Dir:" + file.getPath() + "/userContent")
        return file.getPath() + "/userContent"
    }

    static def copyFileToUserContent(String[] fileNames, String srcType, String destType, Object out) {
        InputStream inputStream = null
        String workspace = getUserContentDir(out)
        String destFilePath = ""
        File destFile = null

        if (workspace != null && workspace.length() != 0) {
            for (String fileName : fileNames) {
                inputStream = Utility.class.getClassLoader().getResourceAsStream("scripts/" + srcType + "/" + fileName)
                out.println("inputStream" + inputStream)
                destFilePath = workspace + "/scripts/" + destType + "/" + fileName
                destFile = new File(destFilePath)
                if (inputStream != null) {
                    FileUtils.copyInputStreamToFile(inputStream, destFile)
                } else {
                    out.println("ERROR- file not found" + fileName)
                }
            }
        }
    }

    static def deleteAllProject(String JobName) {
        for (Item item in jenkins.getAllItems()) {
            if (!item.getName().equals(JobName))
                item.delete();
        }
    }

    //copy file relative to jenkins root dir
    static def copyFile(String[] fileNames, String srcDir, String destDir, Object out) {
        InputStream inputStream = null
        String rootDir = getJenkinsRootDir();
        String destFilePath = ""
        File destFile = null

        if (rootDir != null && rootDir.length() != 0) {
            for (String fileName : fileNames) {
                inputStream = Utility.class.getClassLoader().getResourceAsStream(srcDir+"/"+fileName)
                destFilePath = rootDir + "/" + destDir + "/" + fileName
                def folder = new File (rootDir + "/" + destDir )
                if( !folder.exists() ) {
                    // Create all folders up-to and including
                    folder.mkdirs()
                }
                destFile = new File(destFilePath)
                if (inputStream != null) {
                    FileUtils.copyInputStreamToFile(inputStream, destFile)
                    out.println("file copied" + destFile)
                } else {
                    out.println("ERROR- file not found" + fileName)
                }
            }
        }
    }

    static def String getJenkinsRootDir(){
        File file = jenkins.getRootDir()
        return file.getPath()
    }

/**
 * In development we dose not require to checkout source code  so we replace that tag and add custom workspace
 * @param file
 * @param workspace
 * @return
 */
    static def String updateSeedJob(File file, String workspace) {

        String text = file.getText()
        Pattern regex = Pattern.compile("(.*?)scm \\{.*?(steps \\{.*)", Pattern.DOTALL);
        Matcher regexMatcher = regex.matcher(text);
        workspace = workspace.replaceAll('\\\\', '\\\\\\\\')
        workspace = "customWorkspace('" + workspace + "')"
        println('workspace:' + workspace)
        if (regexMatcher.find()) {
            text = regexMatcher.group(1) + ' ' + workspace + '\n' + regexMatcher.group(2)
        }
        return text;
    }
}

