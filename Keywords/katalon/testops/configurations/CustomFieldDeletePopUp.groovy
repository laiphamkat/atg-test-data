package katalon.testops.configurations

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class CustomFieldDeletePopUp extends BasePage<CustomFieldDeletePopUp> {

	public CustomFieldDeletePopUp verifyDeleteCFLabelIsVisible() {
		WebUI.verifyElementVisible(xpath("//div[text()='Delete custom field']"))
		return this
	}

	public CustomFieldDeletePopUp verifyDeleteButtonIsClickable() {
		WebUI.verifyElementClickable(title("Delete"))
		return this
	}

	public CustomFieldDeletePopUp verifyCancelButtonIsClickable() {
		WebUI.verifyElementVisible(title("Cancel"))
		return this
	}
}
