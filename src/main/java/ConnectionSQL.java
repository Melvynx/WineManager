import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ConnectionSQL{
    //Variable of DB :
    String strongAlcoholColumnName = "name";
    String strongAlcoholColumnRegion = "region";
    String strongAlcoholColumnAge = "age";
    String strongAlcoholColumnDegreeOfAlcohol = "degree_of_alcohol";
    String strongAlcoholColumnCapacityML = "capacity_ml";
    
    String beerColumnName = "name";
    String beerColumnRegion = "region";
    String beerColumnAge = "age";
    String beerColumnDegreeOfAlcohol = "degree_of_alcohol";
    String beerColumnCapacityML = "capacity_ml";
    String beerColumnType = "type_beer";

    String wineColumnName = "name";
    String wineColumnRegion = "region";
    String wineColumnAge = "age";
    String wineColumnDegreeOfAlcohol = "degree_of_alcohol";
    String wineColumnCapacityML = "capacity_ml";
    String wineColumnType = "type_wine";
    String wineColumnStartMaturity = "start_maturity";
    String wineColumnEndMaturity = "end_maturity";


    private String DBPath;
    private Connection connection = null;
    private Statement statement = null;

    public ConnectionSQL(String dbPath) {
        DBPath = dbPath;
    }
    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DBPath);
            statement = connection.createStatement();
            System.out.println("Connexion à "+ DBPath +" réussi avec succès.");
        } catch (ClassNotFoundException classNoteFound) {
            classNoteFound.printStackTrace();
            System.out.println("Erreur de connexion CLASS.");
        } catch (SQLException sql) {
            sql.printStackTrace();
            System.out.println("Erreur de connexion SQL.");
        }
        this.createTable();
    }
    private void createTable() {
        String sqlAlcohol = "CREATE TABLE IF NOT EXISTS StrongAlcohol (" +
                "    id INTEGER PRIMARY KEY," +
                "    "+ strongAlcoholColumnName +" VARCHAR(40) NOT NULL," +
                "    "+ strongAlcoholColumnRegion +" VARCHAR(40)," +
                "    "+ strongAlcoholColumnAge +" INTEGER NOT NULL," +
                "    "+ strongAlcoholColumnDegreeOfAlcohol +" INTEGER NOT NULL," +
                "    "+ strongAlcoholColumnCapacityML +" INTEGER NOT NULL" +
                ");";
        String sqlBeer = "CREATE TABLE IF NOT EXISTS Beer (" +
                "    id INTEGER PRIMARY KEY," +
                "    "+ beerColumnName +" VARCHAR(40) NOT NULL," +
                "    "+ beerColumnRegion +" VARCHAR(40)," +
                "    "+ beerColumnAge +" INTEGER NOT NULL," +
                "    "+ beerColumnDegreeOfAlcohol +" INTEGER NOT NULL," +
                "    "+ beerColumnCapacityML +" INTEGER NOT NULL," +
                "    "+ beerColumnType +" VARCHAR(10)" +
                ");";
        String sqlWine = "CREATE TABLE IF NOT EXISTS Wine (" +
                "    id INTEGER PRIMARY KEY," +
                "    "+ wineColumnName +" VARCHAR(40) NOT NULL," +
                "    "+ wineColumnRegion +" VARCHAR(40)," +
                "    "+ wineColumnAge +" INTEGER NOT NULL," +
                "    "+ wineColumnDegreeOfAlcohol +" INTEGER NOT NULL," +
                "    "+ wineColumnCapacityML +" INTEGER NOT NULL," +
                "    "+ wineColumnType +" VARCHAR(10), " +
                "    "+ wineColumnStartMaturity +" INTEGER NOT NULL," +
                "    "+ wineColumnEndMaturity +" INTEGER NOT NULL" +
                ");";
        if (connection == null) {
            return;
        }
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sqlAlcohol);
            stmt.execute(sqlBeer);
            stmt.execute(sqlWine);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void close() {
        if (connection != null) {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Erreur de déconnexion.");
            }
        } else {
            System.out.println("Erreur de déconnexion.");
        }
    }
    public ResultSet query(String request) {
        ResultSet result = null;
        try {
            result = statement.executeQuery(request);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur dans la requet : "+request+".");
        }
        return result;
    }
    public void addWine(Wine wine) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO Wine ("+ wineColumnName+ ", "+wineColumnRegion+", "+wineColumnAge+", "+wineColumnDegreeOfAlcohol+", "+wineColumnCapacityML+", "+wineColumnType+", "+wineColumnStartMaturity+", "+wineColumnEndMaturity+") VALUES(?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, wine.getName());
            preparedStatement.setString(2, wine.getRegion());
            preparedStatement.setInt(3, wine.getAge());
            preparedStatement.setInt(4, wine.getDegreeOfAlcohol());
            preparedStatement.setInt(5, wine.getCapacityML());
            preparedStatement.setString(6, wine.getType().getName());
            preparedStatement.setInt(7, wine.getStartMaturity());
            preparedStatement.setInt(8, wine.getEndMaturity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addBeer(Beer beer) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO Beer ("+beerColumnName+", "+beerColumnRegion+","+beerColumnAge+", "+beerColumnDegreeOfAlcohol+", "+beerColumnCapacityML+", "+beerColumnType+") VALUES(?,?,?,?,?,?)");
            preparedStatement.setString(1, beer.getName());
            preparedStatement.setString(2, beer.getRegion());
            preparedStatement.setInt(3, beer.getAge());
            preparedStatement.setInt(4, beer.getDegreeOfAlcohol());
            preparedStatement.setInt(5, beer.getCapacityML());
            preparedStatement.setString(6, beer.getType().getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addStrongAlcohol(StrongAlcohol alcohol) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO StrongAlcohol ("+strongAlcoholColumnName+", "+strongAlcoholColumnRegion+","+strongAlcoholColumnAge+", "+strongAlcoholColumnDegreeOfAlcohol+", "+strongAlcoholColumnCapacityML+") VALUES(?,?,?,?,?)");
            preparedStatement.setString(1, alcohol.getName());
            preparedStatement.setString(2, alcohol.getRegion());
            preparedStatement.setInt(3, alcohol.getAge());
            preparedStatement.setInt(4, alcohol.getDegreeOfAlcohol());
            preparedStatement.setInt(5, alcohol.getCapacityML());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Wine> getWines() throws SQLException {
        List<Wine> wines = new LinkedList<>();
        TypeWine typeNewWine;
        ResultSet resultSet = this.query("SELECT * FROM Wine");
        while (resultSet.next()) {
            typeNewWine = TypeWine.getFromName(resultSet.getString("type_wine"));
            Wine wine = new Wine(resultSet.getString("name"), resultSet.getString("region"), resultSet.getInt("age"), resultSet.getInt("degree_of_alcohol"), resultSet.getInt("capacity_ml"), typeNewWine, resultSet.getInt("start_maturity"), resultSet.getInt("end_maturity"));
            wines.add(wine);
        }
        return wines;
    }
    public List<Beer> getBeer() throws SQLException {
        List<Beer> beers = new LinkedList<>();
        TypeBeer typeNewBeer;
        ResultSet resultSet = this.query("SELECT * FROM Beer");
        while (resultSet.next()) {
            typeNewBeer = TypeBeer.getFromName(resultSet.getString("type_beer"));

            Beer beer = new Beer(resultSet.getString("name"), resultSet.getString("region"), resultSet.getInt("age"), resultSet.getInt("degree_of_alcohol"), resultSet.getInt("capacity_ml"), typeNewBeer);
            beers.add(beer);
        }
        return beers;
    }
    public List<StrongAlcohol> getStrongAlcohol() throws SQLException {
        List<StrongAlcohol> strongAlcohol = new LinkedList<>();
        ResultSet resultSet = this.query("SELECT * FROM StrongAlcohol");
        while (resultSet.next()) {
            StrongAlcohol alcohol = new StrongAlcohol(resultSet.getString("name"), resultSet.getString("region"), resultSet.getInt("age"), resultSet.getInt("degree_of_alcohol"), resultSet.getInt("capacity_ml"));
            strongAlcohol.add(alcohol);
        }
        return strongAlcohol;
    }
    public List<Alcohol> getAllAlcohol() throws  SQLException {
        List<Alcohol> allAlcohol = new LinkedList();
        allAlcohol.addAll(this.getWines());
        allAlcohol.addAll(this.getBeer());
        allAlcohol.addAll(this.getStrongAlcohol());

        return allAlcohol;
    }

}