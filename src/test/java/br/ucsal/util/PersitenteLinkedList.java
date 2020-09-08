package br.ucsal.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ucsal.util.exception.InvalidElementException;
import br.ucsal.util.interfaces.PersistentList;

// Este código pode conter defeitos. Seu objetivo é ilustrar falhas a serem
// reveladas por testes automatizados.
public class PersitenteLinkedList<T> extends LinkedList<T> implements PersistentList<T> {

    @Override
    public void persist(Long id, Connection connection, String tabName) throws SQLException, IOException {
        Node aux = start;
        while (aux != null) {
            persist(aux.element, id, connection, tabName);
            aux = aux.prox;
        }
    }

    @Override
    public void load(Long id, Connection connection, String tabName)
            throws SQLException, ClassNotFoundException, IOException, InvalidElementException {
        clear();
        String query = "select element from " + tabName + " where id = ? order by seq";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    InputStream serializedObject = rs.getBinaryStream("element");
                    T element = deserializeElement(serializedObject);
                    add(element);
                }
            }
        }
    }

    @Override
    public void delete(Long id, Connection connection, String tabName) throws SQLException {
        String query = "delete from " + tabName + " where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
        clear();
    }

    private void persist(T element, Long id, Connection connection, String tabName) throws SQLException, IOException {
        String query = "insert into " + tabName + " (id, element) values (?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ByteArrayInputStream serializedObject = serializeElement(element);
            stmt.setLong(1, id);
            stmt.setBinaryStream(2, serializedObject);
            stmt.executeUpdate();
        }
    }

    private ByteArrayInputStream serializeElement(T element) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(element);
        oos.close();
        return new ByteArrayInputStream(baos.toByteArray());
    }

    private T deserializeElement(InputStream serializedObject) throws ClassNotFoundException, IOException {
        ObjectInputStream ois = new ObjectInputStream(serializedObject);
        @SuppressWarnings("unchecked")
        T element = (T) ois.readObject();
        ois.close();
        return element;
    }

}
