import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.configurations.TestEnvironmentsPage
import katalon.testops.report.TestRunDetailPage
import katalon.testops.testexecution.ApplicationRepositoryPage
import katalon.testops.testexecution.NewUIScheduleTestRunPopUp
import katalon.testops.testexecution.TestRunCalendarPage
import katalon.testops.testexecution.TestRunListPage
import katalon.testops.testexecution.TestRunsPage

'Pre-condition: Find a user to log in'
Credential user = Page.nav(Credential)
	.getCredentials()
	.withRole(role)
	.getFirst()

'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential(user.email, user.pwd).clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User navigates to Test Run Calendar page'
Page.nav(TestRunCalendarPage).navigateTo(user.teamId, user.projectId)

'User clicks Test Run List tab'
Page.nav(HeaderBar).clickTestRunList()

'Verify can filter test run based on the git repo'
Page.nav(TestRunListPage)
	.clickScriptRepoFilter()
	.selectScriptRepo('cloudian-automation-1')
	.sleep(2) // Wait for the web page applies new changes
	.verifyTestRunsFilteredBy('cloudian-automation-1')
	
'Users click a test run name'
Page.nav(TestRunListPage)
	.clickTestRunName(1)
	
'Verify redirected Test Runs page, then go back'
Page.nav(TestRunsPage)
	.clickConfigurationTab()
	.back()
	.back()
	
'Users click the first test environment of the first test run'
Page.nav(TestRunListPage)
	.clickTestEnvironment(1, 1)
	
'Verify redirected Test Environments page, then go back'
Page.nav(TestEnvironmentsPage)
	.verifyPageDisplayed()
	.back()

'Users click a last run'
Page.nav(TestRunListPage)
	.clickLastRun(1)
	
'Verify redirected Test Run Detail page, then go back'
Page.nav(TestRunDetailPage)
	.verifyPageDisplayed()
	.back()
	
'Verify users can see and click on the execute button on the test run'
Page.nav(TestRunListPage)
	.verifyExecuteButtonDisplayed(1)
	.verifyExecuteButtonClickable(1)
// Do not really click the button here because it can create uncontrolled resource on TestCloud, there will be another test case to cover this
	
'Verify the filter textbox is displayed and still working'
Page.nav(TestRunListPage)
	.filterByName('This test run does not exist')
	.sleep(2) // Wait for the web page applies new changes
	.verifyNoTestRunsDisplayed()
	.clearFilter()
	
'Verify the sort filter is displayed and still working'
Page.nav(TestRunListPage)
	.clickFilterSort()
	.selectNameDescending()
	.sleep(2) // Wait for the web page applies new changes
	.verifyTestRunsOrderedByNameDescending()
	
'Verify the pagination is displayed and still working'
Page.nav(TestRunListPage)
	.verifyPageActive(1)
	.verifyPageNotActive(2)
	.clickPagination(2)
	.verifyPageNotActive(1)
	.verifyPageActive(2)
	.clickPreviousPage()
	.verifyPageActive(1)
	.verifyPageNotActive(2)
	.clickNextPage()
	.verifyPageNotActive(1)
	.verifyPageActive(2)

'Verify can open Schedule Test Run modal'
Page.nav(TestRunListPage).clickScheduleTestRun()
Page.nav(NewUIScheduleTestRunPopUp)
	.inputTestRunName('Fast Test')
	.closeScheduleDialog()

'User clicks Test Run Calendar tab'
Page.nav(HeaderBar).clickTestRunCalendar()

'Verify can click on all the filters in the page'
Page.nav(TestRunCalendarPage)
	.clickTestSuiteFilter()
	.setTestSuiteFilter('WA TS with 1 test passed')
	.selectTestSuite('WA TS with 1 test passed')
	.clickTSCFilter()
	.setTSCFilter('TSC with 6 passed WA TS')
	.selectTSC('TSC with 6 passed WA TS')
	.clickStatusFilter()
	.selectPassed()
	.clickStatusFilter()
	.selectFailed()
	.clickStartedFilter()
	.setWithinLast(100)
	.clickUpdate()
	.clickProfileFilter()
	.setProfileFilter('staging')
	.clickUpdate()
	.clickByFilter()
	.setUserFilter(GlobalVariable.owner_mail)
	.clickUser(GlobalVariable.owner_mail)
	.clickReleaseFilter()
	.setReleaseFilter('cloudian-automation-1 (v1.1.0)')
	.clickRelease('cloudian-automation-1 (v1.1.0)')
	.clearFilter()
	
'Verify can open Schedule Test Run modal'
Page.nav(TestRunCalendarPage).clickScheduleTestRun()
Page.nav(NewUIScheduleTestRunPopUp)
	.inputTestRunName('Fast Test')
	.closeScheduleDialog()
	
'Verify able to click on Month, Week, Day, Timeline, next, previous buttons'
Page.nav(TestRunCalendarPage)
	.clickMonth()
	.clickWeek()
	.clickDay()
	.clickTimeline()
	.clickPrevious()
	.clickNext()
	
'Verify timezone is displayed'
Page.nav(TestRunCalendarPage)
	.verifyTimeZoneDisplayed()
