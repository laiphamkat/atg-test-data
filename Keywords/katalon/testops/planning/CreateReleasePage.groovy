package katalon.testops.planning
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BasePage
import katalon.testops.configurations.TestEnvironmentsPage

public class CreateReleasePage extends BasePage<CreateReleasePage> {

	def inputElement = { value -> return xpath("//div[@class = 'form-group'][./label[text() = '${value}']]//input") }
	
	public CreateReleasePage verifyPageDisplayed() {
		WebUI.waitForElementVisible(title('Create Release'), 5)
		WebUI.verifyElementVisible(title('Create Release'))
		return this
	}

	public CreateReleasePage inputName(String name) {
		clearTextAndSendKeys(inputElement('Name'), name)
		return this
	}
	
	public CreateReleasePage inputStartDate(String startDate) {
		clearTextAndSendKeys(inputElement('Start Date'), startDate)
		return this
	}
	
	public CreateReleasePage inputReleaseDate(String releaseDate) {
		clearTextAndSendKeys(inputElement('Release Date'), releaseDate)
		return this
	}
	
	public CreateReleasePage inputDescription(String description) {
		WebUI.setText(xpath("//div[@class = 'form-group'][./label[text() = 'Description']]//textarea"), description)
		return this
	}
	
	public CreateReleasePage verifyCreateButtonIsClickable() {
		WebUI.verifyElementClickable(text('Create'))
		return this
	}

	
}
