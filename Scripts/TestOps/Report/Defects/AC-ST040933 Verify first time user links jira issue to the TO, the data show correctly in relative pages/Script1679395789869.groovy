import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import external.services.JiraService
import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.SignInPage
import katalon.enums.IssueTypes
import katalon.enums.ObjectTypes
import katalon.fw.lib.Page
import katalon.model.GitInfo
import katalon.services.ProjectService
import katalon.services.SearchService
import katalon.testops.report.DefectsPage
import katalon.testops.report.PlanningPage
import katalon.testops.report.ReportPage
import katalon.testops.report.RequirementPage
import katalon.testops.services.ExternalIssueService
import katalon.testops.services.GitService
import katalon.testops.services.IntegrationService
import katalon.testops.services.JUnitReportService
import katalon.utility.DateTimeUtility

'Pre-condition: The Admin creates new project'
projectId = Page.nav(ProjectService).createProject(GlobalVariable.currentTestCaseName, GlobalVariable.turingAndGryffindoorTeamId)
								 	.verifyStatusCode(200).parseResponseBodyToJsonObject().id

'Pre-condition: Setup git repo'
Page.nav(GitService).createGit(new GitInfo().createGitInfoData(projectId))

'Pre-condtion: Create new jira issue and integrate TO to Jira'
Page.nav(IntegrationService).setUpJira(projectId)
String issueName = String.format("%s%s", IssueTypes.BUG, new DateTimeUtility().getCurrentDateTime("ddMMyy"))
String issueKey = Page.nav(JiraService)
						.createIssue(GlobalVariable.externalProjectId, IssueTypes.BUG.getValue(), issueName, "BUG")
						.parseResponseBodyToJsonObject().key

'Pre-condition: Add jira tickets: Story, Epic, Task to test case'
Number testCaseId = Page.nav(SearchService)
						.searchTestCaseByName(testCaseName, projectId)
						.parseResponseBodyToJsonObject().content[0].id
						
Page.nav(ExternalIssueService)
	.linkExternalIssue(projectId, epicKey, ObjectTypes.TEST_CASE, testCaseId)
	.linkExternalIssue(projectId, storyKey, ObjectTypes.TEST_CASE, testCaseId)
	.linkExternalIssue(projectId, taskKey, ObjectTypes.TEST_CASE, testCaseId)

'Pre-condition: Upload JUnit test run report'
def uploadBody = Page.nav(JUnitReportService).getSignedUrlFromAWS(projectId).parseResponseBodyToJsonObject()[0]
Page.nav(JUnitReportService).putReportToAWS(uploadBody.uploadUrl, filePath)
							.uploadJUnitReport(String.valueOf(projectId), "", "", "true", "JUnitReport.xml", uploadBody.path)

'Pre-condition: Add Jira defect to test result'
def searchTestRun = Page.nav(SearchService)
						.searchTestRunByName(testRunName, projectId)
						.parseResponseBodyToJsonObject().content[0]
if(searchTestRun ==  null) {
	long current = 0
	long end = 35
	while (current < end && searchTestRun == null ) {
		WebUI.delay(5)
		searchTestRun = Page.nav(SearchService)
			.searchTestRunByName(testRunName, projectId)
			.parseResponseBodyToJsonObject().content[0]
		current = current + 5
	}
}

Number testRunId = searchTestRun.id

Number testResultId = Page.nav(SearchService)
						.searchTestResultsOfTestRun(testRunId, projectId)
						.parseResponseBodyToJsonObject().content[0].id
						
Page.nav(ExternalIssueService).linkExternalIssue(projectId, issueKey, ObjectTypes.EXECUTION_TEST_RESULT, testResultId)

'Admin login to TO success'
String url = "${GlobalVariable.testOpsUrl}team/${GlobalVariable.turingAndGryffindoorTeamId}/project/${projectId}"
Page.nav(SignInPage).navigateToUrl(url).login(email, password).clickSignIn()

'Select the project and go to Report > Requirement page'
Page.nav(HeaderBar).clickReports()
Page.nav(ReportPage).navigateToRequirementsPage()

'Verify: Traceability Matrix shows external issue'
Page.nav(RequirementPage).clickTraceabilityMatrixTab()
	.verifyLinkedExternalIssueIsShown(epicKey)
	.verifyLinkedExternalIssueIsShown(storyKey)
	.verifyLinkedExternalIssueIsShown(taskKey)

'Verify: The Defect page shows external issue'
Page.nav(ReportPage).navigateDefects()
Page.nav(DefectsPage).verifyExternalIssueIsShown(issueName)

'Verify: The Planning - Requirement page shows external issue'
Page.nav(HeaderBar).clickPlanning()
Page.nav(PlanningPage).clickRequirementPage()
Page.nav(RequirementPage).clickTraceabilityMatrixTab()
	.verifyLinkedExternalIssueIsShown(epicKey)
	.verifyLinkedExternalIssueIsShown(storyKey)
	.verifyLinkedExternalIssueIsShown(taskKey)
