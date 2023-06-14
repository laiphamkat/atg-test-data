package katalon.enums

public enum IssueStatus {
	TODO(11),
	INPROGRESS(21),
	DONE(31)

	Number value

	IssueStatus(Number value) {
		this.value = value
	}


	public Number getValue() {
		return value;
	}
}