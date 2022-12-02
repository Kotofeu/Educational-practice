using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace Restaurant
{
    /// <summary>
    /// Логика взаимодействия для auth.xaml
    /// </summary>
    public partial class auth : Window
    {
        CommandSelect command;
        public auth()
        {
            InitializeComponent();
        }

        private void Submit_Click(object sender, RoutedEventArgs e)
        {
            string password = PasswordTextBox.Password;
            string login = LoginTextBox.Text;

            string selectQuery = "Users WHERE Login = '" + login + "' AND Password = '" + password + "'";
            command = new CommandSelect("Role", selectQuery);

            try
            {
                if (command.SqlCommand().ExecuteScalar() != null)
                {
                    string role = command.SqlCommand().ExecuteScalar().ToString();
                    MainWindow w = new MainWindow(role);
                    w.Show();
                    this.Close();
                }
                else
                {
                    MessageBox.Show("Пользователь не найден !", "Ошибка", MessageBoxButton.OK, MessageBoxImage.Hand);
                }

            }
            catch (Exception ex)
            {
                MessageBox.Show("Непредвиденная ошибка!", "Ошибка", MessageBoxButton.OK, MessageBoxImage.Hand);
            }

            PasswordTextBox.Password = "";
            LoginTextBox.Text = "";
        }

        private void button3_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }
    }
}
