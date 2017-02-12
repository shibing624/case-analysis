package org.xm;

import org.ansj.splitWord.analysis.DicAnalysis;
import org.junit.Test;

import java.util.List;

/**
 * @author xuming
 */
public class CaseAnalysisTest {
    String text = "既往史：往体健，否认有高血压病及糖尿病病史。无肝炎、结核等传染病及密切接触史。无重大外伤、手术史及输血史。无食物及药物过敏史，预防接种史不祥。";
    String path = "library/case-reports/casereport1.txt";
    @Test
    public void segment() {
        System.out.println(CaseAnalysis.segment(text));
    }

    @Test
    public void segmentFile() {
        CaseAnalysis.segmentFile(path).forEach(System.out::println);
    }

    @Test
    public void extractSick() throws Exception {
        System.out.println(DicAnalysis.parse(text));
        CaseAnalysis analysis = new CaseAnalysis();
        List<String> list = analysis.extractSick(text);
        list.forEach(System.out::println);
    }

    @Test
    public void extractSickFile() throws Exception {
        List<String> list = CaseAnalysis.extractSickFile(path);
        list.forEach(System.out::println);
    }


}