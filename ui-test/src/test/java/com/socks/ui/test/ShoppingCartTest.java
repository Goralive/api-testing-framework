package com.socks.ui.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.socks.api.services.CartApiService;
import com.socks.ui.CatalogPage;
import com.socks.ui.ShoppingCardPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ShoppingCartTest extends BaseUITest {

    private CartApiService cartApiService = new CartApiService();

    @Test
    public void userCanAddItemToCardFromCatalog(){
        CatalogPage.open()
        .addItemByIndex(0)
        .goToCart();

        at(ShoppingCardPage.class).totalAmount().shouldHave(Condition.exactText("$104.98"));
    }

    @Test
    public void userCanDeleteItemFromCart(){
        ShoppingCardPage.open();

        String cookies = WebDriverRunner.getWebDriver().manage().getCookieNamed("md.sid").getValue();

        cartApiService.addItemToCard("3395a43e-2d88-40de-b95f-e00e1502085b", cookies);

        ShoppingCardPage.open()
                .deleteItem()
                .totalAmount()
                .shouldHave(Condition.exactText("$0.00"));


    }

    @AfterMethod
    public void tearDown() {
        Selenide.clearBrowserCookies();
    }
}
