package com.company;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static List<Block> blockchain = new ArrayList<Block>();
    public static int tamanoPrefixo = 3;
    public static String prefixo = new String(new char[tamanoPrefixo]).replace('\0', '0');

    public static void main(String[] args) {
        var genesis = new Block("Genesis", "0", new Date().getTime());
        genesis.minerar(tamanoPrefixo);
        blockchain.add(genesis);

        var b1 = new Block("B1", genesis.getHash(), new Date().getTime());
        b1.minerar(tamanoPrefixo);
        blockchain.add(b1);

        var b2 = new Block("B2", b1.getHash(), new Date().getTime());
        b1.minerar(tamanoPrefixo);
        blockchain.add(b2);

        blockchain.forEach(System.out::println);
        validarBlockChain();
    }

    public static void validarBlockChain() {
        boolean flag = true;
        for (int i = 0; i < blockchain.size(); i++) {
            String previousHash = i == 0 ? "0"
                    : blockchain.get(i - 1)
                    .getHash();
            flag = blockchain.get(i)
                    .getHash()
                    .equals(blockchain.get(i).calcularHash())
                    && previousHash.equals(blockchain.get(i)
                    .getPreviousHash())
                    && blockchain.get(i)
                    .getHash()
                    .substring(0, tamanoPrefixo)
                    .equals(prefixo);
            if (!flag)
                break;
        }

        System.out.println("blockchain é "+ (!flag ? "valida": "invalida"));
    }
}
