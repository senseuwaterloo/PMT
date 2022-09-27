package pmt.project.visitors.sts1;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

public class Utilities {

	static boolean is_in_variable_declaration_statement(ASTNode node) {
		if (node.getParent().equals(node.getRoot())) {
			return false;
		}
		if (node.getParent() instanceof VariableDeclarationStatement) {
			return true;
		} else {
			return is_in_variable_declaration_statement(node.getParent());
		}

	}

}
