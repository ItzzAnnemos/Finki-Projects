using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace WebApplication2
{
    public partial class WebForm2 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (IsPostBack)
            {
                if (ListBox1.SelectedIndex == 0)
                {
                    Label1.Text = "Проф. д-р Гоце Арменски";
                    ListBox2.SelectedIndex = 0;
                }
                else if (ListBox1.SelectedIndex == 1)
                {
                    Label1.Text = "Проф. д-р Никола Арменски";
                    ListBox2.SelectedIndex = 1;
                }
                else if (ListBox1.SelectedIndex == 2)
                {
                    Label1.Text = "Проф. д-р Стефан Арменски";
                    ListBox2.SelectedIndex = 2;
                } else
                {
                    ListBox2.SelectedIndex = ListBox1.SelectedIndex;
                }
            } else
            {
                ListBox1.Items.Add("Интернет технологии");
                ListBox1.Items.Add("Интернет");
                ListBox1.Items.Add("Дигитална електроника");
                ListBox2.Items.Add("6");
                ListBox2.Items.Add("5.5");
                ListBox2.Items.Add("5.5");
            }
        }

        protected void button_Click(object sender, EventArgs e)
        {
            if (ListBox1.SelectedIndex != -1)
            {
                Response.Redirect("lab1UspeshnoGlasanje.aspx");
            }
        }

        protected void add_Click(object sender, EventArgs e)
        {
            String subject = TextBox1.Text;
            String credits = TextBox2.Text;

            ListBox1.Items.Add(subject);
            ListBox2.Items.Add(credits);
        }

        protected void delete_Click(object sender, EventArgs e)
        {
            ListBox1.Items.RemoveAt(ListBox1.SelectedIndex);
            ListBox2.Items.RemoveAt(ListBox2.SelectedIndex);
        }
    }
}