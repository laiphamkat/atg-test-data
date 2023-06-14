import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.ProfilePopup
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

'User click to Profile icon'
Page.nav(HeaderBar).clickAvatar()

'Verify Avatar icon'
Page.nav(ProfilePopup)
.verifyAvatarIconDisplayed()

'Verify email'
Page.nav(ProfilePopup)
.verifyEmailDisplayed(user.email)

'Verify View Profile button display and clickable'
Page.nav(ProfilePopup)
.verifyViewProfileDisplayed()
.verifyViewProfileClickable()

'Verify User settings display and clickable'
Page.nav(ProfilePopup)
.verifyUserSettingsDisplayed()
.verifyUserSettingsClickable()

'Verify Documentation display and clickable'
Page.nav(ProfilePopup)
.verifyDocumentationDisplayed()
.verifyDocumentationClickable()

'Verify Release Notes display and clickable'
Page.nav(ProfilePopup)
	.verifyReleaseNotesDisplayed()
	.verifyReleaseNotesClickable()

'Verify Community Portal display and clickable'
Page.nav(ProfilePopup)
	.verifyCommunityPortalDisplayed()
	.verifyCommunityPortalClickable()

'Verify Submit a Support Case display and clickable'
Page.nav(ProfilePopup)
	.verifySupportCaseDisplayed()
	.verifySupportCaseClickable()

'Verify Switch Account display and clickable'
Page.nav(ProfilePopup)
	.verifySwitchAccountDisplayed()
	.verifySwitchAccountClickable()

'Verify Sign Out display and clickable'
Page.nav(ProfilePopup)
	.verifySignOutDisplayed()
	.verifySignOutClickable()

   
   
   







