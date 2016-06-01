package de.saring.sportstracker.data;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * This class contains all unit tests for the SportSubType class.
 *
 * @author Stefan Saring
 */
public class SportSubTypeTest {

    /**
     * Tests of method clone().
     */
    @Test
    public void testClone() {
        SportSubType sstOrg = new SportSubType(123);
        sstOrg.setName("Sstype");

        SportSubType sstClone = sstOrg.clone();
        assertFalse(sstOrg == sstClone);
        assertEquals(123, sstClone.getId());
        assertEquals("Sstype", sstClone.getName());
    }
}
