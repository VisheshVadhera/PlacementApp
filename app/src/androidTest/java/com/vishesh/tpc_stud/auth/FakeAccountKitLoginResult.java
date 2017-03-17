package com.vishesh.tpc_stud.auth;

import android.os.Parcel;
import android.support.annotation.Nullable;

import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;

class FakeAccountKitLoginResult implements AccountKitLoginResult {

    private final AccessToken accessToken;
    private final String authorizationCode;
    private final boolean cancelled;
    private final AccountKitError error;
    private final String finalAuthorizationState;
    private final long tokenRefreshIntervalInSeconds;

    public static final Creator<FakeAccountKitLoginResult> CREATOR = new Creator() {
        public FakeAccountKitLoginResult createFromParcel(Parcel source) {
            return new FakeAccountKitLoginResult(source);
        }

        public FakeAccountKitLoginResult[] newArray(int size) {
            return new FakeAccountKitLoginResult[size];
        }
    };

    public FakeAccountKitLoginResult(AccessToken accessToken,
                                      String authorizationCode,
                                      boolean cancelled,
                                      AccountKitError error,
                                      String finalAuthorizationState,
                                      long tokenRefreshIntervalInSeconds) {
        this.accessToken = accessToken;
        this.authorizationCode = authorizationCode;
        this.cancelled = cancelled;
        this.error = error;
        this.finalAuthorizationState = finalAuthorizationState;
        this.tokenRefreshIntervalInSeconds = tokenRefreshIntervalInSeconds;
    }

    @Nullable
    @Override
    public AccessToken getAccessToken() {
        return accessToken;
    }

    @Nullable
    @Override
    public String getAuthorizationCode() {
        return authorizationCode;
    }

    @Nullable
    @Override
    public AccountKitError getError() {
        return error;
    }

    @Nullable
    @Override
    public String getFinalAuthorizationState() {
        return finalAuthorizationState;
    }

    @Override
    public long getTokenRefreshIntervalInSeconds() {
        return tokenRefreshIntervalInSeconds;
    }

    @Override
    public boolean wasCancelled() {
        return this.error == null && this.authorizationCode == null && this.accessToken == null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.accessToken, flags);
        dest.writeString(this.authorizationCode);
        dest.writeString(this.finalAuthorizationState);
        dest.writeLong(this.tokenRefreshIntervalInSeconds);
        dest.writeParcelable(this.error, flags);
        dest.writeByte((byte)(this.cancelled?1:0));
    }

    private FakeAccountKitLoginResult(Parcel parcel) {
        this.accessToken = (AccessToken)parcel.readParcelable(AccessToken.class.getClassLoader());
        this.authorizationCode = parcel.readString();
        this.finalAuthorizationState = parcel.readString();
        this.tokenRefreshIntervalInSeconds = parcel.readLong();
        this.error = (AccountKitError)parcel.readParcelable(AccountKitError.class.getClassLoader());
        this.cancelled = parcel.readByte() == 1;
    }
}
