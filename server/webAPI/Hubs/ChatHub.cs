using Microsoft.AspNetCore.SignalR;
using System.Collections;
using System.Collections.Concurrent;
using Domain;

namespace webAPI.Hubs
{
    public class ChatHub : Hub
    {
        public static ConcurrentDictionary<string, ConnectionToken> Connections = new ConcurrentDictionary<string, ConnectionToken>();

        public void registerConId(string userID)
        {
            if (Connections.ContainsKey(userID))
                Connections[userID] = new ConnectionToken() { token = Context.ConnectionId, type = "SIGNALR"};
            else
                Connections.TryAdd(userID,new ConnectionToken() { token = Context.ConnectionId, type = "SIGNALR" });
        }
    }
}