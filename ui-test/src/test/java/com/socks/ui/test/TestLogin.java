package com.socks.ui.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.socks.api.conditions.Conditions;
import com.socks.api.payloads.UserPayload;
import com.socks.api.services.UserApiService;
import com.socks.ui.LoggedUserPage;
import com.socks.ui.MainPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

public class TestLogin extends BaseUITest {

    private final UserApiService userApiService = new UserApiService();

    @Test
    public void userCanLoginWithValidCredentials() {
    //given
        UserPayload userPayload = new UserPayload()
                .username(RandomStringUtils.randomAlphabetic(6))
                .password("123456")
                .email("demo@gmail.com");

        userApiService.registerUser(userPayload)
                .shouldHave(Conditions.statusCode(200));

        MainPage.open()
                .loginAs(userPayload.username(),userPayload.password());

        LoggedUserPage loggedUserPage = at(LoggedUserPage.class);
        loggedUserPage.logoutBtn().shouldHave(Condition.text("Logout"));
        loggedUserPage.userName().shouldHave(Condition.text("Logged in as"));
    }
}
