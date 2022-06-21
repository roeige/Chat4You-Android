using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Domain
{
    public class Message
    {
        public int? id { get; set; }
        public string? content { get; set; }

        public DateTime? created { get; set; }

        public Boolean? sent { get; set; }

        public string? from_id { get; set; }

        public string? to_id { get; set; }

        public Message(bool? sent = null, int? id = null, string? content = null, DateTime? created=null,  string? from_id=null, string to_id=null)
        {
            this.id = id;
            this.content = content;
            this.created = created;
            this.sent = sent;
            this.from_id = from_id;
            this.to_id = to_id;
        }
    }
}
