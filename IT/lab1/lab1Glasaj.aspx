<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="lab1Glasaj.aspx.cs" Inherits="WebApplication2.WebForm2" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div class="row">
        <asp:Image ID="Image1" runat="server" ImageUrl="~/finki-logo.png" Width="300px" Height="150px" />
        <asp:Label ID="Label1" runat="server" Text="Професор"></asp:Label>
        <div class="col">
            <asp:ListBox ID="ListBox1" runat="server" AutoPostBack="True">
            </asp:ListBox>
            <asp:ListBox ID="ListBox2" runat="server">
            </asp:ListBox> <br />
            <asp:Button ID="button" runat="server" Text="Гласајте" OnClick="button_Click" />
            <hr />
            Предмет:<br />
            <asp:TextBox ID="TextBox1" runat="server" CssClass="mb-3"></asp:TextBox> <br />
            Кредити:<br />
            <asp:TextBox ID="TextBox2" runat="server" CssClass="mb-2"></asp:TextBox> <br />
            <asp:Button ID="add" runat="server" Text="Dodadi" OnClick="add_Click" CssClass="mb-2" /> <br />
            <asp:Button ID="delete" runat="server" Text="Izbrisi" OnClick="delete_Click" />
        </div>
    </div>
</asp:Content>
