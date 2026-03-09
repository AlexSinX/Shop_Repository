package ProductManager;

import Product_Manager.NotFoundException;
import Product_Manager.Product;
import Product_Manager.ShopRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShopRepositoryTest {

    @Test
    public void shouldRemoveExistingProduct() {
        ShopRepository repo = new ShopRepository();
        Product product1 = new Product(1, "телефон", 30_000);
        Product product2 = new Product(2, "книга", 300);
        Product product3 = new Product(3, "машина", 3_000_000);

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        repo.remove(2);

        Product[] actual = repo.findAll();
        Product[] expected = {product1, product3};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowExceptionWhenRemovingNonExistingProduct() {
        ShopRepository repo = new ShopRepository();
        Product product1 = new Product(1, "телефон", 30_000);
        Product product2 = new Product(2, "книга", 300);
        Product product3 = new Product(3, "машина", 3_000_000);

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        int nonExistingId = 235234;

        // Проверяем, что метод выбрасывает исключение
        assertThrows(NotFoundException.class,
                () -> repo.remove(nonExistingId));

        // Проверяем, что репозиторий не изменился
        Product[] actual = repo.findAll();
        Product[] expected = {product1, product2, product3};
        assertArrayEquals(expected, actual);
    }
}