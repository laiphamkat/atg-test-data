package katalon.testops.configurations

import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BasePage

public class TestCloudTunnelsPage extends BasePage<TestCloudTunnelsPage> {
	def dropdownOption = { String option -> return xpath("//div[contains(@id, 'option') and text()='${option}']") }
	def status = { int rowIndex -> return xpath("(//tbody//td[1])[${rowIndex}]") }
	def tunnelID = { int rowIndex -> return xpath("(//tbody//td[2])[${rowIndex}]") }
	def tunnelVersion = { int rowIndex -> return xpath("(//tbody//td[3])[${rowIndex}]") }
	def owner = { int rowIndex -> return xpath("(//tbody//td[4])[${rowIndex}]") }
	def tunnelType = { int rowIndex -> return xpath("(//tbody//td[5])[${rowIndex}]") }
	def duration = { int rowIndex -> return xpath("(//tbody//td[6])[${rowIndex}]") }
	TestObject allStatus = xpath("//tbody//td[1]")
	TestObject allTunnelId = xpath("//tbody//td[2]")
	TestObject allTunnelVersion = xpath("//tbody//td[3]")
	TestObject allOwner = xpath("//tbody//td[4]")
	TestObject allTunnelType = xpath("//tbody//td[5]")
	TestObject allDuration = xpath("//tbody//td[6]")

	public TestCloudTunnelsPage navigateTo(String teamId, String projectId) {
		WebUI.navigateToUrl("${GlobalVariable.testOpsUrl}team/${teamId}/project/${projectId}/testCloudTunnel")
		return this
	}
	
	public TestCloudTunnelsPage clickTunnels() {
		WebUI.click(btn('Tunnels'))
		return this
	}

	public TestCloudTunnelsPage clickSetup() {
		WebUI.click(btn('Setup'))
		return this
	}

	public TestCloudTunnelsPage clickOSDropdown() {
		WebUI.click(css('[for="osName"] ~ div'))
		return this
	}

	public TestCloudTunnelsPage selectOS(String os) {
		WebUI.click(dropdownOption(os))
		return this
	}

	public TestCloudTunnelsPage clickDownload() {
		WebUI.click(btn('Download'))
		return this
	}

	public TestCloudTunnelsPage clickAPIKeyDropdown() {
		WebUI.click(css('[for="apiKey"] ~ div'))
		return this
	}

	public TestCloudTunnelsPage selectAPIKey(String apiKey) {
		WebUI.click(dropdownOption(apiKey))
		return this
	}

	public TestCloudTunnelsPage clickSharedTunnel() {
		WebUI.click(id('kobiton-enable'))
		return this
	}

	public TestCloudTunnelsPage clickCopyConfig() {
		WebUI.click(xpath("(//div[contains(@class, 'code-box')]//a)[1]"))
		return this
	}

	public TestCloudTunnelsPage clickCopyStart() {
		WebUI.click(xpath("(//div[contains(@class, 'code-box')]//a)[2]"))
		return this
	}

	public TestCloudTunnelsPage clickTestRunTypesLink() {
		WebUI.click(link("Test Run Types"))
		return this
	}
	
	public TestCloudTunnelsPage verifyStatus(int rowIndex, String expectedStatus) {
		WebUI.verifyElementText(status(rowIndex), expectedStatus)
		return this
	}
	
	public TestCloudTunnelsPage verifyTunnelIdDisplayed(int rowIndex) {
		WebUI.verifyElementVisible(tunnelID(rowIndex))
		return this
	}
	
	public TestCloudTunnelsPage verifyTunnelVersion(int rowIndex, String expectedVersion) {
		WebUI.verifyElementText(tunnelVersion(rowIndex), expectedVersion)
		return this
	}
	
	public TestCloudTunnelsPage verifyOwner(int rowIndex, String expectedOwner) {
		WebUI.verifyElementText(owner(rowIndex), expectedOwner)
		return this
	}
	
	public TestCloudTunnelsPage verifyTunnelType(int rowIndex, String expectedType) {
		WebUI.verifyElementText(tunnelType(rowIndex), expectedType)
		return this
	}
	
	public TestCloudTunnelsPage verifyDurationDisplayed(int rowIndex) {
		WebUI.verifyElementVisible(duration(rowIndex))
		return this
	}
	
	public TestCloudTunnelsPage verifyActiveTunnelExist(Map expectedTunnel) {
		List<WebElement> status = WebUI.findWebElements(allStatus, 10)
		List<WebElement> id = WebUI.findWebElements(allTunnelId, 10)
		List<WebElement> version = WebUI.findWebElements(allTunnelVersion, 10)
		List<WebElement> owner = WebUI.findWebElements(allOwner, 10)
		List<WebElement> tunnelType = WebUI.findWebElements(allTunnelType, 10)
		List<WebElement> duration = WebUI.findWebElements(allDuration, 10)
		List<String> actualTunnels = []

		for (int i = 0; i < status.size(); i++) {
			actualTunnels.add([
				'Status': status.get(i).getText(),
				'Id': id.get(i).getText(),
				'Version': version.get(i).getText(),
				'Owner': owner.get(i).getText(),
				'TunnelType': tunnelType.get(i).getText(),
				'Duration': duration.get(i).getText()
			])
		}

		Map tunnel = actualTunnels.find { 
			it['Status'] == expectedTunnel['Status'] && 
			it['Version'] == expectedTunnel['Version'] &&
			it['Owner'] == expectedTunnel['Owner'] &&
			it['TunnelType'] == expectedTunnel['TunnelType'] 
		}
		
		// Verify should contain a tunnel as expected
		WebUI.verifyNotEqual(tunnel, null)
		WebUI.verifyMatch(tunnel['Id'], '[a-z0-9\\\\-]+', true)
		WebUI.verifyNotEqual(tunnel['Duration'], '')
		
		return this
	}
}
