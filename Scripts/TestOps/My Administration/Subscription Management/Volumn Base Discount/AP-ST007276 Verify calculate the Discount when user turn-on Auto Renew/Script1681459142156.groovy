import com.kms.katalon.core.util.KeywordUtil

KeywordUtil.markFailed("This test should be performed by manual testing")

'''
I. Verify after turn off auto-renew subscription and try to re-activate again will count on discount price on product re-activate successfully
Pre-condition:
- Subscription Management opening
- Org purchased volume base plan with: KSE per-User, KRE Floating, TO Platform, TestCloud, Visual Testing

Steps:
1. Turn off auto renew KSE per-User
2. Re-activate KSE per-User again
3. Update next billing date close to current date
4. Waiting until renewed new billing cycle

Continues with other products:
5. Turn off and turn on auto renew KRE Floating
6. Turn off and turn on auto renew TO Platform
7. Turn off and turn on  auto renew TestCloud
8. Turn off and turn on  auto renew Visual Testing

1. Create new pending change subscription
3,4,5,6,7. After renewing will count on discount price on product re-activated
'''