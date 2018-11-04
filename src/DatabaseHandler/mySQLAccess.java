package DatabaseHandler;

import java.sql.*;
import java.util.Date;

public class mySQLAccess {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void connectToDatabase(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            String url       = "jdbc:mysql://localhost/canoe";
            String user      = "root";
            String password  = "";

            // create a connection to the database
            connect = DriverManager.getConnection(url, user, password);

        } catch(Exception e) {
            System.out.println("Unable to connect to database");
            System.out.println(e);
        }
    }

    public void selectFromDatabase(String query){
        try {
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            preparedStatement = connect
                    .prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            writeResultSet(resultSet);
        } catch(Exception e) {
            System.out.println("Unable to select from database");
            System.out.println(e);
        }
    }

    public void insertIntoDatabase(String query, Object [] args){
        try {
            preparedStatement = connect
                    .prepareStatement(query);
            setPreparedStatement(args);
            preparedStatement.executeUpdate();
        } catch(Exception e) {
            System.out.println("Unable to insert into database");
            System.out.println(e);
        }
    }

    public void updateDatabase(String query, Object [] args){
        try {
            preparedStatement = connect
                    .prepareStatement(query);
            setPreparedStatement(args);
            preparedStatement.executeUpdate();
        } catch(Exception e) {
            System.out.println("Unable to insert into database");
            System.out.println(e);
        }
    }

    public void setPreparedStatement(Object [] args){
        try{
            int i = 1;
            for (Object arg : args) {
                if (arg instanceof Date) {
                    preparedStatement.setDate(i++, (java.sql.Date) arg);
                } else if (arg instanceof Integer) {
                    preparedStatement.setInt(i++, (Integer) arg);
                } else if (arg instanceof Long) {
                    preparedStatement.setLong(i++, (Long) arg);
                } else if (arg instanceof Double) {
                    preparedStatement.setDouble(i++, (Double) arg);
                } else if (arg instanceof Float) {
                    preparedStatement.setFloat(i++, (Float) arg);
                } else {
                    preparedStatement.setString(i++, (String) arg);
                }
            }
        } catch(Exception e){
            System.out.println("Unable to set statement");
            System.out.println(e);
        }
    }
    public void deleteFromDatabase(String query, Object [] args){
        try {
            preparedStatement = connect
                    .prepareStatement(query);
            setPreparedStatement(args);
            preparedStatement.executeUpdate();

        } catch(Exception e) {
            System.out.println("Unable to delete from database");
            System.out.println(e);
        }
    }

    private void writeMetaData(ResultSet resultSet) throws SQLException {
        //   Now get some metadata from the database
        // Result set get the result of the SQL query

        System.out.println("The columns in the table are: ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
            System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
        }
    }

    private void writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnCount = rsmd.getColumnCount();
        String [] columns = new String[columnCount];
        // The column count starts from 1
        for (int i = 1; i <= columnCount; i++ ) {
            columns[i-1] = rsmd.getColumnName(i);
        }
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            for (int i = 0; i < columns.length; i++ ) {
                String value = resultSet.getString(columns[i]);
                System.out.println(value);
            }
        }
    }

    // You need to close the resultSet
    public void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

}
