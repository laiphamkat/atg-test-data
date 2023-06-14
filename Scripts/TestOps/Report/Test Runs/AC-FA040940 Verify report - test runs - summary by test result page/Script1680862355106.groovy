import internal.GlobalVariable
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.report.SummaryByTestResultPage

Credential user = Page.nav(Credential)
						.getCredentials()
						.withRole(role)
						.inProject(GlobalVariable.platformGeneralProject)
						.getFirst()

'Login to TO and navigate to Report - Test Runs - Summary by Test Result page'
String url = "${url}team/${user.teamId}/project/${user.projectId}/test-reports/test-execution/test-performance"
Page.nav(SignInPage).navigateToUrl(url).login().clickSignIn()

'Verify the UI of Report - Test Runs - Summary by Test Result page'
Page.nav(SummaryByTestResultPage)
	.verifyUploadReportButtonIsEnable()
	.verifyRefreshButtonIsEnable()
	.verifyCollapseButtonIsEnable()
	.verifySearchInputIsEnable()
	.verifyProfileFilterIsVisible()
	.verifyTestSuiteFilterIsVisible()
	.verifyTestSuiteCollectionFilterIsVisible()