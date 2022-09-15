package pmt.project.visitors.ptw;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;


public class Utilities {

	// **********************************************************//
	// **********************************************************//
	// **********************************************************//
	// **********************************************************//
//			Utilities
	// **********************************************************//
	// **********************************************************//
	// **********************************************************//
	// **********************************************************//

	static boolean is_prefix(ASTNode node) {
		if (node.getParent() instanceof PrefixExpression) {
			return true;
		}
		return false;
	}

	static boolean is_postfix(ASTNode node) {
		if (node.getParent() instanceof PostfixExpression) {
			return true;
		}
		return false;
	}

	static boolean is_in_field_declaration(ASTNode node) {
		if (node.getParent().equals(node.getRoot())) {
			return false;
		}
		if (node.getParent() instanceof FieldDeclaration) {
			return true;
		} else {
			return is_in_field_declaration(node.getParent());
		}

	}



	static String extractWrapperType(String nodeType) {
		String wrapperType = "";
		switch (nodeType) {

		case "boolean":
			wrapperType = "Boolean";
			break;

		case "int":
			wrapperType = "Integer";
			break;

		case "long":
			wrapperType = "Long";
			break;
		case "short":
			wrapperType = "Short";
			break;
		case "double":
			wrapperType = "Double";
			break;
		case "float":
			wrapperType = "Float";
			break;

		}
		return wrapperType;
	}

}
