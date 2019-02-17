package org.lasencinas;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class WalletTest {

    @Test
    public void constructor_test() {
        Wallet wallet = new Wallet();
        assertNotNull(wallet);
    }

    @Test
    public void generate_key_pair_test() {
        Wallet wallet = new Wallet();
        assertNotNull(wallet);
        wallet.generateKeyPair();
        assertNotNull(wallet.getAddress());
        assertNotNull(wallet.getSKey());
    }


    @Test
    public void total_pigcoins_input_y_output_test() {

        Wallet origin = new Wallet();
        origin.generateKeyPair();
        Wallet wallet_1 = new Wallet();
        wallet_1.generateKeyPair();
        Wallet wallet_2 = new Wallet();
        wallet_2.generateKeyPair();

        BlockChain bChain = new BlockChain();
        Transaction trx = new Transaction("hash_1", "0", origin.getAddress(), wallet_1.getAddress(), 20, "a flying pig!");
        bChain.addOrigin(trx);
        trx = new Transaction("hash_2", "1", origin.getAddress(), wallet_2.getAddress(), 10, "pig things!");
        bChain.addOrigin(trx);

        wallet_1.loadCoins(bChain);
        assertEquals(20, wallet_1.getTotalInput(), 0);
        assertEquals(0, wallet_1.getTotalOutput(), 0);
        assertEquals(20, wallet_1.getBalance(), 0);

        wallet_2.loadCoins(bChain);
        assertEquals(10, wallet_2.getTotalInput(), 0);
        assertEquals(0, wallet_2.getTotalOutput(), 0);
        assertEquals(10, wallet_2.getBalance(), 0);

        // una wallet que no existe
        Wallet wallet_3 = new Wallet();
        wallet_3.generateKeyPair();
        wallet_3.loadCoins(bChain);
        assertEquals(0, wallet_3.getTotalInput(), 0);
        assertEquals(0, wallet_3.getTotalOutput(), 0);
        assertEquals(0, wallet_3.getBalance(), 0);

}
