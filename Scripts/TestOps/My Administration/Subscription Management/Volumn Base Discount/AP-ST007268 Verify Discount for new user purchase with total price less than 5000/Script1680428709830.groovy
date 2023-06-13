import com.kms.katalon.core.util.KeywordUtil

KeywordUtil.markFailed("This test should be performed by manual testing")

'1. Purchase TO quota 3500 (price: 350)'
'2. Purchase KSE quota 1 (price: 1999)'
'3. Purchase KRE quota 1 (price: 1599)'
'4. Purchase TO quota 3500 and TestCloud quota 1 (price: 350+1449)'
'5. Purchase TO quota 3500 and Visual Testing quota 20000 (price: 350+1449)'
'6. Verify all are applied discount 30%'