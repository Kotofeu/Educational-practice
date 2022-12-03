using System;
using System.Collections.Generic;
using System.Windows;
using System.Windows.Controls;
using System.Data;
using System.Data.SqlClient;
using System.Threading;
using Excel = Microsoft.Office.Interop.Excel;
using Microsoft.Office.Interop.Excel;

namespace Restaurant
{
    public partial class MainWindow : System.Windows.Window
    {
        System.Data.DataTable dataSet;
        SqlDataAdapter adapter;
        CommandSelect command;
        CommandDelete commandDelete;
        Dictionary<string, string> ComboboxResurs;
        ListBoxItem listBoxItem;
        Dictionary<string, string> Tables = new Dictionary<string, string>
        {
            {"Блюда","Dish"},
            {"Напитки","Drink"},
            {"Ингредиенты","Ingredient"},
            {"Официанты","Waiter"},
            {"Заказы","Orders"},
        };

        static string ChoseString;
        public object Form { get; private set; }

        public MainWindow(string role = "USER")
        {
            InitializeComponent();
            SearchGrid.Visibility = Visibility.Hidden;
            ResetBtn.Visibility = Visibility.Hidden;
            GridBtn.Visibility = Visibility.Hidden;
            AboutBtn.Visibility = Visibility.Collapsed;
            this.Closed += Close;
            DishListBoxItem.IsSelected = true;
            if (role != "ADMIN")
            {
                Ingredient.Visibility = Visibility.Collapsed;
                Waiter.Visibility = Visibility.Collapsed;
            }
        }

        private void DBchose(object sender, SelectionChangedEventArgs e)
        {
            listBoxItem = (sender as System.Windows.Controls.ListBox).SelectedItem as ListBoxItem;
            if (listBoxItem != null)
            {
                ChoseString = listBoxItem.Content.ToString();
                CallDataGridSelect(ChoseString);
                SearchGrid.Visibility = Visibility.Visible;
                GridBtn.Visibility = Visibility.Visible;

            }
            if (ChoseString == "Блюда" || ChoseString == "Заказы")
            {
                AboutBtn.Visibility = Visibility.Visible;
            }
            else
            {
                AboutBtn.Visibility = Visibility.Collapsed;

            }
        }

        public void CallDataGridSelect(string ChoseStr)
        {
            if (ChoseString == "Блюда")
            {
                ComboboxResurs = new Dictionary<string, string>
                    {
                        {"id", "id"},
                        {"Название", "Name"},
                        {"Себестоимость","Cost_price" },
                        {"Цена","Price" },
                        {"Вес","Weight" }
                    };
                DataGridSelect(CommandSelect.selectAll, Tables["Блюда"]);
            }
            else if (ChoseString == "Напитки")
            {
                ComboboxResurs = new Dictionary<string, string>
                    {
                        {"id", "id"},
                        {"Название", "Name"},
                        {"Себестоимость","Cost_price" },
                        {"Цена","Price" },
                        {"Объём","Volume" }
                    };
                DataGridSelect(CommandSelect.selectAll, Tables["Напитки"]);
            }
            else if (ChoseString == "Ингредиенты")
            {
                ComboboxResurs = new Dictionary<string, string>
                    {
                        {"id", "id"},
                        {"Название", "Name"},
                        {"Себестоимость","Cost_price" },
                    };
                DataGridSelect(CommandSelect.selectAll, Tables["Ингредиенты"]);
            }
            else if (ChoseString == "Официанты")
            {
                ComboboxResurs = new Dictionary<string, string>
                    {
                        {"id", "id"},
                        {"Имя", "Name"},
                    };
                DataGridSelect(CommandSelect.selectAll, Tables["Официанты"]);
            }
            else if (ChoseString == "Заказы")
            {
                ComboboxResurs = new Dictionary<string, string>
                    {
                        {"id", "id"},
                        {"Номер стола","Table_number" },
                        {"Сумма","Total_amount" },
                        {"Номер официанта","id_waiter" }
                    };
                string[] selectQuery = { "Orders.id", "Orders.Date AS Дата", "Orders.Table_number AS Номер_Стола", "Orders.Total_amount AS Сумма", "Waiter.Name AS Официант" };
                DataGridSelect(selectQuery, "Orders, Waiter WHERE Waiter.id = id_waiter");
            }
            else if (ChoseString == "Состав")
            {
                ComboboxResurs = new Dictionary<string, string>
                    {
                        {"Ингредиент", "Ингредиент"},
                        {"Количество","Количество" }
                    };
            }
            if (ComboboxResurs != null)
            {
                try
                {
                    SearchCombobox.ItemsSource = ComboboxResurs.Keys;

                }
                catch (Exception ex)
                {
                    MessageBox.Show(ex.Message);
                }
            }
        }
        public void DataGridSelect(string[] array, string datatable, string where = null, string wherevalue = null)
        {

            try
            {
                if (where != null && wherevalue != null)
                {
                    command = new CommandSelect(array, datatable, where, wherevalue);
                }
                else
                {
                    command = new CommandSelect(array, datatable);

                }
              //  command.Print();
                command.SqlCommand().ExecuteNonQuery();
                adapter = new SqlDataAdapter(command.SqlCommand());
                dataSet = new System.Data.DataTable(datatable);
                adapter.Fill(dataSet);
                DataBaseGrid.ItemsSource = dataSet.DefaultView;
            }
            catch (Exception e)
            {
                MessageBox.Show(e.ToString());
            }
        }
        private void SearchBtnClick(object sender, RoutedEventArgs e)
        {
            DataGridSelect(CommandSelect.selectAll, Tables[ChoseString], ComboboxResurs[SearchCombobox.Text], SearchTextBox.Text.ToString());
            ResetBtn.Visibility = Visibility.Visible;
        }

        private void ResetBtnClick(object sender, RoutedEventArgs e)
        {
            SearchTextBox.Text = "";
            ResetBtn.Visibility = Visibility.Hidden;
            CallDataGridSelect(ChoseString);
        }
        private void Close(object sender, EventArgs e)
        {
            SqlConnectionSingle.CloseConnection();
            System.Windows.Application.Current.Shutdown();
        }
        public void OpenPerson(int id)
        {
            Thread thread = new Thread(() =>
            {
                ItemInfo w = new ItemInfo(id, ChoseString);
                w.Show();

                w.Closed += (sender2, e2) =>
                {
                    w.Dispatcher.InvokeShutdown();
                };

                System.Windows.Threading.Dispatcher.Run();
            });
            thread.SetApartmentState(ApartmentState.STA);
            thread.IsBackground = true;
            thread.Start();
            DataBaseGrid.ItemsSource = null;
            ChoseDateBase.SelectedItem = null;
            GridBtn.Visibility = Visibility.Hidden;
            SearchGrid.Visibility = Visibility.Hidden;
        }
        private void AddBtnClick(object sender, RoutedEventArgs e)
        {
            OpenPerson(0);

        }
        private void DeleteBtnClick(object sender, RoutedEventArgs e)
        {
            DataRowView dataRowView = (DataRowView)DataBaseGrid.SelectedItem;
            if (dataRowView != null)
            {
                MessageBoxResult result = MessageBox.Show("Вы уверенны?", "Удалить", MessageBoxButton.YesNo, MessageBoxImage.Question);
                if (result == MessageBoxResult.Yes)
                {
                    commandDelete = new CommandDelete(Tables[ChoseString], "id", Convert.ToString(dataRowView.Row[0]));
                    commandDelete.SqlCommand().ExecuteScalar();
                    CallDataGridSelect(ChoseString);
                }
            }
            else
            {
                MessageBox.Show("Выберите что-нибудь!", "Ошибка", MessageBoxButton.OK, MessageBoxImage.Hand);
            }
        }
        private void UpdateBtnClick(object sender, RoutedEventArgs e)
        {
            DataRowView dataRowView = (DataRowView)DataBaseGrid.SelectedItem;
            if (dataRowView != null)
            {
                OpenPerson(Convert.ToInt32(dataRowView.Row[0]));
            }
            else
            {
                MessageBox.Show("Выберите что-нибудь!", "Ошибка", MessageBoxButton.OK, MessageBoxImage.Hand);
            }


        }


        private void AboutBtnBtnClick(object sender, RoutedEventArgs e)
        {
            if (ChoseString == "Блюда")
            {
                SearchGrid.Visibility = Visibility.Collapsed;
                GridBtn.Visibility = Visibility.Collapsed;
                DataRowView dataRowView = (DataRowView)DataBaseGrid.SelectedItem;
                if (dataRowView != null)
                {
                    string[] Composition_Select = {
                        "Ingredient.Name AS Ингредиент",
                        "Amount AS Количество"
                    };
                    DataGridSelect(Composition_Select,
                        "Dish, Composition_dish, Ingredient ",
                        "Dish.id = id_dish AND Ingredient.id = id_ingredient  AND id_dish",
                        dataRowView.Row[0].ToString());

                }
            }
            if (ChoseString == "Заказы")
            {
                SearchGrid.Visibility = Visibility.Collapsed;
                GridBtn.Visibility = Visibility.Collapsed;
                DataRowView dataRowView = (DataRowView)DataBaseGrid.SelectedItem;
                if (dataRowView != null)
                {
                    string[] Composition_Select = {
                        "Dish.Name AS Блюдо",
                        "Drink.Name AS Напиток"

                    };
                    DataGridSelect(Composition_Select,
                        "Orders_list LEFT JOIN Dish ON Dish.id = Orders_list.id_dish " +
                        "LEFT JOIN Drink ON  Drink.id = Orders_list.id_drink ",
                        "Orders_list.id_order",
                        dataRowView.Row[0].ToString());

                }
            }
        }

        private void CreateXlsxBtnClick(object sender, RoutedEventArgs e)
        {
            {
                Excel.Application excel = new Excel.Application();
                excel.Visible = true; //www.yazilimkodlama.com
                Workbook workbook = excel.Workbooks.Add(System.Reflection.Missing.Value);
                Worksheet sheet1 = (Worksheet)workbook.Sheets[1];

                for (int j = 0; j < DataBaseGrid.Columns.Count; j++) //Başlıklar için
                {
                    Range myRange = (Range)sheet1.Cells[1, j + 1];
                    sheet1.Cells[1, j + 1].Font.Bold = true; //Başlığın Kalın olması için
                    sheet1.Columns[j + 1].ColumnWidth = 15; //Sütun genişliği ayarı
                    myRange.Value2 = DataBaseGrid.Columns[j].Header;
                }
                for (int i = 0; i < DataBaseGrid.Columns.Count; i++)
                { //www.yazilimkodlama.com
                    for (int j = 0; j < DataBaseGrid.Items.Count; j++)
                    {
                        TextBlock b = DataBaseGrid.Columns[i].GetCellContent(DataBaseGrid.Items[j]) as TextBlock;
                        Microsoft.Office.Interop.Excel.Range myRange = (Microsoft.Office.Interop.Excel.Range)sheet1.Cells[j + 2, i + 1];
                        myRange.Value2 = b.Text;
                    }
                }
            }
        }
    }
}