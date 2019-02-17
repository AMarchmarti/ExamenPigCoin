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

        public void setTotal_input(double input){
            this.total_input = input;
        }

        public void setTotal_output(double output){
            this.total_output = output;
        }

        public void setBalance(){
            this.balance = getTotalInput() - getTotalOutput();
        }

    /*----------------Getters-----------------------*/

        public PublicKey getAddress(){
            return this.address;
        }


        public PrivateKey getSKey(){
            return this.sKey;
        }


        public double getTotalInput(){return this.total_input;}


        public double getTotalOutput(){return this.total_output;}


        public double getBalance(){return this.balance;}

    /*----------------Lógica------------------------*/

        public void generateKeyPair(){
            setAddress(GenSig.generateKeyPair().getPublic());
        }

        @Override
        public String toString() {
            return "\n" + "Wallet = " + getAddress().hashCode() + "\n" +
                        "Total input = " + getTotalInput() + "\n" +
                        "Total output = " + getTotalOutput() + "\n" +
                        "Balance = " + getBalance() + "\n";
            }


        public void loadCoins (BlockChain bChain){
            double[] transferencia = bChain.loadWallet(this.getAddress());
            setTotal_input(transferencia[0]);
            setTotal_output(transferencia[1]);
            setBalance();
        }
        }

