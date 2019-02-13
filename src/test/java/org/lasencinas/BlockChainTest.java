package org.lasencinas;

import org.junit.Assert;
import org.junit.Test;

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
}
