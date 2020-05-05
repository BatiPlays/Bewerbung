package com.homestream.HomeStream.hibernate.sqlite;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.identity.IdentityColumnSupport;

import java.sql.Types;

/**
 * Represents the SQLite dialect and makes it available for Hibernate
 * @author baeldung
 * @see <a href="https://github.com/eugenp/tutorials/blob/51352c470f98c417bb4ecb9b3150eb9f167bc361/spring-data-rest/src/main/java/com/baeldung/dialect/SQLiteDialect.java">Source</a> (Last checked: Dec 17, 2019 6:20PM CET)
 */


public class SQLiteDialect extends Dialect {

    public SQLiteDialect() {
        //registering SQLIte data types
        registerColumnType(Types.BIT, "integer");
        registerColumnType(Types.TINYINT, "tinyint");
        registerColumnType(Types.SMALLINT, "smallint");
        registerColumnType(Types.INTEGER, "integer");
        registerColumnType(Types.BIGINT, "bigint");
        registerColumnType(Types.FLOAT, "float");
        registerColumnType(Types.REAL, "real");
        registerColumnType(Types.DOUBLE, "double");
        registerColumnType(Types.NUMERIC, "numeric");
        registerColumnType(Types.DECIMAL, "decimal");
        registerColumnType(Types.CHAR, "char");
        registerColumnType(Types.VARCHAR, "varchar");
        registerColumnType(Types.LONGVARCHAR, "longvarchar");
        registerColumnType(Types.DATE, "date");
        registerColumnType(Types.TIME, "time");
        registerColumnType(Types.TIMESTAMP, "timestamp");
        registerColumnType(Types.BINARY, "blob");
        registerColumnType(Types.VARBINARY, "blob");
        registerColumnType(Types.LONGVARBINARY, "blob");
        registerColumnType(Types.BLOB, "blob");
        registerColumnType(Types.CLOB, "clob");
        registerColumnType(Types.BOOLEAN, "integer");
    }

    public IdentityColumnSupport getIdentityColumnSupport() {
        return new SQLiteIdentityColumnSupport();
    }

    /**
     * @author baeldung
     * And, SQLite doesn't have support for the database constraints
     */
    public boolean hasAlterTable() {
        return false;
    }

    /**
     * @author baeldung
     * And, SQLite doesn't have support for the database constraints
     */
    public boolean dropConstraints() {
        return false;
    }

    /**
     * @author baeldung
     * And, SQLite doesn't have support for the database constraints
     */
    public String getDropForeignKeyString() {
        return "";
    }

    /**
     * @author baeldung
     * And, SQLite doesn't have support for the database constraints
     */
    public String getAddForeignKeyConstraintString(String constraintName, String[] foreignKey, String referencedTable, String[] primaryKey, boolean referencesPrimaryKey) {
        return "";
    }

    /**
     * @author baeldung
     * And, SQLite doesn't have support for the database constraints
     */
    public String getAddPrimaryKeyConstraintString(String constraintName) {
        return "";
    }

    /**
     * @author baeldung
     * And, SQLite doesn't have support for the database constraints
     */
    public String getForUpdateString() {
        return "";
    }

    /**
     * @author baeldung
     * And, SQLite doesn't have support for the database constraints
     */
    public String getAddColumnString() {
        return "add column";
    }

    public boolean supportsOuterJoinForUpdate() {
        return false;
    }

    /**
     * @author baeldung
     * And, SQLite doesn't have support for the database constraints
     */
    public boolean supportsIfExistsBeforeTableName() {
        return true;
    }

    /**
     * @author baeldung
     * And, SQLite doesn't have support for the database constraints
     */
    public boolean supportsCascadeDelete() {
        return false;
    }
}
