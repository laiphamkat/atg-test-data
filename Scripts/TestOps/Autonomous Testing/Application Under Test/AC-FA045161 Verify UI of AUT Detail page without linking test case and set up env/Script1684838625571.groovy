import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SettingPopup
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.autonomous.AppicationUnderTestDetailPage
import katalon.testops.autonomous.ApplicationUnderTestPage

'Pre-condition: Find a user to log in'
Credential user = Page.nav(Credential)
	.getCredentials()
	.withRole(role)
	.inProject('AI Normal Package')
	.getFirst()

'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential(user.email, user.pwd).clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()


'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential(user.email, user.pwd).clickSignIn()
Page.nav(HomePage).navigateToOrgHome(user.orgId)

'User click to Setting icon > Click Autonomous Testing'
Page.nav(HeaderBar).clickSettingIcon()
Page.nav(SettingPopup).clickAutonomousTesting()

'Verify Application Under Test empty listing page displayed with full information'
Page.nav(ApplicationUnderTestPage).waitForAUTListingPageLoaded()
								  .clickToAUTIdOfAUT(autName)

'Verify AUT Detail Name, domains table is empty'
Page.nav(AppicationUnderTestDetailPage).verifyAUTNameDisplayed(autName)
									   .verifyAUTDescriptionDisplayed(autDescription)
									   .verifyApplicationDomainTableHeader()
									   .verifyApplicationDomainsTableIsEmpty()
									   
'Verify Installation Steps for Katalon AI section'
Page.nav(AppicationUnderTestDetailPage).verifyInstallationStepsTitleDisplays()
									   .verifyInstallationStepsDescriptionsDisplays()
									   .clickToLearnMore()
									   .switchToWindowTitle("Autonomous test generation with Katalon AI | Katalon Docs")
									   .closeCurrentWindow()
									   .switchToWindowTitle("Autonomous Testing - Katalon Platform")
																   
'Verify Active Traffic Agent step section with full info'
Page.nav(AppicationUnderTestDetailPage).verifyInstallationStepsTitleDisplays()
									   .verifyActiveTraficAgentTitleDisplays()
									   .verifyCodeSnippeIsDisplayed()
									   .clickCopyCodeSnippet()
									   
'Verify Add Test Environment() step section with full information'
Page.nav(AppicationUnderTestDetailPage).verifyAddTestEnvtTitleDisplays()
									   .verifyEnvTableHeader()
									   .verifyEnvTableIsEmpty()
									   .clickAddTestEnvButton()
									   .verifyTestEnvFormTitle()
									   .verifySetDefaultEnvIsCheckedAndDisabled()
									   .clickCancelAddEnv()
									   
'Verify Link Project step section with full information'
Page.nav(AppicationUnderTestDetailPage).verifyAddTestEnvtTitleDisplays()
										.verifyLinkProjectTitleIsDisplayed()
										.verifyLinkProjectTableHeader()
										.verifyLinkProjectTableIsEmpty()
										.clickLinkProject()
										.verifyLinkProjectFormTitleIsDisplayed()
										.verifyProjectDropdownIsDisplayed()
										.verifyScriptRepoDropdownIsDisplayed()
										.verifySampleScriptDescriptionDisplayed()
										.verifySaveLinkProjectIsDisabled()
										.clickCancelLinkProject()