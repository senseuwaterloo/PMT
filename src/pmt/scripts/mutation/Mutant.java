package pmt.scripts.mutation;

import java.util.ArrayList;
import java.util.List;

public class Mutant {


	public Mutant(String mutant_hash_code) {
		super();
		this.mutant_hash_code = mutant_hash_code;
		this.tests_covering = new ArrayList<>();
	}

	public String mutant_hash_code;
	public List<Test_covering> tests_covering;

	public class Test_covering {
		public Test_covering(String testName, int hitting_count, double bug_size, double avg_thrpt) {
			super();
			this.testName = testName;
			this.hitting_count = hitting_count;
			this.bug_size = bug_size;
			this.avg_thrpt = avg_thrpt;
		}
		public String testName="";
		public int hitting_count=0;
		public double bug_size=0.0;
		public double avg_thrpt=0.0;
	}
}


