package pmt.project.visitors.hwo;

import java.io.IOException;

import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.text.edits.MalformedTreeException;

public class Visitor {

	public Handler myParent;

	public Injector myInjector=null;

	public void set_unit(Handler myParent) throws JavaModelException {
		this.myParent = myParent;
		myInjector =new Injector();
		myInjector.set_unit(myParent);

	}

	class myMethodDeclaration extends ASTVisitor {
		@Override
		public boolean visit(MethodDeclaration node) {
			if (Validator.validate(node)) {

				try {
					myInjector.inject_mutant_to_ast_rewriter(node);
				} catch (JavaModelException | MalformedTreeException | BadLocationException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			return super.visit(node);
		}
	}

	public ASTVisitor myMethodDeclaration() {
		// TODO Auto-generated method stub
		return new myMethodDeclaration();
	}


}
