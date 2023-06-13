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
	.withRole(role)
	.getFirst()

'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential(user.email, user.pwd).clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User navigates to Test Run Calendar page'
Page.nav(TestRunCalendarPage).navigateTo(user.teamId, user.projectId)

'User click Schedule Test Run button'
Page.nav(TestRunCalendarPage).sleep(1).clickScheduleTestRun() // Sleep is workaround for running test on Safari

'Verify user can select git repo, tsc, refresh tsc, input test run name, and see the tooltip'
Page.nav(NewUIScheduleTestRunPopUp)
	.waitForModalCompleteLoading()
	.clickTSCTab()
	.inputTestRunName('Verify ui of schedule test run modal ts tab')
	.clickScriptRepo()
	.sleep(1) // Sleep is workaround for running test on Safari
	.selectScriptRepo('cloudian-automation-1')
	.clickTSCObjectName()
	.sleep(1) // Sleep is workaround for running test on Safari
	.selectTSC('Test Suites/TSC/TSC with 6 passed WA TS')
	.hoverEnvironmentTooltip()
	.verifyTextTooltip("If you do not override environment for a test suite in a test suite collection, it will be executed on a Linux instance with the latest versions of Chrome and Firefox installed.")
	.clickRefreshTSC()
	
'Verify user can work with the TestCloud environment'	
Page.nav(NewUIScheduleTestRunPopUp)
	.sleep(1) // Sleep is workaround for running test on Safari
	.clickTSCConfigLink()
	.sleep(1) // Sleep is workaround for running test on Safari
	.hoverTSNameInTSCConfig(1)
	.sleep(1) // Sleep is workaround for running test on Safari
	.verifyTextTooltip('Test Suites/Web App/Test Suites/Public/Smart Wait TS')
	.clickTSCTestCloudEnv(1)
	.selectPlatform('Linux')
	.selectBrowser('Chrome')
	.selectVersion("${BrowserVersion.CHROME_LINUX_LATEST}")
	.selectedAllTSs()
	.selectEnvForAllTSs()
	.selectPlatform('Android')
	.clickTestCloudTunnelEnable()
	.clickSaveTSCEnv()
	.sleep(1) // Sleep is workaround for running test on Safari
	.verifyNumberOfEnvIsSelectedAtTSC(3)
	.verifyNumberOfRemainEnv("+3")

'Verify user can work with the Kubernetes environment'
Page.nav(NewUIScheduleTestRunPopUp)
	.clickTSCConfigLink()
	.clickKubernetesRadio() // kubernetes
	.clickKubernetesEnvDropdown()
	.selectKubernetesEnv('Kubernetes | cloudian-k8s-1')
	.clickSaveTSCEnv()
	.sleep(1) // Sleep is workaround for running test on Safari
	.clickRemoveTSCEnv()
	.verifyNoTSCEnvSelected()
	
'Verify user can work with the CirclCI environment'
Page.nav(NewUIScheduleTestRunPopUp)
	.clickTSCConfigLink()
	.clickCircleCIRadio() // circle CI
	.clickCircleCIEnvDropdown()
	.selectCircleCIEnv('CirCleCI | cloudian-CircleCI-1')
	.clickSaveTSCEnv()
	.sleep(1) // Sleep is workaround for running test on Safari
	.clickRemoveTSCEnv()
	.verifyNoTSCEnvSelected()

'Verify user can work with the Local agent environment'
Page.nav(NewUIScheduleTestRunPopUp)
	.clickTSCConfigLink()
	.clickLocalRadio() // Local agent
	.clickLocalTestEnvDropdown()
	.selectLocalTestEnv('cloudian-local-agent-1')
	.clickSaveTSCEnv()
		
'Verify user can input schedule configurations'
Page.nav(NewUIScheduleTestRunPopUp)
	.inputStart('04/01/2050 22:52')
	.inputEnd('04/02/2050 18:29')
	.inputIntervalTime('10')
	.inputIntervalUnit('Hour(s)')
	.clickRepeatEnable()

'Verify user can see and work with the advanced settings link'
Page.nav(NewUIScheduleTestRunPopUp)
	.clickAdvancedSettings()
	.clickVisualTesting()
	.selectVisualTesting('Generate a new Baseline Collection')
	.clickPreinstallKRERadio()
	.inputPreinstallKRE('test')
	.clickKREVersionRadio()
	.clickKRE()
	.selectKREVersion('8.6.0')
	.clickExecutionMode()
	.selectExecutionMode('Sequential')
	.clickExecutionMode()
	.selectExecutionMode('Parallel')
	.clickKobitonToggle()
	.inputKobitonDeviceId('123')
	.clickAddNewCustomField()
	.selectCustomFieldDisplayName('Priority')
	.selectCustomFieldDisplayValue('Critical')
	.clickAssignCustomField()
	.clickUnassignCustomField('Critical')
	.clickTagInputField()
	.createNewTag(tag)
	.assignTag(tag)
	.unassignTag(tag)
	.clickRelease()
	.selectRelease('cloudian-automation-1 (v1.1.0)')
	.inputTimeout('100')
	.clickBackAdvancedSettings()

'Verify user can remove local agent'
Page.nav(NewUIScheduleTestRunPopUp)
	.sleep(1) // Sleep is workaround for running test on Safari
	.clickRemoveTSCEnv()
	.verifyNoTSCEnvSelected()
	
'Verify user can cancel'
Page.nav(NewUIScheduleTestRunPopUp)
	.sleep(1) // Sleep is workaround for running test on Safari
	.clickTSCConfigLink()
	.clickCancelTSCEnv()

@com.kms.katalon.core.annotation.SetUp
def setUp() {
	tag = new DateTimeUtility().getCurrentDateTime()
}
	