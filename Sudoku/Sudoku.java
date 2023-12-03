	import java.util.ArrayList;
	import java.util.Collections;
	import java.util.List;
	import java.util.Random;
	import java.util.Arrays;
	import java.io.*;
import java.util.*;

import sun.io.Converters;
import sun.misc.Compare;

public class Sudoku {
		static final int N = 9;
		static final int CELLS = N*N;
		static Random rnd = new Random();
		static List<Cell>[]numbers = new ArrayList[N];

		private Boolean flag;
		private int cells;
		int pr = 0;
		public enum State { In, Unknown, Solved, Used, Moved };
		class Cell{
			public State state;
			int Value;
			List<Integer> ContainerValue;
			public Cell(){
				//flag = false;
				Value = 0;
				ContainerValue = new ArrayList();
				state = State.Unknown;
			}
		}

		public Sudoku()
		{
			 for(int i = 0; i < N; i++)
			 {
				 numbers[i] = new ArrayList<Cell>();
				 for(int j = 1; j <= N; j++)
				 {
					 Cell c = new Cell();
					 if(i == 0)
					 {
						 c.Value = j;
						 numbers[i].add(c);
					 }
					 else
					 {
						 c.Value = 0;
						 numbers[i].add(c);
					 }
				 }
			 }
			 cells = 0;
			 Collections.shuffle(numbers[0]);
		}

		Sudoku(Sudoku prototype) {
			pr = prototype.pr;
			flag = prototype.flag;
	        cells = prototype.cells;
			rnd = prototype.rnd;
			numbers = prototype.numbers.clone();
	    }

		private boolean checkLine(int line, int value)
		{
			 for(int i = 0; i < N; i++)
			 {
				 if(numbers[line].get(i).Value == value)
				 {
					 return false;
				 }
			 }
			 return true;
		}

		private boolean checkColumn(int column, int value)
		{
			 for(int i = 0; i < N; i++)
			 {
				 if(numbers[i].get(column).Value == value)
				 {
					 return false;
				 }
			 }
			 return true;
		}

		private boolean checkMove(int l, int c, int r)
		{
			 if(!checkLine(l, r))
			 {
				 return false;
			 }
			 if(!checkColumn(c, r))
			 {
				 return false;
			 }
			 if(!checkBlock(l, c, r))
			 {
				 return false;
			 }

			 return true;
		}

		private boolean checkBlock(int line, int column, int value)
		{
			 for(int i = 0; i < 3; i++)
			 {
				 for(int j = 0; j < 3; j++)
				 {
					 if(numbers[line - (line % 3) + i].get(column - (column % 3) + j).Value == value)
					 {
						 return false;
					 }
				 }
			 }
			 return true;
		}

		public void setNumbersZero(int line)
		{
			Cell c = new Cell();
			c.Value = 0;
			for(int i=0;i<N;i++)
			numbers[line].set(i, c);
		}

		public Sudoku refreshField()
		{
			for(int i=0;i<N;i++)
				for(int j=0;j<N;j++)
					if(numbers[i].get(j).state != State.In){
						numbers[i].get(j).state = State.Unknown;
						numbers[i].get(j).Value = 0;
					}
			goContainerValue();
			return this;
		}

		public void refreshCell()
		{
			for(int i=0;i<N;i++)
				for(int j=0;j<N;j++)
					if(numbers[i].get(j).state != State.In){
						numbers[i].get(j).state = State.Unknown;
					}
		}

		public void generateSudoku()
		{
			 boolean move = false;
			 int r = 0, count = 0, count2 = 0, c3 =0;

			 for(int line = 1; line < N; line++)
			 {
				 for(int column = 0; column < N; column++)
				 {
					 while(!move)
					 {
						 r = rnd.nextInt(9) + 1;//генерируем цифру от 1 до 9
						 move = checkMove(line, column, r);//провер€ем по строке, столбу и блоку
						 count++;
						 if(count>100)//если превысили, то текущую строку обнул€ем и генерируем снова
						 {
							 setNumbersZero(line);
							 column =0;
							 count2++;
							 if(count2 > 100)
							 {

								 if(line != 0)
								 {
									 line--;
									 column = 0;
									 setNumbersZero(line);
								 }
								 else{c3++;
									 Collections.shuffle(numbers[0]);
									 }
							 }
						 }
					 }
					 count=0;
					 if(move)
					 {
						 Cell c = new Cell();
						 c.Value = r;
						 numbers[line].set(column, c);
						 move = false;
					 }
				 }
			 }
			 int k = 30 + (int)(Math.random() * ((35 - 30) + 1));
				int r1 = 0, r2 =0;
				cells =0;
				while(cells != k)
				{
					r1 = rnd.nextInt(8) + 1;
					r2 = rnd.nextInt(8) + 1;
					if(numbers[r1].get(r2).state != State.In)
					{
						cells++;
						numbers[r1].get(r2).state = State.In;
					}
				}

				for(int line = 0; line < N; line++)
				{
					 for(int column = 0; column < N; column++)
					 {
						 if(numbers[line].get(column).state != State.In)
						 {
							 numbers[line].get(column).Value = 0;
						 }
					 }
				}
		}

		public void goContainerValue()
		{
			int val =0;
			cells = 0;
			for(int i = 0; i < N; i++)
			{
				List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
				 for(int j = 0; j < N; j++)
				 {
					 if(numbers[i].get(j).state == State.In || numbers[i].get(j).state == State.Solved)
						 cells++;
					 else{
						 numbers[i].get(j).ContainerValue.clear();
						 for(int q =0; q < list.size();q++)
						 {
							 if(checkMove(i,j, list.get(q))){
								 val = list.get(q);
								 numbers[i].get(j).ContainerValue.add(val);
							 }
						 }
						 if(numbers[i].get(j).state != State.Moved && numbers[i].get(j).state != State.Used)
							 numbers[i].get(j).state = State.Unknown;
					 }
				 }
			}
		}

		public int removeDuplicate(ArrayList<Integer> list)
		{try{
			Collections.sort(list);
			ArrayList<Integer> result = new ArrayList<Integer>();
			int count =0;
			for (int i = 0; i < list.size(); i++)
			{
				count = 0;
			    for (int j = 0; j < list.size(); j++)
			        if (list.get(i) == list.get(j))
			            count++;
			    if(count == 1)
			    	result.add(list.get(i));
			}

			if(result.size() == 1)
				return result.get(0);
			else return -1;
		}catch(Exception e){}
		return -1;
		}

		public Sudoku Solve()
		{
			CountCells();
			int oldCells;
			do{
				oldCells = cells;
	                    if (getSingleValue() > 0) {
	                        goContainerValue();
	                    }else if(getUniqueValue() > 0)
	                    {
	                    	goContainerValue();
	                    }
			}while(cells != oldCells);
			if (cells == CELLS)
				return this;

			int minContainer = 10, row=-1, col=-1;
				ArrayList<Integer> lis = new ArrayList<Integer>();
				//поиск минимального
				for(int i = 0; i< N; i++)
					for(int j = 0; j< N; j++)
						if(minContainer >= numbers[i].get(j).ContainerValue.size() &&  numbers[i].get(j).state != State.Used && numbers[i].get(j).state != State.In){
							minContainer = numbers[i].get(j).ContainerValue.size();}
				for(int i = 0; i< N; i++)
					for(int j = 0; j< N; j++)
						if(minContainer == numbers[i].get(j).ContainerValue.size() &&  numbers[i].get(j).state != State.Used && numbers[i].get(j).state != State.In){
							row = i;col=j;lis.add(i);lis.add(j);
						}
				if(row == -1)
					refreshCell();
				else{
					int k = rnd.nextInt(lis.size());
					if(k%2 == 0){row = lis.get(k);col=lis.get(k+1);}
					else {row = lis.get(k-1);col=lis.get(k);}lis.clear();
					//идем по контейнеру
					int q = -1;
					for(int i=0; i<numbers[row].get(col).ContainerValue.size();i++)//ищем текущее значение €чейки, чтобы вывести следующее из контейнера допустимых значений
						if(numbers[row].get(col).ContainerValue.size()>0 && numbers[row].get(col).Value < numbers[row].get(col).ContainerValue.get(i))
						{
							q = i;
							break;
						}
					if(q == -1){
						numbers[row].get(col).state = State.Used;
					}
					else{
						numbers[row].get(col).Value = numbers[row].get(col).ContainerValue.get(q);
						numbers[row].get(col).state = State.Moved;
					}
						Sudoku candidate = new Sudoku(this);
						if ((candidate = candidate.Solve()) != null) {
			                return candidate;
			            }else {refreshCell();goContainerValue();
			            }
		            }
			return null;
		}

		public int getSingleValue()
		{
			int val = -1;
			for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++)
                {
					if(numbers[i].get(j).Value == 0 && numbers[i].get(j).ContainerValue.size() == 1)//провер€ем на единственное значение
					{
						//if(checkMove(i, j, numbers[i].get(j).ContainerValue.get(0)))

						val = numbers[i].get(j).ContainerValue.get(0);
						numbers[i].get(j).Value = val;
						if(flag == false)
							numbers[i].get(j).state = State.Solved;
						else
							numbers[i].get(j).state = State.Used;
						cells++;
						return val;
					}
                }
			}
			return val;
		}

		public int getUniqueValue()
		{
			int remD = -1;
			try{
			ArrayList<Integer> list = new ArrayList<Integer>();
			//находим уникальное значение в контейнерах возможных значений, по строкe
			for (int i = 0; i < N; i++)
			{
				for(int j = 0; j < N; j++)
				{
					if(numbers[i].get(j).state == State.Unknown)
    				list.addAll(numbers[i].get(j).ContainerValue);
    			}
				//System.out.println("все эл.конт.в строке " + list.toString());

					remD = removeDuplicate(list);list.clear();
				if(remD != -1)
					for(int j = 0; j < N; j++)
					{
						for(int y = 0; y < N; y++)
						{
							if(numbers[i].get(j).ContainerValue.get(y) == remD){
								numbers[i].get(j).Value = remD;
									if(flag == false)
										numbers[i].get(j).state = State.Solved;
									else
										numbers[i].get(j).state = State.Used;
								cells++;
								return remD;
								}
						}
					}
			}
			//находим уникальное значение в контейнерах возможных значений, по столбцу
			for (int j = 0; j < N; j++)
			{
				for(int i = 0; i < N; i++)
				{
					if(numbers[i].get(j).state == State.Unknown)
					list.addAll(numbers[i].get(j).ContainerValue);
				}
				//System.out.println("все эл.конт.в строке " + list.toString());
				remD = removeDuplicate(list);list.clear();
				if(remD != -1)
						for(int i = 0; i < N; i++)
					{
						for(int y = 0; y < N; y++)
						{
							if(numbers[i].get(j).ContainerValue.get(y) == remD){
								numbers[i].get(j).Value = remD;
									if(flag == false)
										numbers[i].get(j).state = State.Solved;
									else
										numbers[i].get(j).state = State.Used;
								cells++;
								return remD;
							}
						}
					}
			}
			}catch(Exception e){}
			return remD;

		}

		public Boolean CellsWell(){
			cells = 0;
			int v ;
			for(int i = 0; i < N; i++){
				 for(int j = 0; j < N; j++){
					 v = numbers[i].get(j).Value;
					 numbers[i].get(j).Value = 0;
					 if(checkMove(i, j, v) == true){
						 cells++;numbers[i].get(j).Value = v;
					 }
				 }
			}
			if(cells == CELLS)
				return true;
			else return false;
		}

		public void CountCells(){
		cells = 0;
		for(int i = 0; i < N; i++)
			 for(int j = 0; j < N; j++)
				 if(numbers[i].get(j).Value != 0)
					 cells++;
		}


}
