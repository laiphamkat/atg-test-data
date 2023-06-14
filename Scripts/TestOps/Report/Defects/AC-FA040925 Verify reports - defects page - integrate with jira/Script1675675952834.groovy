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
					.inProject(GlobalVariable.platformGeneralProject)
					.getFirst()

'Admin login to TO success'
String url = "${GlobalVariable.testOpsUrl}team/${GlobalVariable.turingAndGryffindoorTeamId}/project/${user.projectId}"
Page.nav(SignInPage).navigateToUrl(url).login(user.email, user.pwd).clickSignIn()

'Select the project and navigate to Defects page'
Page.nav(HeaderBar).clickReports()
Page.nav(ReportPage).navigateDefects()

'Verify: The Defect page is shown correctly'
Page.nav(DefectsPage).verifyDefectTitleIsVisible()
					  .verifyRefreshBtnIsClickable()
					  .verifyDefectTableIsVisible()
					  .verifyChartSummaryIsVisible()
					  .verifyChartIsVisible()
					  .verifyTimeRangeFilterIsVisible()
					  .verifyStatusFilterIsVisible()
					  .verifyPriorityFilterIsVisible()
					  .verifyReleaseFilterIsVisible()
					  .verifyAssigneeFilterIsVisible()
					  .verifyChartTextItemIsVisible("Created")
					  .verifyChartTextItemIsVisible("Resolved")
					  .verifyTableColumnNameIsVisible("Title")
					  .verifyTableColumnNameIsVisible("Status")
					  .verifyTableColumnNameIsVisible("priority")
					  .verifyTableColumnNameIsVisible("Test Cases")
					  .verifyTableColumnNameIsVisible("Test Results")
					  .verifyTableColumnNameIsVisible("Requirements")
					  .verifyTableColumnNameIsVisible("Assignee")
					  .verifySortDefaultValueIsVisible("Created at (Descending)")
