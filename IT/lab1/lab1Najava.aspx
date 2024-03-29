<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="lab1Najava.aspx.cs" Inherits="WebApplication2.WebForm1" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div class="row flex-column gap-2">
        <div class="col gap-2">
            Име     <asp:TextBox ID="TextBox1" runat="server"></asp:TextBox> <asp:Label ID="nameWarning" runat="server" ForeColor="Red"></asp:Label> <br />
        </div>
        <div class="col gap-2">
            Презиме     <asp:TextBox ID="TextBox2" runat="server"></asp:TextBox> <asp:Label ID="surnameWarning" runat="server" ForeColor="Red"></asp:Label> <br />
        </div>
        <div class="col gap-2">
            е-адреса     <asp:TextBox ID="TextBox3" runat="server"></asp:TextBox> <asp:Label ID="emailWarning" runat="server" ForeColor="Red"></asp:Label> <br />
        </div>
        <div>
        <asp:Button ID="button" runat="server" Text="Најавете се" OnClick="button_Click" />
        </div>
    </div>
</asp:Content>

