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

'Login to TO and navigate to Report - Test Cases - slowest page'
String url = "${url}team/${user.teamId}/project/${user.projectId}/test-reports/test-maintenance/slowest"
Page.nav(SignInPage).navigateToUrl(url).login().clickSignIn()

'Verify the UI of Report - Test Cases - slowest page'
Page.nav(TestCasesPage)
	.verifyFilterLabelIsVisible('Status: All')
	.verifyFilterLabelIsVisible('Type: All')
	.verifyFilterLabelIsVisible('Last Run: All')
	.verifyFilterLabelIsVisible('Maintainer: All')
	.verifyFilterLabelIsVisible('Custom Fields')
	.verifyFilterLabelIsVisible('Tags')
	.verifySortDefaultFilterIsVisible("Average Duration (Descending)")
	.verifyCopyButtonIsVisible()
	.verifySearchBoxIsVisible()
	.verifyTableColumnNameIsVisible("Status")
	.verifyTableColumnNameIsVisible("Name")
	.verifyTableColumnNameIsVisible("Maintainer")
	.verifyTableColumnNameIsVisible("Assertions")
	.verifyTableColumnNameIsVisible("Last Test Run")
	.verifyTableColumnNameIsVisible("Average Duration")
	.verifyTableColumnNameIsVisible("Flakiness (%)")
	.verifyTableColumnNameIsVisible("Requirements")
	.verifyPaginationIsVisible()
	.verifyTotalTestCasesIsVisible()