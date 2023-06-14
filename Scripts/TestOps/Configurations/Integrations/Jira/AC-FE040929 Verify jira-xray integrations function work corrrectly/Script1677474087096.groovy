import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.model.GitInfo
import katalon.model.IntegrationProject
import katalon.services.ProjectService
import katalon.testops.configurations.ConfigurationPage
import katalon.testops.configurations.IntegrationPage
import katalon.testops.services.GitService
import katalon.utility.DateTimeUtility

'Pre-condition: The Admin creates new project'
String projectName = String.format('Auto %s %s', GlobalVariable.currentTestCaseName, new DateTimeUtility().getCurrentDateTime())
projectId = Page.nav(ProjectService).createProject(projectName, GlobalVariable.turingAndGryffindoorTeamId)
								 	.verifyStatusCode(200).parseResponseBodyToJsonObject().id
									 
'Pre-condition: Setup git repo'
Page.nav(GitService).createGit(new GitInfo().createGitInfoData(projectId))

'Login TO with Admin role'
String url = "${GlobalVariable.testOpsUrl}team/${GlobalVariable.turingAndGryffindoorTeamId}/project/${projectId}"
Page.nav(SignInPage).navigateToUrl(url).login(email, password).clickSignIn()

'Go to Configurations page'
Page.nav(HeaderBar).clickConfigurations()

'Click on Integrations tab'
Page.nav(ConfigurationPage).selectIntegrationTab()

'Select Jira/Xray at Intergrations dropdown'
Page.nav(IntegrationPage)
	.selectIntegration("Jira/Xray")
	.verifyCreateWebhookBtnIsDisable()

'At Set up Jira step, input: url, Username, Password'
IntegrationProject integrationProject = new IntegrationProject(projectId)
Page.nav(IntegrationPage)
	.inputUrl(integrationProject.serverUrl)
	.inputUsername(integrationProject.username)
	.inputPassword(integrationProject.password)
	.clickTestConnectionBtn()
	.verifyCreateWebhookBtnIsDisable()
	
'Verify: Click Save button, enable Create Webhook button'
Page.nav(IntegrationPage)
	.selectProject('Tuyen test')
	.clickSaveBtn()
	.verifyCreateWebhookBtnIsEnable()

'Verify: Click Back button, enable Setup Jira and disable Create Webhook button'
Page.nav(IntegrationPage)
	.clickBackBtn()
	.verifyUrlTextBoxIsShown()
	.verifyCreateWebhookBtnIsEnable()
	
'Verify: Finish link project, create Webhook button is enable'
Page.nav(IntegrationPage)
	.clickTestConnectionBtn()
	.clickSaveBtn()
	.verifyCreateWebhookBtnIsEnable()

'Verify: Deactive Jira, disable Setup Jira, Link Project and Webhook function'
Page.nav(IntegrationPage)
	.clickActiveButton()
	.verifyUrlTextBoxIsHidden()
	.verifySaveBtnIsHidden()
	.verifyCreateWebhookBtnIsHidden()
	

						
