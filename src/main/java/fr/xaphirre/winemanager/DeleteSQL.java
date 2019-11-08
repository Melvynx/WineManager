package fr.xaphirre.winemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteSQL {
  private String DBPath;
  public Connection connect(String DBPath) {
    this.DBPath = DBPath;
    // SQLite connection string
    String url = "jdbc:sqlite:" + DBPath;
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(url);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return conn;
  }

  public void deleteWine(int id) {
    String sql = "DELETE FROM Wine WHERE id = ?";

    try (Connection conn = this.connect(DBPath);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

      // set the corresponding param
      pstmt.setInt(1, id);
      // execute the delete statement
      pstmt.executeUpdate();

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
  public void deleteBeer(int id) {
    String sql = "DELETE FROM Beer WHERE id = ?";

    try (Connection conn = this.connect(DBPath);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

      // set the corresponding param
      pstmt.setInt(1, id);
      // execute the delete statement
      pstmt.executeUpdate();

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
  public void deleteStrongAlcohol(int id) {
    String sql = "DELETE FROM StrongAlcohol WHERE id = ?";

    try (Connection conn = this.connect(DBPath);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

      // set the corresponding param
      pstmt.setInt(1, id);
      // execute the delete statement
      pstmt.executeUpdate();

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

}
