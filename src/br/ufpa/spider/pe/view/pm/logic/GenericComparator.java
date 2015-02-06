package br.ufpa.spider.pe.view.pm.logic;

import java.util.Comparator;

public class GenericComparator implements Comparator<Object> {

    @Override
    public int compare(Object o1, Object o2) {
        return o1.toString().compareToIgnoreCase(o2.toString());
    }

}
