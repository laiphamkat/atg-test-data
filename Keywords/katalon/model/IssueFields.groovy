package katalon.model

public class IssueFields {
	Object project;
	Object issuetype;
	String summary;

	public IssueFields(Object project, Object issuetype, String summary) {
		this.project = project
		this.issuetype = issuetype
		this.summary = summary
	}
}