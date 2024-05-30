using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(MVC_lab2.Startup))]
namespace MVC_lab2
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
