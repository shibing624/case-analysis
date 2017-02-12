package org.xm;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.DicAnalysis;
import org.nlpcn.commons.lang.util.IOUtil;
import org.nlpcn.commons.lang.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xuming
 */
public class CaseAnalysis {
    public static final Logger LOGGER = LoggerFactory.getLogger(CaseAnalysis.class);

    public static Result segment(String str){
        return DicAnalysis.parse(str);
    }
    public static List<String> segment(List<String> strs){
        return strs.stream().map(str -> segment(str).toString()).collect(Collectors.toList());
    }
    public static List<String> segmentFile(String filePath){
        List<String> result = new ArrayList<>();
        List<String> fileStrs = new ArrayList<>();
        try {
            BufferedReader br = IOUtil.getReader(filePath, "utf-8");
            String temp;
            while ((temp = br.readLine()) != null) {
                if (StringUtil.isNotBlank(temp)) {
                    //temp = StringUtil.trim(temp);
                    fileStrs.add(temp);
                }
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.warn("unsupport encoding exception" + e);
        } catch (IOException e) {
            LOGGER.warn("IO exception file :{},path:{}", e.getMessage(), filePath);
        }

        return segment(fileStrs);
    }

    public static List<String> extractSick(String str) {
        List<String> result = new ArrayList<>();
        List<Term> list = segment(str).getTerms();
        if (list.size() > 0)
            result.addAll(list.stream()
                    .filter(term -> term.getNatureStr().equals("sick"))
                    .map(Term::getName)
                    .collect(Collectors.toList()));
        return result;
    }

    public static List<String> extractSick(List<String> strs) {
        List<String> result = new ArrayList<>();
        for (String str : strs) result.addAll(extractSick(str));
        return result;
    }

    public static List<String> extractSickFile(String filePath) {
        List<String> result = new ArrayList<>();
        try {
            BufferedReader br = IOUtil.getReader(filePath, "utf-8");
            String temp;
            while ((temp = br.readLine()) != null) {
                if (StringUtil.isNotBlank(temp)) {
                    temp = StringUtil.trim(temp);
                    result.add(temp);
                }
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.warn("unsupport encoding exception" + e);
        } catch (IOException e) {
            LOGGER.warn("IO exception file :{},path:{}", e.getMessage(), filePath);
        }
        return extractSick(result);
    }
}
