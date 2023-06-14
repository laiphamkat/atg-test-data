import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.visualtesting.VisualTestRunDetailPage
import katalon.testops.visualtesting.VisualTestRunPage

'Pre-condition: Find a user to log in'
Credential user = Page.nav(Credential)
	.getCredentials()
	.withRole(role)
	.inProject('AI Visual Test')
	.getFirst()

'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential(user.email, user.pwd).clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User navigates to Visual Testing > Visual Test Runs tab'
Page.nav(VisualTestRunPage).navigateTo(user.teamId, user.projectId)

'Verify Visual Test Run tab is acvited and Visual Test Runs title is displayed'
Page.nav(VisualTestRunPage).verifyVisualTestRunsTabIsActived()
						   .verifyVisualTestRunTitleDisplayed()
						   .verifyProIconIsDisplayed()
						   
'Verify Visual Test Run table is displayed with full collumn, and have data in table'
Page.nav(VisualTestRunPage).verifyVisualTestRunableHeaderDisplayed()
                           .verifyTestRunTableNotEmpty()
						   
'Click to more option at first row test run and verify Re-Import option is clickabled'
Page.nav(VisualTestRunPage).clickToMoreOptionAtRow(1)
                           .verifyReImportOptionIsClickabled(1)
						   
'Click to id of first row test run to go to test run detail page'
Page.nav(VisualTestRunPage).clickToTestRunIdAtRow(1)

'Verify Result tab is displayed with full information'
Page.nav(VisualTestRunDetailPage).verifyResultsTabIsActived()
                                 .verifyStatusBarDisplayed()
								 .verifyStatusChartDisplayed()
								 .verifyCheckpointsSectionTitleIsDisplayed()
								 .verifySearchInputDisplayed()
								 .clickStatusFilter()
								 .verifyStatusFilterListIsDisplayed()
								 .verifySaveToBaselineButtonIsDisabled()

'Click to Comments tab and verify comment page is displayed'
Page.nav(VisualTestRunDetailPage).clickToCommentsTab()
                                 .verifyCommentsTitleIsDisplayed()
								 .verifySubmitButtonIsDisabled()
								 .inputComment('Sample comment')

