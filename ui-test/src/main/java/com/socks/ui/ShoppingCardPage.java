package com.socks.ui;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class ShoppingCardPage {

    @Step
    public SelenideElement totalAmount() {
        return $("#orderGrandTotal");
    }

    @Step
    public static ShoppingCardPage open() {
        Selenide.open("basket.html");
        return new ShoppingCardPage();
    }

    @Step
    public ShoppingCardPage deleteItem() {
        $("i.fa-trash-o").click();
        return this;
    }
}
