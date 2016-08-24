import java.util.Random;

public class MagicSquareBySA {

	 public static int Counter = 0, Replacement = 0;
     public static int[][] Board = {{6,4,1,2,1,2,4,6,3},
                        {1,5,6,2,6,3,9,7,4},
                        {4,5,9,1,5,6,8,5,9},
                        {4,8,4,6,9,3,9,5,7},
                        {8,3,7,2,9,8,7,6,5},
                        {9,8,3,5,5,5,3,8,7},
                        {7,6,3,9,3,4,4,6,3},
                        {2,8,2,2,7,8,8,1,2},
                        {1,1,1,7,9,7,3,1,2}};
     public static int Heuristic()
     {
         int Sum = 0, Temp = 0;
         for (int i = 0; i < 9; i++)
         {
             for (int j = 0; j < 9; j++)
             {
                 Temp += Board[i][j];
             }
             Sum += Math.abs(45 - Temp);
             Temp = 0;
         }
         for (int i = 0; i < 9; i++)
         {
             for (int j = 0; j < 9; j++)
             {
                 Temp += Board[i][j];
             }
             Sum += Math.abs(45 - Temp);
             Temp = 0;
         }
         for (int i = 0; i < 9; i += 3)
         {
             for (int j = 0; j < 9; j += 3)
             {
                 Temp += Board[i][j] + Board[i + 1][ j] + Board[i + 2][ j] +
                         Board[i][ j + 1] + Board[i + 1][ j + 1] + Board[i + 2][ j + 1] +
                         Board[i][ j + 2] + Board[i + 1][ j + 2] + Board[i + 2][ j + 2];
                 Sum += Math.abs(45 - Temp);
                 Temp = 0;
             }
         }
         return Sum;
     }
     public static int NextHeuristic()
     {
         int Sum = 0;
         for (int i = 0; i < 9; i++)
         {
             Sum += ColSimulator(i);
         }
         for (int i = 0; i < 9; i++)
         {
             Sum += RowSimulator(i);
         }
         for (int i = 0; i < 9; i += 3)
         {
             for (int j = 0; j < 9; j += 3)
             {
                 Sum += SquareSimulator(i, j);
             }
         }
         return Sum;
     }
     public static int ColSimulator(int Col)
     {
         int Num = 0, Sum = 0;
         for (int i = 1; i < 10; i++)
         {
             for (int j = 0; j < 9; j++)
                 if (Board[j][ Col] == i)
                     Num++;
             Sum += (Num == 0) ? Num : Num - 1;
             Num = 0;
         }
         return Sum;
     }
     public static int RowSimulator(int Row)
     {
         int Num = 0, Sum = 0;
         for (int i = 1; i < 10; i++)
         {
             for (int j = 0; j < 9; j++)
                 if (Board[Row][ j] == i)
                     Num++;
             Sum += (Num == 0) ? Num : Num - 1;
             Num = 0;
         }
         return Sum;
     }
     public static int SquareSimulator(int Row, int Col)
     {
         int Num = 0, Sum = 0;
         for (int k = 1; k < 10; k++)
         {
             for (int i = Row; i < Row + 3; i++)
             {
                 for (int j = Col; j < Col + 3; j++)
                 {
                     if (Board[i][ j] == k)
                         Num++;
                 }
             }
             Sum += (Num == 0) ? Num : Num - 1;
             Num = 0;
         }
         return Sum;
     }
     public static void Print()
     {
         for (int i = 0; i < 9; i++)
         {
             for (int j = 0; j < 9; j++)
                 System.out.print("  " + Board[i][ j]);
             System.out.println();
         }
        // System.out.println();
     }
     public static void Initialize()
     {
         for (int i = 0; i < 9; i++)
             for (int j = 0; j < 9; j++)
                 Board[i][ j] = 1;
     }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        
        int i, Num;
        int Heuristic_cur = Integer.MAX_VALUE;
        int Heuristic_next;
        double final_temp = 1;
        int initial_temp = 70000; //initial temp should be big enough
        long StartTime = System.nanoTime();
        Random rand = new Random();
        for (double t = initial_temp; t > final_temp; t--)
        {
            Counter++;
            /*Find a random Number To determine 
             * subtract or add 1 to Selected Cell*/       
            double r = rand.nextDouble();
            //Select Cell By Generating Random Number
            Num = rand.nextInt(80);
            //Control Domain
            if (Board[Num / 9][Num % 9] == 9)
                r = 0.7;
            else if (Board[Num / 9][Num % 9] == 1)
                r = 0.2;
            //Doing Operation
            if (r <= 0.5)
                    Board[Num / 9][Num % 9]++;
            else
                    Board[Num / 9][Num % 9]--;

            double T = 1 / t;
            //calculate Heuristic
            Heuristic_next = NextHeuristic();
            //if the next result is better, then move 
            if (Heuristic_next < Heuristic_cur)
            {
                Replacement++;
                Heuristic_cur = Heuristic_next;
            }
            // the next result is worse, accept move according to the exp
            else
            {
                double DE = Heuristic_next - Heuristic_cur;
                //the higher the T, the higher the exp 
                double exp = Math.exp(-DE / T);
              
                // if (exp > 1) {  System.out.println("greater"); }
                double y = rand.nextDouble();
                if (exp > y)
                {
                    Replacement++;
                    Heuristic_cur = Heuristic_next;
                }
                else
                	//back 
                    Board[Num / 9][Num % 9] += (r <= 0.5) ? -1 : 1;
            }
        }
        long EndTime = System.nanoTime();
        long difference = EndTime - StartTime;
        
      
        System.out.println("Simulated Annealing");
        System.out.println("Time of execution is " + (difference/100000000.000000) + "s");
        System.out.println("Loop Counter is:" + Counter);
        System.out.println("Replacements is:" + Replacement);
        System.out.println("Best Result Magic Square is:");
        Print();
        System.out.println("Best Heuristic is:"+Heuristic_cur);
	}
	
}
