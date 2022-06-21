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
    public class ContactsService
    {
        private Context _context;

        public ContactsService()
        {
            _context = new Context();
        }

        public IEnumerable<Contact> GetAll(string user)
        {
            return _context.Contacts.Where(contact => contact.owner_id == user).OrderBy(contact => contact.lastdate).ToList();
        }

        public void Add(Contact contact)
        {
            contact.last = null;
            _context.Contacts.Add(contact);
            _context.SaveChanges();
        }

        public Contact GetContact(string owner,string id)
        {
            return _context.Contacts.Where(contact => contact.owner_id==owner && contact.id==id).FirstOrDefault();
        }

        public bool isContactExists(string owner_id,string id)
        {
            return _context.Contacts.Find(id,owner_id) != null;
        }

        public void UpdateContact(string owner, string id,Contact contact)
        {
            if (contact == null) throw new Exception("No payload");
            var oldContact = _context.Contacts.Find(id,owner);
            if (oldContact == null) throw new Exception("Contact not found");
            oldContact.server = contact.server!=null ? contact.server : oldContact.server;
            oldContact.name = contact.name != null ? contact.name : oldContact.name;
            oldContact.last = contact.last != null ? contact.last : oldContact.last;
            oldContact.lastdate = contact.lastdate != null ? contact.lastdate : oldContact.lastdate;
            _context.SaveChanges();
        }

        public void Delete(string owner,string id)
        {
            var contact = _context.Contacts.Where(contact => contact.owner_id == owner && contact.id == id).FirstOrDefault();
            if (contact == null) throw new Exception("Contact does not exists");
            _context.Contacts.Remove(contact);
            _context.SaveChanges();
        }
    }
}
