import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ConnectionSQL{

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
    public void createTable() {
        String sqlAlcohol = "CREATE TABLE IF NOT EXISTS StrongAlcohol (" +
                "    id INTEGER PRIMARY KEY," +
                "    name VARCHAR(40) NOT NULL," +
                "    region VARCHAR(40)," +
                "    age INTEGER NOT NULL," +
                "    degree_of_alcohol INTEGER NOT NULL," +
                "    capacity_ml INTEGER NOT NULL" +
                ");";
        String sqlBeer = "CREATE TABLE IF NOT EXISTS Beer (" +
                "    id INTEGER PRIMARY KEY," +
                "    name VARCHAR(40) NOT NULL," +
                "    region VARCHAR(40)," +
                "    age INTEGER NOT NULL," +
                "    degree_of_alcohol INTEGER NOT NULL," +
                "    capacity_ml INTEGER NOT NULL," +
                "    type_beer VARCHAR(10)" +
                ");";
        String sqlWine = "CREATE TABLE IF NOT EXISTS Wine (" +
                "    id INTEGER PRIMARY KEY," +
                "    name VARCHAR(40) NOT NULL," +
                "    region VARCHAR(40)," +
                "    age INTEGER NOT NULL," +
                "    degree_of_alcohol INTEGER NOT NULL," +
                "    capacity_ml INTEGER NOT NULL," +
                "    type_wine VARCHAR(10), " +
                "    start_maturity INTEGER NOT NULL," +
                "    end_maturity INTEGER NOT NULL" +
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
                    .prepareStatement("INSERT INTO Wine (name, region, age, degree_of_alcohol, capacity_ml, type_wine, start_maturity, end_maturity) VALUES(?,?,?,?,?,?,?,?)");
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
                    .prepareStatement("INSERT INTO Beer (name, region, age, degree_of_alcohol, capacity_ml, type_beer) VALUES(?,?,?,?,?,?)");
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
                    .prepareStatement("INSERT INTO StrongAlcohol (name, region, age, degree_of_alcohol, capacity_ml) VALUES(?,?,?,?,?)");
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
            System.out.println(typeNewWine);
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