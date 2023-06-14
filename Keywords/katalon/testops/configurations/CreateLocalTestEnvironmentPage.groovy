package katalon.testops.configurations

import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BasePage

public class CreateLocalTestEnvironmentPage extends BasePage<CreateLocalTestEnvironmentPage> {
	TestObject allStatus = xpath("tbody td:nth-child(1) span")
	TestObject allNames = xpath("tbody td:nth-child(2)")
	TestObject allIPs = xpath("tbody td:nth-child(3)")
	TestObject allUUIDs = xpath("tbody td:nth-child(4)")
	TestObject allAssignedSessions = xpath("tbody td:nth-child(5)")
	TestObject allExecutingSessions = xpath("tbody td:nth-child(6)")
	TestObject allLastPing = xpath("tbody td:nth-child(7)")
	TestObject allOS = xpath("tbody td:nth-child(8) span.platform-info span[title]:nth-child(1)")
	TestObject allHostNames = xpath("tbody td:nth-child(9)")
	TestObject allDeleteAgentBtns = css('tbody td:nth-child(10) [title="Delete Agent"]')
	def deleteBtn = { rowIndex -> return css("tbody tr:nth-child($rowIndex) td:nth-child(10) button") }
	def dropdownOption = { String option -> return xpath("//div[contains(@id, 'option') and text()='${option}']") }

	public CreateLocalTestEnvironmentPage clickLocalEnvironmentTab() {
		WebUI.click(btn('Local Environment'))
		return this
	}

	public CreateLocalTestEnvironmentPage clickDockerEnvironmentTab() {
		WebUI.click(btn('Docker Environment'))
		return this
	}

	public CreateLocalTestEnvironmentPage clickOSDropdown() {
		WebUI.click(css('[for="agentName"] ~ div'))
		return this
	}

	public CreateLocalTestEnvironmentPage selectOS(String os) {
		WebUI.click(dropdownOption(os))
		return this
	}

	public CreateLocalTestEnvironmentPage clickDownloadAgent() {
		WebUI.click(btn('Download Agent'))
		return this
	}

	public CreateLocalTestEnvironmentPage inputAgentName(String name) {
		WebUI.setText(id('agentName'), name)
		return this
	}

	public CreateLocalTestEnvironmentPage clickAPIKeyDropdown() {
		WebUI.click(css('[for="apiKey"] ~ div'))
		return this
	}

	public CreateLocalTestEnvironmentPage selectAPIKey(String apiKey) {
		WebUI.click(dropdownOption(apiKey))
		return this
	}

	public CreateLocalTestEnvironmentPage clickAPIKeyLink() {
		WebUI.click(css('a[href="/user/apikey"]'))
		return this
	}

	public CreateLocalTestEnvironmentPage clickCopyConfig() {
		WebUI.click(xpath("(//div[contains(@class, 'code-box')]//a)[1]"))
		return this
	}

	private CreateLocalTestEnvironmentPage verifyConfigLocalAgentCommandFromClipboard(String prefix, String userEmail, String userAPIKey, String teamId, String agentName) {
		String url = ((String) GlobalVariable.testOpsUrl).replaceAll('[\\\\/]$', '')
		String expected = "${prefix} config --server-url ${url} --username ${userEmail} --apikey ${userAPIKey} --teamid ${teamId} --agent-name \"${agentName}\""
		WebUI.verifyEqual((String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor), expected)
		return this
	}

	public CreateLocalTestEnvironmentPage verifyWin64ConfigLocalAgentCommandFromClipboard(String userEmail, String userAPIKey, String teamId, String agentName) {
		verifyConfigLocalAgentCommandFromClipboard('cli-win-x64', userEmail, userAPIKey, teamId, agentName)
		return this
	}

	public CreateLocalTestEnvironmentPage verifyConfigDockerComposeCommandFromClipboard(String userEmail, String userAPIKey, String teamId, String agentName) {
		String url = ((String) GlobalVariable.testOpsUrl).replaceAll('[\\\\/]$', '')
		String expected = """version: "3.2"
		services:
		  katalon-agent:
		    image: "katalonstudio/agent"
		    restart: always
		    deploy:
		      mode: replicated
		      replicas: 1
		    environment:
		      SERVER_URL: ${url}
		      KATALON_USERNAME: ${userEmail}
		      AGENT_NAME: ${agentName}
		      TEAM_ID: ${teamId}
		      KATALON_API_KEY: ${userAPIKey}
		    volumes:
		      - katalon-runtime-engines:/root/.katalon
		
		volumes:
		  katalon-runtime-engines:"""

		WebUI.verifyEqual((String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor), expected.replaceAll('		', '')) // Replace the indentation on Katalon Studio
		return this
	}

	public CreateLocalTestEnvironmentPage clickCopyStart() {
		WebUI.click(xpath("(//div[contains(@class, 'code-box')]//a)[2]"))
		return this
	}

	public CreateLocalTestEnvironmentPage clickCopyStartDockerRunCommand() {
		WebUI.click(xpath("(//div[contains(@class, 'code-box')]//a)[1]"))
		return this
	}

	public CreateLocalTestEnvironmentPage verifyWin64StartLocalAgentCommandFromClipboard() {
		WebUI.verifyEqual((String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor), 'cli-win-x64 start-agent')
		return this
	}

	public CreateLocalTestEnvironmentPage verifyStartDockerComposeCommandFromClipboard() {
		WebUI.verifyEqual((String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor), 'docker-compose up -d')
		return this
	}

	public CreateLocalTestEnvironmentPage verifyStartDockerRunCommandFromClipboard(String userEmail, String userAPIKey, String teamId, String agentName) {
		String url = ((String) GlobalVariable.testOpsUrl).replaceAll('[\\\\/]$', '')
		String expected = "docker run -t -d --restart always --mount source=katalon-runtime-engines,target=/root/.katalon -e SERVER_URL=${url} -e KATALON_USERNAME=${userEmail} -e AGENT_NAME=${agentName} -e TEAM_ID=${teamId} -e KATALON_API_KEY=${userAPIKey} katalonstudio/agent:latest"
		WebUI.verifyEqual((String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor), expected)
		return this
	}

	public CreateLocalTestEnvironmentPage clickStartDockerWithDropdown() {
		WebUI.click(css('[for="start-with"] ~ div'))
		return this
	}

	public CreateLocalTestEnvironmentPage selectStartDockerWith(String value) {
		WebUI.click(dropdownOption(value))
		return this
	}

	public CreateLocalTestEnvironmentPage clickTestEnvironmentsLink() {
		WebUI.click(css(".MuiStepContent-last a"))
		return this
	}
}
