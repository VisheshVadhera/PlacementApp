## Synopsis

An Android app which lets students connect to the recruiters visiting campus. This app is a Work In Progress.

## Motivation

The process of inviting companies to visit the college campus for hiring students is long and tedious with lots of manual work (sending invites, tracking the dates of visit) and excel sheets! Why not make this process super simple & hassle free by having an app which connects students & recruiters directly?

## What does it have

* MVP with Clean Architecture.
* Dependency Injection.
* Unit Tests: Written with JUnit & Mockito. Tests the Presenters, UseCases and the Repositories in isolation. Find them in the [`src/test`](/app/src/test) directory.
* Functional UI Tests: Written with the help of Espresso & Stubbed Retrofit Service classes, they are intended to test that the app works as expected from the user's point of view. Find them in the [`src/androidTest`](/app/src/androidTest) directory.
* Password less email login using Facebook AccountKit.
* And lots more!

## List of TODOs

* File upload feature using persistent job queue.
* An activity showing full job description.
* Notifications.
* Integration tests for tetsing integration of OkHttp, Retrofit, Json Parsing & RxJava.
* Java docs.
* Another version of the app built using MVI (Model View Intent) pattern. (Just for fun's sake!)
* Develop the backend. App right now uses mocked responses using mocked Retrofit services.

## Libraries

 * Dagger - http://square.github.io/dagger
 * ButterKnife - http://jakewharton.github.io/butterknife
 * Retrofit - http://square.github.io/retrofit
 * OkHttp - http://square.github.io/okhttp
 * RxJava - https://github.com/ReactiveX/RxJava
 * RxBindings - https://github.com/JakeWharton/RxBinding
 * Android Iconics - https://github.com/mikepenz/Android-Iconics
 * PermissionsDispatcher - https://github.com/hotchemi/PermissionsDispatcher
 * Espresso - https://developer.android.com/training/testing/ui-testing/espresso-testing.html
 * JUnit - https://github.com/junit-team/junit4
 * Mockito - https://github.com/mockito/mockito

## Build

 * Clone the project

   `
    git clone git@github.com:VisheshVadhera/PlacementApp.git
   `
 * Change the directory

    `
    cd PlacementApp
    `
 * Get your account kit credentials from [this](https://developers.facebook.com/docs/accountkit/android) and the following
   variables to the gradle.properties file.

   ```
    FACEBOOK_APP_ID=<facebook_app_id>
    ACCOUNT_KIT_CLIENT_TOKEN=<account_kit_client_token>
    BASE_URL=<base_url_with_trialing_slash>
   ```
 * Build in Android Studio and deploy!


