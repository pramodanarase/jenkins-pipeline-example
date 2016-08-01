import com.tpt.ci.GlobalVar
import com.tpt.ci.Utility
import static com.tpt.ci.GlobalVar.*

/**
 * Created by pramod.anarase on 01-08-2016.
 */

def out =  getProperty("out")
GlobalVar.init(out)
//get seed job workspace
String seedJobWorkspace = Utility.getWorkspaceDir("${SEED_JOB_NAME}", out)
println( "seedJobWorkspace :"+seedJobWorkspace)
String userContentDir  = Utility.getUserContentDir(out)
println( "userContentDir :"+userContentDir)

String srcDir = seedJobWorkspace + File.separator + "job-resources" + File.separator + "conf"
String destDir = userContentDir + File.separator + "conf" + File.separator + "${GIT_BRANCH}"
//copy scenario to user content directory
Utility.copyDir(new File(srcDir),new File(destDir))

//copy job scripts
srcDir = seedJobWorkspace + File.separator + "job-resources" + File.separator + "scripts"
destDir = userContentDir + File.separator + "scripts"
Utility.copyDir(new File(srcDir),new File(destDir) )
