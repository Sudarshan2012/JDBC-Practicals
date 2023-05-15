package com.jdbcproject.Productmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ConClass               //connection mathod
{
	static Connection con;          //initializing connection
	public static Connection connect()   // creating connection class 
	{
	
		String url="jdbc:mysql://localhost:3306/product_management";   //database connection url
        String username="root";                                       //mysql username
        String password="2012";                                         //mysql password
		try                                                      //try block
		{
			 
			con=DriverManager.getConnection(url, username, password);     // attempting connection to mysql
			
			if(con.isClosed())                                             //checking connection established or not 
			{
				System.out.println("the connection is not established");      // if connection not established then this statement will print
			}
			else 
			{
				System.out.println("Connection is ok !");        // if connecton is istablished then this statement will print
			}
		}
		catch (SQLException e)     //catch block
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
    }   //connection method 

	
public void create()              // this is create method to create table in database
{
	    
		
		try    //try block
		{
			con=ConClass.connect();     // calling connection method 
			String cr="create table if not exists product (product_id int primary key,product_name varchar(50) not null,product_price int , product_description varchar(50) not null)";    // query statemet for creating table
			Statement crt=con.createStatement();
			crt.execute(cr);           //execute query
			System.out.println("Table created successfully");      //this line will print when table created successfully 
			
			
		} catch (SQLException e)     //catch block
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
}
public void insert()       // data insertion method
{
		try              //try block
		{
			//con=ConClass.connect();
			String q="insert into product value(?,?,?,?)";     //query for inserting data in table
			PreparedStatement pstm=con.prepareStatement(q);
			Scanner sc=new Scanner(System.in);
			
			System.out.println("Enter product_id");     //product id
			int product_id=sc.nextInt();
			pstm.setInt(1, product_id);
			
			System.out.println("Enter product_name: ");   //product name
			String product_name=sc.next();
			pstm.setString(2, product_name);
			
			System.out.println("Enter product_price: ");   //product price
			int product_price=sc.nextInt();
			pstm.setInt(3, product_price);
			
			System.out.println("Enter Product Description:");   //product description
			String product_description=sc.next();
			pstm.setString(4, product_description);
			
			pstm.executeUpdate();     //execute query
			System.out.println("Data Inserted successfully");  //this line will print when data inserted successfully 
			
		} 
		catch (SQLException e)      //catch block
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
}
public void delete()        //data deletion method
{
	try                        //try block
	{
		//con=ConClass.connect();
		String dl="delete from product where product_id=?;";   //query for delete data from table
		PreparedStatement dlt=con.prepareStatement(dl);
		Scanner dscan=new Scanner(System.in);
		System.out.println("Enter product ID NO. to delete :");
		int product_id=dscan.nextInt();
		dlt.setInt(1,product_id);
		dlt.executeUpdate();
		System.out.println("product_id No: "+product_id+" Deleted");   //this line will print when data deleted successfully 
	} 
	catch (SQLException e)    //catch block
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
}
public void update() throws SQLException    //data update method
{
	//con=ConClass.connect();
	System.out.println("Enter What you want to update : \n 1. product_name \n 2. product_price \n 3. product_description \n 4.None to update:");
	Scanner upd=new Scanner(System.in);
	int upc=upd.nextInt();
	int product_id;
    String product_name;
    int product_price;
    String product_description;
          
	switch(upc)     // giving options for different table column's data
        {
	                     
	          case 1: String upn="Update product set product_name=? where product_id=?;";    //case 1 query for update product name in table 
			          PreparedStatement up=con.prepareStatement(upn);
			          Scanner upsc=new Scanner(System.in);
			          
			          System.out.println("Enter product Name: ");
			          product_name=upsc.next();
			          up.setString(1, product_name);
			          
			          System.out.println("Enter Product ID: ");
			          product_id=upsc.nextInt();
			          up.setInt(2, product_id);
			          up.executeUpdate();
			          System.out.println("Product Name Updated");             //this line will print when product name successfully updated
		               break;
	          
	          case 2:  String upap="Update product set product_price=? where product_id=?;";   //case 2 query for update product price in table 
	                   PreparedStatement upap1=con.prepareStatement(upap);
	                   Scanner upsca=new Scanner(System.in);
	                   
	                   System.out.println("Enter Product Price: ");
	                   product_price=upsca.nextInt();
	                   upap1.setInt(1, product_price);
	                   
	                   System.out.println("Enter Product ID: ");
	                   product_id=upsca.nextInt();
	                   upap1.setInt(2, product_id);
	                   upap1.executeUpdate();
	                   System.out.println("Product price Updated");        //this line will print when product price  successfully updated
                       break;
	          case 3: String upcp="Update product set product_description=? where product_id=?;";   //case 3 query for update product description in table 
			         PreparedStatement upcp1=con.prepareStatement(upcp);
			         Scanner upcp2=new Scanner(System.in);
			         
			         System.out.println("Enter Product Description");
			         product_description=upcp2.next();
			         upcp1.setString(1, product_description);
			         
			         System.out.println("Enter product Id:");
			         product_id=upcp2.nextInt();
			         upcp1.setInt(2, product_id);
			         upcp1.executeUpdate();
			         System.out.println("Product Description updated");       //this line will print when product description successfully updated
			         break;
			         
			   default:                              //deafault case to wrong input
				   System.out.println("None to update:");			          
		}
}
public void view() throws SQLException            //data view method
{
	
	//con=ConClass.connect();
	String view="Select * from product;";      //query to view data from table
	PreparedStatement view1=con.prepareStatement(view);
	ResultSet rs=view1.executeQuery(view);
	while(rs.next())                      //while loop used to print whole data from table
	{
		int product_id=rs.getInt("product_id");
		String product_name=rs.getString("product_name");
		String product_price=rs.getString("product_price");
		String product_description=rs.getString("product_description");
		
		System.out.println(product_id+"  "+product_name+"  "+product_price+" "+product_description);
	}
}

} //class







	
  


