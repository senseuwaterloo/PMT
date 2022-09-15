package pmt.project.visitors;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class PTWVisitor implements PMTVisitor{

	public ICompilationUnit unit;
	public CompilationUnit parsedCompilationUnit;
	public IPackageFragment packageFragment;
	
	
	@Override
	public void set_unit(IPackageFragment packageFragment, ICompilationUnit unit, CompilationUnit parsedCompilationUnit)
			throws JavaModelException {
		this.packageFragment = packageFragment;
		this.parsedCompilationUnit = parsedCompilationUnit;
		this.unit = unit;
		
	}
}
