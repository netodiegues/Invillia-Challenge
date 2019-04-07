package com.acme.order.controller

import com.acme.order.domain.model.Order
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
class OrderControllerTest extends Specification {
    
    @Shared
    RequestSpecification api
    @Shared
    Order order
    
    @LocalServerPort
    int randomServerPort

    def setup() {
        api = given().contentType(JSON)
    }

//    @Unroll
//    def "Test - Should be create the Order return statusCode SC_CREATED"() {
//        given:
//        order = [name    : name,            
//            address : address]
//
//        when:
//        def response = api.headers("Authorization", getToken()).body(order).post("http://localhost:${randomServerPort}/orders")        
//       
//        def jsonSlurper = new JsonSlurper().parseText(response.asString())
//        def jsonBuilder = new JsonBuilder(jsonSlurper)
//        
//        then:                
//        assert response.find(){order.address  == "Address Invillia"}
//        
//        response.statusCode == SC_CREATED
//        println("Order created: " + jsonBuilder.toPrettyString() + "\n")
//
//        where:
//        address             | dateConfirmation       | orderStatus | storeId | paymentId 
//        "Address Invillia"  | new Date("05/04/2019") | "PENDING"   | 1       | 1   
//    }
//    
//    @Unroll
//    def "Test - Should be updating Order statusCode SC_OK"() {
//        given:
//        order = createOrder().as(Order)
//        order = [id          : id,            
//            address          : address,
//            dateConfirmation : dateConfirmation,
//            orderStatus      : orderStatus,
//            storeId          : storeId,
//            paymentId        : paymentId
//        ]
//
//        when:
//        def response = api.headers("Authorization", getToken()).body(order).put("http://localhost:${randomServerPort}/orders/{id}", order.id)
//
//        def jsonSlurper = new JsonSlurper().parseText(response.asString())
//        def jsonBuilder = new JsonBuilder(jsonSlurper)
//        
//        then:
//        assert response.find(){order.orderStatus  == "CANCELED"}
//
//        response.statusCode == SC_OK
//        println("Order updated: " + jsonBuilder.toPrettyString() + "\n")
//
//        where:
//        id | address               | dateConfirmation       | orderStatus  | storeId | paymentId 
//        1  | "Address Invillia"    | new Date("05/04/2019") | "CANCELED"   | 1      | 1   
//    }
//
//    
//    @Unroll
//    def "Test - Should be deleting Order statusCode SC_NO_CONTENT"() {
//        given:
//        order = createOrder().as(Order)
//
//        when:
//        def response = api.headers("Authorization", getToken()).delete("http://localhost:${randomServerPort}/orders/{id}", order.getId())
//
//        then:
//        response.statusCode == SC_NO_CONTENT
//        println("Order deleted\n")
//    }
//    
//    @Unroll
//    def "Test - Should be return statusCode SC_OK"() {
//        when:        
//        def response = api.headers("Authorization", getToken()).get("http://localhost:${randomServerPort}/orders")
//
//        def jsonSlurper = new JsonSlurper().parseText(response.asString())
//        def jsonBuilder = new JsonBuilder(jsonSlurper)
//
//        then:
//        response.statusCode == SC_OK
//        println("Return Order: " + jsonBuilder.toPrettyString() + "\n")
//    }
//    
//    def "createOrder"(){
//        order = [address          : "Address Invillia",
//            dateConfirmation : new Date("05/04/2019"),
//            orderStatus      : "PENDING",
//            storeId          : 1L,
//            paymentId        : 1L
//        ]
//        
//        api.headers("Authorization", getToken()).body(order).post("http://localhost:${randomServerPort}/orders")
//    }
//    
    def "getToken"(){
        return "Bearer eyJhbGciOiJIUzUxMiJ9.eyJ1c2VybmFtZSI6ImFjbWUiLCJpZCI6MSwiZXhwIjoxMjAwMDAxNTU0NDY0NzI3fQ.8g4TNE_XOgXx8GLjWO7z885H-cjkQwiOPALw1gwnIsw6DPaJRmRw5EKSsbCl9t5wjw-Ke0U1GdCEB9eBJyzJRQ";
    }
}