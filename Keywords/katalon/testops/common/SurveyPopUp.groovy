package katalon.testops.common

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class SurveyPopUp extends BasePage<SurveyPopUp> {
	private TestObject survey = css("[data-testid='survey']")

	public SurveyPopUp verifySurveyDisplayed() {
		WebUI.waitForElementVisible(survey, 10)
		WebUI.verifyElementVisible(survey)
		return this
	}

	public SurveyPopUp verifySurveyNotDisplayed() {
		WebUI.verifyElementNotPresent(survey, 3)
		return this
	}

	public SurveyPopUp clickX() {
		WebUI.click(css('[aria-label="close"]'))
		return this
	}

	public SurveyPopUp selectStarRating(int rating) {
		WebUI.click(css(".sv-rating[data-value='${rating}']"))
		return this
	}

	public SurveyPopUp inputMessage(String message) {
		WebUI.setText(css("#survicate-box textarea[data-gramm_editor]"), message)
		return this
	}

	public SurveyPopUp clickSubmit() {
		WebUI.click(css('#survicate-box button[data-testid]'))
		return this
	}
}