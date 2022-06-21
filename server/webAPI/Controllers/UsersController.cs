using Microsoft.AspNetCore.Mvc;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using Microsoft.IdentityModel.Tokens;
using System.Text;
using Domain;
using Services;

namespace webAPI.Controllers
{
    [Route("api")]
    [ApiController]
    
    public class UsersController : ControllerBase
    {

        UsersService _service;
        public IConfiguration _configuration;

        public UsersController(IConfiguration config)
        {
            _configuration = config;
            _service = new UsersService();
        }

        [Route("login")]
        [HttpPost]
        public IActionResult Login([FromBody] User user)
        {
            var username = user.username;
            var password = user.password;
            try
            {
                if (_service.Login(username,password))
                {
                    saveTokenCookie(username);
                    return Ok();
                }
                else return Unauthorized();
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
            
            

        }

        [Route("register")]
        [HttpPost]
        public IActionResult Register([FromBody] User user)
        {
            try
            {
                if (user == null || user.username == null || user.password == null || user.name == null) return BadRequest("Please enter valid fields");
                if (_service.isUserExists(user.username)) return BadRequest("Username already exists");
                _service.Create(user);
                saveTokenCookie(user.username);
                return Created("User created",user);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
            
        }

        private void saveTokenCookie(string username)
        {
            var claims = new[]
                {
                    new Claim(JwtRegisteredClaimNames.Sub, username),
                    new Claim(JwtRegisteredClaimNames.Jti, Guid.NewGuid().ToString()),
                    new Claim(JwtRegisteredClaimNames.Iat, new DateTimeOffset(DateTime.Now).ToUnixTimeSeconds().ToString()),
                    new Claim("userid", username)
                };
            var secretKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(_configuration["JWTParams:SecretKey"]));
            var mac = new SigningCredentials(secretKey, SecurityAlgorithms.HmacSha256);
            var token = new JwtSecurityToken(_configuration["JWTParams:Issuer"], _configuration["JWTParams:Audience"], claims, expires: DateTime.UtcNow.AddMinutes(60), signingCredentials: mac);
            Response.Cookies.Append("token", new JwtSecurityTokenHandler().WriteToken(token), new CookieOptions //Save the JWT in the browser cookies, Key is "jwt"
            {
                Expires = DateTime.UtcNow.AddMinutes(60),
                HttpOnly = true,
                SameSite = SameSiteMode.None,
                Secure = true
            });
        }

    }
        

    
}