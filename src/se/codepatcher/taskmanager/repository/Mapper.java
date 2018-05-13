package se.codepatcher.taskmanager.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

interface Mapper <T> {

	T map(ResultSet row) throws SQLException;
}