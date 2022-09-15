package pmt.project.visitors.ptw;

import java.io.IOException;

import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.text.edits.MalformedTreeException;

public class Visitor {

	public Handler myParent;
	public static String str_wrapper;
	public static String str_valueOf = "valueOf";
	public static String str_primitiveValue;
	public Injector myInjector=null;

	public void set_unit(Handler myParent) throws JavaModelException {
		this.myParent = myParent;
		myInjector =new Injector();
		myInjector.set_unit(myParent);

	}

	class myBooleanLiteral extends ASTVisitor {
		@Override
		public boolean visit(BooleanLiteral node) {
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

	public ASTVisitor myBooleanLiteral() {
		// TODO Auto-generated method stub
		return new myBooleanLiteral();
	}

	class mySimpleName extends ASTVisitor {
		@Override
		public boolean visit(SimpleName node) {

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

	public ASTVisitor mySimpleName() {
		// TODO Auto-generated method stub
		return new mySimpleName();
	}

	class myNumberLiteral extends ASTVisitor {
		@Override
		public boolean visit(NumberLiteral node) {
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

	public ASTVisitor myNumberLiteral() {
		// TODO Auto-generated method stub
		return new myNumberLiteral();
	}

	class myPrefixExpression extends ASTVisitor {
		@Override
		public boolean visit(PrefixExpression node) {
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

	public ASTVisitor myPrefixExpression() {
		// TODO Auto-generated method stub
		return new myPrefixExpression();
	}

	class myPostfixExpression extends ASTVisitor {
	@Override
	public boolean visit(PostfixExpression node) {
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
	public ASTVisitor myPostfixExpression() {
		// TODO Auto-generated method stub
		return new myPostfixExpression();
	}

	class myMethodInvocation extends ASTVisitor {
	@Override
	public boolean visit(MethodInvocation node) {

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
	public ASTVisitor myMethodInvocation() {
		// TODO Auto-generated method stub
		return new myMethodInvocation();
	}
	class myQualifiedName extends ASTVisitor {
	@Override
	public boolean visit(QualifiedName node) {

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
	public ASTVisitor myQualifiedName() {
		// TODO Auto-generated method stub
		return new myQualifiedName();
	}


	class myInfixExpression extends ASTVisitor {
	@Override
	public boolean visit(InfixExpression node) {

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
	public ASTVisitor myInfixExpression() {
		// TODO Auto-generated method stub
		return new myInfixExpression();
	}

}
