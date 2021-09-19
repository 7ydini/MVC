package ru.lisiy.springsource.dao;

import org.springframework.stereotype.Component;
import ru.lisiy.springsource.models.Person;
import ru.lisiy.springsource.models.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class TicketDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/springmvc";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static Connection connection;

    static {
        try {
            Class.forName("mysql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Ticket> index(){
        List<Ticket> tickets = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Ticket";
            ResultSet resultSet = statement.executeQuery(SQL);
            while(resultSet.next()){
                Ticket ticket = new Ticket();

                ticket.setId(resultSet.getInt("Ticket_id"));
                ticket.setTicketName(resultSet.getString("Ticket_Name"));

                tickets.add(ticket);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return tickets;
    }

    public void update(int id, Person person){
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE tickets " +
                            "SET Ticket_Name='Занято', Person_id=? " +
                            "WHERE Ticket_id=?");
            preparedStatement.setInt(1, person.getId());
            preparedStatement.setInt(2, id);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
