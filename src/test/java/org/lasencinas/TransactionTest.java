package org.lasencinas;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TransactionTest {

    @Test
    public void TransTest(){
        Transaction transaction = new Transaction();
        assertNotNull(transaction);
    }
}
