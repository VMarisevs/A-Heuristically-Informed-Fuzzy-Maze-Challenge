package ie.gmit.sw.ai.jfuzzy;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class Fight {

	
	
	private FIS fis;
	private FunctionBlock functionBlock;
	
	// creating a singleton class
	private static Fight instance;
	
	private Fight() {
		String fileName = "fcl/FightCalc.fcl";
        fis = FIS.load(fileName,true);
        
        functionBlock = fis.getFunctionBlock("Fight");
	}
	
	public static Fight getInstance(){
		
		if (instance == null){
			instance = new Fight();
		}
		
		return instance;
	}
	
	public double getVictory(int monsterStrength, int weaponPower, int lifeForce){
				
        fis.setVariable("monster_strength", monsterStrength);
        fis.setVariable("weapon_power", weaponPower);
        fis.setVariable("life_force", lifeForce);
        fis.evaluate();        
        Variable victory = functionBlock.getVariable("victory");
        
        System.out.println("Monster strength: " + monsterStrength 
        			+ " Weapon power: " + weaponPower 
        			+ " Life force: " + lifeForce);
		System.out.println("Fight victory result: " + victory.getValue());
		return victory.getValue();
	}
	
	public static void main(String[] args) {
		/*
		 * This is jfuzzy logic tester class
		 * 
		 */
		
		String fileName = "fcl/FightCalc.fcl";
        FIS fis = FIS.load(fileName,true);
        
        FunctionBlock functionBlock = fis.getFunctionBlock("Fight");
        
        JFuzzyChart.get().chart(functionBlock);
        
        /*
         *  monster_strength 		low		average		high
         *  						->2		3			7->
         * 
         * 	weapon_power	none	low		average		high
         * 					->1		1 4 6	7			7->
         * 
         *  life_force		dead	alive
         *  				->20	40->
         */
        
        /*
         * Testing:
         * Rule 1: weak monster, powerful weapon = excellent victory
         */
        fis.setVariable("monster_strength", 0);
        fis.setVariable("weapon_power", 0);
        fis.setVariable("life_force", 0);
        fis.evaluate();        
        Variable victory = functionBlock.getVariable("victory");
        System.out.println("Rule 1 excellent victory : " + victory.getValue());
        
        
        
        //JFuzzyChart.get().chart(victory, victory.getDefuzzifier(), true);

	}
}
