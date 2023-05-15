package com.jdbcproject.LibraryManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ConClass                  // creating connection class 
{
    static Connection con;              //initializing connection
    public static Connection connect()   //connection mathod
    {
    	String url="jdbc:mysql://localhost:3306/library_management";   // database connection url
        String username="root";                                        //mysql user name
        String password="2012";                                         //mysql password
		try                                                           //try block
		{
			 
			con=DriverManager.getConnection(url, username, password);    // // attempting connection to mysql
			
			if(con.isClosed())                                             //checking connection established or not 
			{
				System.out.println("the connection is not established");   // if connecton is istablished then this statement will print
			}
			else 
			{
				System.out.println("Connection is ok !");                 // if connecton is istablished then this statement will print
			}
		}
		catch (SQLException e)                   //catch block
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
    }   //connection  method

public void create()     // this is create method to create table in database
{
	// TODO Auto-generated method stub
	try                   //try block
	{
	con= ConClass.connect();       // calling connection method 
	String c="create table if not exists library (Book_id int primary key, Book_name varchar(50) not null, Author_name varchar(50))";   // query statemet for creating table
	Statement stmt=con.createStatement();
	stmt.execute(c);
	System.out.println("Table created succesfully:");   // this satement will print after creating table successfully
	}
	catch(Exception e)           //catch block
	{
		e.printStackTrace();
	}
}

public void insert()       //Data insertion method
{
	try                    // try block
	{
		//con=ConClass.connect();
		String i="insert into library values(?,?,?)";     //query for inserting data in table
		PreparedStatement pstmt=con.prepareStatement(i);
		Scanner in=new Scanner(System.in);
		
		System.out.println("Enter Book ID:");      //Book ID
		int Book_id=in.nextInt();
		pstmt.setInt(1, Book_id);
		
		System.out.println("Enter Book Name:");    //Book Name
		String Book_name=in.next();
		pstmt.setNString(2, Book_name);
		
		System.out.println("Enter Author Name:");    //Author Name
		String Author_name=in.next();
		pstmt.setString(3, Author_name);
		
		pstmt.execute();             //execute  inserting query
		System.out.println("Data Inserted Successfully:");    // this satement will print after inserting data
				
	}
	catch(Exception e)      //catch block
	{
		e.printStackTrace();
	}
	
}

public void delete()        //data delete method
{
	try     //try block
	{
	//con=ConClass.connect();
	String d="delete from library where Book_id=?";    //query for delete data in table
	PreparedStatement pstm=con.prepareStatement(d);
	Scanner de=new Scanner(System.in);
	
	System.out.println("Enter the Book ID to delete:");
	int Book_id=de.nextInt();
	pstm.setInt(1, Book_id);
	pstm.executeUpdate();
	System.out.println("Book ID No: "+Book_id+" deleted");	
	}
	catch(Exception e)    //catch block
	{
		e.printStackTrace();
	}
	
}

public void update() throws SQLException     //data update method
{
	//con=ConClass.connect();
	System.out.println("Enter what do you want to update \n1.Book Name: \n2.Author Name:");
	Scanner up=new Scanner(System.in);
	int a=up.nextInt();
	int Book_id;
	String Book_name;
	String Author_name;
	// giving options for different table column's data
	switch(a)
	{
	case 1: String u="update library set Book_name=? where Book_id=?;";   //query for update book name in table
	        PreparedStatement pst=con.prepareStatement(u);
	        Scanner ui=new Scanner(System.in);
	        
	        System.out.println("Enter Book Name:");
	        Book_name=ui.next();
	        pst.setString(1, Book_name);
	        
	        System.out.println("Enter Book ID:");
	        Book_id=ui.nextInt();
	        pst.setInt(2, Book_id);
	        pst.execute();
	        System.out.println("Book Name Updated");     //this statement will print after book name successfully updated 
	        break;
	        
	case 2: String p="update library set Author_name=? where Book_id=?;";    //query for update Author name in table
	        PreparedStatement ps=con.prepareStatement(p);
	        Scanner ud=new Scanner(System.in);
	        
	        System.out.println("Enter Author Name");
	        Author_name=ud.next();
	        ps.setString(1, Author_name);
	        
	        System.out.println("Enter Book Id");
	        Book_id=ud.nextInt();
	        ps.setInt(2, Book_id);
	        ps.execute();
	        System.out.println("Author Name Updated");  //this statement will print after Author name successfully updated 
	        break;
	 default: System.out.println("Nothing to update:");    //default case if wrong input is given
	
	}
	
}

public void view() throws SQLException    //data view method
{
	String v="select * from library;";       //query for view data in the table
	PreparedStatement view=con.prepareStatement(v);
	ResultSet rs=view.executeQuery(v);
	while(rs.next())                             //while loop used to print whole data from table
	{
		int Book_id=rs.getInt("Book_id");
		String Book_name=rs.getString("Book_name");
		String Author_name=rs.getString("Author_name");		
		System.out.println(Book_id+"  "+Book_name+"  "+Author_name);
	}	
	
}
   
}//public class
