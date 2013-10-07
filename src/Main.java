import java.util.Random;


public class Main {

	public static int[][] ResultMatrix1;
	public static int[][] ResultMatrix2;
	public static int[][] ResultMatrix3;
	public static int[][] M1;
	public static int[][] M2;
	public static int size = 100;
	
	public static void main(String[] args) {
		int Rows = size;
		int Columns = size;
		long start,elapsedTime1,elapsedTime2,elapsedTime3;
	
		ResultMatrix1 = new int[Rows][Columns];
		ResultMatrix2 = new int[Rows][Columns];
		ResultMatrix3 = new int[Rows][Columns];
		M1 = createMatrix(Rows, Columns);
		M2 = createMatrix(Rows, Columns);
		
		System.out.println("Normally:");	
		start = System.nanoTime();    
		multiplyMatrices(M1, M2);
		elapsedTime1 = System.nanoTime() - start;
		System.out.println(elapsedTime1);	
		
		System.out.println("Concurrent (new thread for each element):");
		start = System.nanoTime();    
		multiplyMatricesConcurrent(M1, M2);
		elapsedTime2 = System.nanoTime() - start;
		System.out.println(elapsedTime2);
		
		System.out.println("Concurrent (4 threads):");
		start = System.nanoTime();    
		multiplyMatricesConcurrent2();
		elapsedTime3 = System.nanoTime() - start;
		System.out.println(elapsedTime3);
		
		check(ResultMatrix1, ResultMatrix2);
		check(ResultMatrix1, ResultMatrix3);

		System.out.println(((double)elapsedTime1/(double)elapsedTime1) + " / " + ((double)elapsedTime2/(double)elapsedTime1) + " / " + ((double)elapsedTime3/(double)elapsedTime1));
		/*
		print(M1);
		System.out.println("");		
		print(M2);
		System.out.println("");		
		print(ResultMatrix);
*/		
	}
	
	public static int[][] createMatrix(int Rows, int Columns) {
		int Max = 100;
		int Min = 1;
		Random r = new Random();
		
        int Matrix[][] = new int[Rows][Columns];
        
        for(int i = 0; i < Rows; ++i){
            for(int j = 0; j < Columns; ++j){
            	Matrix[i][j] = r.nextInt(Max-Min) + Min;
            }
        }        
        return Matrix;
	}
	
	public static void multiplyMatricesConcurrent(int[][] M1, int[][] M2){
        for(int i = 0; i < M1.length; ++i){
            for(int j = 0; j < M2[0].length; ++j){
        		new MultiplicationThread(i, j, M1, M2);
            }
        }
	}
	
	public static void multiplyMatricesConcurrent2(){
		Thread t1 = new MultiplicationThread2 (0, 0);
		Thread t2 = new MultiplicationThread2 (size / 2, 0);
		Thread t3 = new MultiplicationThread2 (0, size / 2);
		Thread t4 = new MultiplicationThread2 (size / 2, size /2);

		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void multiplyMatrices(int[][] M1, int[][] M2){
        for(int i = 0; i < M1.length; ++i){
            for(int j = 0; j < M2[0].length; ++j){
            	Main.ResultMatrix1[i][j] = 0;
                for(int k = 0; k <M2.length; ++k){
                	Main.ResultMatrix1[i][j] += M1[i][k] * M2[k][j];
                }
            }
        }
	}
	
	public static void check(int[][] M1, int[][] M2){
        for(int i = 0; i < M1.length; ++i){
            for(int j = 0; j < M1[0].length; ++j){
            	if(M1[i][j] != M2[i][j]) System.out.println("fail");
            }
        }
        System.out.println("Check done.");
	}
	
    public static void print(int[][] M){
    	 
        for(int i = 0; i < M.length; ++i){
            for(int j = 0; j < M[i].length; ++j){
                System.out.printf("["+M[i][j]+"]");
            }
            System.out.println();
        }
    }

}
