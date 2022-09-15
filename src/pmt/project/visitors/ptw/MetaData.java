package pmt.project.visitors.ptw;

import java.util.List;

public class MetaData {

	public List<String> primitiveList;
	public List<String> wrappersList;


	public MetaData(List<String> primitiveList, List<String> wrappersList) {
		this.primitiveList = primitiveList;
		this.wrappersList = wrappersList;

	}
}