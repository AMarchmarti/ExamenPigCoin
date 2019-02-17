package org.lasencinas;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TransactionTest {

    @Test
    public void TransTest(){
        Transaction transaction = new Transaction();
        assertNotNull(transaction);
    }


    @Test
    public void getTest(){
        Wallet wallet_1 = new Wallet();
        Wallet wallet_2 = new Wallet();
        wallet_1.generateKeyPair();
        wallet_2.generateKeyPair();
        Transaction trx = new Transaction();
        trx = new Transaction("hash_1", "0", wallet_1.getAddress(), wallet_2.getAddress(), 20, "a flying pig!");
        assertEquals("hash_1", trx.getHash());
        assertEquals("0", trx.getPrevHash());
        assertNotNull(trx.getPKeySender());
        assertNotNull(trx.getpKeyRecipient());
        assertEquals(20.0, trx.getPigCoins(), 0.01);
        assertEquals("a flying pig!", trx.getMessage());
    }
}
