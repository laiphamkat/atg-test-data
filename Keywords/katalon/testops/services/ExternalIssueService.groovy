package katalon.testops.services

import internal.GlobalVariable
import katalon.enums.ObjectTypes
import katalon.fw.lib.BaseService

public class ExternalIssueService extends BaseService<ExternalIssueService>{
	public static String linkExternalIssueUrl ="$GlobalVariable.testOpsApiUrl$GlobalVariable.version/external-issue?projectId=%s"

	public ExternalIssueService linkExternalIssue(Number projectId, String issueKey, ObjectTypes objectType, Number objectId) {
		def body = [
			"issueId": issueKey,
			"objectType": objectType,
			"objectId": objectId
		]
		initRequestObject()
				.create(body, String.format(linkExternalIssueUrl, projectId), [
					[
						'Authorization',
						"Basic $GlobalVariable.basicToken"
					]
				])

		return this
	}
	
	public ExternalIssueService changeIssueStatus(String status) {
		// TODO:Tris
		return this
	}
}
