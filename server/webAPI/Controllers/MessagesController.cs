using Microsoft.AspNetCore.Mvc;
using Domain;
using Services;
using Microsoft.AspNetCore.Authorization;
using System.Runtime;
using webAPI.Hubs;

namespace webAPI.Controllers
{
    [Authorize]
    [ApiController]
    [Route("api/contacts/{id}/messages")]
    public class MessagesController : ControllerBase
    {
        MessagesService _messagesService;
        ContactsService _contactsService;
        ChatHub _hub;
        public MessagesController()
        {
            _messagesService = new MessagesService();
            _contactsService = new ContactsService();
            _hub = new ChatHub();
        }


        [HttpGet]
        public IActionResult Index(string id)
        {
            try
            {
                var user = HttpContext.User.FindFirst("userid")?.Value;
                return Ok(_messagesService.GetAll(id,user));
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpPost]
        public async Task<IActionResult> Create(string id, [FromBody] Message messageBody)
        {
            try
            {
                var user = HttpContext.User.FindFirst("userid")?.Value;
                var from = user;
                var to = id;
                var now = DateTime.Now;
                Message message = new Message(null, null, messageBody.content, now, from, to);
                _messagesService.Create(id, message);
                _contactsService.UpdateContact(user, to, new Contact(null, null, null, null, messageBody.content, now));
                return Created("",message);
            }
            catch
            {
                return NotFound();
            }
        }

        [HttpGet("{id2}")]
        public IActionResult GetMessage(string id, int id2)
        {
            try
            {
                var user = HttpContext.User.FindFirst("userid")?.Value;
                return Ok(_messagesService.GetMessage(id, id2,user));
            }

            catch (Exception ex)
            {
                return NotFound(ex.Message);
            }
        }

        [HttpPut("{id2}")]
        public IActionResult UpdateMessage(string id, int id2, [FromBody] Message message)
        {
            try
            {
                var user = HttpContext.User.FindFirst("userid")?.Value;
                _messagesService.UpdateMessage(id, id2, message,user);
                return Ok();
            }

            catch (Exception ex)
            {
                return NotFound(ex.Message);
            }
        }

        [HttpDelete("{id2}")]
        public IActionResult DeleteMessage(string id, int id2)
        {
            try
            {
                _messagesService.DeleteMessage(id, id2);
                return NoContent();
            }

            catch (Exception ex)
            {
                return NotFound(ex.Message);
            }
        }



    }

    
}