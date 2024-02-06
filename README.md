# PayU-Android

## Start
* https://developers.payu.com/pl/restapi.html
* https://developers.payu.com/en/mobile_sdk.html# (prepare your backend and mobile app for rest communication)

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
