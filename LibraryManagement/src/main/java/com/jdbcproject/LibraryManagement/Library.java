/*
 Create a JDBC project for Library Management system(Bookid(PK), BookName(Not Null), AuthorName(Not Null)) . 
  The project should have following methods 

1. Create method to create new table in the database

2. Add method to add new data to the table

3. Delete method to delete the data from the table based on bookid

4. Update method to update data in the table based on bookid

5. select method to view all the data in the table.

Give choice to the user and excute as per his choice.
*/

package com.jdbcproject.LibraryManagement;

import java.sql.SQLException;
import java.util.Scanner;
public class Library 
{
    public static void main( String[] args ) throws SQLException
    {
    	ConClass con=new ConClass();           //creating object for calling methods
    	
    	Scanner ch=new Scanner(System.in);
    	System.out.println("Welcome Library Management System:");
    	while(true)                            // here we used while loop for do task in loop, we don't need to run main method again and again
    	{
    		System.out.println("\nEnter 1 to create table:");
    		System.out.println("Enter 2 for inserting:");
    		System.out.println("Enter 3 for deleting:");
    		System.out.println("Enter 4 for updating:");
    		System.out.println("Enter 5 for View:");
    		
    		int x=ch.nextInt();
    		
    		  switch(x)                               // here given task for different choice 
    	        {
    	        
    	        case 1:con.create();                    // calling table creation method
    	                break;
    	        case 2: con.insert();                   //calling insertion data method
    	                break;
    	        case 3: con.delete();                    // calling delete data method
    	                break;
    	        case 4: con.update();                   //calling update data method
    	                break;
    	        case 5: con.view();                       //calling view data method
    	                break;
    	        default : System.out.println("You have Entered Wrong input");   //default case
    		   		
    		} // switch case
    		
    	} //while loop
    	    	
    } //main method
}     //main class

