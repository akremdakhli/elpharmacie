package ELpharmacie.Service;
import java.util.Optional;
import java.util.List;


import ELpharmacie.entities.Order;

public interface OrderManagerInterface {
    List<Order> retrieveAllOrder();
    Order addOrder(Order O);
    void deleteOrder(Long id);
    Order updateOrder(Long id,Order O);
    Optional<Order> FindOrder(Long id);}
