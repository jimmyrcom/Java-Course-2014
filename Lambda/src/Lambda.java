// function foobar() { var x=1; myFunc=function(){ return x+1; }; return myFunc; }

public class Lambda {
	public int myNum=1;
	public Silly foobar(){
    	return new Silly(){
       	 public int myFunc(int num){ return Lambda.this.myNum + 1; }	
       	};
	}
}
interface Silly {
    int myFunc(int num);
}	





class Someclass implements Silly {
	public int myFunc(int num){ return 1; }
}