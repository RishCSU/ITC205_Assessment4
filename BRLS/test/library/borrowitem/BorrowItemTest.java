package library.borrowitem;

import library.entities.*;

//import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BorrowItemTest {

    static Library library;
    static Patron patron;
    static Item item;
    static Loan loan;
    static Calendar calendar;
    
    static BorrowItemControl biControl;
    @Mock static BorrowItemUI biUI;

    @BeforeAll
    static void setUpBeforeClass() {
        
    }

    @AfterAll
    static void tearDownAfterClass() {

    }

    @BeforeEach
    void setUp() {
        library = Library.getInstance();
        calendar = Calendar.getInstance();
        
        library.addPatron("Test", "Person", "test@example.com", 5551234);
        patron = library.getPatron(1);
        
        library.addItem("Another Person", "Test Book 1", "1", ItemType.BOOK);
        library.addItem("Another Person", "Test Book 2", "2", ItemType.BOOK);
        library.addItem("Another Person", "Test Book 3", "3", ItemType.BOOK);
        library.addItem("Another Person", "Test Book 4", "4", ItemType.BOOK);
        library.addItem("Another Person", "Test Book 5", "5", ItemType.BOOK);
        library.addItem("Another Person", "Test Book 6", "6", ItemType.BOOK);
                
    }

    @AfterEach 
    void tearDown() {

    }

    @Test 
    void testBorrowItem() {
        // Arrange
        biControl = new BorrowItemControl();
        biControl.setUI(biUI);

        // Act
        biControl.cardSwiped(1);
        biControl.itemScanned(1);
        biControl.itemScanned(2);

        // Assert 
        verify(biUI, times(1)).display("Loan limit reached");
    }

    @Test 
    void testBorrowItemAgain() {
        // Arrange
        biControl = new BorrowItemControl();
        biControl.setUI(biUI);

        // Act
        biControl.cardSwiped(1);
        biControl.itemScanned(1);
        biControl.itemScanned(2);
        Executable act = () -> biControl.itemScanned(3);

        // Assert 
        assertThrows(RuntimeException.class, act);
    }
    
}