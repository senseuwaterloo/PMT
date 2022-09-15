package pmt.project.visitors.ptw;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.text.edits.TextEdit;

import pmt.project.setting.ProjectSetting;
import pmt.project.visitors.PTWVisitor;

public class Handler extends ASTVisitor {

	public static MetaData primitive_wrapper_list = new MetaData(
			Arrays.asList("int", "boolean", "long", "short", "double", "float"),
			Arrays.asList("Integer", "Boolean", "Long", "Short", "Double", "Float"));
	public ASTRewrite rewriter;
	public Document doc;
	public String unit_name = "";
	public String file_path="";
	public pmt.project.visitors.PTWVisitor visitor;

	public Handler(PTWVisitor my_visitor) throws JavaModelException {
		this.visitor=my_visitor;
		this.file_path=this.visitor.unit.getPath().toOSString();
		this.rewriter = ASTRewrite.create(this.visitor.parsedCompilationUnit.getAST());
		this.doc = new Document();
		this.doc.set(this.visitor.unit.getSource().toString());
	}

	public void analyze() throws JavaModelException, BadLocationException, IOException {

		this.visit_nodes();
		this.apply_changes();
		write_to_file(this.doc);


	}

	private void visit_nodes() throws JavaModelException, BadLocationException, IOException {

		Visitor myPTW_visitor = new Visitor();
		myPTW_visitor.set_unit(this);


//		this.visitor.parsedCompilationUnit.accept(myPTW_visitor.myBooleanLiteral());

//		this.visitor.parsedCompilationUnit.accept(myPTW_visitor.myNumberLiteral());

//		this.visitor.parsedCompilationUnit.accept(myPTW_visitor.mySimpleName());

//		this.visitor.parsedCompilationUnit.accept(myPTW_visitor.myQualifiedName());

//		this.visitor.parsedCompilationUnit.accept(myPTW_visitor.myPrefixExpression());

//		this.visitor.parsedCompilationUnit.accept(myPTW_visitor.myPostfixExpression());

//		this.visitor.parsedCompilationUnit.accept(myPTW_visitor.myInfixExpression());

		this.visitor.parsedCompilationUnit.accept(myPTW_visitor.myMethodInvocation());





	}


	public void apply_changes() throws JavaModelException, BadLocationException, IOException {
		TextEdit edits = this.rewriter.rewriteAST(this.doc, null);
		edits.apply(this.doc);

	}

	private void write_to_file(Document doc) throws JavaModelException, BadLocationException, IOException {

		String full_path = ProjectSetting.path_to_project+this.file_path.substring(this.file_path.indexOf("src/main/java"));
		FileWriter file_writer = new FileWriter(full_path);
		file_writer.write(doc.get());
		file_writer.close();
	}

}
