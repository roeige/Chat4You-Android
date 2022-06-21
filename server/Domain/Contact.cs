namespace Domain
{
    public class Contact
    {

        public Contact(string? owner_id, string? id= null, string? name = null, string? server = null, string? last = null, DateTime? lastdate = null)
        {
            this.owner_id = owner_id;
            this.id = id;
            this.name = name;
            this.server = server;
            this.last = last;
            this.lastdate = lastdate;
        }

        public string? owner_id { get; set; }

        public string? id { get; set; }

        public string? name { get; set; }

        public string? server { get; set; }

        public string? last { get; set; }

        public DateTime? lastdate { get; set; }

        


    }

}

