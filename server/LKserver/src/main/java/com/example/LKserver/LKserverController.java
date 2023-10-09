package com.example.LKserver;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;




//@RestController: It can be considered as a combination of @Controller and @ResponseBody annotations. The @RestController annotation is itself annotated with the @ResponseBody annotation. It eliminates the need for annotating each method with @ResponseBody.
//@Controller: The @Controller is a class-level annotation. It is a specialization of @Component. It marks a class as a web request handler. It is often used to serve web pages. By default, it returns a string that indicates which route to redirect. It is mostly used with @RequestMapping annotation.
//Annotation reference -> https://www.javatpoint.com/spring-boot-annotations .

@RestController 
public class LKserverController {
    
    private Map<String, LKserverData> data = new HashMap<>() {{
        put("1", new LKserverData("1", new String[]{"a","b","c"}, "HELLO SIR", 1234567890L, 1234567890L)); //placeholder list with data for testing purposes.
    }};

    @GetMapping("/")   //this annotation is to enable a http get request at localhost:8000
    public String Hello()
    { 
        return "hello world";
    }

    @GetMapping("/items") //returns a list of type LKserverData at localhost:8000/items endpoint.
    public Collection<LKserverData> get(){

        return data.values();
    }

    @GetMapping("/item/{user_id}")
    public LKserverData get(@PathVariable String user_id){ //@PathVariable: It is used to extract the values from the URI. It is most suitable for the RESTful web service, where the URL contains a path variable. We can define multiple @PathVariable in a method.
        LKserverData lKserverData = data.get(user_id);
        if (lKserverData==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND); //if id value is not found returns a 404
        return lKserverData;
    }

    @DeleteMapping("/item/{user_id}")
    public void delete(@PathVariable String user_id){
        LKserverData lKserverData = data.remove(user_id); //if id exists it will delete the data found at {id} parameter.
        if (lKserverData==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND); //if id value is not found returns a 404
    }

    @PostMapping("/item")
    public LKserverData create(@RequestBody @Valid LKserverData lKserverData ){
        lKserverData.setInternal_id(UUID.randomUUID().toString()); //adds a uuid to the internal id field contained in the data object.
        data.put(lKserverData.getUser_id(),lKserverData);
        return lKserverData;
    }
    

}
