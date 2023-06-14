import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SettingPopup
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.autonomous.ApplicationUnderTestPage

'Pre-condition: Find a user to log in'
Credential user = Page.nav(Credential)
	.getCredentials()
	.withRole(role)
	.inProject('AI Normal Package')
	.getFirst()

'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential(user.email, user.pwd).clickSignIn()
Page.nav(HomePage).navigateToOrgHome(user.orgId)

'User click to Setting icon > Click Autonomous Testing'
Page.nav(HeaderBar).clickSettingIcon()
Page.nav(SettingPopup).clickAutonomousTesting()

'Verify Application Under Test empty listing page displayed with full information'
Page.nav(ApplicationUnderTestPage).verifyAutonomousTestingTitleIsDisplayed()
                                  .verifyApplicationTestTabIsDisplayed()
								  .hoverOnInfomrationIcon()
								  .verifyTooltipTestGenDisplayed()
								  .verifyEmptyAUTListPageIsDisplayed()

'Click to Add Application Under Test button > Verify Add form is displayed'
Page.nav(ApplicationUnderTestPage).clickAddAUTtBtnOnEmpty()
								  .verifyAddAUTFormTitle()
								  .verifyAddButtonIsDisabled()
								  .inputName('Sample AUT')
								  .inputApplicationDomains(["env1.vn, env2.vn"])
								  .inputDescription("Sample Description")
								  .verifyAddButtonIsClickabled()
								  .clickCancel()
