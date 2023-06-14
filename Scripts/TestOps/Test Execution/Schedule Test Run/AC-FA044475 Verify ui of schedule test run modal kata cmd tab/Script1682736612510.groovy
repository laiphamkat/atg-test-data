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

'Verify user can select git repo, input test run name, katalon command'
Page.nav(NewUIScheduleTestRunPopUp)
	.waitForModalCompleteLoading()
	.inputTestRunName('Verify ui of schedule test run modal katalon command tab')
	.clickScriptRepo()
	.sleep(1) // Sleep is workaround for running test on Safari
	.selectScriptRepo('cloudian-automation-1')
	.clickKatalonCmdTab()
	.inputKatalonCmd('test')

'Verify user can select suggested environments and remove the environments'
Page.nav(NewUIScheduleTestRunPopUp)
	.clickKatalonCmdEnvDropdown()
	.sleep(1) // Sleep is workaround for running test on Safari
	.verifySuggestedEnvironments([
		"TestCloud Windows Chrome ${BrowserVersion.CHROME_WINDOWS_LATEST}", 
		"TestCloud Linux Chrome ${BrowserVersion.CHROME_LINUX_LATEST}", 
		"TestCloud Windows Firefox ${BrowserVersion.FIREFOX_WINDOWS_LATEST}", 
		"TestCloud MacOS Safari ${BrowserVersion.SAFARI_MACOS_LATEST}"
	])
	.selectSuggestedEnvironment('Linux', 'Chrome', BrowserVersion.CHROME_LINUX_LATEST)
	.clickRemoveAllTSEnv()
	
'Verify user can select more options and work with TestCloud environments'
Page.nav(NewUIScheduleTestRunPopUp)
	.clickKatalonCmdEnvDropdown()
	.sleep(1) // Sleep is workaround for running test on Safari
	.clickMoreOptions()
	.sleep(1) // Sleep is workaround for running test on Safari
	.clickDesktopBrowsersTab()
	.selectPlatform('Linux')
	.selectBrowser('Chrome')
	.selectVersion(BrowserVersion.CHROME_LINUX_LATEST)
	.clickSaveEnv()
	
'Verify user can work with the Kubernetes, CircleCI, local agent environments'
Page.nav(NewUIScheduleTestRunPopUp)
	.sleep(1) // Sleep is workaround for running test on Safari
	.clickKatalonCmdEnvDropdown()
	.sleep(1) // Sleep is workaround for running test on Safari
	.clickMoreOptions()
	.sleep(1) // Sleep is workaround for running test on Safari
	.clickKubernetesRadio() // kubernetes
	.clickKubernetesEnvDropdown()
	.selectKubernetesEnv('Kubernetes | cloudian-k8s-1')
	.clickCircleCIRadio() // circle CI
	.clickCircleCIEnvDropdown()
	.selectCircleCIEnv('CirCleCI | cloudian-CircleCI-1')
	.clickLocalRadio() // Local agent
	.clickLocalTestEnvDropdown()
	.selectLocalTestEnv('cloudian-local-agent-1')
	.clickSaveEnv()
	
'Verify user can select profile and input schedule config'
Page.nav(NewUIScheduleTestRunPopUp)
	.sleep(1) // Sleep is workaround for running test on Safari
	.inputStart('04/01/2050 22:52')
	.inputEnd('04/02/2050 18:29')
	.inputIntervalTime('10')
	.inputIntervalUnit('Hour(s)')
	.clickRepeatEnable()
	
'Verify user can work with advanced settings'
Page.nav(NewUIScheduleTestRunPopUp)
	.clickAdvancedSettings()
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
	
'Verify user can cancel'
Page.nav(NewUIScheduleTestRunPopUp)
	.sleep(1) // Sleep is workaround for running test on Safari
	.clickKatalonCmdEnvDropdown()
	.sleep(1) // Sleep is workaround for running test on Safari
	.clickMoreOptions()
	.clickCancelEnv()

@com.kms.katalon.core.annotation.SetUp
def setUp() {
	tag = new DateTimeUtility().getCurrentDateTime()
}
		
