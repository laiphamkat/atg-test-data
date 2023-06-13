import com.kms.katalon.core.util.KeywordUtil

KeywordUtil.markFailed('This test case should be executed manually')

"Pre-condition: a G4 repo contains Test Case's data binding"

"1. Valid login into TestOps."
"2. Upload G4 repo to TO."
"3. Created TO Test Suite with Test Case's data binding."
"4. Schedule Test Run with TS in step 3."
"5. Execute TO Test Suite by test cloud."
"6. Verify:"
"Test Cases can be executed with defined data"