using Microsoft.AspNetCore.Mvc;
using Domain;
using Services;
using Microsoft.AspNetCore.Authorization;
using System.Runtime;
using webAPI.Hubs;
using Microsoft.AspNetCore.SignalR;
using CorePush.Google;
using System.Net.Http.Headers;
using FirebaseAdmin;
using Google.Apis.Auth.OAuth2;
using FirebaseAdmin.Messaging;

namespace webAPI.Controllers
{

    [ApiController]
    [Route("api")]
    public class ConnectionsController : ControllerBase
    {
        ContactsService contacts_service;
        MessagesService messages_service;
        UsersService users_service;
        IHubContext<ChatHub> hubContext;
        public ConnectionsController(IHubContext<ChatHub> hubContext)
        {
            contacts_service = new ContactsService();
            messages_service = new MessagesService();
            users_service = new UsersService();
            this.hubContext = hubContext;
        }

        [Route("invitations")]
        [HttpPost]
        public IActionResult Invite([FromBody] Invite invite)
        {
            try 
            {
                // need to ask what the Name of the contact to add, no description about that
                if (invite == null || invite.from == null || invite.to == null || invite.server == null) throw new Exception("One or more of the fields are missing");
                if (contacts_service.isContactExists(invite.to, invite.from)) throw new Exception("Contact already exists");
                if (invite.from == invite.to) throw new Exception("Adding yourself as a contact is not allowed");
                Contact contact = new Contact(invite.to, invite.from, invite.from, invite.server, null, null);
                contacts_service.Add(contact);
                return NoContent();
            }
            catch (Exception ex)
            {
                return NotFound(ex.Message);
            }
            
        }

        [Route("transfer")]
        [HttpPost]
        public async Task<IActionResult> Transfer([FromBody] Transfer transfer)
        {
            try
            {
                Domain.Message message = new Domain.Message(null, null, transfer.content, DateTime.Now, transfer.from, transfer.to);
                var fromExists = users_service.isUserExists(transfer.from);
                var toExists = users_service.isUserExists(transfer.to);
                if (!users_service.isUserExists(transfer.from) && !users_service.isUserExists(transfer.to))
                {
                    return NotFound("User does not exists in this server");
                }
                if (!fromExists)
                {
                    messages_service.Create(transfer.to, message);
                    contacts_service.UpdateContact(transfer.to, transfer.from, new Contact(null, null, null, null, transfer.content, DateTime.Now));
                }
                else if (contacts_service.isContactExists(transfer.from, transfer.to))
                {
                    contacts_service.UpdateContact(transfer.to, transfer.from, new Contact(null, null, null, null, transfer.content, DateTime.Now));
                }
                else throw new Exception("Contact does not exists");
                string? conId = ChatHub.Connections.ContainsKey(transfer.to) ? ChatHub.Connections[transfer.to].token : null;
                if (conId != null && ChatHub.Connections[transfer.to].type == "SIGNALR") await hubContext.Clients.Client(conId).SendAsync("ReceiveMessage", transfer.content);
                else if(conId != null && ChatHub.Connections[transfer.to].type == "FIREBASE")
                {

                    var firebaseMessage = new FirebaseAdmin.Messaging.Message()
                    {
                        Token = conId,
                        Notification = new Notification()
                        {
                            Title = transfer.from,
                            Body = transfer.content
                        }
                    };
                    
                    
                    string response = FirebaseMessaging.DefaultInstance.SendAsync(firebaseMessage).Result;
                    
                        
                    
                }
                return NoContent();
            }
            catch (Exception ex)
            {
                return NotFound(ex.Message);
            }

        }

        [Authorize]
        [Route("firebase/onconnect")]
        [HttpPost]
        public async Task<IActionResult> FirebaseOnConnect([FromBody] string firebaseToken)
        {
            try
            {
                var userId = HttpContext.User.FindFirst("userid")?.Value;
                if (ChatHub.Connections.ContainsKey(userId))
                    ChatHub.Connections[userId] = new ConnectionToken() { token = firebaseToken,type = "FIREBASE" };
                else
                    ChatHub.Connections.TryAdd(userId, new ConnectionToken() { token = firebaseToken, type = "FIREBASE" });
                
                return Ok();
            }
            catch(Exception ex)
            {
                return NotFound();
            }
            
            
        }

        }

    
}