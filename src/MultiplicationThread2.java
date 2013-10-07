
public class MultiplicationThread2 extends Thread {
	int startRow,startColumn;
	
	MultiplicationThread2(int startRow, int startColumn){
		this.start();
		this.startRow = startRow;
		this.startColumn = startColumn;
	}
	
	public void run(){
	    for(int i = startRow; i < startRow + Main.size / 2; i++) {
	        for(int j = startColumn; j < startColumn + Main.size / 2; j++) {
	        	Main.ResultMatrix3[i][j] = 0;
	            for(int k =0 ; k < Main.size; k++) {
	            	Main.ResultMatrix3[i][j] += Main.M1[i][k] * Main.M2[k][j];
	            }
	        }
	    }
	}
}
