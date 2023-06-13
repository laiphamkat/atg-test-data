package katalon.services

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BaseService
import katalon.fw.lib.Page

public class UserManagementServices extends BaseService<UserManagementServices> {
	static String inviteUserUrl = "${GlobalVariable.myApi}${GlobalVariable.version}/user-invitations/"
	static String acceptInvitationUrl = "${GlobalVariable.myApi}${GlobalVariable.version}/user-invitations/"
	static String searchUserUrl = "${GlobalVariable.myApi}${GlobalVariable.version}/org-users"

	public Object invite(String email, String orgId, List<String> assignedFeatures = []) {
		def body = ["invitedUserEmail": email, "organizationId": orgId, "assignedFeatures": assignedFeatures, "ssoOptions": []]
		initRequestObject()
				.setUrl(inviteUserUrl)
				.setBearerAuthorizationHeader()
				.setJsonContentTypeHeader()
				.setPayLoad(parseObjectToString(body))
				.sendPostRequest()
				.verifyStatusCode(200)
		return parseResponseBodyToJsonObject().data
	}

	// TODO-TRIS: I know it is duplicate,
	// But I don't want to break any feature tests
	// I will change all id param type from String to long -> plz wait... another PR come soon
	public UserManagementServices inviteToOrg(String email, long orgId, List<String> assignedFeatures = []) {
		def body = ["invitedUserEmail": email, "organizationId": orgId, "assignedFeatures": assignedFeatures, "ssoOptions": []]
		initRequestObject()
				.setUrl(inviteUserUrl)
				.setBearerAuthorizationHeader()
				.setJsonContentTypeHeader()
				.setPayLoad(parseObjectToString(body))
				.sendPostRequest()
				.verifyStatusCode(200)
		return this
	}

	public UserManagementServices acceptInvitation(long invitationId, String invitationToken) {
		def body = ["id": invitationId,  "accepted": true, "invitationToken": invitationToken]
		initRequestObject()
				.setUrl(String.format(acceptInvitationUrl, invitationId))
				.setBearerAuthorizationHeader()
				.setJsonContentTypeHeader()
				.setPayLoad(parseObjectToString(body))
				.sendPutRequest()
		return this
	}

	public UserManagementServices removeUser(String email, String orgId) {
		String userId = getUserIdByOrgId(email, orgId)

		if(!userId) return this
		
		initRequestObject()
				.setBearerAuthorizationHeader()
				.setUrl(searchUserUrl+"/$userId")
				.sendDeleteRequest()
				.verifyStatusCode(200)
		return this
	}

	public String getUserIdByOrgId( String email, String orgId) {
		def body = [
			"filters":[
				[ "field": "organizationId", "operator": "EQ", "value": orgId],
				[ "field": "user.email", "operator": "EQ", "value": email]]
		]
		def result = searchUser(body)
		return result.total == 0 ? null : result.data[0].id
	}

	public UserManagementServices verifyUserNotExistInOrg(String email, String orgId) {
		def body = [
			"filters":[
				[ "field": "organizationId", "operator": "EQ", "value": orgId],
				[ "field": "user.email", "operator": "EQ", "value": email]]
		]
		def result = searchUser(body)
		WebUI.verifyEqual(result.total, 0)
		return this
	}
	
	public Object getUserInvitation(def bodySearch) {
		initRequestObject()
			.setUrl(inviteUserUrl)
			.setBearerAuthorizationHeader()
			.setJsonContentTypeHeader()
			.setPayLoad(parseObjectToString(bodySearch))
			.sendGetRequest()
			.verifyStatusCode(200)
		return parseResponseBodyToJsonObject()
	}

	public void addUserToOrg(String email, String orgId, List<String> assignedFeatures = []) {
		def result = invite(email, orgId)
		acceptInvitation(result.id, result.invitationTokenn)
	}

	private Object searchUser(def bodySearch) {
		initRequestObject()
				.setUrl(searchUserUrl)
				.setBearerAuthorizationHeader()
				.setJsonContentTypeHeader()
				.setPayLoad(parseObjectToString(bodySearch))
				.sendGetRequest()
				.verifyStatusCode(200)
		return parseResponseBodyToJsonObject()
	}
}
