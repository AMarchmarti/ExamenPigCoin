package org.lasencinas;

import java.security.PublicKey;

public class Transaction {

        private String hash = null;
        private String prev_hash = null;
        private PublicKey pKey_sender = null;
        private PublicKey pKey_recipient = null;
        private double pigcoins = 0d;
        private String message = null;
        private byte [] signature = null;

        //Constructor
        public Transaction(){

        }

        public Transaction(String hash, String prev_hash, PublicKey enviado, PublicKey recibido, double pigcoins, String mensaje){
            this.hash = hash;
            this.prev_hash = prev_hash;
            this.pKey_sender = enviado;
            this.pKey_recipient = recibido;
            this.pigcoins = pigcoins;
            this.message = mensaje;
        }


        public void setSignature(byte[] signature){
            this.signature = signature;
        }

    /*----------------Getters---------------------------------*/
        public String getHash(){
            return this.hash;
        }


        public String getPrevHash(){
            return this.prev_hash;}


        public PublicKey getPKeySender() {
            return this.pKey_sender;
        }


        public PublicKey getpKeyRecipient(){
            return this.pKey_recipient;}


        public Double getPigCoins(){
            return this.pigcoins;}


        public String getMessage(){
            return this.message;}


        public byte[] getSignature(){
            return this.signature;
        }



        //Lógica

        @Override
        public String toString(){
            return "\n" + "hash = " + getHash()  + "\n" +
                    "prev_hash = " + getPrevHash() + "\n" +
                    "pKey_sender = " + getPKeySender().hashCode() + "\n" +
                    "pKey_recipient = " + getpKeyRecipient().hashCode() + "\n" +
                    "pigcoins = " + getPigCoins() + "\n" +
                    "message = " + getMessage();
        }
}
