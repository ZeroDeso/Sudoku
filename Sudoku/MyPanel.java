import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JPanel;

import com.sun.corba.se.spi.orbutil.fsm.State;


public class MyPanel extends JPanel {//класс наше поле, таблица 9 х 9

	    private int hx = -1;
	    private int hy = -1;

	    static final int lw = 9;
	    static final int lh = 9;
	    boolean flag = false;
	    private Sudoku n = new Sudoku();

	    public MyPanel() {
	        addMouseListener(new MouseHandler());
	        addMouseMotionListener(new MouseMotionHandler());
	        n.generateSudoku();
	        n.goContainerValue();
	    }

	    public void ClearField()
	    {
	    	n.refreshField();
	    	n.pr  =0;
	    }

	    public boolean InSolved()
	    {
	    	do{
	    		n.refreshField();
	    		n.pr++;
	    		if(n.Solve() != null && n.CellsWell() == true)
	    			break;
	    		if(n.pr >= 200)
	    			break;
	    	}while(n.CellsWell() != true);
	    	if(n.pr >= 200){
	    		System.out.println("решени€ нет" + n.pr);
	    		return false;
	    	}
	    	else {
	    		System.out.println("кол. предположений " + n.pr);
	    		System.out.println("проверка на правильность значений " + n.CellsWell());
	    		//System.out.println("€чеек " + n.cells);
	    		n.goContainerValue();
	    		return true;
	    	}
	    }

	    public void NewSudoku()
	    {
	    	n = new Sudoku();
	    	n.generateSudoku();
	    	n.goContainerValue();
	    }

	    @Override
	    protected void paintComponent(Graphics g) {//прорисовка клеток
	        super.paintComponent(g);
	        Font font = new Font("Verdana", Font.PLAIN, 25);//задаем размеры цифры
	        if(getWidth() < 350 || getHeight() < 350)
	        	font = new Font("Verdana", Font.PLAIN, 15);
	        double dx = (double) getWidth() / lw; // ширина клетка
	        double dy = (double) getHeight() / lh;//высота клетки
	        double dx2 = (double) getWidth() / 3; // ширина блока
	        double dy2 = (double) getHeight() / 3;//высота блока

	        g.setColor(Color.darkGray);//цвет клетки
	        for (int i = 0; i <= lw; i++) {
	            g.drawLine((int) (dx * i), 0, (int) (dx * i), getHeight());//рисуем линию (х1, у1, х2, у2) по горизонтали

	        }
	        for (int i = 0; i <= lh; i++) {
	            g.drawLine(0, (int) (dy * i), getWidth(), (int) (dy * i));//рисуем линию (х1, у1, х2, у2) по вертикали
	        }
	        //прорисовка блоков

	        Graphics2D g2 = (Graphics2D)g;
	        g2.setColor(Color.blue);
	        //      создаем "кисть" дл€ рисовани€
	        BasicStroke pen1 = new BasicStroke(5); //толщина линии 20
	        g2.setStroke(pen1);

	        for (int i = 0; i <= 3; i++) {
	            g.drawLine((int) (dx2 * i), 0, (int) (dx2 * i), getHeight());//рисуем линию (х1, у1, х2, у2) по горизонтали

	        }
	        for (int i = 0; i <= 3; i++) {
	            g.drawLine(0, (int) (dy2 * i), getWidth(), (int) (dy2 * i));//рисуем линию (х1, у1, х2, у2) по вертикали
	        }
	        //выводим цифры
			 g2.setFont(font);
			 for(int i = 0; i < Sudoku.N; i++)
			 {
				 for(int j = 0; j < Sudoku.N; j++)
				 {
					 if(Sudoku.numbers[i].get(j).Value != 0)
					 {
						 if(Sudoku.numbers[i].get(j).state == Sudoku.State.In)
						 {
							 g2.setColor(Color.red);
							 g2.drawString(Integer.toString(Sudoku.numbers[i].get(j).Value), (int) (dx * (j)+(dx/3)), (int) (dy * (i+0.7)));//выводим цифру
						 }else
						 {
							 g.setColor(Color.darkGray);
							 g2.drawString(Integer.toString(Sudoku.numbers[i].get(j).Value), (int) (dx * (j)+(dx/3)), (int) (dy * (i+0.7)));//выводим цифру
						 }
					 }
				 }
			 }
	        if (hx >= 0 && hy >= 0) {
	            g.setColor(Color.yellow);
	            g.drawRect((int) (hx * dx) + 1, (int) (hy * dy) + 1, (int) dx - 1, (int) dy - 1);
	        }
	    }

	    private class MouseMotionHandler extends MouseMotionAdapter {
	        @Override
	        public void mouseMoved(MouseEvent e) {//функци€ вычислени€ с помощью координат - номер строки и номер столбца €чейки при наведении
	            double dx = (double) getWidth() / lw;
	            double dy = (double) getHeight() / lh;
	            int nx = (int)(e.getX() / dx);
	            int ny = (int)(e.getY() / dy);
	            if (nx != hx || ny != hy) {
	                hx = nx;
	                hy = ny;
	                repaint();
	            }
	        }
	    }

	    private class MouseHandler extends MouseAdapter {
	        @Override
	        public void mouseEntered(MouseEvent e) {
	            double dx = (double) getWidth() / lw;
	            double dy = (double) getHeight() / lh;
	            hx = (int)(e.getX() / dx);
	            hy = (int)(e.getY() / dy);
	            repaint();
	        }

	        @Override
	        public void mouseExited(MouseEvent e) {
	            hx = -1;
	            hy = -1;
	            repaint();
	        }

	        @Override
	        public void mouseClicked(MouseEvent event) {

	        	if(hx != -1 && hy != -1)
	        	{
	        		if(Sudoku.numbers[hy].get(hx).state != Sudoku.State.In)
	        		{
	        			if(Sudoku.numbers[hy].get(hx).ContainerValue.size() != 0)
	        			{
		        			int q = -1;
		        			for(int i=0; i<Sudoku.numbers[hy].get(hx).ContainerValue.size();i++)//ищем текущее значение €чейки, чтобы вывести следующее из контейнера допустимых значений
		        				if(Sudoku.numbers[hy].get(hx).Value < Sudoku.numbers[hy].get(hx).ContainerValue.get(i))
		    					{
		        					System.out.println(Sudoku.numbers[hy].get(hx).Value);
		        					System.out.println(Sudoku.numbers[hy].get(hx).ContainerValue.toString());
		        					q = i;
		        					break;
		    					}
		        			if(q == -1)
		        				Sudoku.numbers[hy].get(hx).Value = 0;
		        			else
		        				Sudoku.numbers[hy].get(hx).Value = Sudoku.numbers[hy].get(hx).ContainerValue.get(q);
	        			}
	        			else
	        				Sudoku.numbers[hy].get(hx).Value = 0;
	        			n.goContainerValue();
	        		}
	        	}repaint();
	        	flag = n.CellsWell();
	        	if(flag == true){
	        		FrameMessage form2 = new FrameMessage(true);
	      		   repaint();
	      		   form2.setTitle("—ообщение!");
	      		   int x = (getBounds().x + getBounds().width/2) - form2.getBounds().width/2;
	      		   int y = (getBounds().y+ getBounds().height/2) - form2.getBounds().height/2;

	      		   form2.setLocation(x, y);
	                 form2.setVisible(true);}
	        	flag = false;

	        }
	    }
	}
