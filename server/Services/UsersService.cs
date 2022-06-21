using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Repository;
using Domain;
using Microsoft.EntityFrameworkCore;

namespace Services
{
    public class UsersService
    {
        private Context _context;

        public UsersService()
        {
            _context = new Context();
        }

        public bool isUserExists(string username)
        {
            return _context.Users.Find(username) != null;
        }

        public void Create(User user)
        {
            _context.Users.Add(user);
            _context.SaveChanges();
        }

        public bool Login(string username,string password)
        {
            var user = _context.Users.Find(username);
            if (user == null) throw new Exception("User does not exists");
            if (password == user.password) return true;
            return false;
        }
    }
}
