import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.configurations.ScriptRepositoryPage
import katalon.testops.configurations.ScriptRepositoryDetailPage


'Pre-condition: Find a user to log in'
Credential user = Page.nav(Credential)
	.getCredentials()
	.withRole(role)
	.getFirst()

'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential(user.email, user.pwd).clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User select Org'
Page.nav(HeaderBar).clickOrganization()
Page.nav(HomePage).selectOrg(user.orgName)

'At Home page, user select Project'
Page.nav(HomePage).selectProject(user.projectName)

'Click on the Configurations menu'
Page.nav(HeaderBar).clickConfigurations()

'Verify Script Repositories page is displayed'
Page.nav(ScriptRepositoryPage).verifyScriptRepositoriesTitle()

'Make sure the “Connect Git Repository” button can click'
Page.nav(ScriptRepositoryPage).verifyConnectGitRepositoryButtonClickable()

'Make sure the filter by Type can click'
Page.nav(ScriptRepositoryPage).verifyFilterByTypeClickable()

'Make sure Filter by Name input text display'
Page.nav(ScriptRepositoryPage).verifyFilterByNameInputTextDisplayed()

'Verify script repo table ( name, description)'
Page.nav(ScriptRepositoryPage).verifyScriptRepoTableDisplayed()

'Make sure to redirect to the Script detail page by clicking any script name'
Page.nav(ScriptRepositoryPage).clickScriptRepoName()

'Verify Script Repository detail page is displayed'
	' + Label name'
	' + Description'
	' + Repository URL'
	' + Branch'
	' + Username'
	' + Test Suite Collection'
Page.nav(ScriptRepositoryDetailPage).verifyScriptRepositoryDetailPageDisplayed()
	
'Verify Schedule Test Run button and clickable'
Page.nav(ScriptRepositoryDetailPage).verifyScheduleTestRunButtonClickable()

'Verify Refresh Test Suite Collection displayed and clickabled'
Page.nav(ScriptRepositoryDetailPage).verifyRefreshTestSuiteCollectionButtonClickable()

'Verify Edit button is clickable'
Page.nav(ScriptRepositoryDetailPage).verifyEditButtonClickable()

'Verify Delete button is clickable'
Page.nav(ScriptRepositoryDetailPage).verifyDeleteButtonClickable()
	  
	  
	  
	  
	  
	  