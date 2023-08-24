package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // This means that this class is a Controller
@RequestMapping(path="/service") // This means URL's start with /demo (after Application path)
public class ServiceController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private ServiceRepository serviceRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public void addNewService (@RequestParam String name) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        Service n = new Service();
        n.setName(name);
        serviceRepository.save(n);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Service> getAllService() {
        // This returns a JSON or XML with the users
        return serviceRepository.findAll();
    }

    @GetMapping(path="/changeName")
    public @ResponseBody String changeService(@RequestParam Integer service_id, @RequestParam String newName){
        Service n=serviceRepository.findById(service_id).orElse(null);
        if(n!=null)
        {n.setName(newName);
            serviceRepository.save(n);
            return "Name changed";}
        else return "error";
    }

    @GetMapping(path="/changeID")
    public @ResponseBody String changeServiceID(@RequestParam Integer service_id, @RequestParam Integer new_id){
        Service n=serviceRepository.findById(service_id).orElse(null);
        if(n!=null && serviceRepository.findById(new_id).orElse(null)==null)
        {
            n.setId(new_id);
            serviceRepository.save(n);
            return "ID changed";}
        else return "error";
    }

    @DeleteMapping(path = "/delete")
    public void deleteService(@RequestParam Integer service_id){
        Service n=serviceRepository.findById(service_id).orElse(null);
        if(n!=null)serviceRepository.delete(n);
    }
}