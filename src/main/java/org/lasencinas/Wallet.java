package org.lasencinas;

import java.security.PrivateKey;
import java.security.PublicKey;

public class Wallet {

        private PublicKey address = null;
        private PrivateKey sKey = null;

    /*-------------Setters-------------------------*/

        public void setSK(PrivateKey sKey){
            this.sKey = sKey;
        }

        public void setAddress(PublicKey adress){
            this.address = adress;
        }

    /*----------------Getters-----------------------*/

        public PublicKey getAddress(){
            return this.address;
        }
}
