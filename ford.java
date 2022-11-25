import java.util.Scanner;


public class ford {
	int d[], size;
	public ford(int size){
		this.size = size;
		d = new int[size + 1];
	}

	public static void main(String[] args) {
		int size, src;
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the no of vertices:");
		size = s.nextInt();
		
		int a[][] = new int[size+1][size+1];
		System.out.println("Enter the adjacency matrix:");
		for(int i=1;i<=size;i++){
			for(int j=1;j<=size;j++){
				a[i][j] = s.nextInt();
			}
		}
		System.out.println("Enter src vertex:");
		src = s.nextInt();
		ford b = new ford(size);
		b.bellmanFordAlg(src, a);
		s.close();
	}
	private void bellmanFordAlg(int src, int[][]a){
		for(int node = 1;node <= size; node++)
			d[node] = 999;
		d[src] = 0;
		for(int node=1;node<=size;node++){
			for(int inter = 1 ; inter<=size ; inter++){
				for(int dn=1;dn<=size;dn++){
					if(a[inter][dn] != 999)
						if(d[dn]> d[inter]+a[inter][dn])
							d[dn] = d[inter] + a[inter][dn];
				}
			}
		}
		
		for(int i=1;i<=size;i++)
			System.out.println(src+" to "+i+" is "+d[i]);
	}

}
