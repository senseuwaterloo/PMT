package pmt.project.visitors.template;

import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.text.edits.TextEdit;

import pmt.project.setting.ProjectSetting;

public class Handler extends ASTVisitor {


	public ASTRewrite rewriter;
	public Document doc;
	public String unit_name = "";
	public String file_path="";
	public pmt.project.visitors.template.PMTVisitor visitor;

	public Handler(PMTVisitor my_visitor) throws JavaModelException {
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

		Visitor myEFL_visitor = new Visitor();
		myEFL_visitor.set_unit(this);


		this.visitor.parsedCompilationUnit.accept(myEFL_visitor.mySimpleName());


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
