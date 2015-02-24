package Serwer;

import Serwer.iterator.Container;
import Serwer.iterator.MIterator;

public class Gracze implements Container{
	int last = 0;
	GraczKomunikacja komunikacjaGraczy[] = new GraczKomunikacja[10];
	
	public void add(GraczKomunikacja nowy){
		komunikacjaGraczy[last] = nowy;
		last++;
	}
	
	public void set(int index, GraczKomunikacja nowy){
		komunikacjaGraczy[index] = nowy;
	}
	
	public GraczKomunikacja get(int index){
		return komunikacjaGraczy[index];
	}
	
	public MIterator getIterator(){
		GraczeIterator result = new GraczeIterator();
        return result;
    }
 
    private class GraczeIterator implements MIterator{
        private int position = 0;
 
        public boolean hasNext(){
            if(position < komunikacjaGraczy.length)
                return true;
            else
                return false;
        }
        
        public GraczKomunikacja next(){
            if (this.hasNext())
                return komunikacjaGraczy[position++];
            else
                return null;
        }
        
        public int getIndex(){
        	return position;
        }
    }
}