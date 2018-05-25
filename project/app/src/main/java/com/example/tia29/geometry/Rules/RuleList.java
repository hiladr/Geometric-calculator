package com.example.tia29.geometry.Rules;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  Naomi on 26/04/2018.
 */
//The class represents the list of all rules in application
public class RuleList {
    private static List<String> ruleList = new ArrayList<String>();

    //This function initiates the rule list
    private static void createRuleList(){
        ruleList.add("SetEqualAngles");
        ruleList.add("ShveShokayim");
        ruleList.add("ShniGdalimShavim");
		ruleList.add("Chofef1");
		ruleList.add("Chofef2");
		ruleList.add("Chofef3");
		ruleList.add("Equals");
		ruleList.add("kodkodiyot");
		ruleList.add("MeshulahHazahav");
		ruleList.add("MeshulashYasharZavit");
		ruleList.add("partsOfSegments");
		ruleList.add("ShveShokayim2");
		ruleList.add("ShveTzlaot");
		ruleList.add("ShveTzlaot2");
		ruleList.add("Tzmudot");
		
		
		
		

    }
    public static List<String> getRuleList(){
        //if ruleList wasn't yet initiated, create the list
        if(ruleList.isEmpty())
            createRuleList();
        return ruleList;
    }

}
