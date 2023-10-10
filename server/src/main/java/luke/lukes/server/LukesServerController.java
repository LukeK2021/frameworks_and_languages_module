package luke.lukes.server;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.Valid;

@CrossOrigin (origins = "http://localhost:8000/", maxAge = 3600)
@RestController 
public class LukesServerController {
    private Map<String, LukesServerData> data = new HashMap<>() {{
        put("1", new LukesServerData("1", new String[]{"a","b","c"}, "HELLO SIR", 1234567890L, 1234567890L,UUID.randomUUID().toString())); //placeholder list with data for testing purposes.
    }};

    @GetMapping("/")   //this annotation is to enable a http get request at localhost:8000
    public String Hello()
    { 
        return "hello world";
    }

    @GetMapping("/items") //returns a list of type LKserverData at localhost:8000/items endpoint.
    public Collection<LukesServerData> get(){

        return data.values();
    }

    @GetMapping("/item/{user_id}")
    public LukesServerData get(@PathVariable String user_id){ //@PathVariable: It is used to extract the values from the URI. It is most suitable for the RESTful web service, where the URL contains a path variable. We can define multiple @PathVariable in a method.
        LukesServerData lKserverData = data.get(user_id);
        if (lKserverData==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND); //if id value is not found returns a 404
        return lKserverData;
    }


    
    @DeleteMapping("/item/{user_id}")
    public void delete(@PathVariable String user_id){
        LukesServerData lKserverData = data.remove(user_id); //if id exists it will delete the data found at {id} parameter.
        if (lKserverData==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND); //if id value is not found returns a 404
    }

    @PostMapping("/item")
    public LukesServerData create(@RequestBody @Valid LukesServerData lKserverData ){
        lKserverData.setInternal_id(UUID.randomUUID().toString()); //adds a uuid to the internal id field contained in the data object.
        data.put(lKserverData.getUser_id(),lKserverData);
        return lKserverData;
    }

}
