package org.lasencinas;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Wallet {

        private PublicKey address = null;
        private PrivateKey sKey = null;
        private double total_input = 0;
        private double total_output = 0;
        private double balance = 0;
        private int inputTransactions = 0;
        private int outputTransactins = 0;

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

    /*----------------Lógica------------------------*/

        public void generateKeyPair(){
            setAddress(GenSig.generateKeyPair().getPublic());
        }

        @Override
        public String toString() {
            return "\n" + "Wallet = " + getAddress().hashCode() + "\n";/* +
                        "Total input = " + getTotalInput() + "\n" +
                        "Total output = " + getTotalOutput() + "\n" +
                        "Balance = " + getBalance() + "\n";*/
            }
        }

