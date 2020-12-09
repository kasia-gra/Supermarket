import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SupermarketTest {

    FileHandler fh = new FileHandler();
    Supermarket supermarket = new Supermarket(fh, "prices.csv");
    List<Integer> bill2 = new ArrayList<>();
    List<Integer> bill3 = new ArrayList<>();
    List<Integer> bill1  = new ArrayList<>();

    SupermarketTest() throws IOException {
    }

    @Test
    void displayBudget() throws InterruptedException, IOException {
        addProductsToBill1();
        addProductsToBill2();
        addProductsToBill3();
        assertAll(
                () -> assertEquals(BigDecimal.valueOf(5.65), supermarket.scanBill(bill1)),
                () -> assertEquals((BigDecimal.valueOf(11.70).setScale(2)), supermarket.scanBill(bill2)),
                () -> assertEquals(BigDecimal.valueOf(4.85), supermarket.scanBill(bill3))
        );

    }

    private void addProductsToBill1(){
        bill1.add(1001);
        bill1.add(1001);
        bill1.add(3401);
        bill1.add(1001);
    }

    private void addProductsToBill2(){
        bill2.add(1001);
        bill2.add(1001);
        bill2.add(3401);
        bill2.add(1001);
        bill2.add(1243);
        bill2.add(1001);
        bill2.add(3401);
        bill2.add(1001);
        bill2.add(1001);
        bill2.add(1243);
    }

    private void addProductsToBill3(){
        bill3.add(1001);
        bill3.add(1243);
        bill3.add(3401);
    }

}