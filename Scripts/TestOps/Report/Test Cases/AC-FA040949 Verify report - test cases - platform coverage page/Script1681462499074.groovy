import internal.GlobalVariable
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.report.TestCasesPage

Credential user = Page.nav(Credential)
						.getCredentials()
						.withRole(role)
						.inProject(GlobalVariable.platformGeneralProject)
						.getFirst()

'Login to TO and navigate to Report - Test Cases - platform-statistics'
String url = "${url}team/${user.teamId}/project/${user.projectId}/test-reports/test-maintenance/platform-statistics"
Page.nav(SignInPage).navigateToUrl(url).login().clickSignIn()

'Verify the UI of Report - Test Cases - platform-statistics page'
Page.nav(TestCasesPage)
	.verifyTheTableTitleIsVisible('Platform Coverage')
	.verifyTheAlertIsVisible('Test case quality by OS and browser-basis.')
	.verifyFilterLabelIsVisible("Last 2 months")
	.verifyCollapseButtonIsClickable()
	.verifyTableColumnNameIsVisible("Test Cases")
	.verifyPaginationIsVisible()
	.verifyTotalTestCasesIsVisible()
	.verifyPassedFailedLabelIsVisible()