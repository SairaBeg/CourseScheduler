package Assignment5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



public class CourseSched {

	public static void main(String[] args) {
		
				try {
						//debugging line
					//Scanner in = new Scanner(new File("/Users/Saira/Desktop/Eclipse Workspace 2021/340 Data Structures and Algorithms/src/Assignment5/cybr.txt"));
					//Scanner in = new Scanner(new File("/Users/Saira/Desktop/Eclipse Workspace 2021/340 Data Structures and Algorithms/src/Assignment5/impossible.txt"));
					//Scanner in = new Scanner(new File("/Users/Saira/Desktop/Eclipse Workspace 2021/340 Data Structures and Algorithms/src/Assignment5/cpsc.txt"));
					
					System.out.println("Enter the path to the course file");
					Scanner in1 = new Scanner(System.in);
					String dictionaryFile = in1.nextLine();
					Scanner in = new Scanner(new File(dictionaryFile));

//Set size of graph based on the number of courses
String Courses = in.nextLine();
int numCourses = Integer.parseInt(Courses);
Graph<String> graph = new Graph<String>(numCourses);

//Insert Courses into the graph (adding nodes)
int courseIndex = 0;
String[] CourseList = new String[numCourses];
while(in.hasNextLine()) {
	String line = in.nextLine();
	String[] parts = line.split(" ");
	String course = parts[0];

	CourseList[courseIndex] = course;
	graph.setValue(courseIndex, course);
	
	courseIndex++;
}

//Insert edges
//Scanner in2 = new Scanner(new FileInputStream(args[0]));


Scanner in2 = new Scanner(new File(dictionaryFile));


String Courses2 = in2.nextLine();


int courseIndex2 = 0;
while(in2.hasNextLine()) {
	String line2 = in2.nextLine();
	String[] parts2 = line2.split(" ");

//iterate through the prereqs of a line
	for(int i = 2; i < parts2.length; i++) {
		for (int j = 0; j <CourseList.length; j++) {
			if(parts2[i].contentEquals(graph.getValue(j))&& courseIndex2 != j) {
				
//adds an edge FROM the given prereq TO the course the prereqs satisfy
		graph.insertEdge(j, courseIndex2);
			}
		}
	}
	courseIndex2++;
}


//Topological Sorting
ArrayList<String> ordering = new ArrayList<String>();
ArrayList<String> activeSet = new ArrayList<String>();


//array of course indexes with no prereqs
int top[] = graph.top();

//add the first set of active classes to the activeSet
for(int i = 0; i < top.length; i++) {
	if(top[i]!= 0) {
activeSet.add(graph.getValue(top[i]));
	}
}

	//DEBUG print the initial active set
/*
	for (int i = 0; i < activeSet.size();i++) { 		      
   System.out.println("Current activeSet: "+activeSet.get(i)); 		
	}   
*/

while(!activeSet.isEmpty()) {
 
//add a class from the active set to the ordering	
String cur = activeSet.get(0);
	ordering.add(activeSet.get(0));
	activeSet.remove(activeSet.get(0));
	
//remove the edges from the taken class
	for(int i = 0; i<CourseList.length; i++) {
		Integer rm = graph.removeEdge(graph.getIndex(cur), i);

	
//if an edge was removed, check to see if that class has no more pre reqs
		if(rm != null && graph.noEdge(rm)) {

//add to active set if there are no pre reqs
	activeSet.add(graph.getValue(rm));
	}	
	}
}

if (graph.complete()==false) {
	//print ordering
	System.out.println("Ordering: ");
	for(int i = 0; i < ordering.size(); i++) {
		 System.out.println((i+1)+". "+ordering.get(i));
	}
	
	
}else {
System.out.println("No topological ordering is possible!");
	return;	
	
}

		//If file is not found, print an error
				}catch (FileNotFoundException e) {
					System.out.println("File not found.");
					e.printStackTrace();
				
				}

			}

		}
