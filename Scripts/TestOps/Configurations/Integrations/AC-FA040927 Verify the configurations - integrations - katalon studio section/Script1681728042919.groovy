import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.configurations.IntegrationPage

Credential user = Page.nav(Credential)
	.getCredentials()
	.withRole(role)
	.inProject('Platform General')
	.getFirst()

'Login TO with User role'
String url = "${url}team/${user.teamId}/project/${user.projectId}/integrations?type=katalon_studio_integration"
Page.nav(SignInPage).navigateToUrl(url).login(user.email, user.pwd).clickSignIn()

'Verify the Configuration - Integrations Page - Katalon Studio section'
Page.nav(IntegrationPage)
	.verifyIntegrationTitle("Katalon Studio")
	.verifyLabelIsCorrect("Automatically Upload Reports")
	.verifyLabelIsCorrect("Manually Upload Reports")
	.verifyIntegrationTypeDefaultValue()
	.verifyIntegrationTypeIsClickable()
	.verifyIntergrationTypesList(role)






