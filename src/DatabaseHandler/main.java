package DatabaseHandler;

public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        mySQLAccess sql = new mySQLAccess();
        try{
            // testing out to ensure we can establish a connection
            sql.connectToDatabase();
            sql.selectFromDatabase("select * from users");
        }catch(Exception e) {
            System.out.println(e);
        }finally {
            sql.close();
        }
    }
}
