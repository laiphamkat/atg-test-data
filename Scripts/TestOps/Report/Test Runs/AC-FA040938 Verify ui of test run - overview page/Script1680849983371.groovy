import internal.GlobalVariable
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.report.OverviewPage


Credential user = Page.nav(Credential)
						.getCredentials()
						.withRole(role)
						.inProject(GlobalVariable.platformGeneralProject)
						.getFirst()
'Login to TO and navigate to Report - Test Runs - Overview page'
String url = "${url}team/${user.teamId}/project/${user.projectId}/overview"
Page.nav(SignInPage).navigateToUrl(url).login().clickSignIn()

'Verify the UI of Report - Test Runs - Overview page'
Page.nav(OverviewPage)
	.verifyShowDataProfileIsVisible()
	.verifyExecutionTrendIsVisible()
	.verifyUploadReportButtonIsEnable()
	.verifyRefreshButtonIsEnable()
	.verifyProfileCoverageIsVisible()
	.verifyPlatformCoverageIsVisible()