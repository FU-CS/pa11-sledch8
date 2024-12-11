package pa11;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {

    @Test
    void testPutAndGet() {
        HashMap map = new HashMap();

        assertNull(map.put("key1", "value1")); 
        assertEquals("value1", map.get("key1"));

        assertNull(map.put("key2", "value2")); 
        assertEquals("value2", map.get("key2"));

        assertEquals("value1", map.put("key1", "newValue1")); 
        assertEquals("newValue1", map.get("key1"));
    }

    @Test
    void testRemove() {
        HashMap map = new HashMap();
        map.put("key1", "value1");
        map.put("key2", "value2");

        assertEquals("value1", map.remove("key1"));
        assertNull(map.get("key1"));
        assertEquals(1, map.size());

        assertNull(map.remove("key3"));
    }

    @Test
    void testSizeAndIsEmpty() {
        HashMap map = new HashMap();

        assertTrue(map.isEmpty());
        assertEquals(0, map.size());

        map.put("key1", "value1");
        assertFalse(map.isEmpty());
        assertEquals(1, map.size());

        map.put("key2", "value2");
        assertEquals(2, map.size());

        map.remove("key1");
        assertEquals(1, map.size());

        map.remove("key2");
        assertTrue(map.isEmpty());
    }

    @Test
    void testKeySet() {
        HashMap map = new HashMap();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");

        String[] keys = map.keySet();
        assertArrayEquals(new String[]{"key1", "key2", "key3"}, keys);
    }

    @Test
    void testValues() {
        HashMap map = new HashMap();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");

        String[] values = map.values();
        assertArrayEquals(new String[]{"value1", "value2", "value3"}, values);
    }
}

