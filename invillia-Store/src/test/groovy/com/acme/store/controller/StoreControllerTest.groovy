package com.acme.store.controller

import com.acme.store.domain.model.Store
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
class StoreControllerTest extends Specification {
    @Shared
    RequestSpecification api
    @Shared
    Store store
    
    @LocalServerPort
    int randomServerPort

    def setup() {
        api = given().contentType(JSON)
    }

    @Unroll
    def "Test - Should be create the Store return statusCode SC_CREATED"() {
        given:
        store = [name    : name,            
            address : address]

        when:
        def response = api.headers("Authorization", getToken()).body(store).post("http://localhost:${randomServerPort}/stores")        
       
        def jsonSlurper = new JsonSlurper().parseText(response.asString())
        def jsonBuilder = new JsonBuilder(jsonSlurper)
        
        then:        
        assert response.find(){store.name  == "Store Invillia"}
        assert response.find(){store.address  == "Rua teste Invillia"}
        
        response.statusCode == SC_CREATED
        println("Store created: " + jsonBuilder.toPrettyString() + "\n")

        where:
        name             | address
        "Store Invillia" | "Rua teste Invillia" 
    }
    
    @Unroll
    def "Test - Should be updating Store statusCode SC_OK"() {
        given:
        store = createStore().as(Store)
        store = [id       : id,
            name          : name,            
            address       : address]

        when:
        def response = api.headers("Authorization", getToken()).body(store).put("http://localhost:${randomServerPort}/stores/{id}", store.id)

        def jsonSlurper = new JsonSlurper().parseText(response.asString())
        def jsonBuilder = new JsonBuilder(jsonSlurper)
        
        then:
        assert response.find(){store.address  == "Rua AB"}

        response.statusCode == SC_OK
        println("Store updated: " + jsonBuilder.toPrettyString() + "\n")

        where:
        id | name             | address
        1  | "Store Invillia" | "Rua AB" 
    }

    
    @Unroll
    def "Test - Should be deleting Store statusCode SC_NO_CONTENT"() {
        given:
        store = createStore().as(Store)

        when:
        def response = api.headers("Authorization", getToken()).delete("http://localhost:${randomServerPort}/stores/{id}", store.getId())

        then:
        response.statusCode == SC_NO_CONTENT
        println("Store deleted\n")
    }
    
    @Unroll
    def "Test - Should be return statusCode SC_OK"() {
        when:        
        def response = api.headers("Authorization", getToken()).get("http://localhost:${randomServerPort}/stores")

        def jsonSlurper = new JsonSlurper().parseText(response.asString())
        def jsonBuilder = new JsonBuilder(jsonSlurper)

        then:
        response.statusCode == SC_OK
        println("Return Store: " + jsonBuilder.toPrettyString() + "\n")
    }
    
    def "createStore"(){
        def store = [name  : "Store Invillia",            
            address        : "street One"]
          
        api.headers("Authorization", getToken()).body(store).post("http://localhost:${randomServerPort}/stores")
    }
    
    def "getToken"(){
        return "Bearer eyJhbGciOiJIUzUxMiJ9.eyJ1c2VybmFtZSI6ImFjbWUiLCJpZCI6MSwiZXhwIjoxMjAwMDAxNTU0NDY0NzI3fQ.8g4TNE_XOgXx8GLjWO7z885H-cjkQwiOPALw1gwnIsw6DPaJRmRw5EKSsbCl9t5wjw-Ke0U1GdCEB9eBJyzJRQ";
    }
}