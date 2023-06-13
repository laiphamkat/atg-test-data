package katalon.my.usermanagement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class RevokeInvitationPopup extends BasePage<RevokeInvitationPopup> {
	
	def revokeBtn = xpath("//div[@role='dialog']//button[text()='Revoke']")
	
	public RevokeInvitationPopup verifyRevokeBtnClickable() {
		WebUI.verifyElementClickable(revokeBtn)
		return this
	}
	
	public RevokeInvitationPopup verifyRevokeBtnDisabled() {
		WebUI.verifyElementNotClickable(revokeBtn)
		return this
	}
	
	public RevokeInvitationPopup removeEmail(String email) {
		click(xpath("//div[@role='dialog']//div[@role='button']/span[text()='$email']/following-sibling::*[local-name()='svg']"))
		return this
	}
	
	public RevokeInvitationPopup verifyDescription(String description) {
		WebUI.verifyElementText(xpath("//*[@id='form-dialog-title']/following-sibling::div/p"), description)
		return this
	}
	
	public RevokeInvitationPopup clickRevoke() {
		click(revokeBtn)
		return this
	}
	
	public RevokeInvitationPopup clickCancel() {
		click(xpath("//div[@role='dialog']//button[text()='Cancel']"))
		return this
	}
	
}
