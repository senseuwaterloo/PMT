package pmt.project.mutator;


public class MutantReplacement {

	public String className;
	public String methodName;
	public String node;
	public String typeName;
	public String line;
	public String position;
	public int mutant_hash;

	public MutantReplacement(String node, String className, String methodName, String typeName, String line, String position) {
		this.node=node;
		this.className=className;
		this.methodName=methodName;
		this.typeName=typeName;
		this.line=line;
		this.position=position;
		this.mutant_hash=get_hash();
	}
	public int get_hash() {
		String str_hash=className+methodName+node.toString()+line+position;
		return str_hash.hashCode();

	}
}
