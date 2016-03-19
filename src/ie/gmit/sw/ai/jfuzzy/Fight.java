package ie.gmit.sw.ai.jfuzzy;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;

public class Fight {

	public static void main(String[] args) {
		/*
		 * This is jfuzzy logic tester class
		 * 
		 */
		
		String fileName = "fcl/FightCalc.fcl";
        FIS fis = FIS.load(fileName,true);
        
        FunctionBlock functionBlock = fis.getFunctionBlock("Fight");
        
        JFuzzyChart.get().chart(functionBlock);
	}
}
