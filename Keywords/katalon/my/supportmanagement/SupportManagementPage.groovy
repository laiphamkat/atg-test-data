package katalon.my.supportmanagement

import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage


public class SupportManagementPage extends BasePage<SupportManagementPage>{

	public SupportManagementPage verifyUISupportManagement () {
		WebUI.verifyElementPresent(xpath("//h2[text()='Support Management']"), 5)
		def textContentLineOne = WebUI.getText(xpath("(//h2[text()='Support Management']//following::p)[1]"))
		def textContentLineTwo = WebUI.getText(xpath("(//h2[text()='Support Management']//following::p)[2]"))
		WebUI.verifyEqual(textContentLineOne, "We provide support about license issues via our CRM platform, Salesforce.")
		WebUI.verifyEqual(textContentLineTwo, "Only the selected members can submit a ticket to Katalon Help Center.")

		WebUI.verifyElementPresent(text("Support Summary"), 5)
		WebUI.verifyElementPresent(text("Quota"), 5)
		WebUI.verifyElementPresent(text("Available slots"), 5)
		WebUI.verifyElementPresent(text("Assigned slots"), 5)

		WebUI.verifyElementPresent(text("Support List"), 5)
		WebUI.verifyElementPresent(text("Select the members to assign supported slots."), 5)
		WebUI.verifyElementPresent(xpath("//*[@placeholder='Filter by email or user name']"), 5)
		WebUI.verifyElementPresent(xpath("//th[text()='Full Name']"), 5)
		WebUI.verifyElementPresent(xpath("//th[text()='Email']"), 5)
		WebUI.verifyElementPresent(xpath("//th[text()='Assign Salesforce Slots']"), 5)
		return this
	}

	public SupportManagementPage verifyUserSelected(String email) {
		WebUI.verifyElementPresent(xpath("//td[text()='$email']/following-sibling::td/span/span[contains(@class,'Mui-checked')]"), 5)
		return this
	}
	
	public boolean isUserSelected(String email) {
		String classProp = WebUI.getAttribute(xpath("//td[text()='$email']/following-sibling::td/span/span"), "class")
		println classProp
		return classProp.contains("Mui-checked")
	}
	
	public SupportManagementPage selectUser(String email) {
		WebUI.click(xpath("//td[text()='$email']/following-sibling::td/span//input"))
		WebUI.waitForElementClickable(xpath("//td[text()='$email']/following-sibling::td/span//input"), 30)
		return this
	}
	
	public SupportManagementPage verifyExpectedAvailableSlots(int expectedSlot) {
		WebUI.verifyEqual(getAvailableSlots(), expectedSlot)
		return this
	}
	
	public SupportManagementPage verifyExpectedAssignedSlots(int expectedSlot) {
		WebUI.verifyEqual(getAssignedSlots(), expectedSlot)
		return this
	}
	
	public int getAvailableSlots(){
		return WebUI.getText(xpath("//h4[text()='Support Summary']/parent::div/div/div[2]/div[2]")).toInteger()
	}
	
	public int getAssignedSlots() {
		return WebUI.getText(xpath("//h4[text()='Support Summary']/parent::div/div/div[2]/div[3]")).toInteger()
	}
	
	public int countNoOfSelectedUsers() {
		List<WebElement> elements = WebUI.findWebElements(xpath("//td/following-sibling::td/span/span[contains(@class,'Mui-checked') and not(contains(@class,'Mui-disabled'))]"), 10)
		return elements.size()
	}
}
