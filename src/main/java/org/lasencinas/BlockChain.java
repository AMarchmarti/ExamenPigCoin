package org.lasencinas;

import java.security.PublicKey;
import java.util.LinkedList;
import java.util.List;

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
                    pigcoinsIn += tran.getPigcoins();
                }
                if (address.equals(tran.getPKeySender())){
                    pigcoinsOut += tran.getPigcoins();
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
}
