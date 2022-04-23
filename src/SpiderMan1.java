import java.sql.*;
import java.util.*;
import java.util.logging.*; //Had to do some poking around to get this thing to import

public class SpiderMan1 {
    private static String username=""; //intending to use empty username as of now
    private static String password=""; //intending to use empty password as of now
    private static String myURL = "jdbc:derby://localhost:3000"; //This might be the issue
    private static Connection myConnection = null;
    private static Statement myStatement = null;
    public static final String INSERT_SPIDERMAN_VALUES = "INSERT SPIDERMAN VALUES";

    public static void main (String [] args){
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance(); //Looked This up
            myConnection = DriverManager.getConnection(myURL);
            myStatement = myConnection.createStatement();
            String myQuery1 = "CREATE TABLE SPIDERMAN"+"("+"ComicName varchar(25), IssueNumber integer, IssueDate varchar(25), IssueName integer, IssueValue integer, MintCondition varchar(5)"+")";
            System.out.println("The Table is Created!");
            String firstRow = INSERT_SPIDERMAN_VALUES+"('Amazing SM', 89, '10/1/70','Doc Ock Lives','$6.50', 'NO')";
            myStatement.executeUpdate(firstRow);
            String secondRow = INSERT_SPIDERMAN_VALUES+"('Spectacular SM', 92, '7/1/84','What Is The Answer','$4.50', 'NO')";
            myStatement.executeUpdate(secondRow);
            String thirdRow = INSERT_SPIDERMAN_VALUES+"('Web Of SM', 35, '2/1/88','You Can Go Home Again','$4.00', 'NO')";
            myStatement.executeUpdate(thirdRow);
            String fourthRow = INSERT_SPIDERMAN_VALUES+"('Amazing SM', 382, '10/1/93','Emerald Rage','$4.00', 'YES')";
            myStatement.executeUpdate(fourthRow);
            //Question: Will having two starting Strings crash my program?
            //If so, how will I handle that?
            String query = "Select from the SpiderMan place";
            System.out.println("The Data is Inserted!");
            ResultSet myResultSet = myStatement.executeQuery(query);
            System.out.println("ComicName | IssueNumber | IssueDate | IssueValue | MintCondition");
            while (myResultSet.next()){//Should I do a has next?
                String theSTRINGtoPRINT = myResultSet.getString("ComicName")+" "+
                        myResultSet.getString("IssueNumber")+" "+
                        myResultSet.getString("IssueDate")+" "+
                        myResultSet.getString("IssueValue")+" "+
                        myResultSet.getString("MintCondition");
                System.out.println(theSTRINGtoPRINT);
            }
        }
        catch(Exception e){
            Logger.getLogger(SpiderMan1.class.getName()).log(Level.SEVERE, null, e);
        }
        finally{ //Might not need this
            if(myStatement!=null){
                try{
                    myStatement.close();
                }
                catch(SQLException e){
                    Logger.getLogger(SpiderMan1.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if(myConnection!=null){
                try{
                    myConnection.close();
                }
                catch(SQLException e){
                    Logger.getLogger(SpiderMan1.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
}
