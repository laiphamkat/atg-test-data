import internal.GlobalVariable
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.dashboard.DashboardPage

Credential user = Page.nav(Credential)
						.getCredentials()
						.withRole(role)
						.inOrg('Katalon on Katalon')
						.getFirst()

'1. User login to TO with roles (User, Admin, Owner)'
String url = "${GlobalVariable.testOpsUrl}team/${user.teamId}/project/${user.projectId}"
Page.nav(SignInPage).navigateToUrl(url).login(user.email, user.pwd).clickSignIn()

'2. Verify the onboarding section is not shown on Dashboard page.'
Page.nav(DashboardPage).verifyOnboardingSectionIsNotDisplayed()
