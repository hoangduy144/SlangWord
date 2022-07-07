import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class SlangApp implements ActionListener{
	JFrame frame;
	JTabbedPane tabbedPane;
	SlangForm slangPanel;
	JPanel findDefiPanel;
	JMenuItem reset;
	JMenuItem aboutMenu;
	Dict dict;
	public SlangApp() {
		dict = new Dict();
		frame = new JFrame("Slang app");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        dict.Save();
		    }
		});
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		aboutMenu = new JMenuItem("About");
		
		aboutMenu.addActionListener(this);
		reset = new JMenuItem("Reset Dictionary");
		reset.addActionListener(this);
		fileMenu.add(reset);
		fileMenu.add(aboutMenu);
		menuBar.add(fileMenu);
		
		slangPanel = new SlangForm(dict);
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Slang Form", slangPanel);
		tabbedPane.addTab("Search definition", new SearchForm(dict));
		tabbedPane.addTab("Game Form", new GameForm(dict));
		tabbedPane.addTab("On this day", new RandomForm(dict));
		
		frame.setJMenuBar(menuBar);
		frame.add(tabbedPane, BorderLayout.CENTER);
		frame.setVisible(true);
		frame.pack();
	}
	
}