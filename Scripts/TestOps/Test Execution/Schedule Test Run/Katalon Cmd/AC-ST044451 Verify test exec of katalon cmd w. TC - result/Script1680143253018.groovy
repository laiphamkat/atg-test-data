import internal.GlobalVariable
import katalon.common.HomePage
import katalon.common.SeedingData
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.testops.testexecution.TestRunsPage

'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential().clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User navigates to the test execution url saved in Google Sheet'
Page.nav(HomePage).navigateToUrl(url)

'Verify the test run job information'
Page.nav(TestRunsPage)
	.verifyTestSuiteName(1, testSuitePath.split('/').last())
	.verifyProfile(1, 'default')
	.verifyTotal(1, total)
	.verifyNumberOfPassed(1, numberOfPassed)
	.verifyNumberOfFailed(1, numberOfFailed)
	.verifyNumberOfError(1, numberOfError)
	.verifyNumberOfIncomplete(1, numberOfIncomplete)
	.verifyNumberOfSkipped(1, numberOfSkipped)

@com.kms.katalon.core.annotation.SetUp
def setUp() {
	'Get the test runs url from Google Sheet'
	url = SeedingData.getTestExecURLFromGSheet('TEST-EXEC-KATA-CMD-1')
}
	
	