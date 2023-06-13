package katalon.services

import java.time.Duration
import java.util.function.Function

import org.openqa.selenium.support.ui.FluentWait
import org.openqa.selenium.support.ui.Wait

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords

import internal.GlobalVariable
import katalon.fw.lib.BaseService
import katalon.fw.lib.WebsocketClientEndpoint
import ro.skyah.comparator.CompareMode
import ro.skyah.comparator.JSONCompare

public class ProvisioningService extends BaseService<ProvisioningService> {
	private String wsEngine
	private String wsMessage

	public ProvisioningService getStatus() {
		GlobalVariable.tcSessionStatus = parseResponseBodyToJsonObject().status
		return this
	}

	public ProvisioningService getWS() {
		wsEngine = parseResponseBodyToJsonObject().wsEngine
		return this
	}

	public ProvisioningService checkSessionStatus() {
		// TODO: refactor the way to pass headers to the this function
		initRequestObject().get("${GlobalVariable.testCloudUrl}ps/sessions/${GlobalVariable.tcSessionId}/status", [
			[
				'Authorization',
				"Bearer $GlobalVariable.encodedToken"
			]
		])
		return this
	}

	public ProvisioningService waitUntilStatus(String expectedStatus, long timeOut, long every) {
		Wait wait = new FluentWait('')
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(every))

		wait.until(new Function<String, Boolean>(){
					public Boolean apply(String str) {
						checkSessionStatus()
						getStatus()
						return GlobalVariable.tcSessionStatus == expectedStatus
					}
				});

		return this
	}

	public ProvisioningService waitUntilStatusREADY(long timeOut, long every) {
		return waitUntilStatus('READY', timeOut, every)
	}

	public ProvisioningService waitUntilStatusREADYTOSERVE(long timeOut, long every) {
		return waitUntilStatus('READY_TO_SERVE', timeOut, every)
	}

	public ProvisioningService waitUntilStatusTERMINATED(long timeOut, long every) {
		return waitUntilStatus('TERMINATED', timeOut, every)
	}

	public ProvisioningService connectToWS(long timeOut) {
		WebsocketClientEndpoint clientEndPoint

		// There is an issue that cannot connect to WS right away, so have to add delay here
		WSBuiltInKeywords.delay(3)

		try {
			clientEndPoint = new WebsocketClientEndpoint(new URI(wsEngine))
		} catch (RuntimeException ex) {
			// Try again
			WSBuiltInKeywords.delay(1)
			clientEndPoint = new WebsocketClientEndpoint(new URI(wsEngine))
		}
		clientEndPoint.waitUntilClosed(timeOut, 1)
		this.wsMessage = clientEndPoint.getWSMessage()
		return this
	}

	public ProvisioningService verifyWSLog(String expectedLogs) {
		JSONCompare.assertEquals(expectedLogs, "[${this.wsMessage}]", CompareMode.JSON_ARRAY_NON_EXTENSIBLE, CompareMode.JSON_OBJECT_NON_EXTENSIBLE, CompareMode.JSON_ARRAY_STRICT_ORDER)
		return this
	}
}
