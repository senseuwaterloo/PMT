package pmt.project.mutator;

import java.io.IOException;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jface.text.BadLocationException;

import pmt.project.visitors.HWOVisitor;
import pmt.project.visitors.PTWVisitor;

public class Mutator {
	public void create_mutants(IPackageFragment packageFragment, ICompilationUnit unit,
			CompilationUnit parsedCompilationUnit) throws JavaModelException {
		
		if (pmt.project.setting.MutationSetting.specified_pattern.equals("hwo")) {
			HWOVisitor my_visitor=new HWOVisitor();
			my_visitor.set_unit(packageFragment, unit, parsedCompilationUnit);
			HWOvisiting(my_visitor);
			System.gc();
			return;
		} else if (pmt.project.setting.MutationSetting.specified_pattern.equals("ptw")) {
			PTWVisitor my_visitor=new PTWVisitor();
			my_visitor.set_unit(packageFragment, unit, parsedCompilationUnit);
			PTWvisiting(my_visitor);
			System.gc();
			return;
		}

	}

	private void PTWvisiting(PTWVisitor my_visitor) throws JavaModelException {
		pmt.project.visitors.ptw.Handler myHandler = new pmt.project.visitors.ptw.Handler(my_visitor);
		myHandler.unit_name = my_visitor.unit.getElementName();

		try {
			
			myHandler.analyze();
		} catch (JavaModelException | BadLocationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void HWOvisiting(HWOVisitor my_visitor) throws JavaModelException {

		pmt.project.visitors.hwo.Handler myHandler = new pmt.project.visitors.hwo.Handler(my_visitor);
		

		try {
			myHandler.analyze();
		} catch (JavaModelException | BadLocationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
