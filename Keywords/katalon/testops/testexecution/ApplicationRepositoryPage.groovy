package katalon.testops.testexecution

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BasePage


class ApplicationRepositoryPage extends BasePage<ApplicationRepositoryPage> {
	def deleteBtn = { int rowIndex -> return css("tbody tr:nth-child(${rowIndex}) button[title='Delete Application']") }

	public ApplicationRepositoryPage navigateTo(String teamId, String projectId) {
		WebUI.navigateToUrl("${GlobalVariable.testOpsUrl}team/${teamId}/project/${projectId}/native-apps")
		return this
	}

	public ApplicationRepositoryPage clickUploadApplication() {
		WebUI.click(btn('Upload Application'))
		return this
	}

	public ApplicationRepositoryPage clickChooseFile() {
		WebUI.click(id('upload-app'))
		return this
	}

	public ApplicationRepositoryPage verifyChooseFileDisplayed() {
		WebUI.verifyElementVisible(id('upload-app'))
		return this
	}

	public ApplicationRepositoryPage verìfySupporttext() {
		WebUI.verifyTextPresent('Supported files', false)
		return this
	}

	public ApplicationRepositoryPage selectFileUpload() {
		WebUI.uploadFile(id('upload-app'), RunConfiguration.getProjectDir() + '/androidapp/APIDemos.apk')
		return this
	}

	public ApplicationRepositoryPage closeUploadAppDialog() {
		WebUI.click(css('h2 button'))
		return this
	}

	public ApplicationRepositoryPage clickDeleteApplication(int rowIndex) {
		WebUI.click(deleteBtn(rowIndex))
		return this
	}

	public ApplicationRepositoryPage clickConfirmDeleteApplication() {
		WebUI.click(btn('Delete'))
		return this
	}

	public ApplicationRepositoryPage clickCancelDeleteApplication() {
		WebUI.click(btn('Cancel'))
		return this
	}

	public ApplicationRepositoryPage verifyDeleteSuccessMessage(String appName) {
		def deleteMsg = 'The application ' + appName +' has been remove'
		WebUI.verifyTextPresent(deleteMsg, false)
		return this
	}

	public ApplicationRepositoryPage placeholderForEmptyList() {
		WebUI.verifyTextPresent('There is no data to display.', false)
		return this
	}
}