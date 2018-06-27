import java.util.Scanner;

public class Dynamic_Program {

    private static Scanner in;

	public static void main(String[] args) {

/*比对的两列字符串*/
    	System.out.println("please enter sequence X:");
        in = new Scanner(System.in);
        String t = in.nextLine();
        System.out.println("please enter sequence Y:");
        String p = in.nextLine();

       int tlen=t.length();
       int plen=p.length();
       int [][] cost=new int[tlen+1][plen+1];

/*初始化矩阵*/
       int s1=0;
       for(int i=tlen;i>=0;i--){

           for(int j=plen;j>=0;j--){
        	   if(i==tlen) {
        		   cost[i][j]=2*(plen-j);
        		   continue;
        	   }
        	   
        	   if(j==plen) {
        		   cost[i][j]=2*(tlen-i);
        		   continue;
        	   }
        	   
        	   if(t.charAt(i)==p.charAt(j)){
                   s1=0;
               }else{
                   s1=1;
               }
               cost[i][j]=minimum(cost[i+1][j]+2,cost[i+1][j+1]+s1,cost[i][j+1]+2);
           }

       }    
/*输出代价矩阵*/

       System.out.println("cost matrix:");

       for(int i=0;i<tlen+1;i++){
    	   
           for(int j=0;j<plen+1;j++){
               System.out.printf("%d ",cost[i][j]);
               if(j!=0&&j%plen==0){
                   System.out.println();
               }
           }

       }
/*输出结果*/
       System.out.println("the alignment is:");
       get_back(t,p,cost,0,0);

    }

/*求最小值*/

    public static int minimum(int a,int b,int c){

    	int min =a;
    	if(min>b){
    		min=b;
    	}
    	if(min>c){
    		min=c;
    	}
    	return min;

    } 

/*回溯方法*/

     public static  void get_back(String t,String p,int[][] cost,int i,int j){
    	 int gap=0;
         int tlen=t.length();
         int plen=p.length();
         if (i==tlen){
     		for(int m=0;m<plen-i-gap;m++){

     			System.out.println("_");
     			System.out.println(p.charAt(j));
     			j++;
     			System.out.println();

     		}
     	}
     	if(j==plen){
     		for(int m=0;m<tlen-j-gap;m++){
     			System.out.println(t.charAt(i));
     			System.out.println("_");
     			i++;
     			System.out.println();
     		}
     	}
     			if(cost[i+1][j]+2==cost[i][j]){ 
     				System.out.println(t.charAt(i));
         			System.out.println("_");
     				i++;
     				gap++;
     				System.out.println();
     			}
     			else if(cost[i][j+1]+2==cost[i][j]){
     				System.out.println("_");
         			System.out.println(p.charAt(j));
     				j++;
     				gap++;
     				System.out.println();
     			}
     			else if(cost[i+1][j+1]+1==cost[i][j]&&t.charAt(i)!=p.charAt(j)){
     				System.out.println(t.charAt(i));
         			System.out.println(p.charAt(j));
     				i++;
     				j++;
     				System.out.println();
     			}
     			else if(cost[i+1][j+1]==cost[i][j]&&t.charAt(i)==p.charAt(j)){
     				System.out.println(t.charAt(i));
         			System.out.println(p.charAt(j));
     				i++;
     				j++;
     				System.out.println();
     			}
     			get_back(t,p,cost,i,j); 
     }    

}


