package pmt.scripts.mutation;

import java.util.Map;
import java.util.TreeMap;

public class Test_mutant_hitting {
	public String testName = null;
	public Map<Integer, Integer> mutant_hitting = null;
	Map<Integer, Integer> treeMap=null;
	public String results="";


	public Map<Integer, Integer> getTreeMap() {
		return new TreeMap<>(mutant_hitting);
	}



	public String whole_info="";

	public Test_mutant_hitting(String name) {
		this.testName = name;
		this.mutant_hitting = new TreeMap<>();

	}

	public void setResults(String results) {
		this.results=results;
		// TODO Auto-generated method stub

	}
}
