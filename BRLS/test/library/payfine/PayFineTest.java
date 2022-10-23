package library.payfine;

//import library.borrowitem.BorrowItemControl;
//import library.borrowitem.BorrowItemUI;
import library.entities.*;
import library.returnItem.ReturnItemControl;
import library.returnItem.ReturnItemUI;

//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;

//import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PayFineTest {

    static Library library;
    static Patron patron;
    static Item item;
    static Loan loan;
    static Calendar calendar;

    //static BorrowItemControl biControl;
    //static BorrowItemUI biUI;
    
    static ReturnItemControl riControl;
    @Mock static ReturnItemUI riUI;

    @BeforeAll
    static void setUpBeforeClass() {
        library = Library.getInstance();
        calendar = Calendar.getInstance();
        
        library.addPatron("Test", "Person", "test@example.com", 5551234);
        patron = library.getPatron(1);
        
        library.addItem("Another Person", "Test Book", "1", ItemType.BOOK);
        item = library.getItem(1);

        library.issueLoan(item, patron);
        loan = library.getLoanByItemId(1);
     
        calendar.incrementDate(4);
        loan.updateStatus(1.0);
    }

    @AfterAll
    static void tearDownAfterClass() {
        
    }

    @BeforeEach
    void setUp() {

    }

    @AfterEach 
    void tearDown() {

    }

    @Test 
    void testPayFine() {
        // Arrange
        riControl = new ReturnItemControl();
        riControl.setUi(riUI);

        // Act
        riControl.itemScanned(1);
        riControl.dischargeLoan(false);

        // Assert 
        assertEquals(2, patron.finesOwed());
    }

}