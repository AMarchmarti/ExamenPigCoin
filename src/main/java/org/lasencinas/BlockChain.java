package org.lasencinas;

import java.security.PublicKey;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BlockChain {

        private List<Transaction> blockChain = new LinkedList<>();


        /*
         * Getters
         */
        public List<Transaction> getBlockChain(){
            return this.blockChain;
        }

        /*
         * Lógica
         */

        public void addOrigin(Transaction tran){
            this.getBlockChain().add(tran);
        }


        public void summarize(){
            for (Transaction tran : this.getBlockChain()){
                System.out.println(tran);
            }
        }


        public void summarize(int index){
            System.out.println(getBlockChain().get(index));
        }


        public double [] loadWallet(PublicKey address){
            double pigcoinsIn = 0d;
            double pigcoinsOut = 0d;
            for (Transaction tran : this.getBlockChain()){
                if (address.equals(tran.getpKeyRecipient())){
                    pigcoinsIn += tran.getPigCoins();
                }
                if (address.equals(tran.getPKeySender())){
                    pigcoinsOut += tran.getPigCoins();
                }
            }
            double [] pigcoins = {pigcoinsIn, pigcoinsOut};
            return pigcoins;
        }


        public List<Transaction> loadInputTransactions(PublicKey address){
            List<Transaction> inputs = new LinkedList<>();
            for (Transaction tran : getBlockChain()){
                if (address.equals(tran.getpKeyRecipient())){
                    inputs.add(tran);
                }
            }
            return inputs;
        }


    public List<Transaction> loadOutputTransactions(PublicKey address){
        List<Transaction> outputs = new LinkedList<>();
        for (Transaction tran : getBlockChain()){
            if (address.equals(tran.getPKeySender())){
                outputs.add(tran);
            }
        }
        return outputs;
    }


    public boolean isSignatureValid(PublicKey pKey, String message, byte[] signedTransaction){
            return GenSig.verify(pKey, message, signedTransaction);
    }


    public boolean isConsumedCoinValid(Map<String, Double> consumedCoins) {
        for (String hash : consumedCoins.keySet()) {
            for (Transaction transaction : getBlockChain()) {
                if (hash.equals(transaction.getPrevHash())) {
                    return false;
                }
            }
        }
        return true;
    }


    public void createTransaction(PublicKey pKey_sender, PublicKey pKey_recipient, Map<String, Double> consumedCoins,
                                  String message, byte[] signedTransaction) {

        PublicKey address_recipient = pKey_recipient;
        Integer lastBlock = 0;

        for (String prev_hash : consumedCoins.keySet()) {

            if (prev_hash.startsWith("CA_")) {
                pKey_recipient = pKey_sender;
            }

            lastBlock = getBlockChain().size() + 1;
            Transaction transaction = new Transaction("hash_" + lastBlock.toString(), prev_hash, pKey_sender,
                    pKey_recipient, consumedCoins.get(prev_hash), message);
            getBlockChain().add(transaction);

            pKey_recipient = address_recipient;
        }
    }


    public void processTransactions(PublicKey pKey_sender, PublicKey pKey_recipient, Map<String, Double> consumedCoins,
                                    String message, byte[] signedTransaction) {

        if (isSignatureValid(pKey_sender, message, signedTransaction) && isConsumedCoinValid(consumedCoins)) {
            // crear las nuevas transacciones y añadirlas al blockchain
            createTransaction(pKey_sender, pKey_recipient, consumedCoins, message, signedTransaction);
        }

    }
}
