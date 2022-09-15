package pmt.project.visitors;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.CompilationUnit;

public interface PMTVisitor {

	public void set_unit(IPackageFragment packageFragment, ICompilationUnit unit, CompilationUnit parsedCompilationUnit)
			throws JavaModelException; 
}
