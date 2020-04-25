package com.example.reactive.product.controller;

import com.example.reactive.product.model.Product;
import com.example.reactive.product.service.ProductService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import util.TestHelper;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ProductControllerTest {

    private static final String PRODUCT_API = "/api/product";

    @MockBean
    private ProductService productService;

    @Test
    @DisplayName("should save a new product")
    public void shouldSaveAProductRequest() throws Exception {
        when(productService.save(productRequest()))
            .thenReturn(Mono.just(productResponse()));

        given()
            .body(productRequest())
            .contentType(ContentType.JSON)
            .when()
            .post(PRODUCT_API)
            .then()
            .assertThat().statusCode(201)
            .assertThat().body(equalTo(TestHelper.convertObjectToJson(productResponse())));

        verify(productService).save(productRequest());
    }

    @Test
    @DisplayName("should find product by id")
    public void shouldGetProductById() throws Exception {
        when(productService.byId("1"))
            .thenReturn(Mono.just(productResponse()));

        given()
            .pathParam("id", "1")
            .when()
            .get(PRODUCT_API + "/{id}")
            .then()
            .assertThat().statusCode(200)
            .assertThat().body(equalTo(TestHelper.convertObjectToJson(productResponse())));

        verify(productService).byId("1");
    }

    private Product productRequest() {
        return Product.builder()
            .name("T-SHIRT")
            .price("30")
            .build();
    }

    private Product productResponse() {
        var product = productRequest();
        product.setId("1");
        return product;
    }
}