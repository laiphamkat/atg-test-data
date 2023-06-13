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

'Login to TO and navigate to Report - Test Cases - Stale page'
String url = "${url}team/${user.teamId}/project/${user.projectId}/test-reports/test-maintenance/stale"
Page.nav(SignInPage).navigateToUrl(url).login().clickSignIn()

'Verify the UI of Report - Test Cases - Stale page'
Page.nav(TestCasesPage)
	.verifyFilterLabelIsVisible('Path: All')
	.verifyFilterLabelIsVisible('Status: All')
	.verifyFilterLabelIsVisible('Type: All')
	.verifyFilterLabelIsVisible('Last Run: All')
	.verifyFilterLabelIsVisible('Maintainer: All')
	.verifyFilterLabelIsVisible('Custom Fields')
	.verifyFilterLabelIsVisible('Tags')
	.verifySortDefaultFilterIsVisible("Last Test Run (Ascending)")
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