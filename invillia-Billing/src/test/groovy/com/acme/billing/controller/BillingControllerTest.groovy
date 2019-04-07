package com.acme.billing.controller

import com.acme.billing.domain.model.Payment
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import io.restassured.specification.RequestSpecification
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import static io.restassured.RestAssured.given
import static io.restassured.http.ContentType.JSON
import static javax.servlet.http.HttpServletResponse.*

/**
 *
 * @author jose.diegues
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class BillingControllerTest extends Specification {
    
    @Shared
    RequestSpecification api
    
    @Shared
    Payment payment
    
    @LocalServerPort
    int randomServerPort

    def setup() {
        api = given().contentType(JSON)
    }
       
    def "getToken"(){
        return "Bearer eyJhbGciOiJIUzUxMiJ9.eyJ1c2VybmFtZSI6ImFjbWUiLCJpZCI6MSwiZXhwIjoxMjAwMDAxNTU0NDY0NzI3fQ.8g4TNE_XOgXx8GLjWO7z885H-cjkQwiOPALw1gwnIsw6DPaJRmRw5EKSsbCl9t5wjw-Ke0U1GdCEB9eBJyzJRQ";
    }
}