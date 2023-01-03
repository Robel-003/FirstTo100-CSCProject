import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class CircularLinkedListTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                          HAPPY TESTS                                                               //
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    void constructorTest() {
        CircularLinkedList<Integer> nums = new CircularLinkedList<>();
        assertEquals(0, nums.getSize());

        CircularLinkedList<Integer> myInts = new CircularLinkedList<>();
        myInts.add(10);
        myInts.add(20);
        myInts.add(30);
        myInts.add(40);
        myInts.add(50);
        myInts.add(60);
        myInts.add(70);

        assertEquals(7, myInts.getSize());
        assertEquals(10, myInts.get(0));
    }


    @Test
    void addMethodTest() {
        CircularLinkedList<String> names = new CircularLinkedList<>();
        names.add("al");
        names.add("bev");
        names.add("cyn");
        names.add("dan");
        names.add("faye");

        assertEquals(5, names.getSize());

        assertTrue(names.get(4).equals("faye"));

        names.addAtFront("abe");
        assertTrue(names.get(0).equals("abe"));

        assertEquals(6, names.getSize());
    }


    @Test
    void removeMethodTest() {
        CircularLinkedList<String> names = new CircularLinkedList<>();
        names.add("al");
        names.add("bev");
        names.add("cyn");
        names.add("dan");
        names.add("faye");

        names.remove(0);
        assertEquals(4, names.getSize());
        assertTrue(names.get(0).equals("bev"));

        names.remove(3);
        assertTrue(names.get(2).equals("dan"));
        assertEquals(3, names.getSize());
    }


    @Test
    void otherRemoveMethodTest() {
        CircularLinkedList<String> aName = new CircularLinkedList<>();
        aName.add("jay");
        assertEquals(1, aName.getSize());
        aName.remove("jay");
        assertEquals(0, aName.getSize());


        CircularLinkedList<String> names = new CircularLinkedList<>();
        assertFalse(names.remove("jamies"));

        names.add("al");
        names.add("bev");
        names.add("cyn");
        names.add("dan");
        names.add("faye");

        assertFalse(names.remove("gina"));

        assertTrue(names.remove("al"));
        assertTrue(names.get(0).equals("bev"));

        assertTrue(names.remove("cyn"));

        assertTrue(names.remove("faye"));
        assertTrue(names.get(1).equals("dan"));

        assertEquals(2, names.getSize());
    }

    @Test
    void iteratorMethodTest() {
        CircularLinkedList<String> names = new CircularLinkedList<>();
        names.add("dan");
        names.add("beverly");
        names.add("cynthia");
        names.add("sam");
        names.add("eli");
        names.add("dan");
        names.add("gina");
        names.add("dan");

        assertEquals(8, names.getSize());

        int totalNamesCount = 0;
        int nameLenLessThan = 0;
        int dansInList = 0;

        Iterator<String> it = names.iterator();
        for (int pos = 0; pos < names.getSize() && it.hasNext(); pos++) {
            String aName = it.next();
            if (aName.length() <= 3) {
                nameLenLessThan++;
            }
            if (aName.equals("dan")) {
                dansInList++;
            }
            totalNamesCount++;
        }

        assertEquals(8, totalNamesCount);
        assertEquals(5, nameLenLessThan);
        assertEquals(3, dansInList);

    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                          EXCEPTIONS TESTS                                                          //
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    void constructorExceptionTest() {
        CircularLinkedList<Integer> nums = new CircularLinkedList<>();
        assertThrows(IllegalArgumentException.class,
                () -> nums.get(0));
    }

    @Test
    void getMethodExceptionTest() {
        CircularLinkedList<String> names = new CircularLinkedList<>();
        names.add("abe");
        names.add("bev");
        names.add("cyn");

        assertThrows(IllegalArgumentException.class,
                () -> names.get(-1));

        assertThrows(IllegalArgumentException.class,
                () -> names.get(3));
    }

    @Test
    void removeMethodExceptionTest() {
        CircularLinkedList<Integer> myInts = new CircularLinkedList<>();
        myInts.add(10);
        myInts.add(20);
        myInts.add(30);
        myInts.add(40);

        assertThrows(IllegalArgumentException.class,
                () -> myInts.remove(-1));

        assertThrows(IllegalArgumentException.class,
                () -> myInts.remove(4));

    }

    @Test
    void iteratorMethodExceptionTest() {
        CircularLinkedList<Integer> myInts = new CircularLinkedList<>();

        int count = 0;
        Iterator<Integer> it = myInts.iterator();

        assertFalse(it.hasNext());

        assertThrows(NoSuchElementException.class,
                () -> it.next());

        assertThrows(UnsupportedOperationException.class,
                () -> it.remove());
    }

}