$(() => {

    let stompClient = null;


    function onConnected() {
        stompClient.subscribe('/game-channel', onMessage)
    }

    function onConnect() {
        const socket = new SockJS('/game-room');
        stompClient = Stomp.over(socket);
        stompClient.connect({username:"Adam"}, onConnected)
    }

    function onMessage(message) {
        const gameUpdateMessage = JSON.parse(message.body);
        console.log(gameUpdateMessage)
    }

    function onMove() {
       const fieldNumber =  $('#fieldNumber').val();
        stompClient.send('/ws/game-room', {}, JSON.stringify({fieldNumber}));
    }

    function onDisconnect() {
        stompClient.disconnect();
    }

    onConnect();
    $('#sendFieldNumber').click(onMove);
});
