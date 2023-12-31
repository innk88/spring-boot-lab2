package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // This means that this class is a Controller
@RequestMapping(path="/order") // This means URL's start with /demo (after Application path)
public class OrderController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private OrderRepository orderRepository;
    private ClientRepository clientRepository;
    private ServiceRepository serviceRepository;
    @PostMapping(path="/add") // Map ONLY POST Requests
    public void addNewOrder (@RequestParam Integer client_id, @RequestParam String name) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Order n= new Order();
        Client client=clientRepository.findById(client_id).orElse(null);
        if(client!=null){
            n.setClient(client);
            orderRepository.save(n);
        }
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Order> getAllOrders() {
        // This returns a JSON or XML with the users
        return orderRepository.findAll();
    }

    @PostMapping("/{order_id}/add-service/{service_id}")
    public void addServiceToRequest(@PathVariable Integer service_Id, @PathVariable Integer order_id) {
        Order order = orderRepository.findById(service_Id).orElse(null);
        Service service = serviceRepository.findById(service_Id).orElse(null);
        if (order != null && service != null) {
            ServicesInOrder orderService = new ServicesInOrder();
            orderService.setOrder(order);
            orderService.setService(service);
            order.getServicesInOrders().add(orderService);
            orderRepository.save(order);
        }

    }

    @GetMapping(path="/changeID")
    public @ResponseBody String changeOrderID(@RequestParam Integer order_id, @RequestParam Integer new_id){
        Order n=orderRepository.findById(order_id).orElse(null);
        if(n!=null && orderRepository.findById(new_id).orElse(null)==null)
        {
            n.setId(new_id);
            orderRepository.save(n);
            return "ID changed";}
        else return "error";
    }

    @DeleteMapping (path = "/delete")
    public void deleteOrder(@RequestParam Integer order_id){
        Order n=orderRepository.findById(order_id).orElse(null);
        if(n!=null)orderRepository.delete(n);
    }
}
