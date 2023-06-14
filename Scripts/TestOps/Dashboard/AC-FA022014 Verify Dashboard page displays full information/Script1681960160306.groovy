import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.dashboard.DashboardPage


'Pre-condition: Find a user to log in'
Credential user = Page.nav(Credential)
	.getCredentials()
	.withRole(role)
	.getFirst()

'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential(user.email, user.pwd).clickSignIn()
					.sleep(2) // Wait for the web page applies new changes
Page.nav(HomePage).waitUntilPageDisplayed()

'User select Org'
Page.nav(HeaderBar).clickOrganization()
Page.nav(HomePage).selectOrg(user.orgName)

'At Home page, user select Project'
Page.nav(HomePage).selectProject(user.projectName)

'Verify Navigate to the Dashboard page'
Page.nav(DashboardPage).verifyDashboardTitleDisplayed()

'Verify Test Activities chart should be displayed include Total test run and Execution Result chart'
Page.nav(DashboardPage)
	.verifyTestActivitiesTitleDisplayed()
	
'Verify The Release Readiness section should be displayed.'
Page.nav(DashboardPage)
	.verifyReleaseReadinessTitleDisplayed()
	
'Verify The Productivity chart should be displayed.'
Page.nav(DashboardPage)
	.verifyProductivityTitleDisplayed()

'Verify The Quality chart should be displayed.'
Page.nav(DashboardPage)
	.verifyQualityTitleDisplayed()

'Verify The Platform Coverage chart should be displayed.'
Page.nav(DashboardPage)
	.verifyPlatformCoverageTitleDisplayed()

'Verify The Requirement Coverage chart should be displayed.'
Page.nav(DashboardPage)
	.verifyRequirementCoverageTitleDisplayed()

'Verify The Local Test Environment should be displayed.'
Page.nav(DashboardPage)
	.verifyLocalTestEnvironmentTitleDisplayed()

'Verify The Refresh button should be displayed and the user able to click the Refresh button.'
Page.nav(DashboardPage)
	.verifyRefreshButtonDisplayed()
	.verifyRefreshButtonClickable()




