import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Test {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		  // 1. 注册驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        
        // 声明变量，使用，而后关闭
        Connection conn = null;        //数据库连接
        Statement stmt = null;  
        Statement stmt1 = null;  
    
        conn = DriverManager.getConnection
        ("jdbc:mysql://localhost:3306/stock","root","12345");
     
       
        File f = new File("E:\\stockfive");    
        String[] name = f.list();
        String fileName = null;
        
        for(int i=0;i<name.length;i++){    
        	  
        	fileName = name[i].substring(0,name[i].length()-4);
        	System.out.println(fileName);
        	
        	stmt = conn.createStatement();
        	String sql = "load data local infile 'E:\\\\stockfive\\\\"+name[i]+"' into table stockdate FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' (date,time,start,end,min,max,volume,amount);";
        	stmt.execute(sql);
        	
        	sql = "update stock.stockdate set stockname='"+fileName+"' where stockname is null;";
        	stmt1 = conn.createStatement();
        	stmt1.execute(sql);
        	System.out.println(i);

}}}
