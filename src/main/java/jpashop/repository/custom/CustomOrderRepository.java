package jpashop.repository.custom;


import jpashop.domain.Order;
import jpashop.domain.OrderSearch;

import java.util.List;

public interface CustomOrderRepository {
    public List<Order> search(OrderSearch orderSearch);
}
