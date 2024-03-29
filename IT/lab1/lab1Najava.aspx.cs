using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services.Description;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace WebApplication2
{
    public partial class WebForm1 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            nameWarning.Text = "";
            surnameWarning.Text = "";
            emailWarning.Text = "";
        }

        protected void button_Click(object sender, EventArgs e)
        {
            if (TextBox1.Text != "" && TextBox2.Text != "" && TextBox3.Text != "")
            {
                string email = TextBox3.Text;
                Session["TextData"] = email;

                Response.Redirect("lab1Glasaj.aspx");
            }
            else
            {
                if (TextBox1.Text == "")
                {
                    nameWarning.Text = "Внесете име";
                }
                if (TextBox2.Text == "")
                {
                    surnameWarning.Text = "Внесете презиме";
                }
                if (!TextBox3.Text.Contains("@"))
                {
                    emailWarning.Text = "Невалиден Формат";
                }
            }
        }
    }
}