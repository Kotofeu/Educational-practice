using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Windows;
namespace Restaurant
{
    public partial class ItemInfo : Window
    {
        private int id;
        private string table;
        private bool isCorrect = true;
        CommandSelect commandSelect;
        CommandInsert commandInsert;
        CommandUpdate commandUpdate;
        private List<string> FieldsValues;
        private List<string> Fields;
        Dictionary<string, string> Tables = new Dictionary<string, string>
        {
            {"Блюда","Dish"},
            {"Напитки","Drink"},
            {"Ингредиенты","Ingredient"},
            {"Официанты","Waiter"},
            {"Заказы","Orders"},
        };
        public ItemInfo(int id = 0, string table = null)
        {
            this.id = id;
            this.table = table;
            InitializeComponent();
            Console.WriteLine(table);
            if (table == "Блюда")
            {
                FIOLabel.Visibility = Visibility.Hidden;
                DateLabel.Visibility = Visibility.Hidden;
                TableNumberLabel.Visibility = Visibility.Hidden;
                TotalAmountLabel.Visibility = Visibility.Hidden;
                VolumeLabel.Visibility = Visibility.Hidden;
                WaiterLabel.Visibility = Visibility.Hidden;
            }
            else if (table == "Напитки")
            {
                FIOLabel.Visibility = Visibility.Hidden;
                DateLabel.Visibility = Visibility.Hidden;
                TableNumberLabel.Visibility = Visibility.Hidden;
                TotalAmountLabel.Visibility = Visibility.Hidden;
                WeightLabel.Visibility = Visibility.Hidden;
                WaiterLabel.Visibility = Visibility.Hidden;
            }
            else if (table == "Ингредиенты")
            {
                FIOLabel.Visibility = Visibility.Hidden;
                DateLabel.Visibility = Visibility.Hidden;
                TableNumberLabel.Visibility = Visibility.Hidden;
                CostLabel.Visibility = Visibility.Hidden;
                TotalAmountLabel.Visibility = Visibility.Hidden;
                VolumeLabel.Visibility = Visibility.Hidden;
                WeightLabel.Visibility = Visibility.Hidden;
                WaiterLabel.Visibility = Visibility.Hidden;


                ThirdTextBox.Visibility = Visibility.Hidden;
                FourthTextBox.Visibility = Visibility.Hidden;

            }
            else if (table == "Официанты")
            {
                NameLabel.Visibility = Visibility.Hidden;
                DateLabel.Visibility = Visibility.Hidden;
                CostPriceLabel.Visibility = Visibility.Hidden;
                TableNumberLabel.Visibility = Visibility.Hidden;
                CostLabel.Visibility = Visibility.Hidden;
                TotalAmountLabel.Visibility = Visibility.Hidden;
                VolumeLabel.Visibility = Visibility.Hidden;
                WeightLabel.Visibility = Visibility.Hidden;
                WaiterLabel.Visibility = Visibility.Hidden;


                SecondTextBox.Visibility = Visibility.Hidden;
                ThirdTextBox.Visibility = Visibility.Hidden;
                FourthTextBox.Visibility = Visibility.Hidden;

            }
            else if (table == "Заказы")
            {
                NameLabel.Visibility = Visibility.Hidden;
                FIOLabel.Visibility = Visibility.Hidden;
                CostPriceLabel.Visibility = Visibility.Hidden;
                CostLabel.Visibility = Visibility.Hidden;
                VolumeLabel.Visibility = Visibility.Hidden;
                WeightLabel.Visibility = Visibility.Hidden;

            }
            if (id != 0)
            {
                commandSelect = new CommandSelect(Command.selectAll, Tables[table]);
                if (table == "Блюда")
                {
                    commandSelect.CommandChange("Name", Tables[table], "id", id.ToString());
                    FirstTextBox.Text = commandSelect.SqlCommand().ExecuteScalar().ToString();
                    commandSelect.CommandChange("Cost_price", Tables[table], "id", id.ToString());
                    SecondTextBox.Text = commandSelect.SqlCommand().ExecuteScalar().ToString();
                    commandSelect.CommandChange("Price", Tables[table], "id", id.ToString());
                    ThirdTextBox.Text = commandSelect.SqlCommand().ExecuteScalar().ToString();
                    commandSelect.CommandChange("Weight", Tables[table], "id", id.ToString());
                    FourthTextBox.Text = commandSelect.SqlCommand().ExecuteScalar().ToString();

                }
                else if (table == "Напитки")
                {
                    commandSelect.CommandChange("Name", Tables[table], "id", id.ToString());
                    FirstTextBox.Text = commandSelect.SqlCommand().ExecuteScalar().ToString();
                    commandSelect.CommandChange("Cost_price", Tables[table], "id", id.ToString());
                    SecondTextBox.Text = commandSelect.SqlCommand().ExecuteScalar().ToString();
                    commandSelect.CommandChange("Price", Tables[table], "id", id.ToString());
                    ThirdTextBox.Text = commandSelect.SqlCommand().ExecuteScalar().ToString();
                    commandSelect.CommandChange("Volume", Tables[table], "id", id.ToString());
                    FourthTextBox.Text = commandSelect.SqlCommand().ExecuteScalar().ToString();
                }
                else if (table == "Ингредиенты")
                {
                    commandSelect.CommandChange("Name", Tables[table], "id", id.ToString());
                    FirstTextBox.Text = commandSelect.SqlCommand().ExecuteScalar().ToString();
                    commandSelect.CommandChange("Cost_price", Tables[table], "id", id.ToString());
                    SecondTextBox.Text = commandSelect.SqlCommand().ExecuteScalar().ToString();
                }
                else if (table == "Официанты")
                {
                    commandSelect.CommandChange("Name", Tables[table], "id", id.ToString());
                    FirstTextBox.Text = commandSelect.SqlCommand().ExecuteScalar().ToString();
                }
                else if (table == "Заказы")
                {
                    commandSelect.CommandChange("Date", Tables[table], "id", id.ToString());
                    FirstTextBox.Text = commandSelect.SqlCommand().ExecuteScalar().ToString();
                    commandSelect.CommandChange("Table_number", Tables[table], "id", id.ToString());
                    SecondTextBox.Text = commandSelect.SqlCommand().ExecuteScalar().ToString();
                    commandSelect.CommandChange("Total_amount", Tables[table], "id", id.ToString());
                    ThirdTextBox.Text = commandSelect.SqlCommand().ExecuteScalar().ToString();
                    commandSelect.CommandChange("id_waiter", Tables[table], "id", id.ToString());
                    FourthTextBox.Text = commandSelect.SqlCommand().ExecuteScalar().ToString();
                }

            }
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            string Fitst = FirstTextBox.Text;
            string Second = SecondTextBox.Text;
            string Third = ThirdTextBox.Text;
            string Fourth = FourthTextBox.Text;
            FieldsValues = new List<string> { Fitst };

            try
            {
                if (table == "Блюда")
                {
                    isCorrect = !(Fitst == string.Empty || Second == string.Empty || Third == string.Empty || Fourth == string.Empty);
                    FieldsValues.Add(Second);
                    FieldsValues.Add(Third);
                    FieldsValues.Add(Fourth);
                    Fields = new List<string> { "Name", "Cost_price", "Price", "Weight" };
                }
                else if (table == "Напитки")
                {

                    isCorrect = !(Fitst == string.Empty || Second == string.Empty || Third == string.Empty || Fourth == string.Empty);

                    FieldsValues.Add(Second);
                    FieldsValues.Add(Third);
                    FieldsValues.Add(Fourth);
                    Fields = new List<string> { "Name", "Cost_price", "Price", "Volume" };

                }
                else if (table == "Ингредиенты")
                {

                    isCorrect = !(Fitst == string.Empty || Second == string.Empty);
                    FieldsValues.Add(Second);
                    Fields = new List<string> { "Name", "Cost_price" };
                }
                else if (table == "Официанты")
                {
                    isCorrect = !(Fitst == string.Empty);
                    Fields = new List<string> { "Name" };
                }
                else if (table == "Заказы")
                {
                    isCorrect = !(Fitst == string.Empty || Second == string.Empty || Third == string.Empty || Fourth == string.Empty);
                    FieldsValues.Add(Second);
                    FieldsValues.Add(Third);
                    FieldsValues.Add(Fourth);
                    Fields = new List<string> { "Date", "Table_number", "Total_amount", "id_waiter" };
                }
                if (isCorrect)
                {
                    if (id == 0)
                    {
                        commandInsert = new CommandInsert(Tables[table], Fields, FieldsValues);
                        commandInsert.SqlCommand().ExecuteScalar();
                        MessageBox.Show(table + ": запись добавлена!", "Добавление", MessageBoxButton.OK, MessageBoxImage.Information);
                    }
                    else
                    {
                        commandUpdate = new CommandUpdate(Tables[table], Fields, FieldsValues, "id", id.ToString());
                        commandUpdate.SqlCommand().ExecuteScalar();
                        MessageBox.Show(table + ": запись обновлена!", "Обновление", MessageBoxButton.OK, MessageBoxImage.Information);

                    }
                    Close();
                }
                else
                {
                    MessageBox.Show("Не все значения заполнены!", "Ошибка", MessageBoxButton.OK, MessageBoxImage.Hand);

                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.ToString());
            }
        }

        private void Button_Click_1(object sender, RoutedEventArgs e)
        {
            this.Close();
        }


    }
}