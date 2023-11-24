

class Solution
{
	//helper method return the change direction of the robot
	public static char turnRight(char face)
	{
		if(face=='n') return 'e';
		if(face=='e') return 's';
		if(face=='w') return 'n'; 
		if(face=='s') return 'w'; 
		return face;		
	}
	
	//method returns the number of clean squares 
	public static int solution(char R[][])
	{
		//get the number of rows
		int N = R.length;
		//get the number of columns
		int M = R[0].length; 
		//check for base case	
		if(N==1 && M==1)
			return 1;
		
		int obstacle = 0, clean = 0, repeat = 0, i = 0, j = 0;
		char face = 'e';
		//processing
		while(true)
		{
			//check for empty squares
			if(R[i][j]=='.'){
				clean++;
				R[i][j] = 'C';
			}
			//check for cleaned squares
			else if(R[i][j]=='C'){
				if(repeat==obstacle+2)
					return clean;
				repeat++;
			}
			//store current value of i and j before modification
			int p = i, q = j;
			//modify the value of i and j based on facing
			if(face=='n') i--;
			else if(face=='e') j++;
			else if(face=='w') j--;
			else if(face=='s') i++;
			//check boundary conditions
			if(i==N){
				i = N-1; j--; face = 'w'; 
			}	
		    else if(j==M){
		    	j = M-1; i++; face = 's';
		    }
		    else if(i<0){
				i = 0; j++; face = 'e';
			}
			else if(j<0){
				j = 0; i--; face = 'n';
			}
			//check for obstacle 
			if(R[i][j]=='X'){
				obstacle++;
				//change the direction of the robot
				face = turnRight(face);
				i = p;
				j = q;
			}
		}
	}
	
	//main method
	public static void main (String[] args) 
	{
		//test #1
		char A[][] = {{'.', '.', '.', 'X', '.', '.'}, 
			          {'.', '.', '.', '.', 'X', 'X'},
			          {'.', '.', 'X', '.', '.', '.'}};
		System.out.println (solution(A)); 
		
		//test #2
		char B[][] = {{'.', '.', '.', '.', 'X', '.', '.'}, 
			          {'X', '.', '.', '.', '.', '.', '.'},
			          {'.', '.', '.', '.', '.', 'X', '.'},
			          {'.', '.', '.', '.', '.', '.', '.'}};
		System.out.println (solution(B)); 
			
		//test #3
		char C[][] = {{'.', '.', '.', 'X', '.'}, 
			          {'.', 'X', '.', '.', 'X'},
			          {'X', '.', '.', '.', 'X'},
			          {'.', '.', 'X', '.', '.'}};
		System.out.println (solution(C)); 
		
		//test #4
		char D[][] = {{'.'}};
		System.out.println (solution(D)); 
		
	}
}