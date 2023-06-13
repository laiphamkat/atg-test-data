import internal.GlobalVariable
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.report.FailedTestResultPage

Credential user = Page.nav(Credential)
							.getCredentials()
							.withRole(role)
							.inProject(GlobalVariable.platformGeneralProject)
							.getFirst()

'Login to TO and navigate to Report - Test Runs - Failed Test Result Page'
String url = "${url}team/${user.teamId}/project/${user.projectId}/failed-test-result"
Page.nav(SignInPage).navigateToUrl(url).login().clickSignIn()

'Verify the UI of Report - Test Runs - Failed Test Result Page'
Page.nav(FailedTestResultPage)
	.verifyFailedTestResultLabelIsVisible()
	.verifyTop10FailuretLabelIsVisible()
	.verifyUploadReportButtonIsEnable()
	.verifyRefreshButtonIsEnable()
	
	
