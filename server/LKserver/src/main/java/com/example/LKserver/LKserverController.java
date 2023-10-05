package com.example.LKserver;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;




@RestController //@RestController: It can be considered as a combination of @Controller and @ResponseBody annotations. The @RestController annotation is itself annotated with the @ResponseBody annotation. It eliminates the need for annotating each method with @ResponseBody.
                //@Controller: The @Controller is a class-level annotation. It is a specialization of @Component. It marks a class as a web request handler. It is often used to serve web pages. By default, it returns a string that indicates which route to redirect. It is mostly used with @RequestMapping annotation.
                //Annotation reference -> https://www.javatpoint.com/spring-boot-annotations .
public class LKserverController {
    private List<LKserverData> data = List.of(new LKserverData("1", "Luke")); //placeholder list with data for testing purposes.

    @GetMapping("/")   //this annotation is to enable a http get request at localhost:8000
    public String Hello()
    { 
        return "hello world";
    }

    @GetMapping("/items") //returns a list of type LKserverData at localhost:8000/items endpoint.
    public List<LKserverData> get(){

        return data;
    }

    @GetMapping("/items/{id}")
    public List<LKserverData> get(@PathVariable String id){ //@PathVariable: It is used to extract the values from the URI. It is most suitable for the RESTful web service, where the URL contains a path variable. We can define multiple @PathVariable in a method.

        return data;
    }
}
