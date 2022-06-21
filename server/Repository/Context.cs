using Microsoft.EntityFrameworkCore;
using Domain;
using Microsoft.Extensions.Configuration;

namespace Repository
{
    public class Context : DbContext
    {
        private string connectionString = "server=localhost;port=3306;database=Items;";

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseMySql(connectionString,MariaDbServerVersion.AutoDetect(connectionString));
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Contact>()
                .HasKey(nameof(Contact.id), nameof(Contact.owner_id));

            modelBuilder.Entity<Message>()
                .HasKey(nameof(Message.id));

            modelBuilder.Entity<User>().HasKey(nameof(User.username));
        }

        public Context()
        {
            var sections = new ConfigurationBuilder().AddJsonFile("appsettings.json").Build().GetSection("DB");
            connectionString = connectionString + "user = " + sections["user"] + "; password = " + sections["password"];
            //Database.EnsureDeleted();
            Database.EnsureCreated();
        }

        public DbSet<Contact> Contacts { get; set; }

        public DbSet<Message> Messages { get; set; }

        public DbSet<User> Users { get; set; }


    }
}
