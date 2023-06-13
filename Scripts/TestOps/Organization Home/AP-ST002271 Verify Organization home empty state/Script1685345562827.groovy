import com.kms.katalon.core.util.KeywordUtil

KeywordUtil.markFailedAndStop('This test case should be executed manually')

"Preconditon: Invited user Admin or Invited user Member role"

"1.Valid login into TestOps."
"2.Access Org's home with no projects"
"3.Verify:"
"Invited user Admin: can see setup project guide"
"Invited user Member: can see guide to help them get invited to project or explore their default org"
"4.Invited user Admin click on Create New project/ Learn more"
"5.Verify:"
"-Direct to Project creation page when click on Create New project"
"-Direct to https://docs.katalon.com/docs/administer/administration-tasks/create-an-organization-and-project#create-a-new-project-on-katalon-testops when click on Learn more"