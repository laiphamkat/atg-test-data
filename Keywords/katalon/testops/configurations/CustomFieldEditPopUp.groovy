package katalon.testops.configurations

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class CustomFieldEditPopUp extends BasePage<CustomFieldEditPopUp> {

	public CustomFieldEditPopUp verifyEditCFLabelIsVisible() {
		WebUI.verifyElementVisible(xpath("//div[contains(@class,'MuiBox-root') and text()='Edit custom field']"))
		return this
	}

	public CustomFieldEditPopUp updateDisplayName(String displayName) {
		clearTextAndSendKeys(id("display-name"), displayName)
		return this
	}

	public CustomFieldEditPopUp updateValue(String value) {
		clearTextAndSendKeys(id("value"), value)
		return this
	}

	public CustomFieldEditPopUp clickSaveChangesButton() {
		WebUI.click(title("Save Changes"))
		return this
	}

	public CustomFieldEditPopUp clickCancelButton() {
		WebUI.click(title("Cancel"))
		return this
	}

	public CustomFieldEditPopUp verifyValueInputIsVisible() {
		WebUI.verifyElementVisible(id("value"))
		return this
	}

	public CustomFieldEditPopUp verifyDisplayNameInputIsVisible() {
		WebUI.verifyElementVisible(id("display-name"))
		return this
	}

	public CustomFieldEditPopUp verifyKeyInputIsVisible() {
		WebUI.verifyElementVisible(id("key"))
		return this
	}


	public CustomFieldEditPopUp verifySaveChangesButtonIsClickable() {
		WebUI.verifyElementClickable(title("Save Changes"))
		return this
	}

	public CustomFieldEditPopUp verifyCancelButtonIsClickable() {
		WebUI.verifyElementClickable(title("Cancel"))
		return this
	}
}
