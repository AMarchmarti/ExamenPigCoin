package org.lasencinas;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.*;

public class Wallet {

        private PublicKey address = null;
        private PrivateKey sKey = null;
        private double total_input = 0;
        private double total_output = 0;
        private double balance = 0;
        private List<Transaction> inputTransactions = new LinkedList<>();
        private List<Transaction> outputTransactions = new LinkedList<>();

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

        public void setInputTransactions(List<Transaction> lista){
            this.inputTransactions = lista;
        }


        public void setOutputTransactions(List<Transaction> lista){
            this.outputTransactions = lista;
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


        public List<Transaction> getInputTransactions(){
            return this.inputTransactions;
        }


        public List<Transaction> getOutputTransactions(){
            return this.outputTransactions;
        }


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



        public void loadInputTransactions(BlockChain bChain){
            setInputTransactions(bChain.loadInputTransactions(getAddress()));
        }

        public void loadOutputTransactions(BlockChain bChain){
            setOutputTransactions(bChain.loadOutputTransactions(getAddress()));
        }

        public Map<String, Double> collectCoins(double pigcoins) {

            Map<String, Double> collectedCoins = new LinkedHashMap<>();

            if (getInputTransactions() == null) {
                return null;
            }

            if (pigcoins > getBalance()) {
                return null;
            }

            Double achievedCoins = 0d;

            Set<String> consumedCoins = new HashSet<>();
            if (getOutputTransactions() != null) {
                for (Transaction transaction : getOutputTransactions()) {
                    consumedCoins.add(transaction.getPrevHash());
                }
            }

            for (Transaction transaction : getInputTransactions()) {

                if (consumedCoins.contains(transaction.getHash())) {
                    continue;
                }

                if (transaction.getPigCoins() == pigcoins) {
                    collectedCoins.put(transaction.getHash(), transaction.getPigCoins());
                    consumedCoins.add(transaction.getHash());
                    break;
                } else if (transaction.getPigCoins() > pigcoins) {
                    collectedCoins.put(transaction.getHash(), pigcoins);
                    collectedCoins.put("CA_" + transaction.getHash(), transaction.getPigCoins() - pigcoins);
                    consumedCoins.add(transaction.getHash());
                    break;
                } else {
                    collectedCoins.put(transaction.getHash(), transaction.getPigCoins());
                    achievedCoins = transaction.getPigCoins();
                    pigcoins = pigcoins - achievedCoins;
                    consumedCoins.add(transaction.getHash());
                }

            }
            return collectedCoins;
        }

        public byte[] signTransaction(String message) {
            return GenSig.sign(getSKey(), message);
        }

        public void sendCoins(PublicKey pKey_recipient, Double coins, String message, BlockChain bChain) {

            Map<String, Double> consumedCoins = new LinkedHashMap<>();

            consumedCoins = collectCoins(coins);

            if (consumedCoins != null) {
                bChain.processTransactions(getAddress(), pKey_recipient, consumedCoins, message, signTransaction(message));
            }

            this.loadCoins(bChain);
        }

}

