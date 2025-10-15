import java.sql.*;
import java.util.Scanner;

public class AnimalAdoptionJdbc {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/animal_adoption"; // ✅ Database for adoption
        String user = "root";
        String pass = "tiger";

        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            System.out.println("✅ Connected to Animal Adoption Database!");

            while (true) {
                System.out.println("\n=== ANIMAL ADOPTION MENU ===");
                System.out.println("1. Add Animal for Adoption");
                System.out.println("2. View All Animals");
                System.out.println("3. Update Animal Details");
                System.out.println("4. Mark as Adopted");
                System.out.println("5. Delete Animal Record");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        // ➕ ADD Animal
                        System.out.print("Enter Animal ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Animal Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Animal Age: ");
                        int age = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Breed: ");
                        String breed = sc.nextLine();

                        PreparedStatement psInsert = con.prepareStatement("INSERT INTO animals VALUES (?, ?, ?, ?, ?)");
                        psInsert.setInt(1, id);
                        psInsert.setString(2, name);
                        psInsert.setInt(3, age);
                        psInsert.setString(4, breed);
                        psInsert.setString(5, "Available");
                        System.out.println(psInsert.executeUpdate() + " animal added for adoption!");
                        psInsert.close();
                        break;

                    case 2:
                        //  VIEW Animals
                        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM animals");
                        System.out.println("\nID\tName\tAge\tBreed\t\tStatus");
                        System.out.println("------------------------------------------------");
                        while (rs.next()) {
                            System.out.println(rs.getInt("id") + "\t" + rs.getString("name") + "\t" + rs.getInt("age") + "\t" + rs.getString("breed") + "\t" + rs.getString("status"));
                        }
                        rs.close();
                        break;

                    case 3:
                        //  UPDATE Animal Details
                        System.out.print("Enter Animal ID to update: ");
                        int uid = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter new Name: ");
                        String newName = sc.nextLine();
                        System.out.print("Enter new Age: ");
                        int newAge = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter new Breed: ");
                        String newBreed = sc.nextLine();

                        PreparedStatement psUpdate = con.prepareStatement("UPDATE animals SET name=?, age=?, breed=? WHERE id=?");
                        psUpdate.setString(1, newName);
                        psUpdate.setInt(2, newAge);
                        psUpdate.setString(3, newBreed);
                        psUpdate.setInt(4, uid);
                        System.out.println(psUpdate.executeUpdate() + " record updated!");
                        psUpdate.close();
                        break;

                    case 4:
                        //  Mark as Adopted
                        System.out.print("Enter Animal ID to mark as adopted: ");
                        int aid = sc.nextInt();

                        PreparedStatement psAdopt = con.prepareStatement("UPDATE animals SET status='Adopted' WHERE id=?");
                        psAdopt.setInt(1, aid);
                        System.out.println(psAdopt.executeUpdate() + " animal marked as Adopted!");
                        psAdopt.close();
                        break;

                    case 5:
                        //  DELETE Animal
                        System.out.print("Enter Animal ID to delete: ");
                        int did = sc.nextInt();

                        PreparedStatement psDelete = con.prepareStatement("DELETE FROM animals WHERE id=?");
                        psDelete.setInt(1, did);
                        System.out.println(psDelete.executeUpdate() + " animal record deleted!");
                        psDelete.close();
                        break;

                    case 6:
                        System.out.println(" Exiting Animal Adoption System...");
                        con.close();
                        sc.close();
                        System.exit(0);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
