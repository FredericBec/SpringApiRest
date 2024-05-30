

import fr.fms.SpringApiRest.dao.OrderRepository;
import fr.fms.SpringApiRest.entities.Customer;
import fr.fms.SpringApiRest.entities.Order;
import fr.fms.SpringApiRest.service.ImplBusinessService;
import fr.fms.SpringApiRest.web.OrderController;
import fr.fms.SpringApiRest.web.TrainingController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

@WebMvcTest(controllers = OrderController.class)
public class OrderControllerTest {
    @Autowired
    private ImplBusinessService implBusinessService;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testSaveOrder() {
        Customer customer = new Customer(null, "John", "Doe", "rue des maisons", "john.doe@gmail.com", "0666666666",null);
        Order order = new Order(null, new Date(), 100.0, customer, null);
        Order savedOrder = implBusinessService.saveOrder(order);
        assertNotNull(savedOrder.getId());
    }
}
