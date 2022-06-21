using Microsoft.AspNetCore.Mvc;
using Domain;
using Services;
using Microsoft.AspNetCore.Authorization;


namespace webAPI.Controllers
{
    [Authorize]
    [ApiController]
    [Route("api/contacts")]
    public class ContactsController : ControllerBase
    {
        ContactsService _service;



        public ContactsController()
        {
            _service = new ContactsService();
        }


        [HttpGet]
        public IActionResult Index()
        {
            try
            {
                var user = HttpContext.User.FindFirst("userid")?.Value;
                return Ok(_service.GetAll(user));
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }

        }

        [HttpPost]
        public IActionResult Create([FromBody] Contact contact)
        {
            var user = HttpContext.User.FindFirst("userid")?.Value;
            try
            {
                if (contact == null || contact.id == null || contact.server == null)
                {
                    throw new Exception("One of contact fields is invalid");
                }
                if (_service.GetContact(user,contact.id)!=null) return BadRequest("Contact is already exists");
                contact.owner_id = user;
                contact.lastdate = DateTime.Now;
                _service.Add(contact);
                return Created("", contact);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }

        }

        [HttpGet("{id}")]
        public IActionResult GetContact(string? id)
        {
            var user = HttpContext.User.FindFirst("userid")?.Value;
            Contact contact = _service.GetContact(user, id);
            if (contact == null) return NotFound();
            return Ok(contact);
        }

        [HttpPut("{contactId}")]
        public IActionResult UpdateContact(string? contactId, [FromBody] Contact contact)
        {
            var user = HttpContext.User.FindFirst("userid")?.Value;
            try
            {
                _service.UpdateContact(user, contactId, contact);
                return NoContent();
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }

        }

        [HttpDelete("{contactId}")]
        public IActionResult DeleteContact(string? contactId)
        {
            var user = HttpContext.User.FindFirst("userid")?.Value;
            try
            {
                _service.Delete(user, contactId);
                return NoContent();
            }
            catch
            {
                return NotFound();
            }
        }

    }


}