package net.customer.repository.sqlQueries;

public class SqlQueriesConstants {

    public static final String SELECT_ALL_REQUEST_TABLE_ =
            "SELECT * FROM request_table WHERE request_status = %s OR request_status = %s LIMIT %s;";
}
