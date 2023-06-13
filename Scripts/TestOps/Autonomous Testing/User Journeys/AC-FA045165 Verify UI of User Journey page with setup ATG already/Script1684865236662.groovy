import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.planning.UserJourneysPage
import katalon.testops.visualtesting.VisualTestRunDetailPage
import katalon.testops.visualtesting.VisualTestRunPage

'Pre-condition: Find a user to log in'
Credential user = Page.nav(Credential)
	.getCredentials()
	.withRole(role)
	.inProject('AI - TestGen Project')
	.getFirst()

'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential(user.email, user.pwd).clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User navigates to User Journeys'
Page.nav(UserJourneysPage).navigateTo(user.teamId, user.projectId)

'Verify User Journeys title, active status, application domains are displayed'
Page.nav(UserJourneysPage).waitForUserJourneyLoaded()
                          .verifyUserJourneysTitleIsDisplayed()
                          .verifyActiveStatusIsDisplayed()
						  .verifyDomainsTableIsDisplayed()
						  .verifyDomainsTableNotEmpty()
						  
'Verify User Journey Map section is displayed'
'Verify table'
Page.nav(UserJourneysPage).verifyUserJourneyMapTitleIsDisplayed()
						  .verifyUserJourneyTableIsDisplayed()
						  .verifyUserJourneyTableNotEmpty()
						  
'Click Generate Nre User Journey Map > Verify Select time period form is displayed'
Page.nav(UserJourneysPage).clickGenerateNewUserJourneyMap()
                          .verifyTimePeriodFormTitleIsDisplayed()
						  .verifyTimePeriodDescriptionIsDisplayed()
						  .selectLas7DaysOption()
						  .selectLas30DaysOption()
						  .selectLas90DaysOption()
						  .selectCustomOption()
						  .clickStartDate()
						  .verifyRangeCalendarIsDisplayed()
						  .clickEndDate()
						  .verifyRangeCalendarIsDisplayed()
						  .verifyGenerateButtonIsClickabled()
						  .clickCancelTimePeriodForm()
						  