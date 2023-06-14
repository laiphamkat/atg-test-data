import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.planning.CreateReleasePage
import katalon.testops.planning.ReleasesPage
import katalon.testops.report.TestRunDetailPage
import katalon.testops.testexecution.NewUIScheduleTestRunPopUp
import katalon.testops.testexecution.TestRunCalendarPage
import katalon.testops.testexecution.TestRunListPage

'Pre-condition: Find a user to log in'
Credential user = Page.nav(Credential)
	.getCredentials()
	.withRole(role)
	.inProject('Platform General')
	.getFirst()

'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential(user.email, user.pwd).clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User navigates to Planning > Release page'
Page.nav(ReleasesPage).navigateTo(user.teamId, user.projectId)

'Verify Releases title is displayed'
Page.nav(ReleasesPage).verifyReleasesTitleDisplayed()

'Verify status bar and status chart are displayed'
Page.nav(ReleasesPage).verifyStatusBarDisplayed()
					  .verifyStatusChartDisplayed()
					  
'Verify can click on all the filters in the page'
Page.nav(ReleasesPage).verifySearchInputDisplayed()
					  .clickSortFilter()
                      .clickReleaseFilter()
					  .clickStatusFilter()
					  .clickStartDateFilter()
					  .clickReleaseDateFilter()
					  .clickBuildFilter()			  
	
'Verify release tabled displayed with full columns: Status, Name, Total Duration, Test Runs, Test Cases, Start Date, Release Date'
Page.nav(ReleasesPage).verifyReleaseTableDisplayed()
	
'Users click to Create Release button'
Page.nav(ReleasesPage).clickCreateReleaseButton()
	
'Verify redirected Create Release page'
Page.nav(CreateReleasePage).verifyPageDisplayed()

'Verify can click on all the fields in the page and create button is clicabled'
Page.nav(CreateReleasePage).inputName('Release Name sample')
						   .inputStartDate('04/21/2023')
						   .inputReleaseDate('06/21/2023')
						   .inputDescription('Release Description sample')
						   .verifyCreateButtonIsClickable()
	
