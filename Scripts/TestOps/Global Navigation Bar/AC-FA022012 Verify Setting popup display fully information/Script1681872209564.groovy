import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SettingPopup
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page


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

'User click to Setting icon'
Page.nav(HeaderBar).clickSettingIcon()

'If Role = Admin or Owner, verify TESTOPS ADMIN section:'
  ' + Product Utilization'
  ' + License Management'
  ' + User Management'
  ' + Payment Method'
  '	+ Subscription Management'
  ' + Organixation Management'
  '	+ Support Management'
if(role == "Owner") {
	Page.nav(SettingPopup)
		.verifyTestOpsAdminDisplayed()
		.verifyProductionUtilizationDisplayed()
		.verifyProductionUtilizationClickable()
		.verifyLicenseManagementDisplayed()
		.verifyLicenseManagementClickable()
		.verifyUserManagementDisplayed()
		.verifyUserManagementClickable()
		.verifyOrganizationManagementDisplayed()
		.verifyOrganizationManagementClickable()
		.verifySupportManagementDisplayed()
		.verifySuportManagementClickable()
}
  
'Verify TESTOPS SETTINGS section:'
   '+ Team Management'
   '+ Project Management'
   '+ Autonomous Testing'
   '+ Project Setting'
   '+ TestOps Homepages'
Page.nav(SettingPopup)
	.verifyTestOpsSettingsDisplayed()
	.verifyTeamManagementDisplayed()
	.verifyTeamManagementClickable()
	.verifyProjectManagementDisplayed()
	.verifyProjectManagementClickable()
	.verifyTestOpsHomepageDisplayed()
	.verifyTestOpsHomepageClickable()
	
if(role == "Owner") {
	Page.nav(SettingPopup)
		.verifyAutonomousTestingDisplayed()
		.verifyAutonomousTestingClickable()
}

if(role != "User") {
	Page.nav(SettingPopup)
		.verifyProjectSettingsDisplayed()
		.verifyProjectSettingsClickable()
}
   
   
   
   