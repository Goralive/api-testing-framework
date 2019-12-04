package com.socks.test;

import com.github.javafaker.Faker;
import com.socks.api.ProjectConfig;
import com.socks.api.payloads.UserPayload;
import com.socks.api.services.UserApiService;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Locale;

import static com.socks.api.conditions.Conditions.bodyField;
import static com.socks.api.conditions.Conditions.statusCode;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.text.IsEmptyString.isEmptyOrNullString;

public class UserTest {

    private final UserApiService userApiService = new UserApiService();
    private Faker faker;

    @BeforeClass
    public void setUp() {
        ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
        RestAssured.baseURI = config.baseUrl();
        faker = new Faker(new Locale(config.locale()));
    }


    @Test
    public void testCanRegisterNewUser() {
        //given
        UserPayload userPayload = new UserPayload()
                .username(faker.name().username())
                .email("bob@gmail.com")
                .password("test123");
        //when
        //UserRegistrationResponse response =
        userApiService.registerUser(userPayload)
                .shouldHave(statusCode(200))
                .shouldHave(bodyField("id", not(isEmptyOrNullString())));
        //.asPojo(UserRegistrationResponse.class);
        //then
        // Assert.assertNotNull(response.getId());


    }

    @Test
    public void testCanNotRegisterSameUser() {
        UserPayload userPayload = new UserPayload()
                .username(faker.name().username())
                .email("bob@gmail.com")
                .password("test123");

        userApiService.registerUser(userPayload)
                .shouldHave(statusCode(200));
        userApiService.registerUser(userPayload)
                .shouldHave(statusCode(500));

    }
}
