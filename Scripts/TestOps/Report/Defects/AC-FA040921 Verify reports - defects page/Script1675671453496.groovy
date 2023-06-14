import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.report.DefectsPage
import katalon.testops.report.ReportPage

Credential user = Page.nav(Credential)
						.getCredentials()
						.withRole(role)
						.inOrg('KOK 2')
						.getFirst()

'Admin login to TO success'
String url = "${GlobalVariable.testOpsUrl}team/${user.teamId}/project/${user.projectId}"
Page.nav(SignInPage).navigateToUrl(url).login(user.email, user.pwd).clickSignIn()

'Select the project and navigate to Defects page'
Page.nav(HeaderBar).clickReports()
Page.nav(ReportPage).navigateDefects()

'Verify: The Defect page is shown correctly'
Page.nav(DefectsPage)
	.verifyDefectTitleIsVisible()
	.verifyDefectDemolIsVisible()
	.verifyDefectLabelIsVisible('Activate Jira integration')
	.verifyDefectLabelIsVisible("Activate and set up Jira integration with Katalon Platform in order to analyze Defects.")
	.verifyDefectLabelIsVisible("Link Jira test results")
	.verifyDefectLabelIsVisible("Link your Jira test results to provide meaningful data for Defect Report.")
