package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path="/client")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @GetMapping(path="/find/{id}")
    public @ResponseBody Client getAllClient(@PathVariable(value = "id")Integer id) {
        return clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found"));
    }

    @PostMapping(path="/add")
    public @ResponseBody String addNewClient (@RequestParam String name) {
        // curl http://localhost:8080/client/add -d name={name}
        Client n = new Client();
        n.setName(name);
//        List<Order> orders=new ArrayList<>();
//        n.setOrder(orders);
        clientRepository.save(n);
        return "Saved";
    }

    @PostMapping(path="/edit_name/{id}")
    public @ResponseBody String editClientName(@PathVariable(value = "id")Integer id, @RequestParam String new_name){
        // curl http://localhost:8080/client/edit_name/{id} -d new_name={name}
        Client n=clientRepository.findById(id).orElse(null);
        if (n!=null) {
            n.setName(new_name);
            clientRepository.save(n);
            return "Name changed";
        } else {
            return "Client not found";
        }
    }

    @GetMapping(path="/delete_all")
    public @ResponseBody String deleteAll() {
        // curl http://localhost:8080/client/delete_all
        clientRepository.deleteAll();
        return "All clients deleted";
    }
    @GetMapping(path = "/delete/{id}")
    public @ResponseBody String deleteClient(@PathVariable(value = "id")Integer client_id) {
        // curl http://localhost:8080/client/delete/{id}
        Client n=clientRepository.findById(client_id).orElse(null);
        if (n != null) {
            clientRepository.delete(n);
            return "Client Deleted";
        } else {
            return "Client not found";
        }
    }
}