# PayU-Android

## Documentations
* https://developers.payu.com/europe/docs/payment-flows/
* https://developers.payu.com/europe/docs/mobile-sdk/ (prepare your backend and mobile app for rest communication)

## Start
Android releases are available on jfrog. To preview it go to PayU's [jfrog artifactory](https://payu.jfrog.io/ui/native/mobile-sdk-gradle-local/). 

If you want to use it in your app add this line to gradle repositories:

```
maven { url "https://payu.jfrog.io/payu/mobile-sdk-gradle-local" }
```

## Languages available in the SDK
bulgarian, croatian, czech, english, french, german, greek, hungarian, lithuanian, polish, romanian, russian, slovak, slovenian, spanish, ukrainian

## Run test app
* Configure correctly this app please select in `app/src/main/res/values/payu.xml` file:
  ```
  <string name="payu_environment"></string>
  ```
  with production or sandbox string

* Add your POS and secret in com.payu.android.front.sdk.demo.repository.PersistentRepository
* Current settings of this app:
    * Env - sandbox
    * POS config - https://developers.payu.com/pl/overview.html#Testing

# Release version
* Update `project.ext.versionName` in [build.gradle](build.gradle)
* Update [CHANGELOG.md](CHANGELOG.md)
* Create PR with a name matching the following pattern `Release: ${project.ext.versionName}` - 
  SDK release is triggered by a commit to `main` with a message `Release: ***`
