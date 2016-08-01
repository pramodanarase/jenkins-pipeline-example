package com.tpt.ci


class GlobalVar {
    static Map CONFIG;
    def static ROOT_DIR;
    def static GIT_CREDENTIAL;
    def static GIT_BRANCH;
    //GIT Repository URLs
    def static GIT_CXL_CLIENT_URL;
    def static GIT_CXL_CLIENT_BROWSER_URL;
    def static GIT_CXL_SCHEMA_URL;
    def static GIT_CXL_SCHEMA_BROWSER_URL;
    def static GIT_CXL_D2007_LIBRARY_URL;
    def static GIT_CXL_D2007_LIBRARY_BROWSER_URL;
    def static GIT_CXL_DISTRIBUTION_URL;
    def static GIT_CXL_DISTRIBUTION_BROWSER_URL;
    def static GIT_CXL_LIBRA_URL;
    def static GIT_CXL_LIBRA_BROWSER_URL;
    def static GIT_CXL_SERVER_URL;
    def static GIT_CXL_SERVER_BROWSER_URL;
    def static CXL_TECHNICAL_PUBLICATION_GIT_URL;
    def static CXL_TECHNICAL_PUBLICATION_GIT_BROWSER_URL;
    def static GIT_TPT_CI_TEST_AUTOMATION_URL;
    def static GIT_TPT_CI_TEST_AUTOMATION_BROWSER_URL;
    //MAVEN related configurations
    def static MAVEN_VERSION;
    def static MAVEN_USER_SETTINGS;
    def static TPT_CI_REPORT_GENERATION_URL;

    // DSL config file generator constant
    def static CXL_SYSTEM_PROPERTY_PATTERN;

    def static CXL_SYSTEM_PROPERTY_BASE_URL;
    def static CXL_SYSTEM_PROPERTY_USERNAME;
    def static CXL_SYSTEM_PROPERTY_PASSWORD;

    //  Build machine's  label
    def static NODE_LABEL_MASTER;
    def static NODE_LABEL_WINDOWS_BUILD;
    def static NODE_LABEL_LINUX_BUILD;
    def static JOB_NAME_PREFIX;

    static void init(Object out) {
        out.println("inside init")
        try {
            ConfigObject conf = new ConfigSlurper().parse(GlobalVar.class.getClassLoader().getResource("job.proper‌​ties"));
            CONFIG = conf.flatten();
        } catch (Exception e) {
            e.printStackTrace()
            out.println(e.getMessage())
        }

        out.println("config dir: " + CONFIG.get("root_dir"))
        initProperties()
    }


    static void initProperties() {
        setROOT_DIR("D:/git-idea/jenkins-pipeline-example")
        setGIT_CREDENTIAL("981102c5-c1a4-456c-aabc-bddcb3c67931")
        setGIT_BRANCH("master")
        //GIT Repository URLs)
        setGIT_CXL_CLIENT_URL("https://portal.tpt.com/stash/scm/cxl/tpt-cxl-client.git")
        setGIT_CXL_CLIENT_BROWSER_URL("https://portal.tpt.com/stash/scm/cxl/tpt-cxl-client")
        setGIT_CXL_SCHEMA_URL("https://portal.tpt.com/stash/scm/cxl/tpt-cxl-schema.git")
        setGIT_CXL_SCHEMA_BROWSER_URL("https://portal.tpt.com/stash/scm/cxl/tpt-cxl-schema")
        setGIT_CXL_D2007_LIBRARY_URL("https://portal.tpt.com/stash/scm/cxl/tpt-cxl-d2007-library.git")
        setGIT_CXL_D2007_LIBRARY_BROWSER_URL("https://portal.tpt.com/stash/scm/cxl/tpt-cxl-d2007-library")
        setGIT_CXL_DISTRIBUTION_URL("https://portal.tpt.com/stash/scm/cxl/tpt-cxl-distribution.git")
        setGIT_CXL_DISTRIBUTION_BROWSER_URL("https://portal.tpt.com/stash/scm/cxl/tpt-cxl-distribution")
        setGIT_CXL_LIBRA_URL("https://portal.tpt.com/stash/scm/cxl/tpt-cxl-libra.git")
        setGIT_CXL_LIBRA_BROWSER_URL("https://portal.tpt.com/stash/scm/cxl/tpt-cxl-libra")
        setGIT_CXL_SERVER_URL("https://portal.tpt.com/stash/scm/cxl/tpt-cxl-server.git")
        setGIT_CXL_SERVER_BROWSER_URL("https://portal.tpt.com/stash/projects/CXL/repos/tpt-cxl-server")
        setCXL_TECHNICAL_PUBLICATION_GIT_URL("https://portal.tpt.com/stash/scm/cxl/tpt-cxl-technical-publication.git")
        setCXL_TECHNICAL_PUBLICATION_GIT_BROWSER_URL("https://portal.tpt.com/stash/scm/cxl/tpt-cxl-technical-publication")
        setGIT_TPT_CI_TEST_AUTOMATION_URL("https://portal.tpt.com/stash/scm/cxl/tpt-ci-test-automation.git")
        setGIT_TPT_CI_TEST_AUTOMATION_BROWSER_URL("https://portal.tpt.com/stash/scm/cxl/tpt-ci-test-automation")
        //MAVEN related configurations)
        setMAVEN_VERSION("maven-3.3.9")
        setMAVEN_USER_SETTINGS("maven-user-settings")
        setTPT_CI_REPORT_GENERATION_URL("https://portal.tpt.com/stash/scm/cxl/tpt-ci-test-report-generation.git")
        // DSL config file generator constant)
        setCXL_SYSTEM_PROPERTY_PATTERN("pattern")
        setCXL_SYSTEM_PROPERTY_BASE_URL("baseUrl")
        setCXL_SYSTEM_PROPERTY_USERNAME("username")
        setCXL_SYSTEM_PROPERTY_PASSWORD("password")
        //  Build machine's  label)
        setNODE_LABEL_MASTER("master")
        setNODE_LABEL_WINDOWS_BUILD("windows-build")
        setNODE_LABEL_LINUX_BUILD("linux-build")
        setJOB_NAME_PREFIX(getJobNamePrefix())
    }

     static String getJobNamePrefix() {
        return GIT_BRANCH.replaceAll("/", "_")
    }

    static getROOT_DIR() {
        return ROOT_DIR
    }

    static void setROOT_DIR(rootDir) {
        ROOT_DIR = rootDir
    }

    static getGIT_CREDENTIAL() {
        return GIT_CREDENTIAL
    }

    static void setGIT_CREDENTIAL(gitCredential) {
        GIT_CREDENTIAL = gitCredential
    }

    static getGIT_BRANCH() {
        return GIT_BRANCH
    }

    static void setGIT_BRANCH(gitBranch) {
        GIT_BRANCH = gitBranch
    }

    static getGIT_CXL_CLIENT_URL() {
        return GIT_CXL_CLIENT_URL
    }

    static void setGIT_CXL_CLIENT_URL(gitCxlClientUrl) {
        GIT_CXL_CLIENT_URL = gitCxlClientUrl
    }

    static getGIT_CXL_CLIENT_BROWSER_URL() {
        return GIT_CXL_CLIENT_BROWSER_URL
    }

    static void setGIT_CXL_CLIENT_BROWSER_URL(gitCxlClientBrowserUrl) {
        GIT_CXL_CLIENT_BROWSER_URL = gitCxlClientBrowserUrl
    }

    static getGIT_CXL_SCHEMA_URL() {
        return GIT_CXL_SCHEMA_URL
    }

    static void setGIT_CXL_SCHEMA_URL(gitCxlSchemaUrl) {
        GIT_CXL_SCHEMA_URL = gitCxlSchemaUrl
    }

    static getGIT_CXL_SCHEMA_BROWSER_URL() {
        return GIT_CXL_SCHEMA_BROWSER_URL
    }

    static void setGIT_CXL_SCHEMA_BROWSER_URL(gitCxlSchemaBrowserUrl) {
        GIT_CXL_SCHEMA_BROWSER_URL = gitCxlSchemaBrowserUrl
    }

    static getGIT_CXL_D2007_LIBRARY_URL() {
        return GIT_CXL_D2007_LIBRARY_URL
    }

    static void setGIT_CXL_D2007_LIBRARY_URL(gitCxlD2007LibraryUrl) {
        GIT_CXL_D2007_LIBRARY_URL = gitCxlD2007LibraryUrl
    }

    static getGIT_CXL_D2007_LIBRARY_BROWSER_URL() {
        return GIT_CXL_D2007_LIBRARY_BROWSER_URL
    }

    static void setGIT_CXL_D2007_LIBRARY_BROWSER_URL(gitCxlD2007LibraryBrowserUrl) {
        GIT_CXL_D2007_LIBRARY_BROWSER_URL = gitCxlD2007LibraryBrowserUrl
    }

    static getGIT_CXL_DISTRIBUTION_URL() {
        return GIT_CXL_DISTRIBUTION_URL
    }

    static void setGIT_CXL_DISTRIBUTION_URL(gitCxlDistributionUrl) {
        GIT_CXL_DISTRIBUTION_URL = gitCxlDistributionUrl
    }

    static getGIT_CXL_DISTRIBUTION_BROWSER_URL() {
        return GIT_CXL_DISTRIBUTION_BROWSER_URL
    }

    static void setGIT_CXL_DISTRIBUTION_BROWSER_URL(gitCxlDistributionBrowserUrl) {
        GIT_CXL_DISTRIBUTION_BROWSER_URL = gitCxlDistributionBrowserUrl
    }

    static getGIT_CXL_LIBRA_URL() {
        return GIT_CXL_LIBRA_URL
    }

    static void setGIT_CXL_LIBRA_URL(gitCxlLibraUrl) {
        GIT_CXL_LIBRA_URL = gitCxlLibraUrl
    }

    static getGIT_CXL_LIBRA_BROWSER_URL() {
        return GIT_CXL_LIBRA_BROWSER_URL
    }

    static void setGIT_CXL_LIBRA_BROWSER_URL(gitCxlLibraBrowserUrl) {
        GIT_CXL_LIBRA_BROWSER_URL = gitCxlLibraBrowserUrl
    }

    static getGIT_CXL_SERVER_URL() {
        return GIT_CXL_SERVER_URL
    }

    static void setGIT_CXL_SERVER_URL(gitCxlServerUrl) {
        GIT_CXL_SERVER_URL = gitCxlServerUrl
    }

    static getGIT_CXL_SERVER_BROWSER_URL() {
        return GIT_CXL_SERVER_BROWSER_URL
    }

    static void setGIT_CXL_SERVER_BROWSER_URL(gitCxlServerBrowserUrl) {
        GIT_CXL_SERVER_BROWSER_URL = gitCxlServerBrowserUrl
    }

    static getCXL_TECHNICAL_PUBLICATION_GIT_URL() {
        return CXL_TECHNICAL_PUBLICATION_GIT_URL
    }

    static void setCXL_TECHNICAL_PUBLICATION_GIT_URL(cxlTechnicalPublicationGitUrl) {
        CXL_TECHNICAL_PUBLICATION_GIT_URL = cxlTechnicalPublicationGitUrl
    }

    static getCXL_TECHNICAL_PUBLICATION_GIT_BROWSER_URL() {
        return CXL_TECHNICAL_PUBLICATION_GIT_BROWSER_URL
    }

    static void setCXL_TECHNICAL_PUBLICATION_GIT_BROWSER_URL(cxlTechnicalPublicationGitBrowserUrl) {
        CXL_TECHNICAL_PUBLICATION_GIT_BROWSER_URL = cxlTechnicalPublicationGitBrowserUrl
    }

    static getGIT_TPT_CI_TEST_AUTOMATION_URL() {
        return GIT_TPT_CI_TEST_AUTOMATION_URL
    }

    static void setGIT_TPT_CI_TEST_AUTOMATION_URL(gitTptCiTestAutomationUrl) {
        GIT_TPT_CI_TEST_AUTOMATION_URL = gitTptCiTestAutomationUrl
    }

    static getGIT_TPT_CI_TEST_AUTOMATION_BROWSER_URL() {
        return GIT_TPT_CI_TEST_AUTOMATION_BROWSER_URL
    }

    static void setGIT_TPT_CI_TEST_AUTOMATION_BROWSER_URL(gitTptCiTestAutomationBrowserUrl) {
        GIT_TPT_CI_TEST_AUTOMATION_BROWSER_URL = gitTptCiTestAutomationBrowserUrl
    }

    static getMAVEN_VERSION() {
        return MAVEN_VERSION
    }

    static void setMAVEN_VERSION(mavenVersion) {
        MAVEN_VERSION = mavenVersion
    }

    static getMAVEN_USER_SETTINGS() {
        return MAVEN_USER_SETTINGS
    }

    static void setMAVEN_USER_SETTINGS(mavenUserSettings) {
        MAVEN_USER_SETTINGS = mavenUserSettings
    }

    static getTPT_CI_REPORT_GENERATION_URL() {
        return TPT_CI_REPORT_GENERATION_URL
    }

    static void setTPT_CI_REPORT_GENERATION_URL(tptCiReportGenerationUrl) {
        TPT_CI_REPORT_GENERATION_URL = tptCiReportGenerationUrl
    }

    static getCXL_SYSTEM_PROPERTY_PATTERN() {
        return CXL_SYSTEM_PROPERTY_PATTERN
    }

    static void setCXL_SYSTEM_PROPERTY_PATTERN(cxlSystemPropertyPattern) {
        CXL_SYSTEM_PROPERTY_PATTERN = cxlSystemPropertyPattern
    }

    static getCXL_SYSTEM_PROPERTY_USERNAME() {
        return CXL_SYSTEM_PROPERTY_USERNAME
    }

    static void setCXL_SYSTEM_PROPERTY_USERNAME(cxlSystemPropertyUsername) {
        CXL_SYSTEM_PROPERTY_USERNAME = cxlSystemPropertyUsername
    }

    static getCXL_SYSTEM_PROPERTY_PASSWORD() {
        return CXL_SYSTEM_PROPERTY_PASSWORD
    }

    static void setCXL_SYSTEM_PROPERTY_PASSWORD(cxlSystemPropertyPassword) {
        CXL_SYSTEM_PROPERTY_PASSWORD = cxlSystemPropertyPassword
    }

    static getNODE_LABEL_MASTER() {
        return NODE_LABEL_MASTER
    }

    static void setNODE_LABEL_MASTER(nodeLabelMaster) {
        NODE_LABEL_MASTER = nodeLabelMaster
    }

    static getNODE_LABEL_WINDOWS_BUILD() {
        return NODE_LABEL_WINDOWS_BUILD
    }

    static void setNODE_LABEL_WINDOWS_BUILD(nodeLabelWindowsBuild) {
        NODE_LABEL_WINDOWS_BUILD = nodeLabelWindowsBuild
    }

    static getNODE_LABEL_LINUX_BUILD() {
        return NODE_LABEL_LINUX_BUILD
    }

    static void setNODE_LABEL_LINUX_BUILD(nodeLabelLinuxBuild) {
        NODE_LABEL_LINUX_BUILD = nodeLabelLinuxBuild
    }

    static getJOB_NAME_PREFIX() {
        return JOB_NAME_PREFIX
    }

    static void setJOB_NAME_PREFIX(jobNamePrefix) {
        JOB_NAME_PREFIX = jobNamePrefix
    }

}