/*
 Write down a program where we can create, insert, update, delete and select

the data from the table in the database(Using JDBC)

Table Product - Product_id, Product_name, Product_Price, Product_description

(add necessary constraints to the table)

*/

package com.jdbcproject.Productmanagement;

import java.util.Scanner;       //importing scanner file
import java.sql.SQLException;   //import SQLException file
import java.util.*;             
public class Product
{
    public static void main( String[] args ) throws SQLException   //main method
    {
    	ConClass con=new ConClass();    //creating object for calling methods
    	
        Scanner ch=new Scanner(System.in);      //scanner for input
        System.out.println("Welcome Product Management System:");
        while(true)                             // here we used while loop for do task in loop, we don't need to run main method again and again
        { 
        
       	 System.out.println("Enter 1 for create table: ");
       	 System.out.println("Enter 2 for insert data: ");
       	 System.out.println("Enter 3 for delete data: ");
       	 System.out.println("Enter 4 for update data: ");
       	 System.out.println("Enter 5 for view data: ");
       	 
       	 int x=ch.nextInt();   
        
        switch(x)                               // here given task for different choice 
        {
        
        case 1:con.create();                //calling  create method
                break;
        case 2: con.insert();               //calling insert method
                break;
        case 3: con.delete();              //calling delete method
                break;
        case 4: con.update();                //calling update method
                break;
        case 5: con.view();                //calling view method
                break;
        default : System.out.println("You have Entered Wrong input");   //print for wrong output

        } //switch case
    } //while
  } // main method
    
}  // main class