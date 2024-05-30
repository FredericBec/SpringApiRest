package fr.fms.SpringApiRest;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.fms.SpringApiRest.dao.*;
import fr.fms.SpringApiRest.entities.Customer;
import fr.fms.SpringApiRest.entities.Order;
import fr.fms.SpringApiRest.entities.OrderItem;
import fr.fms.SpringApiRest.entities.Training;
import fr.fms.SpringApiRest.service.ImplBusinessService;
import fr.fms.SpringApiRest.web.OrderController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

@WebMvcTest(controllers = OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ImplBusinessService implBusinessService;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private OrderItemRepository orderItemRepository;

    @MockBean
    private TrainingRepository trainingRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    void testSaveCustomer() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();

        Customer testCustomer = new Customer(null, "Dupont", "Robert", "5 chemin de Moulinsart 41700 cherveny", "0123456789", "dup.rob@gmail.com", null);
        String requestContent = objectMapper.writeValueAsString(testCustomer);
        when(implBusinessService.saveCustomer(testCustomer)).thenReturn(testCustomer);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestContent))
                .andExpect(status().isCreated())
                .andReturn();

    }

    @Test
    void testSaveOrder() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();

        Customer testMartine = new Customer(1L, "Huguette", "Martine", "10 rue des mimosas 19000 Tulle", "0123456789", "hug.martine25@gmail.com", null);
        Order testOrder = new Order(null, new Date(), 5000, testMartine, null);
        String requestContent = objectMapper.writeValueAsString(testOrder);
        when(implBusinessService.saveOrder(testOrder)).thenReturn(testOrder);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void testSaveOrderItem() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();

        Customer testRaymond = new Customer(1L, "Dubarre", "Raymond", "chemin des 6 bières 24000 Perigueux", "6666666666", "bourre.galette@sfr.fr", null);
        Order testOrder = new Order(1L, new Date(), 10000, testRaymond, null);
        Training oenologie = new Training(1L, "oenologie", "Devenez incollable sur les vins et les bières", 2000, 1, "", null);
        OrderItem testOrderItem = new OrderItem(null, 5, 2000, oenologie, testOrder);
        String requestContent = objectMapper.writeValueAsString(testOrderItem);
        when(implBusinessService.saveOrderItem(testOrderItem)).thenReturn(testOrderItem);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/orderItem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void testGetCustomer() throws Exception{
        Customer testCustomer = new Customer(2L, "César", "Jules", "1 rue de la création Rome Italie", "1111111111", "caesar.jule@wanadoo.fr", null);
        Long customerId = 2L;
        when(implBusinessService.getCustomerById(customerId)).thenReturn(testCustomer);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/customer/{id}", customerId).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testGetOrder() throws Exception{
        Customer testCustomer = new Customer(3L, "Albert", "Alberta", "5 rue du mousquetaire 75000 Paris", "0123456789", "al.berta@gmail.com", null);
        Order testOrder = new Order(5L, new Date(), 2000,testCustomer, null);
        Long orderId = 5L;
        when(implBusinessService.getOrder(orderId)).thenReturn(testOrder);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/order/{id}", orderId).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
}
