import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();


    public Order addOrder(List<String> productIds) throws NullPointerException{
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
           Optional<Product> productToOrder = productRepo.getProductById(productId);
            if (productToOrder == null) {
                throw new NullPointerException ("Product does not exist.");
            }
            products.add(productToOrder.get());
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products,OrderStatus.COMPLETED);

        return orderRepo.addOrder(newOrder);
    }
    public List<Order> orderStatus(OrderStatus status){
           return orderRepo.getOrders().stream().filter(order -> order.status().equals(status))
                   .toList();
    }

}
