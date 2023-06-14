import internal.GlobalVariable
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.report.TestRunCoveragePage

Credential user = Page.nav(Credential)
						.getCredentials()
						.withRole(role)
						.inProject(GlobalVariable.platformGeneralProject)
						.getFirst()


'Login to TO and navigate to Report - Test Runs - Failed Test Result Page'
String url = "${GlobalVariable.testOpsUrl}team/${user.teamId}/project/${user.projectId}/test-reports/test-design/requirement-test-run-coverage"
Page.nav(SignInPage).navigateToUrl(url).login().clickSignIn()

'Verify the UI of Report - Test Runs - Failed Test Result Page'
Page.nav(TestRunCoveragePage)
	.verifyNoticeLabelIsVisible()
	.verifyResultTableIsVisible()
	.verifyPassedFailedLabelIsVisible()
	.verifyRefreshButtonIsEnable()