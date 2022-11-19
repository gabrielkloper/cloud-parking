package controller;

import io.restassured.RestAssured;
import org.example.controller.dto.ParkingCreateDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import service.AbstractContainerBase;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParkingControllerTest extends AbstractContainerBase {

    @LocalServerPort
    private int randomport;

    @BeforeEach
    public void setUpTest(){
        RestAssured.port = randomport;
    }

    @Test
    void whenfindAllThenCheckResult() {
        RestAssured.given()
                .auth()
                .basic("user", "senha123")
                .when()
                .get("/parking")
                .then()
                .statusCode(HttpStatus.OK.value());
        // .body("license[0]", Matchers.equalTo("WAS-1234"));
        //        .extract().response().body().prettyPrint();
    }

    @Test
    void whenCreateThenCheckIsCreated() {

        var createDTO = new ParkingCreateDTO();
        createDTO.setColor("AMARELO");
        createDTO.setLicense("WRT-5555");
        createDTO.setModel("BRASILIA");
        createDTO.setState("SP");

        RestAssured.given()
                .auth()
                .basic("user", "user123")
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createDTO)
                .post("/parking")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("license", Matchers.equalTo("WRT-5555"))
                .body("color", Matchers.equalTo("AZUL"));
    }
}
