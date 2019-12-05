package com.socks.api.services;

import com.socks.api.assertions.AssertableResponse;
import com.socks.api.payloads.UserPayload;
import io.qameta.allure.Step;

public class UserApiService extends ApiService {

    @Step
    public AssertableResponse registerUser(UserPayload userPayload) {
        return new AssertableResponse(setUp()
                .body(userPayload)
                .when()
                .post("register"));
    }
}
