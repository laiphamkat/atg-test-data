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

'Login to TO and navigate to Report - Test Cases - flakiness-report '
String url = "${url}team/${user.teamId}/project/${user.projectId}/test-reports/test-maintenance/flakiness-report"
Page.nav(SignInPage).navigateToUrl(url).login().clickSignIn()

'Verify the UI of Report - Test Cases - flakiness-report page'
Page.nav(TestCasesPage)
	.verifyTheTableTitleIsVisible('Flaky')
	.verifyTheAlertIsVisible('The most unreliable test cases.')
	.verifyCollapseButtonIsClickable()
	.verifyTableColumnNameIsVisible("Name")
	.verifyTableColumnNameIsVisible("Maintainer")
	.verifyTableColumnNameIsVisible("History (Old → New)")
	.verifyTableColumnNameIsVisible("Flakiness (%)")
	.verifyPaginationIsVisible()
	.verifyTotalTestCasesIsVisible()