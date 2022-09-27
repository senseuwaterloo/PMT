package pmt.project.visitors.template;

import java.io.IOException;

import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.text.edits.MalformedTreeException;

import pmt.project.setting.MutationSetting;

public class Visitor {

	public Handler myParent;
	public static String str_wrapper;
	public static String str_valueOf = "valueOf";
	public static String str_primitiveValue;
	public Injector myInjector = null;

	public void set_unit(Handler myParent) throws JavaModelException {
		this.myParent = myParent;
		myInjector = new Injector();
		myInjector.set_unit(myParent);

	}

	public void inject_logger(ASTNode node) {
		if (Validator.validate(node)) {

			try {
				myInjector.inject_mutant_logger_to_ast_rewriter(node);
			} catch (JavaModelException | MalformedTreeException | BadLocationException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void inject_mutant(ASTNode node) {
		if (Validator.validate(node)) {

			try {
				myInjector.inject_mutant_to_ast_rewriter(node);
			} catch (JavaModelException | MalformedTreeException | BadLocationException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}


	class mySimpleName extends ASTVisitor {
		@Override
		public boolean visit(ForStatement node) {
			if (MutationSetting.logging) {
				inject_logger(node);
			} else if (MutationSetting.mutating){
				inject_mutant(node);
			}
			return super.visit(node);
		}
	}

	public ASTVisitor mySimpleName() {
		// TODO Auto-generated method stub
		return new mySimpleName();
	}


}
