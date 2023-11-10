////package com.codingcomrades.fullstackbackend.controller;
////
////import com.codingcomrades.fullstackbackend.exception.OrderNotFoundException;
////import com.codingcomrades.fullstackbackend.model.Order;
////
////import com.codingcomrades.fullstackbackend.repository.OrderRepository;
////import jakarta.transaction.Transactional;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.web.bind.annotation.*;
////
////import java.util.List;
////@Transactional
////@RestController
////
////public class OrderController {
////
////    @Autowired
////    private OrderRepository orderRepository;
////
////    @PostMapping("/orders")
////    Order createOrder(@RequestBody Order newOrder) {
////        return orderRepository.save(newOrder);
////    }
////
////
////    @GetMapping("/orders")
////    List<Order> getAllOrders() {
////        return orderRepository.findAll();
////    }
////
////    @GetMapping("/orders/{id}")
////    Order getOrderById(@PathVariable Long id) {
////        return orderRepository.findById(id)
////                .orElseThrow(() -> new OrderNotFoundException(id));
////    }
////
////    @PutMapping("/orders/{id}")
////    Order updateOrder(@RequestBody Order newOrder, @PathVariable Long id) {
////        return orderRepository.findById(id)
////                .map(order -> {
////                   // order.setDate(newOrder.getOrderStatus());
////                    order.setDeliveryInformation(newOrder.getDeliveryInformation());
////                    // Update other fields as needed
////                    return orderRepository.save(order);
////                }).orElseThrow(() -> new OrderNotFoundException(id));
////    }
////
////    @DeleteMapping("/orders/{id}")
////    String deleteOrder(@PathVariable Long id) {
////        if (!orderRepository.existsById(id)) {
////            throw new OrderNotFoundException(id);
////        }
////        orderRepository.deleteById(id);
////        return "Order with ID " + id + " has been deleted successfully.";
////    }
////}
//
////
////package com.codingcomrades.fullstackbackend.controller;
////import com.codingcomrades.fullstackbackend.exception.OrderNotFoundException;
////import com.codingcomrades.fullstackbackend.model.Order;
////import com.codingcomrades.fullstackbackend.model.OrderItem;
////import com.codingcomrades.fullstackbackend.repository.OrderRepository;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.http.HttpStatus;
////import org.springframework.http.ResponseEntity;
////import org.springframework.mail.SimpleMailMessage;
////import org.springframework.mail.javamail.JavaMailSender;
////import org.springframework.transaction.annotation.Transactional;
////import org.springframework.web.bind.annotation.*;
////import org.springframework.web.client.RestOperations;
////import org.springframework.web.client.RestTemplate;
////
////import java.util.List;
////import java.util.stream.Collectors;
////
////@Transactional
////@RestController
////public class OrderController {
////
////    @Autowired
////    private OrderRepository orderRepository;
////
////    @Autowired
////    private JavaMailSender javaMailSender;
////
////    @Autowired
////    private RestTemplate restTemplate;
////
////    //    @PostMapping("/orders")
//////    Order createOrder(@RequestBody Order newOrder) {
//////        return orderRepository.save(newOrder);
//////    }
////@PostMapping("/orders")
////Order createOrder(@RequestBody Order newOrder) throws ProductNotAvailableException {
////    List<OrderItem> productCheckRequests = newOrder.getOrderItems()
////            .stream()
////            .map(item -> new OrderItem(item.getProductId(), item.getQuantity()))
////            .collect(Collectors.toList());
////
////    // Make a REST API call to the Inventory microservice to check product availability
////
////    ResponseEntity<String> availabilityResponse = restTemplate.postForEntity(
////            "http://localhost:8081/api/inventory/checkAvailability",
////            productCheckRequests,
////            String.class);
////
////    if (availabilityResponse.getStatusCode() == HttpStatus.OK) {
////        // Products are available, proceed to create the order
////        Order order = orderRepository.save(newOrder);
////        // Update the quantities in the Inventory microservice
////
////        return order;
////    } else {
////        // Products are not available
////        throw new ProductNotAvailableException("One or more products are not available.");
////    }
////}
////
////    @GetMapping("/orders")
////    List<Order> getAllOrders() {
////        return orderRepository.findAll();
////    }
////
////    @GetMapping("/orders/{id}")
////    Order getOrderById(@PathVariable Long id) {
////        return orderRepository.findById(id)
////                .orElseThrow(() -> new OrderNotFoundException(id));
////    }
////
////    @PutMapping("/orders/{id}")
////    Order updateOrder(@RequestBody Order newOrder, @PathVariable Long id) {
////        return orderRepository.findById(id)
////                .map(order -> {
////                    order.setDeliveryInformation(newOrder.getDeliveryInformation());
////                    // Update other fields as needed
////                    return orderRepository.save(order);
////                }).orElseThrow(() -> new OrderNotFoundException(id));
////    }
////
////    @DeleteMapping("/orders/{id}")
////    String deleteOrder(@PathVariable Long id) {
////        if (!orderRepository.existsById(id)) {
////            throw new OrderNotFoundException(id);
////        }
////        orderRepository.deleteById(id);
////        return "Order with ID " + id + " has been deleted successfully.";
////    }
////
////    // Update order status
////    @PatchMapping("/orders/{id}/status")
////    public ResponseEntity<String> updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
////        Order order = orderRepository.findById(id)
////                .orElseThrow(() -> new OrderNotFoundException(id));
////
////        if (isValidOrderStatus(status)) {
////            order.setOrderStatus(status);
////            orderRepository.save(order);
////
////            // Send an email notification to the customer
////            String to = order.getCustomerEmail();
////            String subject = "Order Status Update";
////            String message = "Your order with ID " + id + " has been updated to: " + status;
////            sendEmailNotification(to, subject, message);
////
////            return ResponseEntity.ok("Order status updated successfully.");
////        } else {
////            return ResponseEntity.badRequest().body("Invalid order status");
////        }
////    }
////
////    private boolean isValidOrderStatus(String status) {
////        // Implement your validation logic here
////        return "in preparation".equals(status) || "ready for pickup".equals(status) || "delivered".equals(status);
////    }
////
////    private void sendEmailNotification(String to, String subject, String message) {
////        SimpleMailMessage mailMessage = new SimpleMailMessage();
////        mailMessage.setTo(to);
////        mailMessage.setSubject(subject);
////        mailMessage.setText(message);
////        javaMailSender.send(mailMessage);
////    }
////}
//
//
//
//package com.codingcomrades.fullstackbackend.controller;
//
//import com.codingcomrades.fullstackbackend.exception.OrderNotFoundException;
//import com.codingcomrades.fullstackbackend.exception.ProductNotAvailableException;
//import com.codingcomrades.fullstackbackend.model.Order;
//import com.codingcomrades.fullstackbackend.model.OrderItem;
//import com.codingcomrades.fullstackbackend.repository.OrderRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestOperations;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Transactional
//@RestController
//public class OrderController {
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @Autowired
//    private JavaMailSender javaMailSender;
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @PostMapping("/orders")
//    public ResponseEntity<Object> createOrder(@RequestBody Order newOrder) {
//        try {
//            List<OrderItem> productCheckRequests = newOrder.getOrderItems()
//                    .stream()
//                    .map(item -> new OrderItem(item.getProductId(), item.getQuantity()))
//                    .collect(Collectors.toList());
//
//            // Make a REST API call to the Inventory microservice to check product availability
//
//            ResponseEntity<String> availabilityResponse = restTemplate.postForEntity(
//                    "http://localhost:8081/api/inventory/checkAvailability",
//                    productCheckRequests,
//                    String.class);
//
//            if (availabilityResponse.getStatusCode() == HttpStatus.OK) {
//                // Products are available, proceed to create the order
//                Order order = orderRepository.save(newOrder);
//                // Update the quantities in the Inventory microservice
//
//                return new ResponseEntity<>(order, HttpStatus.CREATED);
//            } else {
//                // Products are not available
//                return new ResponseEntity<>("One or more products are not available.", HttpStatus.BAD_REQUEST);
//            }
//        } catch (ProductNotAvailableException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @GetMapping("/orders")
//    public ResponseEntity<Object> getAllOrders() {
//        List<Order> orders = orderRepository.findAll();
//        return new ResponseEntity<>(orders, HttpStatus.OK);
//    }
//
//    @GetMapping("/orders/{id}")
//    public ResponseEntity<Object> getOrderById(@PathVariable Long id) {
//        try {
//            Order order = orderRepository.findById(id)
//                    .orElseThrow(() -> new OrderNotFoundException(id));
//            return new ResponseEntity<>(order, HttpStatus.OK);
//        } catch (OrderNotFoundException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @PutMapping("/orders/{id}")
//    public ResponseEntity<Object> updateOrder(@RequestBody Order newOrder, @PathVariable Long id) {
//        try {
//            Order order = orderRepository.findById(id)
//                    .map(existingOrder -> {
//                        existingOrder.setDeliveryInformation(newOrder.getDeliveryInformation());
//                        // Update other fields as needed
//                        return orderRepository.save(existingOrder);
//                    })
//                    .orElseThrow(() -> new OrderNotFoundException(id));
//            return new ResponseEntity<>(order, HttpStatus.OK);
//        } catch (OrderNotFoundException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("/orders/{id}")
//    public ResponseEntity<Object> deleteOrder(@PathVariable Long id) {
//        try {
//            if (!orderRepository.existsById(id)) {
//                throw new OrderNotFoundException(id);
//            }
//            orderRepository.deleteById(id);
//            return new ResponseEntity<>("Order with ID " + id + " has been deleted successfully.", HttpStatus.NO_CONTENT);
//        } catch (OrderNotFoundException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @PatchMapping("/orders/{id}/status")
//    public ResponseEntity<Object> updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
//        try {
//            Order order = orderRepository.findById(id)
//                    .orElseThrow(() -> new OrderNotFoundException(id));
//
//            if (isValidOrderStatus(status)) {
//                order.setOrderStatus(status);
//                orderRepository.save(order);
//
//                // Send an email notification to the customer
//                String to = order.getCustomerEmail();
//                String subject = "Order Status Update";
//                String message = "Your order with ID " + id + " has been updated to: " + status;
//                sendEmailNotification(to, subject, message);
//
//                return new ResponseEntity<>("Order status updated successfully.", HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>("Invalid order status", HttpStatus.BAD_REQUEST);
//            }
//        } catch (OrderNotFoundException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }
//
//    private boolean isValidOrderStatus(String status) {
//        // Implement your validation logic here
//        return "in preparation".equals(status) || "ready for pickup".equals(status) || "delivered".equals(status);
//    }
//
//    private void sendEmailNotification(String to, String subject, String message) {
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(to);
//        mailMessage.setSubject(subject);
//        mailMessage.setText(message);
//        javaMailSender.send(mailMessage);
//    }
//}

//
//
//package com.codingcomrades.fullstackbackend.controller;
//
//import com.codingcomrades.fullstackbackend.exception.OrderNotFoundException;
//import com.codingcomrades.fullstackbackend.exception.ProductNotAvailableException;
//import com.codingcomrades.fullstackbackend.model.Order;
//import com.codingcomrades.fullstackbackend.model.OrderItem;
//import com.codingcomrades.fullstackbackend.repository.OrderRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestOperations;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Transactional
//@RestController
//public class OrderController {
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @Autowired
//    private JavaMailSender javaMailSender;
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @PostMapping("/orders")
//    public ResponseEntity<Object> createOrder(@RequestBody Order newOrder) {
//        try {
//            List<OrderItem> productCheckRequests = newOrder.getOrderItems()
//                    .stream()
//                    .map(item -> new OrderItem(item.getProductId(), item.getQuantity()))
//                    .collect(Collectors.toList());
//
//            // Make a REST API call to the Inventory microservice to check product availability
//
//            ResponseEntity<String> availabilityResponse = restTemplate.postForEntity(
//                    "http://localhost:8081/api/inventory/checkAvailability",
//                    productCheckRequests,
//                    String.class);
//
//            if (availabilityResponse.getStatusCode() == HttpStatus.OK) {
//                // Products are available, proceed to create the order
//                Order order = orderRepository.save(newOrder);
//                // Update the quantities in the Inventory microservice
//
//                return new ResponseEntity<>(order, HttpStatus.CREATED);
//            } else {
//                // Products are not available
//                return new ResponseEntity<>("One or more products are not available.", HttpStatus.BAD_REQUEST);
//            }
//        } catch (ProductNotAvailableException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @GetMapping("/orders")
//    public ResponseEntity<Object> getAllOrders() {
//        List<Order> orders = orderRepository.findAll();
//        return new ResponseEntity<>(orders, HttpStatus.OK);
//    }
//
//    @GetMapping("/orders/{id}")
//    public ResponseEntity<Object> getOrderById(@PathVariable Long id) {
//        try {
//            Order order = orderRepository.findById(id)
//                    .orElseThrow(() -> new OrderNotFoundException(id));
//            return new ResponseEntity<>(order, HttpStatus.OK);
//        } catch (OrderNotFoundException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @PutMapping("/orders/{id}")
//    public ResponseEntity<Object> updateOrder(@RequestBody Order newOrder, @PathVariable Long id) {
//        try {
//            Order order = orderRepository.findById(id)
//                    .map(existingOrder -> {
//                        existingOrder.setDeliveryInformation(newOrder.getDeliveryInformation());
//                        // Update other fields as needed
//                        return orderRepository.save(existingOrder);
//                    })
//                    .orElseThrow(() -> new OrderNotFoundException(id));
//            return new ResponseEntity<>(order, HttpStatus.OK);
//        } catch (OrderNotFoundException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("/orders/{id}")
//    public ResponseEntity<Object> deleteOrder(@PathVariable Long id) {
//        try {
//            if (!orderRepository.existsById(id)) {
//                throw new OrderNotFoundException(id);
//            }
//            orderRepository.deleteById(id);
//            return new ResponseEntity<>("Order with ID " + id + " has been deleted successfully.", HttpStatus.NO_CONTENT);
//        } catch (OrderNotFoundException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @PatchMapping("/orders/{id}/status")
//    public ResponseEntity<Object> updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
//        try {
//            Order order = orderRepository.findById(id)
//                    .orElseThrow(() -> new OrderNotFoundException(id));
//
//            if (isValidOrderStatus(status)) {
//                order.setOrderStatus(status);
//                orderRepository.save(order);
//
//                // Send an email notification to the customer
//                String to = order.getCustomerEmail();
//                String subject = "Order Status Update";
//                String message = "Your order with ID " + id + " has been updated to: " + status;
//                sendEmailNotification(to, subject, message);
//
//                return new ResponseEntity<>("Order status updated successfully.", HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>("Invalid order status", HttpStatus.BAD_REQUEST);
//            }
//        } catch (OrderNotFoundException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }
//
//    private boolean isValidOrderStatus(String status) {
//        // Implement your validation logic here
//        return "in preparation".equals(status) || "ready for pickup".equals(status) || "delivered".equals(status);
//    }
//
//    private void sendEmailNotification(String to, String subject, String message) {
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(to);
//        mailMessage.setSubject(subject);
//        mailMessage.setText(message);
//        javaMailSender.send(mailMessage);
//    }
//}



package com.codingcomrades.fullstackbackend.controller;
import com.codingcomrades.fullstackbackend.exception.OrderNotFoundException;
import com.codingcomrades.fullstackbackend.model.Order;
import com.codingcomrades.fullstackbackend.model.OrderItem;
import com.codingcomrades.fullstackbackend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RestController
@RequestMapping("api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/create")
    public ResponseEntity<Object> createOrder(@RequestBody Order newOrder) {
        try {
            List<OrderItem> productCheckRequests = newOrder.getOrderItems()
                    .stream()
                    .map(item -> new OrderItem(item.getProductId(), item.getQuantity()))
                    .collect(Collectors.toList());

            ResponseEntity<String> availabilityResponse = restTemplate.postForEntity(
                    "http://localhost:8081/api/inventory/checkAvailability",
                    productCheckRequests,
                    String.class);

            if (availabilityResponse.getStatusCode() == HttpStatus.OK) {
                Order order = orderRepository.save(newOrder);
                return new ResponseEntity<>(order, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("One or more products are not available.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrderById(@PathVariable Long id) {
        try {
            Order order = orderRepository.findById(id)
                    .orElseThrow(() -> new OrderNotFoundException(id));
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateOrder(@RequestBody Order newOrder, @PathVariable Long id) {
        try {
            Order order = orderRepository.findById(id)
                    .map(existingOrder -> {
                        existingOrder.setDeliveryInformation(newOrder.getDeliveryInformation());
                        // Update other fields as needed
                        return orderRepository.save(existingOrder);
                    })
                    .orElseThrow(() -> new OrderNotFoundException(id));
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable Long id) {
        try {
            if (!orderRepository.existsById(id)) {
                throw new OrderNotFoundException(id);
            }
            orderRepository.deleteById(id);
            return new ResponseEntity<>("Order with ID " + id + " has been deleted successfully.", HttpStatus.NO_CONTENT);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Object> updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            Order order = orderRepository.findById(id)
                    .orElseThrow(() -> new OrderNotFoundException(id));

            if (isValidOrderStatus(status)) {
                order.setOrderStatus(status);
                orderRepository.save(order);

                String to = order.getCustomerEmail();
                String subject = "Order Status Update";
                String message = "Your order has been updated to: " + status;
                sendEmailNotification(to, subject, message);

                return new ResponseEntity<>("Order status updated successfully.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid order status", HttpStatus.BAD_REQUEST);
            }
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    private boolean isValidOrderStatus(String status) {
        return "in preparation".equals(status) || "ready for pickup".equals(status) || "delivered".equals(status);
    }

    private void sendEmailNotification(String to, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        javaMailSender.send(mailMessage);
    }
}
