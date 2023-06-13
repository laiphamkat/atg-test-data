import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.LeftNavBar
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.testops.integrations.IntegrationsPage

'Login into TestOps'
Page.nav(SignInPage).enterCredential()
					.doCheckRemberMe()
					.clickSignIn()
'Wait for the web page applies new changes'
Page.nav(HomePage).waitUntilPageDisplayed()
					
'Click on the Avatar > Click User settings menu'
Page.nav(HeaderBar).clickAvatar()
				   .clickUserSettings()

'Click on the Integrations from the left navbar'
Page.nav(LeftNavBar).clickIntegrations()

'Enter valid git Credential'
Page.nav(IntegrationsPage).enterGitCredential(username, accessToken)

'Click on the Connect button'
Page.nav(IntegrationsPage).clickConnect()

'Verify Connect Successful message "Connection succeeded! You have connected with Github account <username> successfully!" show correctly.'
'Verify username and PAT show correctly.'
Page.nav(IntegrationsPage).verifySuccessMessageIsDisplayed(successMessage)
						  .verifyUsernameIsDisplayed(username)
						  .verifyAccessTokenIsDisplayed(accessToken)