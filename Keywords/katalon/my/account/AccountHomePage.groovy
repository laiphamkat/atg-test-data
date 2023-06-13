
package katalon.my.account

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BasePage

public class AccountHomePage extends BasePage<AccountHomePage>  {

	public AccountHomePage verifyAccountName (String expected) {
		WebUI.verifyElementText(title(expected), expected)
		return this
	}

	public AccountHomePage verifyNoOfMemberOnThisAccount (String expected) {
		String actual = WebUI.getText(xpath("//*[text()='Member on this Account']/child::*"))
		WebUI.verifyEqual(actual, expected)
		return this
	}

	public AccountHomePage verifyNoOfOrganizationOnThisAccount (String expected) {
		String actual = WebUI.getText(xpath("//*[text()='Organization on this Account']/child::*"))
		WebUI.verifyEqual(actual, expected)
		return this
	}

	public AccountHomePage verifyAccountRole (String expected) {
		String actual = WebUI.getText(xpath("//*[@title]/following-sibling::*"))
		WebUI.verifyEqual(actual, expected)
		return this
	}

	public AccountHomePage clickGetTheAccountID() {
		WebUI.click(id("admin.home.link.get.the.account.id"))
		return this
	}

	public AccountHomePage clickManageMyLicenses() {
		WebUI.click(id("admin.home.link.manage.my.licenses"))
		return this
	}

	public AccountHomePage clickInviteUsersToAccount() {
		WebUI.click(id("admin.home.link.invite.users.to.account"))
		return this
	}

	public AccountHomePage clickSubscribeToKatalonProducts() {
		WebUI.click(id("admin.home.link.subscribe.to.katalon.products"))
		return this
	}

	public AccountHomePage navigate2LicenseManagementPage(String orgId) {
		WebUI.navigateToUrl(GlobalVariable.myUrl+"organization/$orgId/admin/license_keys/per_user_kse")
		return this
	}

	public AccountHomePage verifyAccountHomepagePresent (String accountName, String role) {
		WebUI.verifyElementPresent(xpath("//h6[@title]"), 5)
		WebUI.verifyElementText(xpath("//h6[@title]"), accountName)
		WebUI.verifyElementPresent(xpath("//h6[@title]/following-sibling::h6"), 5)
		WebUI.verifyElementText(xpath("//h6[@title]/following-sibling::h6"), role)
		def noMember = WebUI.getText(xpath("//img[@alt]/parent::div/following-sibling::div/h6[1]/span"))
		noMember.toInteger() > 1 ? WebUI.verifyElementPresent(text("Members on this Account"), 5) : WebUI.verifyElementPresent(text("Member on this Account"), 5)
		WebUI.verifyElementPresent(text('Organization on this Account'), 5)
		return this
	}

	public AccountHomePage verifyAvatarPresent() {
		WebUI.verifyElementPresent(xpath("//img[@alt='avatar']"), 5)
		return this
	}

	public AccountHomePage verifyQuickActionsPresent() {
		WebUI.verifyElementPresent(text('Quick Actions'), 5)
		WebUI.verifyElementText(id("admin.home.link.manage.my.licenses"), "Manage My Licenses")
		WebUI.verifyElementText(id("admin.home.link.subscribe.to.katalon.products"), "Subscribe To Katalon products")
		WebUI.verifyElementText(id("admin.home.link.invite.users.to.account"), "Invite Users To Account")
		WebUI.verifyElementText(id("admin.home.link.get.the.account.id"), "Get the Account ID")
		WebUI.verifyElementText(id("admin.home.link.update.profile"), "Update My Profile")
		return this
	}

	public AccountHomePage verifyManageMyLicensesPresent() {
		WebUI.verifyElementText(id("admin.home.link.manage.my.licenses"), "Manage My Licenses")
		return this
	}
	
	public AccountHomePage verifyViewMyLicensesPresent() {
		WebUI.verifyElementPresent(text("View My Licenses"), 5)
		return this
	}

	public AccountHomePage verifySubscribeToKatalonProductsPresent() {
		WebUI.verifyElementText(id("admin.home.link.subscribe.to.katalon.products"), "Subscribe To Katalon products")
		return this
	}

	public AccountHomePage verifySubscribeToKatalonProductsNotPresent() {
		WebUI.verifyElementNotPresent(id("admin.home.link.subscribe.to.katalon.products"), 5)
		return this
	}

	public AccountHomePage verifyInviteUsersToAccountPresent() {
		WebUI.verifyElementText(id("admin.home.link.invite.users.to.account"), "Invite Users To Account")
		return this
	}
	
	public AccountHomePage verifyInviteUsersToAccountNotPresent() {
		WebUI.verifyElementNotPresent(id("admin.home.link.invite.users.to.account"), 5)
		return this
	}

	public AccountHomePage verifyGetAccountIdPresent() {
		WebUI.verifyElementText(id("admin.home.link.get.the.account.id"), "Get the Account ID")
		return this
	}

	public AccountHomePage verifyUpdateMyProfilePresent() {
		WebUI.verifyElementText(id("admin.home.link.update.profile"), "Update My Profile")
		return this
	}


	public AccountHomePage verifyFeaturedDocumentationPresent() {
		WebUI.verifyElementPresent(text('Featured Documentation'), 5)
		WebUI.verifyElementText(id("admin.home.link.about_platform"), "About Katalon Platform")
		WebUI.verifyElementText(id("admin.home.link.roles_and_permissions"), "Administrative Roles and Permissions")
		WebUI.verifyElementText(id("admin.home.link.platform_plans"), "Katalon Platform Plans")
		WebUI.verifyElementText(id("admin.home.link.platform_get_started"), "Get started with the Katalon Platform")
		return this
	}

	public AccountHomePage verifyMyProductsPresent() {
		WebUI.verifyElementPresent(text('My Katalon Products'), 5)
		return this
	}

	public AccountHomePage checkHaveKSEperUserProduct () {
		WebUI.verifyElementPresent(text('Studio Enterprise (per-User)'), 5)
		return this
	}

	public AccountHomePage clickAvatar() {
		WebUI.click(id("user_setting"))
		return this
	}

	public AccountHomePage clickProfile() {
		WebUI.click(text("View Profile"))
		return this
	}
}


