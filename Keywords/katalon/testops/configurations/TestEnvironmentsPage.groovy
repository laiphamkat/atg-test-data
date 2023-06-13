package katalon.testops.configurations

import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BasePage

public class TestEnvironmentsPage extends BasePage<TestEnvironmentsPage> {
	TestObject pageTitle = css("#main-body [title='Test Environments']")
	def dropdownOption = { String option -> return xpath("//div[contains(@id, 'option') and text()='${option}']") }
	
	// Local Agents
	TestObject allStatus = css("tbody td:nth-child(1) span")
	TestObject allNames = css("tbody td:nth-child(2)")
	TestObject allIPs = css("tbody td:nth-child(3)")
	TestObject allUUIDs = css("tbody td:nth-child(4)")
	TestObject allAssignedSessions = css("tbody td:nth-child(5)")
	TestObject allExecutingSessions = css("tbody td:nth-child(6)")
	TestObject allLastPing = css("tbody td:nth-child(7)")
	TestObject allOS = css("tbody td:nth-child(8) span.platform-info span[title]:nth-child(1)")
	TestObject allHostNames = css("tbody td:nth-child(9)")
	TestObject allDeleteAgentBtns = css('tbody td:nth-child(10) [title="Delete Agent"]')
	def deleteBtn = { int rowIndex -> return css("tbody tr:nth-child($rowIndex) td:nth-child(10) button") }
	def localAgentName = { String name -> return xpath("//tbody//tr[.//a[. = '${name}']]//td[2]//a") }
	
	// Kubernetes
	TestObject allK8sNames = css("tbody td:nth-child(1) a")
	TestObject allK8sUrls = css("tbody td:nth-child(2)")
	TestObject allK8sNamespaces = css("tbody td:nth-child(3)")
	def k8sName = { String name -> return xpath("//tbody//tr[.//a[. = '${name}']]//td[1]//a") }
	
	// CircleCI
	TestObject allCircleCINames = css("tbody td:nth-child(1) a")
	TestObject allCircleCIUrls = css("tbody td:nth-child(2)")
	TestObject allCircleCIFollowedProjects = css("tbody td:nth-child(3)")
	TestObject allCircleCIBranches = css("tbody td:nth-child(4)")
	TestObject allCircleCIVersionControlTypes = css("tbody td:nth-child(5)")
	def circleCIName = { String name -> return xpath("//tbody//tr[.//a[. = '${name}']]//td[1]//a") }
	
	// TestCloud
	def tcName = { int rowIndex -> return xpath("//tbody//tr[${rowIndex}]//td[1]//a") }

	public TestEnvironmentsPage navigateTo(String teamId, String projectId) {
		WebUI.navigateToUrl("${GlobalVariable.testOpsUrl}team/${teamId}/project/${projectId}/agent")
		return this
	}

	public TestEnvironmentsPage verifyPageDisplayed() {
		WebUI.waitForElementVisible(pageTitle, 0)
		WebUI.verifyElementVisible(pageTitle)
		return this
	}

	public TestEnvironmentsPage clickLocalTab() {
		WebUI.click(link('Local'))
		return this
	}

	public TestEnvironmentsPage clickKubernetesTab() {
		WebUI.click(link('Kubernetes'))
		return this
	}

	public TestEnvironmentsPage waitUntilKubernetesLoaded() {
		WebUI.waitForElementVisible(css("//th[text()='Namespace']"), 5)
		return this
	}

	public TestEnvironmentsPage clickCircleCITab() {
		WebUI.click(link('CircleCI'))
		return this
	}

	public TestEnvironmentsPage waitUntilCircleCILoaded() {
		WebUI.waitForElementVisible(css("//th[text()='Version Control Type']"), 5)
		return this
	}

	public TestEnvironmentsPage clickTestCloudTab() {
		WebUI.click(link('TestCloud'))
		return this
	}

	public TestEnvironmentsPage clickCreateLocalTestEnvironment() {
		WebUI.click(css('a[title="Create Local Test Environment"]'))
		return this
	}

	public TestEnvironmentsPage clickCreateKubernetesTestEnvironment() {
		WebUI.click(css('a[title="Create Kubernetes Test Environment"]'))
		return this
	}

	public TestEnvironmentsPage clickCreateCircleCITestEnvironment() {
		WebUI.click(css('a[title="Create CircleCI Test Environment"]'))
		return this
	}

	public TestEnvironmentsPage verifyTotalLabelDisplayed() {
		WebUI.verifyElementVisible(css('.status-bar-v2__item:nth-child(1) .counter__label'))
		return this
	}

	public TestEnvironmentsPage verifyOnlineLabelDisplayed() {
		WebUI.verifyElementVisible(css('.status-bar-v2__item:nth-child(2) .counter__label'))
		return this
	}

	public TestEnvironmentsPage verifyOfflineLabelDisplayed() {
		WebUI.verifyElementVisible(css('.status-bar-v2__item:nth-child(3) .counter__label'))
		return this
	}

	private List<Map> getAllLocalAgentsOnPage() {
		List<WebElement> status = WebUI.findWebElements(allStatus, 10)
		List<WebElement> names = WebUI.findWebElements(allNames, 10)
		List<WebElement> ips = WebUI.findWebElements(allIPs, 10)
		List<WebElement> uuids = WebUI.findWebElements(allUUIDs, 10)
		List<WebElement> assignedSessions = WebUI.findWebElements(allAssignedSessions, 10)
		List<WebElement> executingSessions = WebUI.findWebElements(allExecutingSessions, 10)
		List<WebElement> lastPing = WebUI.findWebElements(allLastPing, 10)
		List<WebElement> os = WebUI.findWebElements(allOS, 10)
		List<WebElement> hostNames = WebUI.findWebElements(allHostNames, 10)
		List<WebElement> allDeletes = WebUI.findWebElements(allDeleteAgentBtns, 10)
		List<Map> actualAgents = []

		for (int i = 0; i < status.size(); i++) {
			actualAgents.add([
				'Status': status.get(i).getAttribute('title'),
				'Name': names.get(i).getText(),
				'IP': ips.get(i).getText(),
				'UUID': uuids.get(i).getText(),
				'AssignedSessions': assignedSessions.get(i).getText(),
				'ExecutingSessions': executingSessions.get(i).getText(),
				'LastPing': lastPing.get(i).getText(),
				'OS': os.get(i).getAttribute('title'),
				'HostName': hostNames.get(i).getText(),
				'DeleteBtn': allDeletes.get(i)
			])
		}

		return actualAgents
	}

	/**
	 * To verify, on the page, there should be an agent that matches your expected
	 * @param expectedAgent This param should be in a map with this format ['Status': 'Expected Status', 'Name': 'Expected Name',...].
	 * It will find an agent that has status/name/etc equals to the value you provided
	 * If you do not pass a column like status/os/etc., it will not verify equal for that column.
	 * But still verify the actual value of that column matches a default expected format.
	 * @return
	 */
	public TestEnvironmentsPage verifyLocalAgentExist(Map expectedAgent) {
		List<Map> actualAgents = getAllLocalAgentsOnPage()

		Map agent = actualAgents.find {
			(expectedAgent['Status'] != null) ? it['Status'] == expectedAgent['Status'] : true && // Verify equal if not null. Otherwise, ignore it
			it['Name'] == expectedAgent['Name'] &&
			(expectedAgent['IP'] != null) ? it['IP'] == expectedAgent['IP'] : true && // Verify equal if not null. Otherwise, ignore it
			(expectedAgent['AssignedSessions'] != null) ? it['AssignedSessions'] == expectedAgent['AssignedSessions'] : true && // Verify equal if not null. Otherwise, ignore it
			(expectedAgent['ExecutingSessions'] != null) ? it['ExecutingSessions'] == expectedAgent['ExecutingSessions'] : true && // Verify equal if not null. Otherwise, ignore it
			(expectedAgent['OS'] != null) ? it['OS'] == expectedAgent['OS'] : true && // Verify equal if not null. Otherwise, ignore it
			(expectedAgent['HostName'] != null) ? it['HostName'] == expectedAgent['HostName'] : true // Verify equal if not null. Otherwise, ignore it
		}

		// Verify should contain an agent as expected
		WebUI.verifyNotEqual(agent, null)

		// Verify data of each columns should match the default expected formats/values
		WebUI.verifyEqual(agent['Status'] == 'Online' || agent['Status'] == 'Offline', true)
		WebUI.verifyMatch(agent['IP'], '[0-9\\\\.]+', true)
		WebUI.verifyMatch(agent['UUID'], '[a-z0-9\\\\-]+', true)
		WebUI.verifyMatch(agent['AssignedSessions'], '[0-9]+\\/[0-9]+', true)
		WebUI.verifyMatch(agent['ExecutingSessions'], '[0-9]+', true)
		WebUI.verifyNotEqual(agent['LastPing'], '')
		WebUI.verifyEqual(agent['OS'] == 'Macos (app)' || agent['OS'] == 'Windows 64', true)
		WebUI.verifyMatch(agent['HostName'], '[a-zA-Z0-9\\\\.\\\\-]+', true)
		WebUI.verifyEqual(((WebElement) agent['DeleteBtn']).isDisplayed(), true)

		return this
	}

	public TestEnvironmentsPage clickLocalAgentName(String name) {
		waitUntilElementClickableWithWebDriverWait(localAgentName(name), 10)
		clickByJS(localAgentName(name))
		return this
	}

	public TestEnvironmentsPage clickDeleteLocalAgent(int rowIndex) {
		WebUI.click(deleteBtn(rowIndex))
		return this
	}

	public TestEnvironmentsPage clickCancelDelete() {
		WebUI.click(btn('Cancel'))
		return this
	}

	private List<Map> getAllKubernetesOnPage() {
		List<WebElement> names = WebUI.findWebElements(allK8sNames, 10)
		List<WebElement> urls = WebUI.findWebElements(allK8sUrls, 10)
		List<WebElement> namespaces = WebUI.findWebElements(allK8sNamespaces, 10)
		List<Map> actualK8s = []

		for (int i = 0; i < names.size(); i++) {
			actualK8s.add([
				'Name': names.get(i).getText(),
				'URL': urls.get(i).getText(),
				'Namespace': namespaces.get(i).getText()
			])
		}

		return actualK8s
	}

	public TestEnvironmentsPage verifyKubernetesExist(Map expectedK8s) {
		List<Map> actualK8s = getAllKubernetesOnPage()

		Map k8s = actualK8s.find {
			it['Name'] == expectedK8s['Name'] &&
			it['URL'] == expectedK8s['URL'] &&
			it['Namespace'] == expectedK8s['Namespace']
		}

		// Verify should contain a k8s environment as expected
		WebUI.verifyNotEqual(k8s, null)

		return this
	}

	public TestEnvironmentsPage clickK8SName(String name) {
		waitUntilElementClickableWithWebDriverWait(k8sName(name), 10)
		clickByJS(k8sName(name))
		return this
	}

	public TestEnvironmentsPage clickK8sDocLink() {
		WebUI.click(css(".alert-primary a"))
		return this
	}

	private List<Map> getAllCircleCIOnPage() {
		List<WebElement> names = WebUI.findWebElements(allCircleCINames, 10)
		List<WebElement> urls = WebUI.findWebElements(allCircleCIUrls, 10)
		List<WebElement> projects = WebUI.findWebElements(allCircleCIFollowedProjects, 10)
		List<WebElement> branches = WebUI.findWebElements(allCircleCIBranches, 10)
		List<WebElement> types = WebUI.findWebElements(allCircleCIVersionControlTypes, 10)
		List<Map> actualCircleCI = []

		for (int i = 0; i < names.size(); i++) {
			actualCircleCI.add([
				'Name': names.get(i).getText(),
				'URL': urls.get(i).getText(),
				'FollowedProject': projects.get(i).getText(),
				'Branch': branches.get(i).getText(),
				'VersionControlType': types.get(i).getText()
			])
		}

		return actualCircleCI
	}

	public TestEnvironmentsPage verifyCircleCIExist(Map expectedCircleCI) {
		List<Map> actualCirclCIs = getAllCircleCIOnPage()

		Map circleCI = actualCirclCIs.find {
			it['Name'] == expectedCircleCI['Name'] &&
			it['URL'] == expectedCircleCI['URL'] &&
			it['FollowedProject'] == expectedCircleCI['FollowedProject'] &&
			it['Branch'] == expectedCircleCI['Branch'] &&
			it['VersionControlType'] == expectedCircleCI['VersionControlType']
		}

		// Verify should contain a CircleCI environment as expected
		WebUI.verifyNotEqual(circleCI, null)

		return this
	}

	public TestEnvironmentsPage clickCircleCIName(String name) {
		waitUntilElementClickableWithWebDriverWait(circleCIName(name), 10)
		clickByJS(circleCIName(name))
		return this
	}

	public TestEnvironmentsPage clickCircleCIDocLink() {
		WebUI.click(css(".alert-primary a"))
		return this
	}

	public TestEnvironmentsPage clickTCName(int rowIndex) {
		waitUntilElementClickableWithWebDriverWait(tcName(rowIndex), 10)
		clickByJS(tcName(rowIndex))
		return this
	}
}
