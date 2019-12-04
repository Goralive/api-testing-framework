package com.socks.api.assertions;

import com.socks.api.conditions.Condition;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class AssertableResponse {
    private final Response response;

    public AssertableResponse shouldHave(Condition condition) {
        log.info("Check condition {} " , condition);
        condition.check(response);
        return this;
    }

    public <T> T asPojo(Class<T> tClass){
        return response.as(tClass);
    }

    public Headers headers(){
        return response.getHeaders();
    }
}
