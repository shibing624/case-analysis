package org.xm;

import org.xm.xmnlp.Xmnlp;
import org.xm.xmnlp.seg.domain.Term;

import java.util.List;

/**
 * @author xuming
 */
public class Case {
    public static void main(String[] args) {
        List<Term> list = Xmnlp.segment("你好外国人");
        System.out.println(list);
    }
}
