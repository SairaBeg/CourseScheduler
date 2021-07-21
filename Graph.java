package Assignment5;

//Directed boolean matrix
class Graph<Type> {
    // the matrix stores the edge information
    private boolean[][] matrix;

    // this stores the values being stored by this graph
    private Type[] values;

    // the size of the graph
    private int size;

    // set the graph as empty
    public Graph(int size) {
        matrix = new boolean[size][size];
        this.size = size;

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                matrix[i][j] = false;
            }
        }

        // make space for the values (and ignore the cast warning)
        @SuppressWarnings("unchecked")
        Type[] values = (Type[]) new Object[size];
        this.values = values;
    }
    
    //print the contents of the Values array
    public void print() {
    	for(int i=0; i < values.length; i++) {
    		System.out.println(values[i]);
    	}
    }
    //prints the graph
    public void printGraph() {
    	for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) { 
            	System.out.println("index: "+i+" "+j+" is "+matrix[i][j]);
            }
            }
    }
    
    //Returns TRUE if there are no edges coming into a given index
    //(all false in a given column index)
    public boolean noEdge(int index) {
            int i = 0;
            while(i < size && !matrix[i][index]){
                i++;
            }
            if(i == size){
                return true;
            }
//return false if pre reqs still exist
         return false;
    }
    
    //find the top of the DAG (activeSet)
    public int[] top() {
    	int[] tops = new int[size];
    	int index = 0;
        for (int j = 0; j < size; j++) { 
            int i = 0;
            while(i < size && !matrix[i][j]){
                i++;
            }
            if(i == size){
            	tops[index]=j;
            	index++;
                
            }
         }
    	//DEBUG prints the active set
/*      
         System.out.println("Returning active set:");
         for(int i = 0; i < tops.length; i++) {
        	 System.out.println(tops[i]);;
        	 }
*/
         return tops;
    }
    
   // returns the value of a given index 	
    public Type getValue(int index) {
        return values[index];
    }
    
    // returns the value of a given index 	
    public int getIndex(String value) {
    	for (int i = 0; i < values.length; i++) {
    		if(values[i] ==value) {
    			return i;
    		}
    		
    	}
		return -1;
       
    }
    
    // insert an edge, in both directions
    public void insertEdge(int from, int to) {
    	//System.out.println("in inserting at "+ from+" "+ to);
        matrix[from][to] = true;
    }

    // remove an edge, in both directions
    public Integer removeEdge(int from, int to) {
    	int removed = 0;
    	if( matrix[from][to] == true) {
    	
    		 matrix[from][to] = false;  
    		 removed = to;
    		 return removed;
    //returns the index of the removed edge
    	}
		return null;   
    }

    // return the cost of an edge or 0 for none
    public boolean getCost(int from, int to) {
        return matrix[from][to];
    }
    // add a node's data to the graph
    public void setValue(int node, Type value) {
        values[node] = value;
    }

    // return whether the graph is complete
    boolean complete() {
 
 //iterate through the matrix
    	for (int i = 0; i < size; i++) {
         for (int j = 0; j < size; j++) { 

 //If there are 0s outside of the Diagonal, a connection is missing
      if(matrix[i][j] == false && i != j && matrix[j][i] == false) {
        return false;
           }
    }
}
//No 0s were found outside of the Diagonal    
        return true;
    }
}