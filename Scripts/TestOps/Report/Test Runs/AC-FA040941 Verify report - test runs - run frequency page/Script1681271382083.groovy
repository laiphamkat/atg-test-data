import internal.GlobalVariable
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.report.RunFrequencyPage

Credential user = Page.nav(Credential)
						.getCredentials()
						.withRole(role)
						.inProject(GlobalVariable.platformGeneralProject)
						.getFirst()
						
'Login to TO and navigate to Report - Test Runs - Run Frequency Page'
String url = "${url}team/${user.teamId}/project/${user.projectId}/test-reports/test-execution/frequency-report"
Page.nav(SignInPage).navigateToUrl(url).login().clickSignIn()

'Verify the UI of Report - Test Runs - Run Frequency Page'
Page.nav(RunFrequencyPage)
	.verifyUploadReportButtonIsEnable()
	.verifyRunFrequencyLabelIsVisible()
	.verifyCollapseButtonIsEnable()
	.verifyResultTableIsVisable()
	.verifyNoticeLabelIsVisible()
	.verifyDateTimeIsVisible()
	.verifyPassedFailedLabelIsVisible()
	