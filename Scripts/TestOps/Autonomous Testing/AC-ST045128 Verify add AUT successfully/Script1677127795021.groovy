import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.SettingPopup
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.testops.autonomous.AppicationUnderTestDetailPage
import katalon.testops.autonomous.ApplicationUnderTestPage

// **TODO : Will implement delete AUT before start test case when API or DB is ready

'Login to TO as admin account'
Page.nav(SignInPage).enterCredential(GlobalVariable.owner_mail, GlobalVariable.owner_pass).clickSignIn()

'Navigate to Katalon on Katalon Org'
Page.nav(HeaderBar).clickOrganization()
				   .selectOrg(GlobalVariable.defaultOrgName)

'Go to Autonomous Testing Page > Application Under Test'
Page.nav(HeaderBar).clickSettingIcon()
Page.nav(SettingPopup).clickAutonomousTesting()

'Open Add Application Under Test popup form'
Page.nav(ApplicationUnderTestPage).clickAddAUT()

'Add an Application Under Test'
Page.nav(ApplicationUnderTestPage).inputName(autName)
								  .inputTrustedDomains(lstTrustedDomains)
								  .inputDescription(autDesc)
								  .clickAdd()

'Verify Added AUT succesffuly'
Page.nav(AppicationUnderTestDetailPage).verifyAUTNameIsDisplayed(autName)
									   .verifyTestOpsDescriptionIsDisplayed()
									   .verifyActiveTrafficAgentTitleIsDisplayed()
									   .verifyATADescriptionIsDisplayed()
									   .veryCodeSnippeIsDisplayed()

'Copy Code Snippet'
Page.nav(AppicationUnderTestDetailPage).clickCopyCodeSnippet()

'Verify Code Snippet is coppied succesfully'
Page.nav(AppicationUnderTestDetailPage).verifyCodeSnippetIsCoppied()

