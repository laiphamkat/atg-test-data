package katalon.my.usermanagement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import katalon.fw.lib.BasePage

public class UserManagementPage extends BasePage<UserManagementPage> {

	public UserManagementPage verifyDisableInviteUsersButton() {
		WebUI.verifyElementNotClickable(btn("Invite Users"))
		return this
	}

	public UserManagementPage verifyContentAfterHoverDisableInviteUsersButton() {
		def hoverText = WebUI.getAttribute(xpath("(//button[(text()='Invite Users')])/parent::span"), "aria-label")
		println(hoverText)
		WebUI.verifyEqual(hoverText, "You’ve exceeded the user quota.")
		return this
	}

	public UserManagementPage clickInviteUsersButton() {
		WebUI.click(btn("Invite Users"))
		return this
	}

	public UserManagementPage inputOneEmail(String email) {
		WebUI.sendKeys(txt('Insert email'), email+' ')
		return this
	}

	public UserManagementPage inputListUserEmail(List<String> value) {
		WebUI.sendKeys(txt('Insert email'), value.join(' ')+' ')
		return this
	}

	public UserManagementPage clickNextButton() {
		WebUI.click(btn("Next"))
		return this
	}

	public UserManagementPage clickConfirmButton() {
		WebUI.click(btn("Confirm"))
		return this
	}

	public UserManagementPage clickSendInvitationButton() {
		WebUI.click(btn("Send Invitation"))
		return this
	}

	public UserManagementPage clickInvitationLinkEmail(String email) {
		WebUI.click(xpath("//*[text()='$email']/following::td[4]//span"))
		return this
	}

	public UserManagementPage clickCheckboxEmail(String email) {
		WebUI.click(xpath("//*[text()='$email']/preceding::td/span"))
		return this
	}

	public UserManagementPage verifyPendingInvitationSuccessfully() {
		def msg = WebUI.getText(xpath("(//*[@id = 'notistack-snackbar'])"))
		println(msg)
		WebUI.verifyEqual(msg, "Invitation to 1 member has been sent.")
		return this
	}

	public UserManagementPage verifyUserManagementPresent() {
		WebUI.verifyElementPresent(text('Active Users'),5)
		WebUI.verifyElementPresent(text('Pending Invitation'),5)
		WebUI.verifyElementPresent(text('Removed Users'),5)
		WebUI.verifyElementPresent(btn('Export Users'),5)
		WebUI.verifyElementClickable(btn('Export Users'))
		WebUI.verifyElementPresent(btn('Invite Users'),5)
		WebUI.verifyElementClickable(btn('Invite Users'))
		return this
	}

	public UserManagementPage clickActiveUsers() {
		WebUI.click(text("Active Users"))
		return this
	}

	public UserManagementPage verifyDetailActiveUserTabPresent() {
		WebUI.verifyElementPresent(btn("Change Role"),5)
		WebUI.verifyElementNotClickable(btn("Change Role"))
		WebUI.verifyElementPresent(btn("Remove Users"),5)
		WebUI.verifyElementNotClickable(btn("Remove Users"))
		WebUI.verifyElementPresent(xpath("//*[@placeholder='Search']"), 5)
		WebUI.verifyElementPresent(text("Full Name"),5)
		WebUI.verifyElementPresent(text("Email"),5)
		WebUI.verifyElementPresent(text("Join Date"),5)
		WebUI.verifyElementPresent(text("Role"),5)
		WebUI.verifyElementPresent(text("License Access"),5)
		WebUI.verifyElementPresent(text("Last Login"),5)
		return this
	}

	public UserManagementPage clickPendingInvitation() {
		WebUI.click(text("Pending Invitation"))
		return this
	}

	public UserManagementPage verifyDetailPendingInvitationTabPresent() {
		WebUI.verifyElementPresent(btn("Revoke"),5)
		WebUI.verifyElementNotClickable(btn("Revoke"))
		WebUI.verifyElementPresent(btn("Resend Invitation"),5)
		WebUI.verifyElementNotClickable(btn("Resend Invitation"))
		WebUI.verifyElementPresent(xpath("//*[@placeholder='Search']"), 5)
		WebUI.verifyElementPresent(text("Full Name"),5)
		WebUI.verifyElementPresent(text("Email"),5)
		WebUI.verifyElementPresent(text("Invitation Date"),5)
		WebUI.verifyElementPresent(text("Role"),5)
		WebUI.verifyElementPresent(text("License Access"),5)
		WebUI.verifyElementPresent(text("Invitation Link"),5)
		return this
	}

	public UserManagementPage clickRemovedUsersTab() {
		WebUI.click(text("Removed Users"))
		return this
	}

	public UserManagementPage verifyDetailRemovedUsersTabPresent() {
		WebUI.verifyElementPresent(xpath("//*[@placeholder='Search']"), 5)
		WebUI.verifyElementPresent(text("Full Name"),5)
		WebUI.verifyElementPresent(text("Email"),5)
		WebUI.verifyElementPresent(text("Join Date"),5)
		WebUI.verifyElementPresent(text("Removal date"),5)
		WebUI.verifyElementPresent(text("Removed by"),5)
		WebUI.verifyElementPresent(text("Role"),5)
		WebUI.verifyElementPresent(text("Last Login"),5)
		return this
	}

	public UserManagementPage verifyIsAtPendingInvitationPage() {
		WebUI.verifyElementPresent(xpath("//a[contains(@class,'Mui-selected') and @id='user_management.tab.pending_invitation']"), 0)
		return this
	}

	public UserManagementPage verifyEmailsExist(List<String> emails) {
		for(String item: emails) {
			verifyEmailExist(item)
		}
		return this
	}

	public UserManagementPage clickChangeRole() {
		WebUI.click(btn("Change Role"))
		return this
	}

	public UserManagementPage clickRemoveUsers() {
		WebUI.click(btn("Remove Users"))
		return this
	}

	public UserManagementPage clickRevoke() {
		WebUI.click(btn("Revoke"))
		return this
	}

	public UserManagementPage clickResendInvitation() {
		WebUI.click(btn("Resend Invitation"))
		return this
	}

	public UserManagementPage selectUser(String email) {
		click(xpath("//tbody//td/span[text()='$email']/parent::td/preceding-sibling::td//input"))
		return this
	}

	public UserManagementPage selectInvitation(String email) {
		click(xpath("//tbody//td[text()='$email']/preceding-sibling::td//input"))
		return this
	}

	public UserManagementPage verifyRole(String email, String role) {
		String roleText = WebUI.getText(xpath("//tbody//td/span[text()='$email']/parent::td/following-sibling::td[2]"))
		WebUI.verifyEqual(role, roleText)
		return this
	}

	public UserManagementPage verifyEmailExist(String email) {
		WebUI.verifyElementPresent(xpath("//td[text()='$email']"), 0)
		return this
	}

	public UserManagementPage verifyEmailNotExist() {
		WebUI.verifyElementPresent(xpath("//tbody/tr/td/h6[text()='No result found.' or text()='There is no data.']"), 5)
		return this
	}
	
	public UserManagementPage searchEmail(String email) {
		WebUI.clearText(xpath("//input[@placeholder='Search']"))
		WebUI.sendKeys(xpath("//input[@placeholder='Search']"), email)
		return this
	}

	public UserManagementPage verifyResendInvitationSuccessfully(String token1, String token2) {
		WebUI.verifyNotEqual(token1, token2)
		return this
	}
}
