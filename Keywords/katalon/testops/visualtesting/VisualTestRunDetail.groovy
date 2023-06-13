package katalon.testops.visualtesting

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.util.stream.IntStream

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage
import katalon.utility.CommonUtility
import org.openqa.selenium.Keys as Keys

public class VisualTestRunDetail extends BasePage<VisualTestRunDetail> {
	def thumbnail_status
	enum StatusImage{
		MISMATCH,
		MISSING,
		NEW,
		PASSED
	}
	def common = new CommonUtility()
	def pageLocator = [
		xpath_mismatch_thumbnails: '//div[@class="thumbnail unresolved"]/div[2]/div/div/span[contains(text(),"mismatch")]',
		xpath_missing_thumbnails: '//div[@class="thumbnail unresolved"]/div[2]/div/div/span[contains(text(),"missing")]',
		xpath_new_thumbnails: '//div[@class="thumbnail unresolved"]/div[2]/div/div/span[contains(text(),"missing")]',
		id_comparison_method: '//label[contains(text(),"Comparison method")]/following-sibling::div[1]',
		xpath_pixel_sensity_slider: "//span[contains(@class,'MuiSlider-markLabel')]/preceding-sibling::span[1]",
		xpath_diff_count: "//label[contains(@class,'checkpoint-comparison__diff-count')]",
		status_visual_test_detail: "//div[@class='status-bar-v2']",
		search_input: "//input[@id='search-input']",
		testRun_info: "//div[@class='object-info__item']",

	]
	def thumbnail_image_status = { status -> "//div[@class='thumbnail ${status}']" }

	List<Integer> diffCount = new ArrayList<>();

	public VisualTestRunDetail clickToImageWithStatus(String thumbnailStatus) {
		WebUI.delay(2)
		switch(StatusImage.valueOf(thumbnailStatus)) {
			case "MISMATCH":
				click(xpath(pageLocator['xpath_mismatch_thumbnails']))
				break;
			case "MISSING":
				click(xpath(pageLocator['xpath_missing_thumbnails']))
				break;
			case "NEW":
				click(xpath(pageLocator['xpath_new_thumbnails']))
				break;
			default:
				break;
		}
		return this
	}

	public VisualTestRunDetail selectComparisonMethod(String method) {
		WebUI.delay(2)
		click(xpath(pageLocator['id_comparison_method']))

		String dropdownOption = pageLocator.get('id_comparison_method') + "//div[text()=\"${method}\"]"
		TestObject option = xpath(dropdownOption)
		WebUI.delay(2)
		click(option)
		return this
	}

	/**
	 * Base on level we want to change we will create the list of Diff-count base on it
	 * Diff-count will be decrease by each level
	 * The range of level from 0 - > 4 (strict -> relaxed)
	 * @param level - how many number you want to change 
	 * @return 
	 */
	public VisualTestRunDetail changePixelSensitivity(int level) {
		List<TestObject> slider = findTestObjects(pageLocator['xpath_pixel_sensity_slider'])
		for(int i = 1; i <= level; i++) {
			click(slider.get(i))
			int tempLevel = getDiffCount()
			diffCount.add(tempLevel)
		}
		return this
	}

	public VisualTestRunDetail isDiffCountDecreased() {
		boolean result = streamDiffCount()
		WebUI.verifyEqual(result, true)
		return this
	}

	def int getDiffCount() {
		String diffCountMatchLevel = WebUI.getText(xpath(pageLocator['xpath_diff_count']))
		return common.substringUseRegExp(diffCountMatchLevel, "\\d+") as Integer
	}

	/** 
	 * less than 0 that means diff count decrease from strict to relaxed
	 */
	def boolean astreamDiffCount()  {
		return IntStream.range(0, diffCount.size() - 1)
				.allMatch({i ->
					i.compareTo(i+1) < 0
				})
	}

	def boolean isStatusBarDisplay(boolean expectedResult) {
		assert expectedResult == WebUI.verifyElementPresent(xpath(pageLocator['status_visual_test_detail']), 0)
	}

	def boolean isSearchInputDisplay() {
		return WebUI.verifyElementPresent(xpath(pageLocator['search_input']),0)
	}

	def boolean isObjectStatusDisplay(boolean expectedResult) {
		List<TestObject> testrun_info = findTestObjects(pageLocator['testRun_info'])
		def actualChildElements = testrun_info.size() == 4
		assert actualChildElements == expectedResult
	}

	def boolean isStatusFilterDisplay(boolean expectedResult) {
		assert WebUI.verifyElementPresent(xpath("//div[@class='filter-btn-content']"), 0) == expectedResult
	}

	def isCheckpointThumbnailDisplay(String status) {
		List<TestObject> listTO = findTestObjects(thumbnail_image_status(status))
		assert listTO.size() >= 1;
	}

	public VisualTestRunDetail inputSearchStatus(String searchString) {
		WebUI.clearText(xpath(pageLocator['search_input']))
		WebUI.setText(xpath(pageLocator['search_input']), searchString.toUpperCase())
		WebUI.sendKeys(xpath(pageLocator['search_input']), Keys.chord(Keys.ENTER))
		return this
	}

	public VisualTestRunDetail searchCondition(String searchInput) {
		switch(searchInput.toUpperCase()) {
			case "MISMATCH":
				click(xpath(pageLocator['xpath_mismatch_thumbnails']))
				break;
			case "MISSING":
				click(xpath(pageLocator['xpath_missing_thumbnails']))
				break;
			case "NEW":
				click(xpath(pageLocator['xpath_new_thumbnails']))
				break;
			default:
				break;
		}
		return this
	}
}

