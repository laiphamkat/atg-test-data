import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.visualtesting.VisualBaselineCollectionDetailPage
import katalon.testops.visualtesting.VisualBaselineCollectionPage
import katalon.testops.visualtesting.VisualTestRunPage

'Pre-condition: Find a user to log in'
Credential user = Page.nav(Credential)
	.getCredentials()
	.withRole(role)
	.inProject('AI Normal Package Default')
	.getFirst()

'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential(user.email, user.pwd).clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User navigates to Visual Testing > Visual Test Runs tab'
Page.nav(VisualTestRunPage).navigateTo(user.teamId, user.projectId)

'Verify Visual Test Run tab is acvited and Visual Test Runs title is displayed, Pro icon is not displayed'
Page.nav(VisualTestRunPage).verifyVisualTestRunsTabIsActived()
						   .verifyVisualTestRunTitleDisplayed()
						   .verifyProIconIsNotsDisplayed()

'Verify welcome page are displayed'
Page.nav(VisualTestRunPage).verifyVisualTestingWelcomeTextIsDisplayed()

'Click to Set up Visual Testing button and verify setup document tab is navigated to'
Page.nav(VisualTestRunPage).clickSetupVisualTestingButton()
						   .switchToWindowTitle("Use TestOps Visual Testing | Katalon Docs")
						   .closeCurrentWindow()
						   .switchToWindowTitle("Project ${user.projectName} - Visual Testing - Katalon TestOps")
						   
'Click to Learn more button and verify setup document tab is navigated to'
Page.nav(VisualTestRunPage).clickLearnMoreButton()
						   .switchToWindowTitle("Visual Testing overview | Katalon Docs")
						   .closeCurrentWindow()
						   .switchToWindowTitle("Project ${user.projectName} - Visual Testing - Katalon TestOps")
						   
'Click to Visual Baseline Collections tab'
Page.nav(VisualTestRunPage).clickVisualBaselineCollectionsTab()
			  
	
'Verify Visual Baseline Collection tab is active and Visual Baseline Collection title is displayed, baseline collection button is disabled, pro icon is not displayed'
Page.nav(VisualBaselineCollectionPage).verifyVisualBaselineCollectionsTabIsActived()
									  .verifyVisualBaselineCollectionsTitleDisplayed()
									  .verifyCreateBaselineCollectionButtionIsDisabled()
									  .verifyProIconIsNotDisplayed()
	
'Verify baseline collection table is displayed with full collumn, and there is no row in table'
Page.nav(VisualBaselineCollectionPage).verifyVBCTableHeaderDisplayed()
                                      .verifyVBCTableIsEmpty()
									  