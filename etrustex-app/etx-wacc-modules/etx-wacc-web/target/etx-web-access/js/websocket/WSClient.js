/**
 * eTrustEx Websocket library to communicate between the webpage and the webstart component
 *
 * @author Cristiano Canofari
 */

function WSClient(endpoint,session_id) {
  var socket = null;
  var stompClient = null;
  var wsconnected=false;
  var endpoint=endpoint+"/wschannel";
  var session_id=session_id;

  disconnect();
  window.onbeforeunload = function() {
    disconnect();
  };

  this.startPolling= function(){
    websocketListener= setInterval(function(){ websocketTimer() }, 1000);
  };

  function connect() {
    socket = new SockJS(endpoint);
    stompClient = Stomp.over(socket);
    stompClient.connect({connectionId:session_id,client_type:"browser"} ,function (frame) {
      console.log('connection established with the webstart server: ' + frame);
      wsconnected = true;


      stompClient.subscribe('/receive/notifications/'+session_id, function (messageOutput) {
        var message = JSON.parse(messageOutput.body)
        if (message.from === "SERVER") {
          switch (message.messageType) {
            case "DISCONNECT":
              unblockPage();
              disconnect();
              break;
            case "WEBSTART_CONNECTED":
              blockPage();
              break;
          }
        }
      });
      stompClient.subscribe('/receive/uploadedFiles/'+session_id, function (messageOutput) {


        $('#encodedIdSelectedPayloadFile').val("");
        $('#fileIdsListJSON').val("");

        // signature data
        //$('#encodedSignature').val("");
        $('#xmlSignedBundle').val("");
        $('#xmlExtractedSignature').val("");
        $('#xmlDataToSign').val("");

        $('#fileList').empty();
        var bundle = JSON.parse(messageOutput.body).uploadBundle;

        currentFileList = bundle.fileList;
        updateList(JSON.parse(currentFileList));
        currentAttachments = b64EncodeUnicode(currentFileList);
        $('#encodedIdSelectedPayloadFile').val(bundle.encodedIdSelectedPayloadFile);
        $('#fileIdsListJSON').val(bundle.fileIdsListJSON);

        //$('#encodedSignature').val(bundle.encodedSignature);
        // signature data

        $('#xmlSignedBundle').val(bundle.xmlSignedBundle);
        $('#xmlExtractedSignature').val(bundle.xmlExtractedSignature);
        $('#xmlDataToSign').val(bundle.xmlDataToSign);

        sendMessage("UPLOADED")
      });


    }, function (error) {
      unblockPage();
      disconnect();
      console.log('failed to connect to the webstart server :' + error);
    });
  }

  function disconnect() {
    if (stompClient != null) {
      stompClient.disconnect();
      wsconnected = false;
    }
    console.log("Disconnected");
  }

  function sendMessage(messageType) {
    var from = "BROWSER";
    var text = messageType;
    stompClient.send('/send/notifications/'+session_id, {}, JSON.stringify({'from': from, 'messageType': text}));
  }

  function websocketTimer() {

    if(wsconnected){
      clearInterval(websocketListener);
    }
    else{
      console.log("connecting to the webstart server... ");
      disconnect();
      connect();
    }

  }
}