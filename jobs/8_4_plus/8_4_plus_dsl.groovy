import com.tpt.ci.GlobalVar
import static com.tpt.ci.GlobalVar.*

/**
 * Created by pramod.anarase on 27-07-2016.
 */

def out = getProperty("out")

GlobalVar.init(out)

pipelineJob("8.4_plus_cxl_deploy_pipeline"){

    parameters {
        stringParam('DEPLOY_SERVER_NAME',)
        stringParam('HOST_NAME',)
        stringParam('RELEASE_TO_DEPLOY_FULL_PATH',)
        stringParam('DEPLOY_INSTALL_FOLDER',)
        stringParam('SCENARIO_NAME',)
        stringParam('BRANCH',)
        stringParam('CXL-BUILD-ID',)

    }

    definition {
        cps {
            script(readFileFromWorkspace("${root_dir}/jobs/8_4_plus/8_4_plus_pipeline.groovy"))
            sandbox()
        }
    }


}