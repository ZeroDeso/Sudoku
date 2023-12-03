import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import javax.swing.JButton;
import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class FrameMessage extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JDesktopPane jDesktopPane = null;

	private JButton jButton = null;

	private JLabel jLabel = null;

	/**
	 * This is the default constructor
	 */
	public FrameMessage(boolean b) {
		super();
		initialize();
		if(b == false)
			jLabel.setText("Решение не найдено!");
		//this.setLocationRelativeTo(null);
		//this.setLocation(200, 200);
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(232, 175);
		Font font = new Font("Verdana", Font.PLAIN, 11);
		this.setMaximumSize(new Dimension(232, 175));
		this.setMinimumSize(new Dimension(232, 175));
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
		this.setFont(font);
		jLabel.setFont(font);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setResizable(false);//JFrame.setDefaultLookAndFeelDecorated(true);
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJDesktopPane(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jDesktopPane
	 *
	 * @return javax.swing.JDesktopPane
	 */
	private JDesktopPane getJDesktopPane() {
		if (jDesktopPane == null) {
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(36, 36, 158, 16));
			jLabel.setText("Судоку решено!");
			jDesktopPane = new JDesktopPane();
			jDesktopPane.add(getJButton(), null);
			jDesktopPane.add(jLabel, null);
		}
		return jDesktopPane;
	}

	/**
	 * This method initializes jButton
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(35, 66, 144, 45));
			jButton.setText("ОК");

			jButton.addMouseListener(new MouseAdapter() {
	        	   @Override
	        	   public void mouseClicked(MouseEvent arg0) {
	        		   setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        		   dispose();
	        		   }
	        	  });
		}
		return jButton;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
