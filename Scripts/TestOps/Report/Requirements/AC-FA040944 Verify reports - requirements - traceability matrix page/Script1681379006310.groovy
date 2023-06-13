import internal.GlobalVariable
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.report.TraceabilityMatrixPage

Credential user = Page.nav(Credential)
						.getCredentials()
						.withRole(role)
						.inProject(GlobalVariable.platformGeneralProject)
						.getFirst()

'Login to TO and navigate to Report - Test Runs - Failed Test Result Page'
String url = "${GlobalVariable.testOpsUrl}team/${user.teamId}/project/${user.projectId}/test-reports/test-design/traceability-report"
Page.nav(SignInPage).navigateToUrl(url).login().clickSignIn()

'Verify the UI of Report - Test Runs - Failed Test Result Page'
Page.nav(TraceabilityMatrixPage)
	.verifyNoticeLabelIsVisible()
	.verifyResultTableIsVisible()
	.verifyTableColumnIsVisible('Requirements')
	.verifyTableColumnIsVisible('Test Cases')
	.verifyTableColumnIsVisible('Test Results')
	.verifyTableColumnIsVisible('Defects')
	.verifyRefreshButtonIsEnable()