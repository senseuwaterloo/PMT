package pmt.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

public class SampleHandler extends AbstractHandler {
	private static final String CONSOLE_NAME = "Performance Mutation Testing framework";
	private static MessageConsole myConsole;
	private static MessageConsoleStream out;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		SampleHandler.myConsole = findConsole(CONSOLE_NAME);
		SampleHandler.out = myConsole.newMessageStream();
		WorkspaceAnalyzer detectException = new WorkspaceAnalyzer();
		detectException.execute(event);
		return null;
	}


	private MessageConsole findConsole(String name) {
		ConsolePlugin plugin = ConsolePlugin.getDefault();
		IConsoleManager conMan = plugin.getConsoleManager();
		IConsole[] existing = conMan.getConsoles();

		for (Integer i = 0; i < existing.length; i++)
			if (name.equals(existing[i].getName()))
				return (MessageConsole) existing[i];

		MessageConsole myConsole = new MessageConsole(name, null);
		conMan.addConsoles(new IConsole[] { myConsole });
		return myConsole;
	}

	static public void printMessage(String message) {
		if (message == null)
			return;
		out.println(message);
	}

}
