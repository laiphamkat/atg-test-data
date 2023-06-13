import com.kms.katalon.core.util.KeywordUtil

KeywordUtil.markFailedAndStop('This test case should be executed manually')

'Preconditon: A user unverified email and finished survey on TO'

'1. Valid login into TestOps.'
'2. Click on any page on TO (excluding Onboarding pages).'
'3. Verify:'
'The banner is shown at all tabs at all page(excluding Onboarding pages) below navigation bar'
'The alert message "Verify your account within 3 days to avoid account deactivation. Check your inbox for the link. Need a new link? Resend Verification Email." should be displayed.'
'4. Click on Resend Verification Email hyperlink'
'5. Verify'
'Show toast message "An email has been resent to [user email]'
'6. Open mailbox'
'7. Click on email with title "Verify your Katalon account"'
'8. Click on "Verify my account"'
'9. Verify'
'The Account verified popup is shown.'
'The email has been verified.'
'10. Back to any page on TO'
'11. Verify'
'The banner email is not visible.'