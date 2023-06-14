import com.kms.katalon.core.util.KeywordUtil

KeywordUtil.markFailedAndStop('This test case should be executed manually')

"Pre-condition"
"The user created two accounts type: first user and invited user."
"First user is a user just signed up without redirect params."
"Invited user is a user just signed up without redirect params."

"1.Valid login into TestOps with first user/invited user"
"2.Verify:"
"-Navigate to the Welcome page."
"-New background are updated."
"-Change Choices -> choose."
"-Remove progress bar."
"3.Completed the questionaries."
"4.Verify:"
"-Redirect them to the default project’s Setup project page in case first user."
"-Redirect them to the destination page based on the redirect params in case invited user"
"Note:"
"-Including nav bar in questionary pages"
"-No Org => Direct to Org Creation"
"-No Project => Direct to Org's home with empty state"