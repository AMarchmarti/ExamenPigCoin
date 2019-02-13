package org.lasencinas;

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
}
