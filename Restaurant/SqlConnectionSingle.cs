﻿using System.Data.SqlClient;
using System.Configuration;

namespace Restaurant
{
    class SqlConnectionSingle
    {
        private static SqlConnectionSingle instance;
        private static SqlConnection sqlConnection = new SqlConnection(ConfigurationManager.ConnectionStrings["restaurant"].ConnectionString);

        private SqlConnectionSingle()
        { }
        public SqlConnection Connection()
        {
            return sqlConnection;
        }
        public static void CloseConnection()
        {
            sqlConnection.Close();
        }
        public static SqlConnectionSingle getInstance()
        {
            if (instance == null)
            {
                instance = new SqlConnectionSingle();
            }
            if (sqlConnection.State == System.Data.ConnectionState.Closed)
            {
                sqlConnection.Open();
            }
            return instance;
        }
    }
}