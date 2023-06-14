package katalon.my.account

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import katalon.fw.lib.BasePage

public class AccountSettingPage extends BasePage<AccountSettingPage> {
	public AccountSettingPage clickDeleteAccount() {
		WebUI.click(id("account_setting.delete_button"))
		return this
	}

	public AccountSettingPage verifyMessageCannotDeleteAccount() {
		String msg = WebUI.getText(id("notistack-snackbar"))
		println "Error msg $msg"
		WebUI.verifyEqual(msg.contains("You still have active subscriptions.\nPlease cancel the following subscriptions before deleting this Account"), true)
		return this
	}

	public AccountSettingPage verifyOnlyOwnerCanViewDeleteAccount(String role) {
		if(role.equalsIgnoreCase("owner")) {
			WebUI.verifyElementVisible(id("account_setting.delete_button"))
		}else {
			WebUI.verifyElementNotPresent(id("account_setting.delete_button"),5)
		}
		return this
	}

	public String getAccountId() {
		WebUI.verifyElementPresent(xpath("//*[contains(text(),'ID')]"), 5)
		String accountId = WebUI.getText(xpath("//*[contains(text(),'ID')]")).substring(4)
		return accountId
	}

	public AccountSettingPage clickEditNameButton () {
		WebUI.click(id("account.setting.edit.name.button"))
		return this
	}

	public AccountSettingPage verifyEditNameButtonPresent() {
		WebUI.verifyElementPresent(id("account_setting.delete_button"), 5)
		return this
	}

	public AccountSettingPage verifyAccountAvatarPresent () {
		WebUI.verifyElementPresent(xpath("//h6[text()='Account Settings']//..//descendant::img"), 5)
		return this
	}

	public AccountSettingPage verifyAccountNamePresent (String accountName) {
		WebUI.verifyElementPresent(xpath("//h6[text()='${accountName}']"), 5)
		return this
	}

	public AccountSettingPage verifyEditNamePopupPresent() {
		WebUI.verifyElementPresent(xpath("//*[@id='account.setting.dialog.content']//preceding-sibling::h2/div[text()='Edit Name']"), 5)
		WebUI.verifyElementPresent(id("account.setting.dialog.textfield.account.name"), 5)
		return this
	}
	
	public AccountSettingPage verifyCancelEditBtnClickable() {
		WebUI.verifyElementPresent(id("account.setting.dialog.actions.button.cancel"), 5)
		WebUI.verifyElementClickable(id("account.setting.dialog.actions.button.cancel"))
		return this
	}
	
	public AccountSettingPage verifySaveEditBtnNotClickable() {
		WebUI.verifyElementPresent(id("account.setting.dialog.actions.button.save"), 5)
		WebUI.verifyElementNotClickable(id("account.setting.dialog.actions.button.save"))
		return this
	}
	
	

	public AccountSettingPage clickCancel() {
		WebUI.click(id("account.setting.dialog.actions.button.cancel"))
		return this
	}

	public AccountSettingPage verifyDeleteButtonPresent() {
		WebUI.verifyElementPresent(id("account_setting.delete_button"), 5)
		return this
	}

	public AccountSettingPage verifyDeletePopupPresent() {
		WebUI.verifyElementPresent(id("account_setting.delete_account_dialog.account_name.text_field"), 5)
		
		
		return this
	}
	
	public AccountSettingPage verifyCancelDelBtnClickable() {
		WebUI.verifyElementPresent(id("account_setting.delete_account_dialog.form.button.cancel"), 5)
		WebUI.verifyElementClickable(id("account_setting.delete_account_dialog.form.button.cancel"))
		return this
	}
	
	public AccountSettingPage verifyConfirmDelBtnNotClickable() {
		WebUI.verifyElementPresent(id("account_setting.delete_account_dialog.form.button.delete"), 5)
		WebUI.verifyElementNotClickable(id("account_setting.delete_account_dialog.form.button.delete"))
		return this
	}

	public AccountSettingPage verifyAccountIdPresent(String id) {
		String actualId = getAccountId()
		WebUI.verifyEqual(actualId, id)
		return this
	}

	public AccountSettingPage verifyNoOfMembers(Number noMember) {
		def text = WebUI.getText(xpath("//h6[contains(text(),'Members')]/span"))
		WebUI.verifyEqual(text, noMember.toString())
		return this
	}

	public AccountSettingPage verifyNoOfOrgs(Number noOrg) {
		def text = WebUI.getText(xpath("//h6[contains(text(),'Organizations')]/span"))
		WebUI.verifyEqual(text, noOrg.toString())
		return this
	}

	public AccountSettingPage verifyNoOfProjects(Number noProj) {
		def text = WebUI.getText(xpath("//h6[contains(text(),'Projects')]/span"))
		WebUI.verifyEqual(text, noProj.toString())
		return this
	}
}