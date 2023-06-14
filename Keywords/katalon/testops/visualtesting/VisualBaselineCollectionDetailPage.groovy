package katalon.testops.visualtesting

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage



public class VisualBaselineCollectionDetailPage extends BasePage<VisualBaselineCollectionDetailPage>{
	
	def propertyLabel = { value -> return xpath("//div[@id = 'properties']//label[text() = '${value}']") }
	
	public VisualBaselineCollectionDetailPage verifyRenameButtonIsClickabled() {
		WebUI.waitForPageLoad(2) // avoid flaky test
		WebUI.verifyElementClickable(xpath("//span[@aria-label='Rename Baseline Collection']//button"))
		return this
	}
	
	public VisualBaselineCollectionDetailPage verifySaveChangeButtonIsDisabled() {
		WebUI.verifyElementNotClickable(xpath("//button[@data-trackid='page-visual-testing-save']"))
		return this
	}
	
	public VisualBaselineCollectionDetailPage verifyBaselineImageTitleIsDisplayed() {
		WebUI.verifyElementClickable(xpath("//div[@class='baseline-image-title']//div[contains(text(), 'Baseline Images')]"))
		return this
	}
	
	public VisualBaselineCollectionDetailPage clickToThumbnailImageAtIndex (int index) {
		WebUI.click(xpath("//div[@class = 'col-4 gallery-item'][${index}]//div[@class = 'thumbnail__image']"))
		return this
	}
	
	public VisualBaselineCollectionDetailPage verifyUploadImagesButtonIsDisabled() {
		WebUI.verifyElementNotClickable(id('upload-baseline-images-button'))
		return this
	}
	
	public VisualBaselineCollectionDetailPage clickUploadImagesButton() {
		WebUI.click(id("upload-baseline-images-button"))
		return this
	}
	
	// Upload Image Popup
	public VisualBaselineCollectionDetailPage verifyAddImagePopupIsDisplayed() {
		WebUI.verifyElementVisible(xpath("//div[text() ='Add image to Baseline Collection']"))
		return this
	}
	
	public VisualBaselineCollectionDetailPage verifyUploadButtonIsDisabled() {
		WebUI.verifyElementNotClickable(id("submit-upload-baseline-images"))
		return this
	}
	
	public VisualBaselineCollectionDetailPage clickCancelAddImageButton() {
		WebUI.click(id("cancel-upload-baseline-images"))
		return this
	}
	
	// Properties section 
	public VisualBaselineCollectionDetailPage verifyPropertiesTitleIsDisplayed() {
		WebUI.verifyElementVisible(xpath("//div[@class = 'card-border-title'][text() = 'Properties']"))
		return this
	}
	
	public VisualBaselineCollectionDetailPage verifyPropertiesLabelIsDisplayed(String label) {
		WebUI.verifyElementVisible(propertyLabel(label))
		return this
	}
	
	// Schedule section
	
	public VisualBaselineCollectionDetailPage verifyScheduleSectionIsDisplayed() {
		WebUI.verifyElementVisible(id('related-test-schedules'))
		return this
	}
	
	// View Image Dialog
	
	public VisualBaselineCollectionDetailPage verifyImageDialogIsDisplayed () {
		WebUI.verifyElementVisible(xpath("//div[contains(@class, 'MuiDialogContent')]//img"))
		return this
	}
	
	public VisualBaselineCollectionDetailPage clickCloseDialog() {
		WebUI.click(xpath("//div[contains(@class, 'MuiBox-root')]/button"))
		return this
	}
	
	public VisualBaselineCollectionDetailPage clickMoreOptionAtImageIndex (int index) {
		WebUI.click(xpath("//div[@class = 'col-4 gallery-item'][${index}]//div[@aria-label='More']//button"))
		return this
	}
	
	public VisualBaselineCollectionDetailPage clickToEditImageAtImageIndex (int index) {
		WebUI.click(xpath("//div[@class = 'col-4 gallery-item'][${index}]//div[@aria-label='Edit Image']//button[contains(@class, 'image-edit')]"))
		return this
	}
	
	public VisualBaselineCollectionDetailPage clickToDeleteImage() {
		WebUI.click(xpath("//li[@data-trackid='delete-baseline-image']"))
		return this
	}
	
	// Delete image confirmation popup
	public VisualBaselineCollectionDetailPage verifyRemoveBaseLineDialogConfirmationIsDisplayed() {
		WebUI.verifyElementVisible(xpath("//div[text() = 'Remove Baseline']"))
		return this
	}
	
	public VisualBaselineCollectionDetailPage verifyDeleteButtonIsClickabled() {
		WebUI.verifyElementClickable(xpath('//button[@data-trackid="submit-delete-baseline-image"]'))
		return this
	}
	
	public VisualBaselineCollectionDetailPage clickCancelDeleteImage() {
		WebUI.click(xpath("//button[@data-trackid='cancel-delete-baseline-image']"))
		return this
	}
	
	// Edit Image Dialog
	
	public VisualBaselineCollectionDetailPage verifyResolutionIsDisplayedOnEditImgDialog() {
		WebUI.verifyElementVisible(xpath("//div[@class = 'edit-baseline__sticky']/div[contains(@class, 'mb-2')]"))
		return this
	}
	
	public VisualBaselineCollectionDetailPage verifyIgnoreZoneBtnIsCliKcabledOnEditImgDialog() {
		WebUI.verifyElementClickable(id("draw-ignore-zones"))
		return this
	}
	
	public VisualBaselineCollectionDetailPage verifySaveButtonIsDisabledOnEditImgDialog() {
		WebUI.verifyElementNotClickable(xpath("//button[@data-trackid='save-draft-ignoring-zone']"))
		return this
	}
	
	public VisualBaselineCollectionDetailPage clickToCancelButton() {
		WebUI.click(xpath("//button[@data-trackid='cancel-define-ignoring-zone']"))
		return this
	}
	
	public VisualBaselineCollectionDetailPage hoverToInstrucmentIcon() {
		WebUI.mouseOver(xpath("//span[@aria-label='Drag to choose to ignore areas that are supposed to have dynamic and changing contents.']"))
		return this
	}
	
	public VisualBaselineCollectionDetailPage verifyInstructmentTooltipIsDisplay() {
		WebUI.verifyElementVisible(xpath("//div[text() = 'Drag to choose to ignore areas that are supposed to have dynamic and changing contents.']"))
		return this
	}
	
}
