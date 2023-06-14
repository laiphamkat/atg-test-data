import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.planning.UserJourneysPage
import katalon.testops.visualtesting.VisualTestRunDetailPage
import katalon.testops.visualtesting.VisualTestRunPage

'Pre-condition: Find a user to log in'
Credential user = Page.nav(Credential)
	.getCredentials()
	.withRole(role)
	.inProject('AI Normal Package')
	.getFirst()

'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential(user.email, user.pwd).clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User navigates to User Journeys'
Page.nav(UserJourneysPage).navigateTo(user.teamId, user.projectId)

'Verify Install and active the AI Agent page is loaded'
Page.nav(UserJourneysPage).verifyLoadingIconIsDisplayed()
                          .verifyInstalledAndActiveAIAgentNotificationIsDisplayed()
						  
'Verify Installation guideline steps are displayed'
Page.nav(UserJourneysPage).verifyInstallStepsGuidelineIsDisplayed()
                          .verifyInstallStepsGuidelineIconIsDisplayed()
						  
'Click learn more > Verify guideline document is navigated to'
Page.nav(UserJourneysPage).clickLearnMore()
                          .switchToWindowTitle("Autonomous test generation with Katalon AI | Katalon Docs")
						  .closeCurrentWindow()
						  .switchToWindowTitle("User Journeys - Katalon TestOps")