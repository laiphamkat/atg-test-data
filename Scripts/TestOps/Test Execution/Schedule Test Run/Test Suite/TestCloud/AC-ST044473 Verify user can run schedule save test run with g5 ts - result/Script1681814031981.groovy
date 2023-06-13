import external.services.GSheetService
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.constants.BrowserVersion
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.report.TestRunDetailPage
import katalon.testops.testexecution.SessionDetailsPage
import katalon.testops.testexecution.TestRunListPage
import katalon.testops.testexecution.TestRunsPage

'Find a user to log in'
Credential user = Page.nav(Credential)
	.getCredentials()
	.getFirst()

'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential(user.email, user.pwd).clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User navigates to the test execution url saved in Google Sheet'
Page.nav(HomePage).navigateToUrl(url)

'Verify the test run job information'
Page.nav(TestRunsPage)
	.verifyTestSuiteName(1, 'test-suites/public/WA TS with 1 test passed')
	.verifyProfile(1, '')
	.verifyTotal(1, '2')
	.verifyNumberOfPassed(1, '2')
	.verifyNumberOfFailed(1, '0')
	.verifyNumberOfError(1, '0')
	.verifyNumberOfIncomplete(1, '0')
	.verifyNumberOfSkipped(1, '0')
	
'User clicks the test run name of the first row to go to the test run details page'
Page.nav(TestRunListPage).clickTestRunName(1)

'Verify all information is correct in the Test Run Details page'
Page.nav(TestRunDetailPage)
	.verifySessionPassed(1)
	.verifySessionPassed(2)
	.verifyTSStatusPassed(1)
	.verifyTSStatusPassed(2)
	.verifyTestSuitesInformation([
		[
			'TSName': 'WA TS with 1 test passed',
			'Profile': '',
			'OS': 'Linux',
			'Browser': "Chrome ${BrowserVersion.G5_CHROME_LINUX_LATEST_REPORT}",
			'Total': '1',
			'Passed': '1',
			'Failed': '0',
			'Error': '0',
			'Incomplete': '0',
			'Skipped': '0'
		],
		[
			'TSName': 'WA TS with 1 test passed',
			'Profile': '',
			'OS': 'Linux',
			'Browser': "Firefox ${BrowserVersion.G5_FIREFOX_LINUX_LATEST_REPORT}",
			'Total': '1',
			'Passed': '1',
			'Failed': '0',
			'Error': '0',
			'Incomplete': '0',
			'Skipped': '0'
		]
	])
	
'Go to the files tab to see the test artifacts are uploaded to TestOps'
Page.nav(TestRunDetailPage)
	.clickFilesTab()
	.verifyFilesExist(['suite-execution0.log', 'suite-execution0.log', 'test-execution0.log', 'test-execution0.log'])
	.back()
	
'Go the session details page to verify debug.log of Linux Chrome'
Page.nav(TestRunDetailPage).clickSessionId(1)
Page.nav(SessionDetailsPage)
	.verifyLogContains('> Run test case: "test-cases/public/WA TS with 1 test passed"')
	.verifyLogContains('Open browser')
	.verifyLogContains("Chrome ${BrowserVersion.G5_CHROME_LINUX_LATEST_REPORT}")
	.verifyLogContains('Closing browser...')
	.verifyLogContains('logger: Updated the execution progress successfully')
	.verifyLogContains('logger: Uploaded report successfully []')
	.back()
	
'Go the session details page to verify debug.log of Linux Firefox'
Page.nav(TestRunDetailPage).clickSessionId(2)
Page.nav(SessionDetailsPage)
	.verifyLogContains('> Run test case: "test-cases/public/WA TS with 1 test passed"')
	.verifyLogContains('Open browser')
	.verifyLogContains("Firefox ${BrowserVersion.G5_FIREFOX_LINUX_LATEST_REPORT}")
	.verifyLogContains('Closing browser...')
	.verifyLogContains('logger: Updated the execution progress successfully')
	.verifyLogContains('logger: Uploaded report successfully []')

@com.kms.katalon.core.annotation.SetUp
def setUp() {
	'Get the test runs url from Google Sheet'
	url = Page.nav(GSheetService).getTestExecURLFromGSheet('SCHEDULE-SAVE-RUN-G5-TS-1')
}