import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.visualtesting.VisualBaselineCollectionDetailPage
import katalon.testops.visualtesting.VisualBaselineCollectionPage
import katalon.testops.visualtesting.VisualTestRunPage

'Pre-condition: Find a user to log in'
Credential user = Page.nav(Credential)
	.getCredentials()
	.withRole(role)
	.inProject('AI Visual Test')
	.getFirst()

'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential(user.email, user.pwd).clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User navigates to Visual Testing > Visual Test Runs tab > Visual Baseline Collection'
Page.nav(VisualTestRunPage).navigateTo(user.teamId, user.projectId)
						   .clickVisualBaselineCollectionsTab()

'Verify Visual Baseline Collection tab is acvited and Visual Baseline Collections title is displayed'
Page.nav(VisualBaselineCollectionPage).verifyVisualBaselineCollectionsTabIsActived()
                                      .verifyVisualBaselineCollectionsTitleDisplayed()
									  .verifyProIconIsDisplayed()
									 
'Click to add base line collection and verify create form display full information'
Page.nav(VisualBaselineCollectionPage).clickToCreateBaselineCollectionButton()
     								  .verifyCreateBaselineCollectionFormTitle()
									  .inputBaselineCollectionName('Sample name')
									  .verifyUploadImageSectionIsDisplayed()
									  .verifyCreateButtonIsDisabled()
									  .clickCancelButton()
						   
'Verify Visual Baseline Collection table is displayed with full collumn, and have data in table'
Page.nav(VisualBaselineCollectionPage).verifyVBCTableHeaderDisplayed()
									  .verifyVBCTableNotEmpty()
									  
'Click to baseline collection id at first row'
Page.nav(VisualBaselineCollectionPage).clickToBaselineCollectionIdAtRow(1)
						   
'Verify baseline collection detail page is opened'
'Verify baseline image section displayed with full information'
Page.nav(VisualBaselineCollectionDetailPage).verifyRenameButtonIsClickabled()
											.verifyBaselineImageTitleIsDisplayed()
											.verifySaveChangeButtonIsDisabled()
											.clickToThumbnailImageAtIndex(1)
											.verifyImageDialogIsDisplayed()
											.clickCloseDialog()
											.clickToEditImageAtImageIndex(1)
											.verifyResolutionIsDisplayedOnEditImgDialog()
											.verifyIgnoreZoneBtnIsCliKcabledOnEditImgDialog()
											.hoverToInstrucmentIcon()
											.verifyInstructmentTooltipIsDisplay()
											.verifySaveButtonIsDisabledOnEditImgDialog()
											.clickToCancelButton()
											.clickMoreOptionAtImageIndex(1)
											.clickToDeleteImage()
											.verifyRemoveBaseLineDialogConfirmationIsDisplayed()
											.verifyDeleteButtonIsClickabled()
											.clickCancelDeleteImage()
											.clickUploadImagesButton()
											.verifyAddImagePopupIsDisplayed()
											.verifyUploadButtonIsDisabled()
											.clickCancelAddImageButton()

'Verify properties displays with full information'
Page.nav(VisualBaselineCollectionDetailPage).verifyPropertiesTitleIsDisplayed()
                                            .verifyPropertiesLabelIsDisplayed('Updated on')
											.verifyPropertiesLabelIsDisplayed('Last Run')
											.verifyPropertiesLabelIsDisplayed('Number of Images')
											.verifyPropertiesLabelIsDisplayed('Default Comparison Method')
											.verifyPropertiesLabelIsDisplayed('Pixel Sensitivity')
											
'Verify Test Schedules section is displayed'
Page.nav(VisualBaselineCollectionDetailPage).verifyScheduleSectionIsDisplayed()

