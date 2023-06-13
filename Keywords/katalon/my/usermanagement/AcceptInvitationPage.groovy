package katalon.my.usermanagement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.common.MySignInPage
import katalon.fw.lib.BasePage

public class AcceptInvitationPage extends BasePage<AcceptInvitationPage> {

	public AcceptInvitationPage goToAcceptPage(String invitationToken) {
		WebUI.navigateToUrl("${GlobalVariable.myUrl}accept-invitation?invitation_token=$invitationToken")
		return this
	}

	public AcceptInvitationPage login(String user_name, String password) {
		WebUI.sendKeys(byName('email'), user_name)
		WebUI.setEncryptedText(byName('password'), password)
		WebUI.click(btn('Sign In'))
		return this
	}

	public AcceptInvitationPage clickJoin() {
		click(xpath("//button[contains(text(),'Join')]"))
		return this
	}

	public AcceptInvitationPage clickDecline() {
		click(xpath("//button[contains(text(),'Decline')]"))
		return this
	}
}
