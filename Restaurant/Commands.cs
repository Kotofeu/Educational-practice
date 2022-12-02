using System.Data.SqlClient;
using System.Windows;

namespace Restaurant
{

    class Command
    {
        public static readonly string[] selectAll = { "*" };
        public static readonly string[] selectAllDish = { "id", "Name", "Cost_price", "Price", "Weight" };
        public static readonly string[] selectAllDrink = { "id", "Name", "Cost_price", "Price", "Volume" };
        public static readonly string[] selectAllIngredient = { "id", "Name", "Cost_price" };
        public static readonly string[] selectAllOrders = { "id", "Date", "Table_number", "Total_amount", "id_waiter" };
        public static readonly string[] selectAllWaiter = { "id", "Name" };



        SqlConnectionSingle sqlConnection = SqlConnectionSingle.getInstance();
        protected string ConnectionString { get; set; }
        public void Print()
        {
            MessageBox.Show(ConnectionString);
        }
        public SqlCommand SqlCommand()
        {
            SqlCommand command = new SqlCommand(ConnectionString, sqlConnection.Connection());
            return command;
        }
    }
}