import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Graphics;
import javax.swing.SwingConstants;
import java.awt.Graphics2D;
import javax.swing.JButton;
import java.awt.List;

public class RulesMessage extends JFrame {

	Image pic;

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JDesktopPane jDesktopPane = null;

	private JLabel jLabel = null;

	private JButton jButton = null;

	private JButton jButton1 = null;

	private ImageIcon[] mas2 = new ImageIcon[5];
	private int Index =0;
	/**
	 * This is the default constructor
	 */
	public RulesMessage() {
		super();
		initialize();
		pack();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(500, 500);
		Font font = new Font("Verdana", Font.PLAIN, 10);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
		this.setFont(font);
		jLabel.setFont(font);
		mas2[0] = new ImageIcon("1.gif");
		mas2[1] = new ImageIcon("2.gif");
		mas2[2] = new ImageIcon("3.gif");
		mas2[3] = new ImageIcon("4.gif");
		mas2[4] = new ImageIcon("5.gif");
		this.addComponentListener(new java.awt.event.ComponentAdapter() {
        	public void componentResized(java.awt.event.ComponentEvent e) {
        		setSize(Math.max(520, 550), Math.max(520, 550));
        	}
        });
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
			jLabel = new JLabel(" ", new ImageIcon("1.gif"), SwingConstants.RIGHT);
			jLabel.setBounds(new Rectangle(0, 0, 500, 500));
			jLabel.setVerticalTextPosition(JLabel.TOP);
			jLabel.setHorizontalTextPosition(JLabel.CENTER);
			jDesktopPane = new JDesktopPane();
			jDesktopPane.add(jLabel, null);
			jDesktopPane.add(getJButton(), null);
			jDesktopPane.add(getJButton1(), null);
		}
		return jDesktopPane;
	}


	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(248, 11, 72, 26));
			jButton.setText("Далее");
			jButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(Index < 4){
						Index++;
						jLabel.setIcon(mas2[Index]);
					}
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jButton1
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBounds(new Rectangle(166, 11, 72, 26));
			jButton1.setText("Назад");
			jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(Index > 0){
						Index--;
						jLabel.setIcon(mas2[Index]);
					}
				}
			});
		}
		return jButton1;
	}

}
