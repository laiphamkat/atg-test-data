package katalon.common

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class ProfilePopup extends BasePage<ProfilePopup> {

	public ProfilePopup verifyAvatarIconDisplayed() {
		WebUI.verifyElementVisible(css('#user > div > div.avatar'))
		return this
	}
	
	public ProfilePopup verifyEmailDisplayed(String email) {
		WebUI.verifyElementVisible(text(email))
		return this
	}

	public ProfilePopup verifyViewProfileDisplayed() {
		WebUI.verifyElementVisible(text('View Profile'))
		return this
	}

	public ProfilePopup verifyViewProfileClickable() {
		WebUI.verifyElementClickable(text('View Profile'))
		return this
	}
	
	public ProfilePopup verifyUserSettingsDisplayed() {
		WebUI.verifyElementVisible(text('User Settings'))
		return this
	}

	public ProfilePopup verifyUserSettingsClickable() {
		WebUI.verifyElementClickable(text('User Settings'))
		return this
	}
	
	public ProfilePopup verifyDocumentationDisplayed() {
		WebUI.verifyElementVisible(text('Documentation'))
		return this
	}

	public ProfilePopup verifyDocumentationClickable() {
		WebUI.verifyElementClickable(text('Documentation'))
		return this
	}
	
	public ProfilePopup verifyReleaseNotesDisplayed() {
		WebUI.verifyElementVisible(text('Release Notes'))
		return this
	}

	public ProfilePopup verifyReleaseNotesClickable() {
		WebUI.verifyElementClickable(text('Release Notes'))
		return this
	}
		
	public ProfilePopup verifyCommunityPortalDisplayed() {
		WebUI.verifyElementVisible(text('Community Portal'))
		return this
	}
	
	public ProfilePopup verifyCommunityPortalClickable() {
		WebUI.verifyElementClickable(text('Community Portal'))
		return this
	}
	
	public ProfilePopup verifySupportCaseDisplayed() {
		WebUI.verifyElementVisible(text('Submit a Support Case'))
		return this
	}
	
	public ProfilePopup verifySupportCaseClickable() {
		WebUI.verifyElementClickable(text('Submit a Support Case'))
		return this
	}
		
	public ProfilePopup verifySwitchAccountDisplayed() {
		WebUI.verifyElementVisible(text('Switch Account'))
		return this
	}
	
	public ProfilePopup verifySwitchAccountClickable() {
		WebUI.verifyElementClickable(text('Switch Account'))
		return this
	}
		
	public ProfilePopup verifySignOutDisplayed() {
		WebUI.verifyElementVisible(text('Sign Out'))
		return this
	}
	
	public ProfilePopup verifySignOutClickable() {
		WebUI.verifyElementClickable(text('Sign Out'))
		return this
	}

}