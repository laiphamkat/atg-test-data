package katalon.model

public class EpicIssueFields extends IssueFields{
	String customfield_10011;

	public EpicIssueFields(Object project, Object issuetype, String summary, String epicName) {
		super(project, issuetype, summary)
		this.customfield_10011 = epicName
	}
}