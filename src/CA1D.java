

public class CA1D {
	public boolean [] organism;
	public boolean [][][] transitionMatrix;
	
	public CA1D(int organismLength)
	{
		organism = new boolean[organismLength];
		transitionMatrix = new boolean[2][2][2];
	}
	
	public void printOrganism()
	{
		for (int i = 0; i < organism.length; i++ )
		{
			if(organism[i]) System.out.print("●");
			else System.out.print(" ");
		}
		System.out.println("");
	}
	
	public void writeTransition (int left, int centre, int right, boolean value)
	{
		transitionMatrix[left][centre][right] = value;
	}
	
	public void step ()
	{
		boolean[] auxiliarOrganism =  new boolean[organism.length];
		for (int i=0; i< organism.length;i++)
		{
			int valueLeft,valueRight,valueCentre;
			int left = i-1;
			int right = i+1;
			if (i==0) left = organism.length-1;
			else if (i==organism.length-1) right = 0;
			if(organism[left]) valueLeft=1;
			else valueLeft=0;
			if(organism[right]) valueRight=1;
			else valueRight=0;
			if(organism[i]) valueCentre=1;
			else valueCentre = 0;
			auxiliarOrganism[i] = transitionMatrix[valueLeft][valueCentre][valueRight];
		}
		for (int i=0; i< organism.length;i++)
		{
			organism[i]=auxiliarOrganism[i];
		}
	}
	
	public void writeRule(int[]rule)
	{
		for(int i = 1; i>=0;i--)
		{
			for(int j = 1; j>=0;j--)
			{
				for(int k = 1; k>=0;k--)
				{
					int position = 7 - (k*1 + j*2+ i*4); 
					if(rule[position]==1) writeTransition(i,j,k,true);
					else writeTransition(i,j,k,false);
				}
			}
		}
	}
	
	public static void main(String[] args)
	{
		CA1D CA = new CA1D(60);
		int generations = 40;
		int [] rule30 = new int[] {0,0,0,1,1,1,1,0};
		int [] rule110 = new int[] {0,1,1,0,1,1,1,0};
		int [] rule90 = new int[] {0,1,0,1,1,0,1,0};
		CA.writeRule(rule110);
		CA.organism[CA.organism.length/2]=true;
		for(int i = 0; i< generations; i++)
		{
			CA.printOrganism();
			CA.step();
		}
	}
}