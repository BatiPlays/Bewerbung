package com.homestream.HomeStream.hibernate.sqlite;

import org.hibernate.MappingException;
import org.hibernate.dialect.identity.IdentityColumnSupportImpl;

/**
 * To tell Hibernate how @Id works in SQLite (supports Integer only)
 * @author baeldung
 * @see <a href="https://github.com/eugenp/tutorials/blob/51352c470f98c417bb4ecb9b3150eb9f167bc361/spring-data-rest/src/main/java/com/baeldung/dialect/SQLiteIdentityColumnSupport.java">Source</a> Last checked: Dec 17 2019, 6:36PM
 */
public class SQLiteIdentityColumnSupport extends IdentityColumnSupportImpl {

    @Override
    public boolean supportsIdentityColumns() {
        return true;
    }

    @Override
    public String getIdentitySelectString(String table, String column, int type)
            throws MappingException {
        return "select last_insert_rowid()";
    }

    @Override
    public String getIdentityColumnString(int type) throws MappingException {
        return "integer";
    }
}
