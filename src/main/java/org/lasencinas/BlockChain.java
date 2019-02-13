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

        public void addOrigin(Transaction tran){
            getBlockChain().add(tran);
        }
}
