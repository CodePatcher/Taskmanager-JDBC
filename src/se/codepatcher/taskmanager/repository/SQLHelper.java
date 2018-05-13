package se.codepatcher.taskmanager.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import se.codepatcher.taskmanager.connection.Connector;
import se.codepatcher.taskmanager.exception.RepositoryException;

public final class SQLHelper {

	private final Connector connector;
	private final String query;
	private final List<Object> queryParameters;
	
	SQLHelper(String query) throws SQLException {
		this.query = query;
		this.connector = new Connector();
		this.queryParameters = new ArrayList<>();
	}

	SQLHelper addParameter(Object parameter) {
		queryParameters.add(parameter);
		return this;
	}
	
	int executeUpdate() throws RepositoryException {
		
		try(Connection connection = connector.getConnection();
			PreparedStatement statement = prepareStatement(connection, query, queryParameters, false)) {
			
			return statement.executeUpdate();
			
		} catch (SQLException e) {
			throw new RepositoryException("Could not execute update: " + query + e.getMessage());
		}
	}

	<T> List<T> executeUpdate(Mapper<T> keyMapper) throws RepositoryException {
		
		try(Connection connection = connector.getConnection();
			PreparedStatement statement = prepareStatement(connection, query, queryParameters, true)) {
			
				List<T> keys = new ArrayList<>(); 

				statement.executeUpdate();
				ResultSet resultKeys = statement.getGeneratedKeys();
				
				while(resultKeys.next()){
					keys.add(keyMapper.map(resultKeys));
				}
				
				return keys;
				
			} catch (SQLException e) {
				throw new RepositoryException("Could not execute update: " + query + e.getMessage());
			}
	}
	
	<T> List<T> executeQuery(Mapper<T> rowMapper) throws RepositoryException {
		try (Connection connection = connector.getConnection();
			 PreparedStatement statement = prepareStatement(connection, query, queryParameters, false);
			 ResultSet resultSet = statement.executeQuery()) {
			
			List<T> result = new ArrayList<>();
			
			while(resultSet.next()) {
				result.add(rowMapper.map(resultSet));
			}

			return result;
			
		} catch(SQLException e){
			throw new RepositoryException("Could not execute update: " + query + e.getMessage());
		}
	}
	
	private static PreparedStatement prepareStatement(Connection connection, String sql, List<Object> parameters, boolean returnKeys) throws SQLException {
		PreparedStatement statement = returnKeys ? connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS) :
									  			   connection.prepareStatement(sql);	
		for(int i = 0; i < parameters.size(); i++){
			statement.setObject(i +1, parameters.get(i));
		}
		return statement;
	}
}