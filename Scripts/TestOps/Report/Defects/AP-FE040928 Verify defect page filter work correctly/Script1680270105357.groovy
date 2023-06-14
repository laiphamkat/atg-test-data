import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import external.services.JiraService as JiraService
import internal.GlobalVariable as GlobalVariable
import katalon.common.HeaderBar as HeaderBar
import katalon.common.SignInPage as SignInPage
import katalon.enums.IssueTypes as IssueTypes
import katalon.enums.ObjectTypes as ObjectTypes
import katalon.fw.lib.Page as Page
import katalon.services.ProjectService as ProjectService
import katalon.services.SearchService as SearchService
import katalon.testops.report.ReportPage as ReportPage
import katalon.testops.services.ExternalIssueService as ExternalIssueService
import katalon.testops.services.JUnitReportService as JUnitReportService
import katalon.utility.DateTimeUtility as DateTimeUtility

KeywordUtil.markFailedAndStop('Please execute this test manually')

'Pre-condition: The Admin creates new project'
String projectName = String.format('Auto %s %s', GlobalVariable.currentTestCaseName, new DateTimeUtility().getCurrentDateTime())

projectId = Page.nav(ProjectService)
				.createProject(projectName, GlobalVariable.turingAndGryffindoorTeamId).verifyStatusCode(200)
				.parseResponseBodyToJsonObject().id

'Pre-condition: Create issues in Jira project'
List<Object> jiraIssueList = Page.nav(JiraService).createIssues()

'Pre-condition: Upload JUnit test run report'
def uploadBody = Page.nav(JUnitReportService).getSignedUrlFromAWS(projectId).parseResponseBodyToJsonObject()[0]

Page.nav(JUnitReportService)
	.putReportToAWS(uploadBody.uploadUrl, filePath)
	.uploadJUnitReport(String.valueOf(projectId), '', '', 'true', 'JUnitReport.xml', uploadBody.path)

'Pre-condition: Add Jira defect to test result'
def searchTestRun = Page.nav(SearchService).searchTestRunWithWaiting(testRunName, projectId)

Number testRunId = searchTestRun.id

Number testResultId = Page.nav(SearchService)
							.searchTestResultsOfTestRun(testRunId, projectId)
							.parseResponseBodyToJsonObject().content[0].id

for (Object issue : jiraIssueList) {
    Page.nav(ExternalIssueService).linkExternalIssue(projectId, issue.key, ObjectTypes.EXECUTION_TEST_RESULT, testResultId)
}

'1. Login to TO with user role'
String url = "$GlobalVariable.testOpsUrlteam/$GlobalVariable.turingAndGryffindoorTeamId/project/$projectId"

Page.nav(SignInPage).navigateToUrl(url).login(email, password).clickSignIn()

'2. Go to Reports > Defects'
Page.nav(HeaderBar).clickReports()

Page.nav(ReportPage).navigateDefects()

'3. Filter with time range'

'Verify show ticket has been created in this time range'

'4. Filter with status'

'Verify show ticket with filter status'

'5. filter with priority'

'Verify show ticket with filter priority'

'6. filter with release '

'Verify show ticket with filter release'

'7. filter with Assignee'

'Verify show ticket with filter assignee'

'8. Check  "No result" screen'

'9. Click reset button'

'Verify all options reverse to Default value'