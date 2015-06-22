/*
 * Text Editor – Notepad style application that can open, edit, and save text documents. 
 * Add syntax highlighting and other features.
 * 
 * Source: http://www.dreamincode.net/forums/topic/78802-martyr2s-mega-project-ideas-list/
 * Tutorial Used: http://www.dreamincode.net/forums/topic/66176-creating-a-basic-notepad-application/
 */
import javax.swing.*;//main JFrame design

import java.awt.*;//GUI
import java.awt.event.*;//event handling
import java.util.Scanner;//reading from a file
import java.io.*;//writing to a file

public class TextEditor extends JFrame implements ActionListener {

	/*
	 * "" = initial text to show in the textarea
	 * 0,0 = height, width = size of the textarea. 0x0 will set it to fill the window
	 * SCROLLBARS_VERTICAL_ONLY = makes the textarea word wrap
	 */
	private TextArea textArea = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
	
	/*
	 * Create the menu bar. 
	 * file is the first option in the bar
	 * openFile, saveFile, and close are the options listed under file
	 */
	private MenuBar menuBar = new MenuBar();
	private Menu file = new Menu();
	private MenuItem openFile = new MenuItem();
	private MenuItem saveFile = new MenuItem();
	private MenuItem close = new MenuItem();
	
	public TextEditor() {
		this.setSize(500, 300);
		this.setTitle("Java Notepad");
		setDefaultCloseOperation(EXIT_ON_CLOSE);//exit program when it closes
		this.textArea.setFont(new Font("Century Gothic", Font.BOLD, 12));

		//make the textarea fill the window automatically with borderlayout
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(textArea);
		
		//add menu bar to the gui
		this.setMenuBar(menuBar);
		//add file to the menu bar
		this.menuBar.add(this.file);	
		//make a title for the file option in the menu bar
		this.file.setLabel("File");
		
		//add open option under file
		this.openFile.setLabel("Open");
		this.openFile.addActionListener(this);
		//add shortcut for the open file option
		//VK_0, false = shortcut is the 0 keystroke; false means you don't use shift
		this.openFile.setShortcut(new MenuShortcut(KeyEvent.VK_O, false));
		this.file.add(this.openFile);
		
		//add save option under file
		this.saveFile.setLabel("Save");
		this.saveFile.addActionListener(this);
		this.saveFile.setShortcut(new MenuShortcut(KeyEvent.VK_S, false));
		this.file.add(this.saveFile);
		
		//add close option under file
		this.close.setLabel("Close");
		this.close.addActionListener(this);
		this.close.setShortcut(new MenuShortcut(KeyEvent.VK_F4, false));
		this.file.add(this.close);
	}
	
	public static void main(String[] args) {
		TextEditor notepad = new TextEditor();
		notepad.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//check what e is using multiple if clauses
		if (e.getSource() == this.close) {
			this.dispose();
		} else if (e.getSource() == this.openFile) {
			//=======================================
			//	handle opening files
			//=======================================
			JFileChooser open = new JFileChooser();//open file dialog window							
			int option = open.showOpenDialog(this);//get user choice: OK or Cancel
			
			if (option == JFileChooser.APPROVE_OPTION) {//if user clicks on OK
				this.textArea.setText("");//clear textarea before filling it with contents
				
				try {
					Scanner scan = new Scanner(new FileReader(open.getSelectedFile().getPath()));
					
					while (scan.hasNext()) {
						this.textArea.append(scan.nextLine() + "\n");
					}
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}//end try
			}//end inner if
		} else if (e.getSource() == this.saveFile) {
			//=======================================
			//	handle saving files
			//=======================================
			JFileChooser save = new JFileChooser();//open save dialog window
			int option = save.showSaveDialog(this);//get user choice
			
			if (option == JFileChooser.APPROVE_OPTION) {
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter(save.getSelectedFile().getPath()));
					out.write(this.textArea.getText());
					out.close();
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}//end try
			}//end inner if
		}//end outer if
	}//end actionPerformed
}//end class
