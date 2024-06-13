package fr.fms.SpringApiRest;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.fms.SpringApiRest.Mapper.CustomerMapper;
import fr.fms.SpringApiRest.Mapper.OrderItemMapper;
import fr.fms.SpringApiRest.Mapper.OrderMapper;
import fr.fms.SpringApiRest.dao.*;
import fr.fms.SpringApiRest.dto.CustomerDto;
import fr.fms.SpringApiRest.dto.OrderDto;
import fr.fms.SpringApiRest.dto.OrderItemDto;
import fr.fms.SpringApiRest.entities.Customer;
import fr.fms.SpringApiRest.entities.Order;
import fr.fms.SpringApiRest.entities.OrderItem;
import fr.fms.SpringApiRest.entities.Training;
import fr.fms.SpringApiRest.service.AccountServiceImpl;
import fr.fms.SpringApiRest.service.ImplBusinessService;
import fr.fms.SpringApiRest.web.OrderController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
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
    private AccountServiceImpl accountService;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private CustomerMapper customerMapper;

    @MockBean
    private OrderMapper orderMapper;

    @MockBean
    private OrderItemMapper orderItemMapper;

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

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void testSaveCustomer() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();

        CustomerDto testCustomerDto = new CustomerDto("Dupont", "Robert", "5 chemin de Moulinsart 41700 cherveny", "0123456789", "dup.rob@gmail.com");
        Customer testCustomer = new Customer(1L, "Dupont", "Robert", "5 chemin de Moulinsart 41700 cherveny", "0123456789", "dup.rob@gmail.com", null);

        String requestContent = objectMapper.writeValueAsString(testCustomerDto);

        when(customerMapper.mapToEntity(any(CustomerDto.class))).thenReturn(testCustomer);
        when(implBusinessService.saveCustomer(any(Customer.class))).thenReturn(testCustomer);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(status().isCreated())
                .andReturn();

        // Vérifiez la réponse pour s'assurer qu'elle contient bien les données attendues
        String jsonResponse = mvcResult.getResponse().getContentAsString();
        Customer responseCustomer = objectMapper.readValue(jsonResponse, Customer.class);
        assertEquals("Dupont", responseCustomer.getName());
        assertEquals("Robert", responseCustomer.getFirstName());
        assertNotNull(responseCustomer.getId());

    }

    @Test
    void testSaveOrder() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();

        Customer testMartine = new Customer(1L, "Huguette", "Martine", "10 rue des mimosas 19000 Tulle", "0123456789", "hug.martine25@gmail.com", null);
        OrderDto orderDto = new OrderDto(new Date(), 5000, testMartine);
        Order testOrder = new Order(null, new Date(), 5000, testMartine, null);

        String requestContent = objectMapper.writeValueAsString(orderDto);

        when(orderMapper.mapToEntity(any(OrderDto.class))).thenReturn(testOrder);
        when(implBusinessService.saveOrder(any(Order.class))).thenReturn(testOrder);

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
        Training oenologie = new Training(1L, "oenologie", "Devenez incollable sur les vins et les bières", 2000, 1, false, true, 10, "", null);
        OrderItemDto orderItemDto = new OrderItemDto(5, 2000, oenologie, testOrder);
        OrderItem testOrderItem = new OrderItem(null, 5, 2000, oenologie, testOrder);

        String requestContent = objectMapper.writeValueAsString(orderItemDto);

        when(orderItemMapper.mapToEntity(any(OrderItemDto.class))).thenReturn(testOrderItem);
        when(implBusinessService.saveOrderItem(any(OrderItem.class))).thenReturn(testOrderItem);

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
