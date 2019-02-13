package org.lasencinas;

import java.util.LinkedList;
import java.util.List;

public class BlockChain {

        private List<Transaction> blockChain = new LinkedList<>();


        public void addOrigin(Transaction tran){
            blockChain.add(tran);
        }
}
