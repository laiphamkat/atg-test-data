import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.constants.BrowserVersion
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.testexecution.NewUIScheduleTestRunPopUp
import katalon.testops.testexecution.TestRunCalendarPage
import katalon.utility.DateTimeUtility

'Pre-condition: Find a user to log in'
Credential user = Page.nav(Credential)
	.getCredentials()
	.getFirst()

'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential(user.email, user.pwd).clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User navigates to Test Run Calendar page'
Page.nav(TestRunCalendarPage).navigateTo(user.teamId, user.projectId)

'User verify the Cancel button works on TS of G4'
Page.nav(TestRunCalendarPage).clickScheduleTestRun()
Page.nav(NewUIScheduleTestRunPopUp)
	.clickScriptRepo()
	.sleep(1) // Sleep is workaround for running test on Safari
	.selectScriptRepo('cloudian-automation-1')
	.clickTSEnvDropdown()
	.sleep(1) // Sleep is workaround for running test on Safari
	.clickMoreOptions()
	.sleep(1) // Sleep is workaround for running test on Safari
	.clickDesktopBrowsersTab()
	.selectPlatform('Linux')
	.selectBrowser('Chrome')
	.selectVersion(BrowserVersion.CHROME_LINUX_LATEST)
	.clickCancelEnv()
	.sleep(1) // Sleep is workaround for running test on Safari
	.verifyTSEnvDropdownNotSelected()
	.clickTSEnvDropdown()
	.sleep(1) // Sleep is workaround for running test on Safari
	.clickMoreOptions()
	.clickLocalRadio() // Local agent
	.clickLocalTestEnvDropdown()
	.selectLocalTestEnv('cloudian-local-agent-1')
	.clickCancelEnv()
	.verifyTSEnvDropdownNotSelected()
	.clickTSEnvDropdown()
	.clickMoreOptions()
	.clickKubernetesRadio() // kubernetes
	.clickKubernetesEnvDropdown()
	.selectKubernetesEnv('Kubernetes | cloudian-k8s-1')
	.clickCancelEnv()
	.verifyTSEnvDropdownNotSelected()
	.clickTSEnvDropdown()
	.clickMoreOptions()
	.clickCircleCIRadio() // circle CI
	.clickCircleCIEnvDropdown()
	.selectCircleCIEnv('CirCleCI | cloudian-CircleCI-1')
	.clickCancelEnv()
	.verifyTSEnvDropdownNotSelected()
	
'User selects verify Cannel button at TSC with TC'
Page.nav(NewUIScheduleTestRunPopUp)
	.clickTSCTab()
	.clickTSCConfigLink()
	.clickTSCTestCloudEnv(1)
	.selectPlatform('Windows')
	.selectBrowser('Chrome')
	.selectVersion(BrowserVersion.CHROME_WINDOWS_LATEST)
	.clickTestCloudTunnelEnable()
	.clickCancelTSCEnv()
	.clickTSCConfigLink()
	.verifyEnvNotSelectedAtTSC()
	.verifyTestCloudTunnelDisable() //
	.clickLocalRadio() // Local agent
	.clickLocalTestEnvDropdown()
	.selectLocalTestEnv('cloudian-local-agent-1')
	.clickCancelTSCEnv()
	.clickTSCConfigLink()
	.clickLocalRadio()
	.verifyTSCLocalAgentEnvIsNotSelected()
	.clickKubernetesRadio() // Kubernetes
	.clickKubernetesEnvDropdown()
	.selectKubernetesEnv('Kubernetes | cloudian-k8s-1')
	.clickCancelTSCEnv()
	.clickTSCConfigLink()
	.clickKubernetesRadio() 
	.verifyTSCKubernetEnvIsNotSelected()
	.clickCircleCIRadio() // circle CI
	.clickCircleCIEnvDropdown()
	.selectCircleCIEnv('CirCleCI | cloudian-CircleCI-1')
	.clickCancelTSCEnv()
	.clickTSCConfigLink()
	.clickCircleCIRadio()
	.verifyTSCCircleCIEnvIsNotSelected()
	.clickCancelTSCEnv()

@com.kms.katalon.core.annotation.SetUp
def setUp() {
	tag = new DateTimeUtility().getCurrentDateTime()
}
		
