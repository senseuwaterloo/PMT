package pmt.project.mutator;

import java.io.IOException;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jface.text.BadLocationException;

import pmt.project.visitors.template.PMTVisitor;


public class PatternMutator {
	public void create_mutants(IPackageFragment packageFragment, ICompilationUnit unit,
			CompilationUnit parsedCompilationUnit) throws JavaModelException {

		if (pmt.project.setting.MutationSetting.specified_pattern.equals("hwo")) {
			PMTVisitor my_visitor=new PMTVisitor();
			my_visitor.set_unit(packageFragment, unit, parsedCompilationUnit);
			HWOvisiting(my_visitor);
			System.gc();
			return;
		} else if (pmt.project.setting.MutationSetting.specified_pattern.equals("ptw")) {
			PMTVisitor my_visitor=new PMTVisitor();
			my_visitor.set_unit(packageFragment, unit, parsedCompilationUnit);
			PTWvisiting(my_visitor);
			System.gc();
			return;
		} else if (pmt.project.setting.MutationSetting.specified_pattern.equals("sts1")) {
			PMTVisitor my_visitor=new PMTVisitor();
			my_visitor.set_unit(packageFragment, unit, parsedCompilationUnit);
			STS1visiting(my_visitor);
			System.gc();
			return;
		} else if (pmt.project.setting.MutationSetting.specified_pattern.equals("efl")) {
			PMTVisitor my_visitor=new PMTVisitor();
			my_visitor.set_unit(packageFragment, unit, parsedCompilationUnit);
			EFLvisiting(my_visitor);
			System.gc();
			return;
		} else if (pmt.project.setting.MutationSetting.specified_pattern.equals("soc")) {
			PMTVisitor my_visitor=new PMTVisitor();
			my_visitor.set_unit(packageFragment, unit, parsedCompilationUnit);
			SOCvisiting(my_visitor);
			System.gc();
			return;
		}

	}

	private void PTWvisiting(PMTVisitor my_visitor) throws JavaModelException {
		pmt.project.visitors.template.Handler myHandler = new pmt.project.visitors.template.Handler(my_visitor);
		myHandler.unit_name = my_visitor.unit.getElementName();

		try {

			myHandler.analyze();
		} catch (JavaModelException | BadLocationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void HWOvisiting(PMTVisitor my_visitor) throws JavaModelException {

		pmt.project.visitors.template.Handler myHandler = new pmt.project.visitors.template.Handler(my_visitor);


		try {
			myHandler.analyze();
		} catch (JavaModelException | BadLocationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void STS1visiting(PMTVisitor my_visitor) throws JavaModelException {

		pmt.project.visitors.template.Handler myHandler = new pmt.project.visitors.template.Handler(my_visitor);


		try {
			myHandler.analyze();
		} catch (JavaModelException | BadLocationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void EFLvisiting(PMTVisitor my_visitor) throws JavaModelException {

		pmt.project.visitors.template.Handler myHandler = new pmt.project.visitors.template.Handler(my_visitor);


		try {
			myHandler.analyze();
		} catch (JavaModelException | BadLocationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void SOCvisiting(PMTVisitor my_visitor) throws JavaModelException {

		pmt.project.visitors.template.Handler myHandler = new pmt.project.visitors.template.Handler(my_visitor);


		try {
			myHandler.analyze();
		} catch (JavaModelException | BadLocationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
