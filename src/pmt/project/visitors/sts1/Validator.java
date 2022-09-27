package pmt.project.visitors.sts1;

import org.eclipse.jdt.core.dom.ASTNode;

public class Validator {

	public static String typeName = "";
//	Validation

	static boolean validate(ASTNode node) {

		return is_personally_valid(node) && is_socially_valid(node);
	}

	static boolean is_personally_valid(ASTNode node) {
		if (node.toString().equals("StringBuilder")) {
			return true;
		} else {
			return false;
		}
	}

	static boolean is_socially_valid(ASTNode node) {
		if (Utilities.is_in_variable_declaration_statement(node)) {
			return true;
		} else {
			return false;
		}
	}

}
