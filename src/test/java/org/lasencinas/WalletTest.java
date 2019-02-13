package org.lasencinas;

import org.junit.Assert;
import org.junit.Test;

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

}
