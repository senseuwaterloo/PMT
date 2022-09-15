package pmt.project.visitors.hwo;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class Validator {

//	Validation

	static boolean validate(ASTNode node) {

		return is_personally_valid(node) && is_socially_valid(node);
	}

	static boolean is_personally_valid(ASTNode node) {
		boolean valid = false;
		if (node instanceof MethodDeclaration) {
			MethodDeclaration myMethod = (MethodDeclaration) node;
			if (!(myMethod.getBody() == null)) {
				if (!myMethod.isConstructor()) {
					valid = true;
				}
			}
		}
		return valid;

	}

	static boolean is_socially_valid(ASTNode node) {
		return true;

	}

}
