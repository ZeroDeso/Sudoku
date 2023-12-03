import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.*;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;

	private JMenuBar jJMenuBar = null;

	private JMenu jMenu = null;

	private JMenu jMenu1 = null;

	private JMenu jMenu2 = null;

	private MyPanel myPanel = null;

	private JMenu jMenu3 = null;

	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJMenu());
			jJMenuBar.add(getJMenu1());
			jJMenuBar.add(getJMenu2());
			jJMenuBar.add(getJMenu3());
		}
		return jJMenuBar;
	}

	private JMenu getJMenu() {
		if (jMenu == null) {
			jMenu = new JMenu();
			jMenu.setText("Новая");
			jMenu.addMouseListener(new MouseAdapter() {
	        	   @Override
	        	   public void mouseClicked(MouseEvent arg0) {
	        		   myPanel.NewSudoku();
	        		   repaint();
	        		   }
	        	  });
		}
		return jMenu;
	}
	private JMenu getJMenu1() {
		if (jMenu1 == null) {
			jMenu1 = new JMenu();
			jMenu1.setText("Решить");
			jMenu1.add(getJMenu3());
			jMenu1.addMouseListener(new MouseAdapter() {
	        	   @Override
	        	   public void mouseClicked(MouseEvent arg0) {
	        		   //myPanel.InSolved();

	        		   FrameMessage form2 = new FrameMessage(myPanel.InSolved());
	        		   repaint();
	        		   form2.setTitle("Сообщение!");
	        		   int x = (getBounds().x + getBounds().width/2) - form2.getBounds().width/2;
	        		   int y = (getBounds().y+ getBounds().height/2) - form2.getBounds().height/2;

	        		   form2.setLocation(x, y);
	                   form2.setVisible(true);
	        		   }
	        	  });
		}
		return jMenu1;
	}
	private JMenu getJMenu2() {
		if (jMenu2 == null) {
			jMenu2 = new JMenu();
			jMenu2.setText("Очистить");
			jMenu2.addMouseListener(new MouseAdapter() {
	        	   @Override
	        	   public void mouseClicked(MouseEvent arg0) {
	        		   myPanel.ClearField();
	        		   repaint();
	        		   }
	        	  });
		}
		return jMenu2;
	}

	private JMenu getJMenu3() {
		if (jMenu3 == null) {
			jMenu3 = new JMenu();
			jMenu3.setText("Правила");
			jMenu3.addMouseListener(new MouseAdapter() {
	        	   @Override
	        	   public void mouseClicked(MouseEvent arg0) {
	        		   RulesMessage form3 = new RulesMessage();
	        		   form3.setTitle("Правила игры");
	        		   int x = getBounds().x;
	        		   int y = getBounds().y;

	        		   form3.setLocation(x, y);
	                   form3.setVisible(true);
	        		   }
	        	  });
		}
		return jMenu3;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Window thisClass = new Window();

				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	public Window() {
		super();

		initialize();
	}

	private void initialize() {
		this.setSize(500, 500);


		this.setMaximumSize(new Dimension(500,500));
		this.setJMenuBar(getJJMenuBar());
		this.setTitle("Судоку");
		Font font = new Font("Verdana", Font.PLAIN, 11);
		jMenu.setFont(font);
		jMenu1.setFont(font);
		jMenu2.setFont(font);
		jMenu3.setFont(font);
		JPanel cp = new JPanel(new BorderLayout());
		cp.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createLineBorder(Color.blue)));
		myPanel = new MyPanel();//создание поля с размерами, и цветом клетки при навидении
        myPanel.setBackground(Color.white);//цвет фона
        cp.add(myPanel, BorderLayout.CENTER);//вывести на панель
        setContentPane(cp);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
        	public void componentResized(java.awt.event.ComponentEvent e) {
        		setSize(Math.max(300, getWidth()), Math.max(300, getHeight()));
        	}
        });
        setLocationRelativeTo(null);

	}


}
