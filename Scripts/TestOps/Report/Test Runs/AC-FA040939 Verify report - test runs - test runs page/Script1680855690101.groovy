import internal.GlobalVariable
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.report.TestRunsPage

Credential user = Page.nav(Credential)
						.getCredentials()
						.withRole(role)
						.inProject(GlobalVariable.platformGeneralProject)
						.getFirst()
'Login to TO and navigate to Report - Test Runs - Test Runs page'
String url = "${url}team/${user.teamId}/project/${user.projectId}/executions"
Page.nav(SignInPage).navigateToUrl(url).login().clickSignIn()

'Verify the UI of Report - Test Runs - Test Runs page'
Page.nav(TestRunsPage)
	.verifyUploadReportButtonIsEnable()
	.verifySearchStringIsVisible()
	.verifyTimeRangeClickable()
	.verifyReleaseFilterClickable()
	.verifyProfileFilterClickable()
	.verifyCustomFieldFilterClickable()
	.verifyTagFilterClickable()
	.verifyAddMoreButtonClickable()
	.verifyChartIsVisible()
	.verifyBoardIsVisible()