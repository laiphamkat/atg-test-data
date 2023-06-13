package katalon.my.usermanagement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import katalon.fw.lib.BasePage

public class ResendInvitationPopup extends BasePage<ResendInvitationPopup> {

	public ResendInvitationPopup verifyResendBtnClickable(){
		WebUI.verifyElementClickable(xpath("//div[@role='dialog']//button[text()='Resend']"))
		return this
	}

	public ResendInvitationPopup verifyDescription(String description) {
		WebUI.verifyElementText(xpath("//h2[@id='form-dialog-title']/following-sibling::div/p"), description)
		return this
	}

	public ResendInvitationPopup verifyResendBtnDisabled() {
		WebUI.verifyElementNotClickable(xpath("//div[@role='dialog']//button[text()='Resend']"))
		return this
	}

	public ResendInvitationPopup removeEmail(String email) {
		click(xpath("//div[@role='dialog']//div[@role='button']/span[text()='$email']/following-sibling::*[local-name()='svg']"))
		return this
	}

	public ResendInvitationPopup clickResend() {
		click(xpath("//div[@role='dialog']//button[text()='Resend']"))
		return this
	}

	public ResendInvitationPopup clickCancel() {
		click(xpath("//div[@role='dialog']//button[text()='Cancel']"))
		return this
	}
}
