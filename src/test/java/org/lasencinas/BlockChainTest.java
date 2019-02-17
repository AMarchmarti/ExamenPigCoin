package org.lasencinas;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BlockChainTest {

    @Test
    public void BlockTest(){
        BlockChain bk = new BlockChain();
        Assert.assertNotNull(bk);
    }


    @Test
    public void chainTest(){
        Transaction trx = new Transaction();
        Wallet origin = new Wallet();
        origin.generateKeyPair();
        Wallet wallet_1 = new Wallet();
        Wallet wallet_2 = new Wallet();
        wallet_1.generateKeyPair();
        wallet_2.generateKeyPair();
        BlockChain bChain = new BlockChain();
        trx = new Transaction("hash_1", "0", origin.getAddress(), wallet_1.getAddress(), 20, "bacon eggs");
        bChain.addOrigin(trx);
        trx = new Transaction("hash_2", "1", origin.getAddress(), wallet_2.getAddress(), 10, "spam spam spam");
        bChain.addOrigin(trx);
        trx = new Transaction("hash_3", "hash_1", wallet_1.getAddress(), wallet_2.getAddress(), 20, "a flying pig!");
        bChain.addOrigin(trx);
        Assert.assertNotNull(bChain);
    }

    @Test
    public void load_wallet_test() {

        Wallet origin = new Wallet();
        origin.generateKeyPair();
        Wallet wallet_1 = new Wallet();
        wallet_1.generateKeyPair();
        Wallet wallet_2 = new Wallet();
        wallet_2.generateKeyPair();

        BlockChain bChain = new BlockChain();
        Transaction transaction = new Transaction("hash_1", "0", origin.getAddress(), wallet_1.getAddress(), 20, "spam eggs");
        bChain.addOrigin(transaction);
        transaction = new Transaction("hash_2", "1", origin.getAddress(), wallet_2.getAddress(), 10, "spam eggs spam");
        bChain.addOrigin(transaction);
        transaction = new Transaction("hash_3", "hash_1", wallet_1.getAddress(), wallet_2.getAddress(), 10, "pig things!");
        bChain.addOrigin(transaction);
        transaction = new Transaction("hash_4", "CA_hash_3", wallet_1.getAddress(), wallet_1.getAddress(), 10, "spam spam spam");
        bChain.addOrigin(transaction);

        double[] pigcoins = bChain.loadWallet(wallet_1.getAddress());
        assertEquals(30, pigcoins[0], 0);
        assertEquals(20, pigcoins[1], 0);
    }


    @Test
    public void load_Input_Transactions_test() {

        Wallet origin = new Wallet();
        origin.generateKeyPair();
        Wallet wallet_1 = new Wallet();
        wallet_1.generateKeyPair();
        Wallet wallet_2 = new Wallet();
        wallet_2.generateKeyPair();
        Wallet wallet_3 = new Wallet();
        wallet_3.generateKeyPair();

        BlockChain bChain = new BlockChain();
        Transaction transaction = new Transaction("hash_1", "0", origin.getAddress(), wallet_1.getAddress(), 20, "a flying pig!");
        bChain.addOrigin(transaction);
        transaction = new Transaction("hash_2", "1", origin.getAddress(), wallet_2.getAddress(), 10, "pig things!");
        bChain.addOrigin(transaction);

        List<Transaction> inputTransactions = bChain.loadInputTransactions(wallet_1.getAddress());
        assertNotNull(inputTransactions);
        assertTrue(inputTransactions.size() == 1);
        assertFalse(inputTransactions.contains(transaction));

        inputTransactions = bChain.loadInputTransactions(wallet_2.getAddress());
        assertNotNull(inputTransactions);
        assertTrue(inputTransactions.size() == 1);
        assertTrue(inputTransactions.contains(transaction));

        inputTransactions = bChain.loadInputTransactions(wallet_3.getAddress());
        assertNotNull(inputTransactions);
        assertTrue(inputTransactions.size() == 0);
        assertFalse(inputTransactions.contains(transaction));
    }

}
