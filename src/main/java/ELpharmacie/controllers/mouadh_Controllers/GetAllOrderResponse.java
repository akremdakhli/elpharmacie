package ELpharmacie.controllers.mouadh_Controllers;

import ELpharmacie.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllOrderResponse {
    List<Order> orders;
}
