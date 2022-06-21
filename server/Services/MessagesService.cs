
using Repository;
using Domain;

namespace Services
{
    public class MessagesService
    {

        private Context _context;

        public MessagesService()
        {
            _context = new Context();
        }

        public IEnumerable<Message> GetAll(string id,string user)
        {
            List<Message> messages = _context.Messages.Where(message => ((message.to_id == user && message.from_id == id) || (message.to_id == id && message.from_id == user))).ToList();
            foreach(Message message in messages)
            {
                message.sent = message.from_id == user ? true : false;
            }
            return messages;
        }

        public void Create(string contact_id,Message message)
        {
            int contact_exists = _context.Contacts.Count(contact => contact.id == contact_id);
            if (contact_exists == 0) throw new Exception("Contact does not exists");
            _context.Messages.Add(message);
            _context.SaveChanges();
        }

        public Message GetMessage(string id, int id2, string user)
        {
            Message msg = _context.Messages.Find(id2);
            if (msg == null || (msg.to_id!=id && msg.from_id!=id) || (msg.from_id!=user && msg.to_id!=user)) throw new Exception("Message id not found");
            msg.sent = msg.from_id == user ? true : false;
            return msg;
        }

        // maybe need to make sure that if user change the "sent" field, we need to change the from_id and to_id also
        public void UpdateMessage(string id, int id2, Message message, string user)
        {
            if (message == null) throw new Exception("Message empty");
            Message msg = _context.Messages.Find(id2);
            if (msg == null || (msg.to_id != id && msg.from_id != id) || (msg.from_id != user && msg.to_id != user)) throw new Exception("Message id not found");
            msg.content = message.content == null ? msg.content : message.content;
            _context.SaveChanges();
        }

        public void DeleteMessage(string contact_id, int msg_id)
        {
            var message = _context.Messages.Find(msg_id);
            if(message==null) throw new Exception("Message does not exists");
            _context.Messages.Remove(message);
            _context.SaveChanges();
        }
    }
}
