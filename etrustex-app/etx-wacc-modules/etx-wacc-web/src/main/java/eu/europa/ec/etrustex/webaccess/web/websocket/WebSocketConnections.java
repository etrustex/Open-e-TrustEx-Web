package eu.europa.ec.etrustex.webaccess.web.websocket;

import eu.europa.ec.etrustex.webaccess.web.model.WSSessionClient;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Websocket connected peers.
 * This class keep tracks of the connected WebSocket peers
 */
@Service
public class WebSocketConnections {

    private static  Map<String, Set<WSSessionClient>>connections = Collections.synchronizedMap(new HashMap<String, Set<WSSessionClient>>());
    private static Logger logger = Logger.getLogger(WebSocketConnections.class);
    public synchronized void addSessionId(String connectionId, WSSessionClient wsSessionClient ) {

        Set<WSSessionClient> wsSessionClients;
        if(connections.containsKey(connectionId)) {
            wsSessionClients = connections.get(connectionId);
        }
        else{
            wsSessionClients=new HashSet<>();
        }
        wsSessionClients.add(wsSessionClient);
        logger.info("websocket client added");
        connections.put(connectionId,wsSessionClients);
    }


    public synchronized void removeSessionId(String connectionId,String sessionId ) {

        WSSessionClient wsSessionClient= new WSSessionClient();
        wsSessionClient.setWsSession(sessionId);
        Set<WSSessionClient> wsSessionClients;
        if(connections.containsKey(connectionId)) {
            logger.info("websocket client removed");
            wsSessionClients = connections.get(connectionId);
            wsSessionClients.remove(wsSessionClient);
            if(wsSessionClients.size()==0){
                connections.remove(connectionId);
            }
            else{
                connections.put(connectionId,wsSessionClients);
            }
        }
    }
    public synchronized String getConnectionId(String sessionId ) {
        WSSessionClient wsSessionClient= new WSSessionClient();
        wsSessionClient.setWsSession(sessionId);
        String conectionId =null;
        for (String conId:connections.keySet()){
            Set<WSSessionClient> wsSessionClients = connections.get(conId);
            if (wsSessionClients.contains(wsSessionClient)) {
                conectionId = conId;
                break;
            }

        }
        return conectionId;
    }



}
