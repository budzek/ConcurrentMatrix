
public class MultiplicationThread extends Thread {
	int i,j;
	int[][] M1, M2;
	MultiplicationThread(int i, int j, int[][] M1, int[][] M2){
		this.i = i;
		this.j = j;
		this.M1 = M1;
		this.M2 = M2;
		this.start();
	}
	public void run(){
		Main.ResultMatrix2[i][j] = 0;
        for(int k = 0; k <M2.length; k++){
        	Main.ResultMatrix2[i][j] += M1[i][k] * M2[k][j];
        }
	}
}
