package katalon.testops.configurations
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class CustomFieldPage extends BasePage<CustomFieldPage> {

	public CustomFieldPage clickCreateBtn() {
		WebUI.click(id("create-new-custom-field"))
		return this
	}

	public CustomFieldPage verifyCreateBtnIsClickable() {
		WebUI.verifyElementClickable(id("create-new-custom-field"))
		return this
	}

	public CustomFieldPage verifyCreateBtnIsNotVisable() {
		WebUI.verifyElementNotPresent(id("create-new-custom-field"), 5)
		return this
	}

	public CustomFieldPage verifyBordersVisable() {
		WebUI.verifyElementClickable(css(".border"))
		return this
	}


	public CustomFieldPage verifyColumnLabelIsCorrect(String label) {
		assert WebUI.getText(cls("custom-field-detail__label")) == label
		return this
	}

	public CustomFieldPage verifyAllCFValuesAreNotNull() {
		for(String value : getTextElements(cls("custom-field-detail__value"))){
			assert value != null &&  value != ''
		}

		return this
	}

	public CustomFieldPage verifyEditIconIsShown() {
		WebUI.verifyElementVisible(xpath("(//button[contains(@class, 'custom-field-detail__edit-and-remove-btn')])[1]"))
		return this
	}

	public CustomFieldPage verifyEditIconIsNotShown() {
		WebUI.verifyElementNotPresent(xpath("(//button[contains(@class, 'custom-field-detail__edit-and-remove-btn')])[1]"), 5)
		return this
	}

	public CustomFieldPage verifyDeleteIconIsShown() {
		WebUI.verifyElementVisible(xpath("(//button[contains(@class, 'custom-field-detail__edit-and-remove-btn')])[2]"))
		return this
	}

	public CustomFieldPage verifyDeleteIconIsNotShown() {
		WebUI.verifyElementNotPresent(xpath("(//button[contains(@class, 'custom-field-detail__edit-and-remove-btn')])[2]"), 5)
		return this
	}

	public CustomFieldPage clickEditIcon(String key='') {
		if(key) {
			WebUI.click(xpath("(//p[text()='${key}']/ancestor::div[contains(@class, 'custom-field-detail__container-btn')]//button)[1]"))
		}
		else {
			WebUI.click(xpath("(//ancestor::div[contains(@class, 'custom-field-detail__container-btn')]//button)[1]"))
		}
		return this
	}

	public CustomFieldPage clickDeleteIcon(String key='') {
		if(key) {
			WebUI.click(xpath("(//p[text()='${key}']/ancestor::div[contains(@class, 'custom-field-detail__container-btn')]//button)[2]"))
		}
		else {
			WebUI.click(xpath("(//ancestor::div[contains(@class, 'custom-field-detail__container-btn')]//button)[2]"))
		}
		return this
	}

	public CustomFieldPage clickDeleteButton() {
		WebUI.click(title("Delete"))
		return this
	}


	public CustomFieldPage getCustomFieldTable(String label) {
		WebUI.getText(text(label))

		return this
	}

	public CustomFieldPage verifyKeyIsVisible(String key) {
		WebUI.verifyElementVisible(text(key))
		return this
	}

	public CustomFieldPage verifyDisplayNameIsVisible(String displayName) {
		WebUI.verifyElementVisible(text(displayName))
		return this
	}

	public CustomFieldPage verifyValuesIsVisible(String values) {
		WebUI.verifyElementVisible(text(values))
		return this
	}

	public CustomFieldPage verifyCustomFieldIsNotDisplay(String key) {
		WS.verifyEqual(verifyElementNotVisible(text(key)), true)
		return this
	}
}
